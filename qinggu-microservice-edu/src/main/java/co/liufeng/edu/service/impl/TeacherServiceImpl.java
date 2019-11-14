package co.liufeng.edu.service.impl;

import co.liufeng.domain.edu.Teacher;
import co.liufeng.edu.mapper.TeacherMapper;
import co.liufeng.edu.query.TeacherQuery;
import co.liufeng.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Refeily
 * @since 2019-10-30
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if (teacherQuery == null) {
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }
        String name = teacherQuery.getName();
        String level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if(!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.le("gmt_create", end);
        }
        baseMapper.selectPage(pageParam, queryWrapper);
    }
}
