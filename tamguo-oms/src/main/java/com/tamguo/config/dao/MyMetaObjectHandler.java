package com.tamguo.config.dao;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.tamguo.modules.sys.utils.ShiroUtils;

import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  注入公共字段自动填充,任选注入方式即可
 */
//@Component
public class MyMetaObjectHandler extends MetaObjectHandler {

    protected final static Logger logger = LoggerFactory.getLogger(MyMetaObjectHandler.class);
    
    @Override
    public void insertFill(MetaObject metaObject) {
        Object testType = getFieldValByName("createBy", metaObject);
        if (testType == null) {
            setFieldValByName("createBy", ShiroUtils.getUser() , metaObject);	//mybatis-plus版本2.0.9+
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        logger.info("更新的时候干点不可描述的事情");
    }
}
