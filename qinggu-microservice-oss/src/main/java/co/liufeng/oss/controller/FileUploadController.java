package co.liufeng.oss.controller;

import co.liufeng.common.vo.R;
import co.liufeng.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author rafeily
 * @date 2019/11/7 19:25
 */
@Api(description = "阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/oss/file")
public class FileUploadController {
    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file
    ) {
        String upload = fileService.upload(file);
        return R.ok().message("文件上传成功").data("url", upload);
    }
}
