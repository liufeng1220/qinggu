package co.liufeng.edu.service.impl;

import co.liufeng.edu.entity.Subject;
import co.liufeng.edu.mapper.SubjectMapper;
import co.liufeng.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Refeily
 * @since 2019-10-30
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public List<String> batchImport(MultipartFile file) throws Exception {
        return null;
    }
}
