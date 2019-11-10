package co.liufeng.edu.service.impl;

import co.liufeng.edu.entity.DemoData;
import co.liufeng.edu.entity.Subject;
import co.liufeng.edu.listener.UploadDataListener;
import co.liufeng.edu.mapper.SubjectMapper;
import co.liufeng.edu.service.SubjectService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
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
    @Autowired
    private SubjectMapper subjectMapper;

    /**
     * 存入excel数据到数据库
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public List<String> batchImport(MultipartFile file) throws Exception {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        EasyExcel.read(file.getInputStream(), DemoData.class, new UploadDataListener(subjectMapper)).sheet().doRead();
        return null;
    }
}
