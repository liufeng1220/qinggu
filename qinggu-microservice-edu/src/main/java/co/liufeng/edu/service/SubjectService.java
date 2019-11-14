package co.liufeng.edu.service;

import co.liufeng.domain.edu.Subject;
import co.liufeng.edu.vo.SubjectVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Refeily
 * @since 2019-10-30
 */
public interface SubjectService extends IService<Subject> {
    //保存一级分类到数据库
    List<String> batchImport(MultipartFile file) throws Exception;
    //查询主题信息
    List<SubjectVo> listSubjectVo();
}
