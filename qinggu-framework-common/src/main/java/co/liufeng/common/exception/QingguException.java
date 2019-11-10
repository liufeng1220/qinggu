package co.liufeng.common.exception;

import co.liufeng.common.constants.ResultCodeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author rafeily
 * @date 2019/11/1 15:06
 */
@Data
@Slf4j
public class QingguException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;
    /**
     *
     * 接收状态码和消息
     */
    public QingguException(Integer code,String message){
        super(message);
        this.code=code;
    }
    /**
     * 接收枚举异常
     */
    public QingguException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
    @Override
    public String toString() {
        return "QingguException{" +
        "message=" + this.getMessage() +
        ", code=" + code+'}';
    }
}
