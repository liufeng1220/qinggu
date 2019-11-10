package co.liufeng.edu.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 用于excel表格中数据的传世
 * @author rafeily
 * @date 2019/11/10 19:28
 */
@Data
public class ExcelData {
    @ExcelProperty(index = 0)
    private String line1;
    @ExcelProperty(index = 1)
    private String line2;
}
