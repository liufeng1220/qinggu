package co.liufeng.common.handler;

import co.liufeng.common.exception.QingguException;
import co.liufeng.common.util.ExceptionUtil;
import co.liufeng.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author rafeily
 * @date 2019/11/1 14:43
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return R.error();
    }
    @ExceptionHandler(QingguException.class)
    @ResponseBody
    public R error(QingguException e){
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }
}
