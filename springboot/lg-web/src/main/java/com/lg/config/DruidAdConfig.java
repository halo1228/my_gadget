package com.lg.config;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.util.Utils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author liuga
 * @since 2023-04-18 10:49
 * Description:Druid广告配置
 */

@Configuration
@ConditionalOnWebApplication
@AutoConfigureAfter(DruidDataSourceAutoConfigure.class)
@ConditionalOnProperty(
        name = "spring.datasource.druid.stat-view-servlet.enabled",
        havingValue = "true",
        matchIfMissing = true)
public class DruidAdConfig {

    /**
     * 去除监控页面底部广告
     */
    @Bean
    public FilterRegistrationBean<Filter> removeDruidAdFilterRegistrationBean(
            DruidStatProperties properties) {
        // 获取web监控页面的参数
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
        // 提取common.js的配置路径
        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");

        final String filePath = "support/http/resources/js/common.js";

        // 创建filter进行过滤
        Filter filter =
                new Filter() {
                    @Override
                    public void init(FilterConfig filterConfig) throws ServletException {
                    }

                    @Override
                    public void doFilter(
                            ServletRequest request, ServletResponse response, FilterChain chain)
                            throws IOException, ServletException {
                        chain.doFilter(request, response);
                        // 重置缓冲区，响应头不会被重置
                        response.resetBuffer();
                        // 获取common.js
                        String text = Utils.readFromResource(filePath);
                        // 正则替换banner, 除去底部的广告信息
                        text = text.replaceAll("<a.*?banner\"></a><br/>", "");
                        text = text.replaceAll("powered.*?shrek.wang</a>", "");
                        response.getWriter().write(text);
                    }
                    @Override
                    public void destroy() {
                    }
                };
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(commonJsPattern);
        return registrationBean;
    }

    /**
     * 配置文件中数据库密码加密
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        //密码明文
        String password = "123456";
        System.out.println("明文密码: " + password);
//        // 获取密钥
        String[] keyPair = ConfigTools.genKeyPair(512);
        //私钥
        String privateKey = keyPair[0];
//        String privateKey = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAoJVSCPpJiAwR9JPpBqK4YQLeLZfS2UUvFwp7XwYVhNhqzguFH6EL7oUjIahrSYlwDRTAZozHF6EcNyj5YcM90QIDAQABAkBcCNiojpJAJ/LOg0tF41LbPuKJrP9KSS2Q/g/xSTJiHSAUxH/iSUMtd6xxTZ9sm3Wgul12fIqmFWOv+fPx7gdFAiEA7BzX3qir3hVfASTExJ1s4hsw3LjY71s6evESR/i/IfcCIQCuG+HPpxaet1FDKo9dWUaZIoF6WEFr/bGjEhMYTIwsdwIhALL2YcDIxAw+0pXBUstcL01qIq0KBpPV6AuLcbnPlr+dAiEAl4m7C7JhVLk3aF9VsqjucoB+8053epevkcA8kGynoFcCIQDhiTKseiMkkjIvxtFCYWbS9ZQNBKjuGBspS4sRJ4IOhg==";
        //公钥
        String publicKey = keyPair[1];
//        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKCVUgj6SYgMEfST6QaiuGEC3i2X0tlFLxcKe18GFYTYas4LhR+hC+6FIyGoa0mJcA0UwGaMxxehHDco+WHDPdECAwEAAQ==";
        System.out.println("privateKey（私钥）:" + privateKey);
        System.out.println("publicKey（公钥）:" + publicKey);

        //用私钥加密后的密文
        String encryptPassword = ConfigTools.encrypt(privateKey, password);
        System.out.println("用私钥加密后的密文:" + encryptPassword);
        String decryptPassword = ConfigTools.decrypt(publicKey, encryptPassword);
        System.out.println("解密后:" + decryptPassword);
    }


}
