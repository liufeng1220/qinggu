package co.liufeng.edu.service.impl;

import co.liufeng.common.util.ExcelImportUtil;
import co.liufeng.domain.edu.Subject;
import co.liufeng.edu.mapper.SubjectMapper;
import co.liufeng.edu.service.SubjectService;
import co.liufeng.edu.vo.SubjectVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
        //错误消息列表
        List<String> errorMsg = new ArrayList<>();
        //创建工具类对象
        ExcelImportUtil excelHSSFUtil = new ExcelImportUtil(file.getInputStream());
        //获取工作表
        Sheet sheet = excelHSSFUtil.getSheet();
        int rowCount = sheet.getPhysicalNumberOfRows();
        if (rowCount <= 1) {
            errorMsg.add("请填写数据");
            return errorMsg;
        }
        for (int rowNum = 1; rowNum < rowCount; rowNum++) {
            Row rowData = sheet.getRow(rowNum);
            if (rowData != null) {// 行不为空
                //获取一级分类
                String levelOneValue = "";
                Cell levelOneCell = rowData.getCell(0);
                if (levelOneCell != null) {
                    levelOneValue = excelHSSFUtil.getCellValue(levelOneCell).trim();
                    if (StringUtils.isEmpty(levelOneValue)) {
                        errorMsg.add("第" + rowNum + "行一级分类为空");
                        continue;
                    }
                }
                //判断一级分类是否重复
                Subject subject = this.getByTitle(levelOneValue);
                String parentId = null;
                if (subject == null) {
                    //将一级分类存入数据库
                    Subject subjectLevelOne = new Subject();
                    subjectLevelOne.setTitle(levelOneValue);
                    subjectLevelOne.setSort(rowNum);
                    baseMapper.insert(subjectLevelOne);//添加
                    parentId = subjectLevelOne.getId();
                } else {
                    parentId = subject.getId();
                }
                //获取二级分类
                String levelTwoValue = "";
                Cell levelTwoCell = rowData.getCell(1);
                if (levelTwoCell != null) {
                    levelTwoValue = excelHSSFUtil.getCellValue(levelTwoCell).trim();
                    if (StringUtils.isEmpty(levelTwoValue)) {
                        errorMsg.add("第" + rowNum + "行二级分类为空");
                        continue;
                    }
                }

                //判断二级分类是否重复
                Subject subjectSub = this.getSubByTitle(levelTwoValue, parentId);
                Subject subjectLevelTwo = null;
                if (subjectSub == null) {
                    //将二级分类存入数据库
                    subjectLevelTwo = new Subject();
                    subjectLevelTwo.setTitle(levelTwoValue);
                    subjectLevelTwo.setParentId(parentId);
                    subjectLevelTwo.setSort(rowNum);
                    baseMapper.insert(subjectLevelTwo);//添加
                }
            }
        }
        return errorMsg;
    }

    /**
     * 树形查询
     *
     * @return
     */
    @Override
    public List<SubjectVo> listSubjectVo() {
        List<SubjectVo> subjectVos = new ArrayList<SubjectVo>();
        //1.查询所有根节点数据
        List<Subject> subjects = baseMapper.selectList(new QueryWrapper<Subject>().eq("parent_id", "0"));
        //2.遍历跟节点对象
        for (int i = 0; i < subjects.size(); i++) {
            //3.根据根节点id查询得到对应跟节点的子节数据。
            List<Subject> subjects1 = baseMapper.selectList(new QueryWrapper<Subject>().eq("parent_id", subjects.get(i).getId()));
            List<SubjectVo> list = new ArrayList<>();
            for (Subject subject : subjects1) {
                list.add(new SubjectVo(subject.getParentId(),subject.getTitle(),null));
            }
            //4.将根节点以及根节点对应的子节点封装成vo对象返回。
            subjectVos.add(new SubjectVo(subjects.get(i).getId(), subjects.get(i).getTitle(), list));
        }
        return subjectVos;

    }

    /**
     * 根据分类名称查询这个一级分类中否存在
     *
     * @param title
     * @return
     */
    private Subject getByTitle(String title) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", "0");//一级分类
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 根据分类名称和父id查询这个二级分类中否存在
     *
     * @param title
     * @return
     */
    private Subject getSubByTitle(String title, String parentId) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", parentId);
        return baseMapper.selectOne(queryWrapper);
    }
}
