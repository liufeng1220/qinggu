package co.liufeng.edu.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

@ExcelIgnoreUnannotated
public class DemoData {
    @ExcelProperty(index = 0)
    private String string;

   private String string1;
    @ExcelIgnore
   private String string3;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }
}