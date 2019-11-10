package co.liufeng.oss.service.impl;

import co.liufeng.common.constants.ResultCodeEnum;
import co.liufeng.common.exception.QingguException;
import co.liufeng.oss.service.FileService;
import co.liufeng.oss.util.ConstantPropertiesUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author rafeily
 * @date 2019/11/7 19:07
 */
@Service
public class FileServiceImpl implements FileService {
    /**
     * 文件上传
     *
     * @param file
     * @return 文件保存路径
     */
    @Override
    public String upload(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String fileHost = ConstantPropertiesUtil.FILE_HOST;
        String myHost = ConstantPropertiesUtil.MY_HOSTNAME;
        String uploadUrl = null;
        try {
            //判断oss实列是否存在，不存在则创建实列，存在则获取
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            if (!ossClient.doesBucketExist(bucketName)) {
                //创建bucket实列
                ossClient.createBucket(bucketName);
                //设置其访问权限，公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            //创建上传路径,avaar/年/月/日/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd");
            //文件名生成
            String originalFilename = file.getOriginalFilename();
            String filename = UUID.randomUUID().toString();
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newfile = filename + fileType;
            //上传路径====拼接
            String fileUrl = fileHost + "/" + filePath + "/" + newfile;

            // 添加 ContentType==========（该操作是为了可以访问，而不是直接下载）
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType("image/jpg");
            ossClient.putObject(bucketName, fileUrl, inputStream, objectMetadata);

            //获取文件上传返回的url，自己的域名就直接可以用来
            // uploadUrl = "http://" + bucketName + "." + endpoint + "/" + fileUrl;
            uploadUrl = "http://" + myHost + "/" + fileUrl;
            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (Exception e) {
            throw new QingguException(ResultCodeEnum.UPLOAD_FILE_MAX);
        }
        return uploadUrl;
    }
}
