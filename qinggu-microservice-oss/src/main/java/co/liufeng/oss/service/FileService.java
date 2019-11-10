package co.liufeng.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author rafeily
 * @date 2019/11/7 19:05
 */
public interface FileService {
    /**
     * 文件上传到阿里云
     */
    String upload(MultipartFile file);
}
