package co.liufeng.edu.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 课程科目
 * </p>
 *
 * @author Refeily
 * @since 2019-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_subject")
@ApiModel(value = "Subject对象", description = "课程科目")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程类别ID")

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    @ExcelProperty(index = 0)
    private String id;

    @ApiModelProperty(value = "类别名称")

    private String title;

    @ApiModelProperty(value = "父ID")
    @ExcelIgnore
    private String parentId;

    @ApiModelProperty(value = "排序字段")
    @ExcelIgnore
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @ExcelIgnore
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ExcelIgnore
    private Date gmtModified;

}
