package com.lg.filter;

import lombok.extern.java.Log;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author liuga
 * @date 2023/02/09 16:08
 * Description:采用过滤器（filter）的方式(推荐)
 * 增加一个CORSFilter 类，并实现Filter接口即可
 * http请求，先走filter，到达servlet后才进行拦截器的处理，如果我们把cors放在filter里，就可以优先于权限拦截器执行。
 */
@Component
@Order(1)
@Log
public class CROSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
       log.info("CROSFilter 启动");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        HttpServletRequest reqs = (HttpServletRequest) req;
        /*
         * 跨域设置允所有请求跨域
         * 如果允许指定的客户端跨域设置: http://127.0.0.1:8020
         */
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        if ("OPTIONS".equals(((HttpServletRequest) req).getMethod())) {
            response.getWriter().println("ok");
            return;
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
