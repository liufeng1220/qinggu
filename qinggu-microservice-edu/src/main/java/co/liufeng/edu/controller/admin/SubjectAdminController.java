package co.liufeng.edu.controller.admin;

import co.liufeng.common.constants.ResultCodeEnum;
import co.liufeng.common.exception.QingguException;
import co.liufeng.common.vo.R;
import co.liufeng.domain.edu.Subject;
import co.liufeng.edu.service.SubjectService;
import co.liufeng.edu.vo.SubjectVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Refeily
 * @since 2019-10-30
 */
@Api(description="课程分类管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/edu/subject")
public class SubjectAdminController {
    @Autowired
    private SubjectService subjectService;

    /**
     * 分类查询所有主题信息
     * @return
     */
    @ApiOperation(value = "树形菜单查询")
    @GetMapping("list")
    public R listSubject(){

        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("id","0");
        List<SubjectVo> subjectVos = subjectService.listSubjectVo();
        return R.ok().message("查询数据成功").data("subList",subjectVos);
    }
    /**
     * 批量导入数据到数据库
     * @param file
     * @return
     */
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("import")
    public R batchImport(
            @ApiParam(name = "file", value = "Excel文件", required = true)
            @RequestParam("file") MultipartFile file) {
        try{
            List<String> errorMsg = subjectService.batchImport(file);
            if(errorMsg.size() == 0){
                return R.ok().message("批量导入成功");
            }else{
                return R.error().message("部分数据导入失败").data("errorMsgList", errorMsg);
            }
        }catch (Exception e){
            System.out.println("开始打印错误信息");
            e.printStackTrace();
            System.out.println("============");
            //无论哪种异常，只要是在excel导入时发生的，一律用转成GuliException抛出
            throw new QingguException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }
}