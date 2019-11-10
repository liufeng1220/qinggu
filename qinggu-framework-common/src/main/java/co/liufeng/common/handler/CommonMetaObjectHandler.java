package co.liufeng.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充字段，如果有个字段需要插入和修改时候同时赋值，需要添加两个
 * @author rafeily
 * @date 2019/10/31 15:41
 */
@Component
@Slf4j
public class CommonMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonMetaObjectHandler.class);

    /**
     * 插入字段时候需要赋的默认值，如为gmtCreate的字段赋值为date()
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("gmtCreate", new Date(), metaObject);//版本号3.0.6以及之前的版本
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }

    /**
     * 修改时候赋值
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
}
