package co.liufeng.edu.listener;

/**
 * @author rafeily
 * @date 2019/11/10 15:01
 */

import co.liufeng.edu.dto.ExcelData;
import co.liufeng.edu.mapper.SubjectMapper;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class UploadDataListener extends AnalysisEventListener<ExcelData> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 13;
    public SubjectMapper subjectMapper;
    List<ExcelData> list = new ArrayList<ExcelData>();

    public UploadDataListener(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param demoDAO
     */

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ExcelData data, AnalysisContext context) {
        //   LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        //  Integer rowNumber = context.readSheetHolder().getApproximateTotalRowNumber();
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            for (ExcelData excelData : list) {

            }
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        //  LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        // LOGGER.info("{}条数据，开始存储数据库！", list.size());
        //demoDAO.save(list);
        // LOGGER.info("存储数据库成功！");
    }
}