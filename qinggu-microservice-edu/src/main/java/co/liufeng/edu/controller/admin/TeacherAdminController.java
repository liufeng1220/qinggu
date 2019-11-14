package co.liufeng.edu.controller.admin;

import co.liufeng.common.constants.ResultCodeEnum;
import co.liufeng.common.exception.QingguException;
import co.liufeng.common.vo.R;
import co.liufeng.domain.edu.Teacher;
import co.liufeng.edu.query.TeacherQuery;
import co.liufeng.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author rafeily
 * @date 2019/10/30 16:18
 */
@RestController
@RequestMapping("/admin/edu/teacher")
@CrossOrigin(value = "*") //跨域
@Api(description = "讲师管理")
public class TeacherAdminController {
    @Autowired
    private TeacherService teacherService;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    /**
     * 新增讲师，修改讲师信息
     */
    @PostMapping
    @ApiOperation(value = "新增讲师")
    public R saveOrUpdate(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
                    Teacher teacher) {
        boolean flag;
        if (StringUtils.isEmpty(teacher.getId())) {
            flag = teacherService.save(teacher);
            return R.ok().success(flag).message("保存成功");
        } else {
            flag = teacherService.updateById(teacher);
            return R.ok().success(flag).message("修改成功");
        }
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation(value = "删除讲师信息")
    public R deleteById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        boolean b = teacherService.removeById(id);
        return R.ok().success(b);
    }

    /**
     * 查询
     *
     * @return
     */
    @GetMapping
    @ApiOperation(value = "所有讲师列表")
    public R listTeachers() {
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items", list).message("返回讲师信息");
    }

    /**
     * 高级查询
     *
     * @param page
     * @param limit
     * @param teacherQuery
     * @return
     */
    @GetMapping("{page}/{limit}")
    @ApiOperation(value = "分页查询")
    public R listPageTeacher(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                    TeacherQuery teacherQuery
    ) {
        if (page < 0) {
            throw new QingguException(ResultCodeEnum.PARAM_ERROR);
        }
        Page<Teacher> teacherPage = new Page<>(page, limit);
        teacherService.pageQuery(teacherPage, teacherQuery);
        List<Teacher> records = teacherPage.getRecords();
        long total = teacherPage.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

 /*   @GetMapping("{page}/{limit}")
    @ApiOperation(value = "分页查询")
    public R listPageTeacher(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {

        if(page<0){
            throw new  QingguException(ResultCodeEnum.PARAM_ERROR);
        }
        Page<Teacher> teacherPage = new Page<>(page, limit);
        IPage<Teacher> page1 = teacherService.page(teacherPage, null);
        List<Teacher> records = page1.getRecords();
        long total = page1.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }*/
}
