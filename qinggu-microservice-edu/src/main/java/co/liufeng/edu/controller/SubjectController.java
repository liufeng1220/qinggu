package co.liufeng.edu.controller;

import co.liufeng.common.vo.R;
import co.liufeng.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Refeily
 * @since 2019-10-30
 */
@Api(description = "课程分类管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/edu/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    /**
     * excel导入文件
     *
     * @param file
     * @return
     */
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("import")
    public R batchImport(
            @ApiParam(name = "file", value = "Excel文件", required = true)
            @RequestParam("file") MultipartFile file) {
        try {
            subjectService.batchImport(file);
        } catch (Exception e) {

        }
        return null;
    }
}

