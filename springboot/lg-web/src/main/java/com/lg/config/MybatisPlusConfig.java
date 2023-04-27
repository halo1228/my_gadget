package com.lg.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author liuga
 * @date 2023/02/12 9:46
 * Description:
 */
@Configuration
public class MybatisPlusConfig implements MetaObjectHandler {
    /**
     *
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //新增分页拦截器，并设置数据库类型为mysql
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        //乐观锁
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        //针对 update 和 delete 语句 作用: 阻止恶意的全表更新删除
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }


    @Override
    public void insertFill(MetaObject metaObject) {
        //this.strictInsertFill(metaObject, "deleted", () -> 0, Integer.class);
        //this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        // 或者 起始版本 3.3.3(推荐)
        // this.strictInsertFill(metaObject, "CREATE_TIME", LocalDateTime::now, LocalDateTime.class);
       /* Object createTime = this.getFieldValByName("createTime", metaObject);
        if (ObjectUtils.isEmpty(createTime)) {
            // 填充
            this.setFieldValByName("createTime",new Date(),metaObject);
        }*/

        setFieldValByName("createDate", new Date(), metaObject);
        setFieldValByName("createTime", new Date(), metaObject);
        setFieldValByName("updateData", new Date(), metaObject);
        setFieldValByName("updateTime", new Date(), metaObject);
        setFieldValByName("deleted", 0, metaObject);
        setFieldValByName("status", 0, metaObject);
        setFieldValByName("bindTime",  new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        //this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
        // 或者 起始版本 3.3.3(推荐)
        //this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
    }

}
