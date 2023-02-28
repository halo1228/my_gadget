
package com.lg.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * HttpServlet工具类，获取当前request和response
 *
 * @author liuga
 * @since 2023-02-01
 */
public class ServletUtil {

    /**
     * 从请求中中获取参数
     *
     * @author xuyuxiang
     * @date 2021/10/14 10:44
     **/
    public static String getParamFromRequest(String paramName) {
        HttpServletRequest request = getRequest();

        // 1. 尝试从请求体里面读取
        String paramValue = request.getParameter(paramName);

        // JSONObject jsonObject = readAsChars(request);


        // 2. 尝试从header里读取
        if (ObjectUtils.isEmpty(paramValue)) {
            paramValue = request.getHeader(paramName);
        }
        // 3. 尝试从cookie里读取
        if (ObjectUtils.isEmpty(paramValue)) {
            Cookie[] cookies = request.getCookies();
            if (ObjectUtils.isNotEmpty(cookies)) {
                for (Cookie cookie : cookies) {
                    String cookieName = cookie.getName();
                    if (cookieName.equals(paramName)) {
                        return cookie.getValue();
                    }
                }
            }
        }
        // 4. 返回
        return paramValue;
    }

    public static JSONObject readAsChars(HttpServletRequest request) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return JSONObject.parseObject(sb.toString());
    }


    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes;
        try {
            servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("非Web上下文无法获取Request");
        }
        if (servletRequestAttributes == null) {
            throw new RuntimeException("非Web上下文无法获取Request");
        } else {
            return servletRequestAttributes.getRequest();
        }
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes;
        try {
            servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("非Web上下文无法获取Response");
        }
        if (servletRequestAttributes == null) {
            throw new RuntimeException("非Web上下文无法获取Response");
        } else {
            return servletRequestAttributes.getResponse();
        }
    }

    public static boolean isWeb() {
        return RequestContextHolder.getRequestAttributes() != null;
    }
}
