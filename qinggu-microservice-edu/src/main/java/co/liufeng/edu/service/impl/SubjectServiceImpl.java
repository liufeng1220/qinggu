package co.liufeng.edu.service.impl;

import co.liufeng.edu.entity.Subject;
import co.liufeng.edu.mapper.SubjectMapper;
import co.liufeng.edu.service.SubjectService;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
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
    /**
     * 存入excel数据到数据库
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public List<String> batchImport(MultipartFile file) throws Exception {
        //创建一个excel对象
        ExcelReaderBuilder excelReaderBuilder = new ExcelReaderBuilder();
        ExcelReaderSheetBuilder excelReaderSheetBuilder = new ExcelReaderSheetBuilder();
        excelReaderBuilder.autoCloseStream()
        return null;
    }
}
