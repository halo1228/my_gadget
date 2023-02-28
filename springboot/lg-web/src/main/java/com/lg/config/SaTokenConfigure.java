package com.lg.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.strategy.SaStrategy;
import cn.dev33.satoken.util.SaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liuga
 * @date 2023/01/14 11:48
 * Description: 注册 Sa-Token 拦截器，打开注解式鉴权功能
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor(e -> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                //不需要拦截的地址 swagger
                .excludePathPatterns("/login/doLogin")
                .excludePathPatterns("/doc.html/**")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/v2/**")
                .excludePathPatterns("/v3/**")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/swagger-ui.html/**")
                //druid
                .excludePathPatterns("/druid/**");
    }
//        @Override
//        public void addInterceptors(InterceptorRegistry registry) {
//            // 注册 Sa-Token 拦截器，定义详细认证规则
//            registry.addInterceptor(new SaInterceptor(handler -> {
//                // 指定一条 match 规则
//                SaRouter
//                        .match("/**")    // 拦截的 path 列表，可以写多个 */
//                        .notMatch("/user/doLogin")        // 排除掉的 path 列表，可以写多个
//                        .check(r -> StpUtil.checkLogin());        // 要执行的校验动作，可以写完整的 lambda 表达式
//                // 角色校验 -- 拦截以 admin 开头的路由，必须具备 admin 角色或者 super-admin 角色才可以通过认证
//                SaRouter.match("/admin/**", r -> StpUtil.checkRoleOr("admin", "super-admin"));
//                // 根据路由划分模块，不同模块不同鉴权
//                SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
//                SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
//                SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
//                SaRouter.match("/orders/**", r -> StpUtil.checkPermission("orders"));
//                SaRouter.match("/notice/**", r -> StpUtil.checkPermission("notice"));
//                SaRouter.match("/comment/**", r -> StpUtil.checkPermission("comment"));
//            })).addPathPatterns("/**");
//        }

    @Autowired
    public void rewriteSaStrategy() {
        // 重写Sa-Token的注解处理器，增加注解合并功能
        SaStrategy.me.getAnnotation = AnnotatedElementUtils::getMergedAnnotation;
    }
}
