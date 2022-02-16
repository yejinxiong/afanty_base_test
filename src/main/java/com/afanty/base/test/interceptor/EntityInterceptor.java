package com.afanty.base.test.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 实体基本信息拦截器
 *
 * 实体类对应的字段需要加上注解：@TableField(value = "create_name", fill = FieldFill.INSERT)
 *
 * 注意：MetaObjectHandler属于Mybatis-plus
 *
 * @Author yejx
 * @Date 2022/2/16
 */
@Component
public class EntityInterceptor implements MetaObjectHandler {

    /**
     * 新增数据
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        setCreateInfo(metaObject);
        setUpdateInfo(metaObject);
    }

    /**
     * 修改数据
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setUpdateInfo(metaObject);
    }

    /**
     * 设置创建信息
     * @param metaObject
     */
    public void setCreateInfo(MetaObject metaObject){
        this.strictInsertFill(metaObject,"tenantId", String.class, "AFANTY");
        this.strictInsertFill(metaObject,"proId", String.class, "xlm");
        this.strictInsertFill(metaObject,"createUser", String.class, "yejx");
        this.strictInsertFill(metaObject,"createName", String.class, "叶金雄");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    }

    /**
     * 设置更新信息
     * @param metaObject
     */
    public void setUpdateInfo(MetaObject metaObject){
        this.strictUpdateFill(metaObject,"updateUser", String.class, "yejx");
        this.strictUpdateFill(metaObject,"updateName", String.class, "叶金雄");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
