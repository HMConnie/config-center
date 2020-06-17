package com.sgcai.config.center.common.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public abstract class WebUtils {

    /**
     * 添加COOKIE
     * @param response
     * @param domain
     * @param name
     * @param value
     * @param maxAge
     */
    public static void addCookie(HttpServletResponse response, String domain, String name, String value, int maxAge) {
        try {
            value=URLEncoder.encode(value, "UTF-8");
            Cookie cookie=new Cookie(name, value);
            cookie.setPath("/");
            if (maxAge >= 0) {
                cookie.setMaxAge(maxAge);
            }
            if (StringUtils.isNotBlank(domain)) {
                cookie.setDomain(domain);
            }
            response.addCookie(cookie);
        } catch(Exception e) {
        }
    }

    /**
     * 获取cookie值
     * @param request
     * @param name
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] array=request.getCookies();
        if (array == null || array.length == 0) {
            return null;
        }
        String value=null;
        for (Cookie cookie: array) {
            if (cookie.getName().equals(name)) {
                value=cookie.getValue();
                break;
            }
        }
        if (StringUtils.isBlank(value)) {
            return null;
        }
        try {
            value=URLDecoder.decode(value, "UTF-8");
        } catch(Exception e) {
        }
        return value;
    }

    /**
     * 删除cookie
     * @param request
     * @param response
     * @param name
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] array=request.getCookies();
        if (array == null || array.length == 0) {
            return;
        }
        for (Cookie cookie: array) {
            if (cookie.getName().equals(name)) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                cookie.setValue(null);
                response.addCookie(cookie);
                cookie.setDomain(".dui1dui.com");
                response.addCookie(cookie);
                cookie.setDomain("www.dui1dui.com");
                response.addCookie(cookie);
                cookie.setDomain("m.dui1dui.com");
                response.addCookie(cookie);
            }
        }
    }
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name,String domain) {
        Cookie[] array=request.getCookies();
        if (array == null || array.length == 0) {
            return;
        }
        for (Cookie cookie: array) {
            if (cookie.getName().equals(name)) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                cookie.setValue(null);
                response.addCookie(cookie);
                cookie.setDomain(domain);
                response.addCookie(cookie);
                cookie.setDomain("www"+domain);
                response.addCookie(cookie);
                cookie.setDomain("m"+domain);
                response.addCookie(cookie);
            }
        }
    }

    public static void clearCookie(HttpServletRequest request, HttpServletResponse response) {
        addCookie(response, "www.dui1dui.com", "access_token", "", 0);
        addCookie(response, "www.dui1dui.com", "app", "", 0);
        addCookie(response, "www.dui1dui.com", "from", "", 0);
        addCookie(response, "www.dui1dui.com", "login_from", "", 0);
        addCookie(response, "www.dui1dui.com", "new_bindType", "", 0);
        
        addCookie(response, "m.dui1dui.com", "access_token", "", 0);
        addCookie(response, "m.dui1dui.com", "app", "", 0);
        addCookie(response, "m.dui1dui.com", "from", "", 0);
        addCookie(response, "m.dui1dui.com", "login_from", "", 0);
        addCookie(response, "m.dui1dui.com", "new_bindType", "", 0);
        
        addCookie(response, null, "access_token", "", 0);
        addCookie(response, null, "app", "", 0);
        addCookie(response, null, "from", "", 0);
        addCookie(response, null, "login_from", "", 0);
        addCookie(response, null, "new_bindType", "", 0);
    }


    /**
     * 获取IP
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip=getSubIp(request);
        if (StringUtils.isBlank(ip)) {
            return "";
        }
        String tmpIps[]=StringUtils.split(ip, ",");
        if (tmpIps == null || tmpIps.length == 0) {
            return "";
        }
        return tmpIps[0];
    }

    /**
     * 获取子IP，可能有多个
     * @param request
     * @return
     */
    private static String getSubIp(HttpServletRequest request) {
        String unknown="unknown";
        String ip=request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() > 0 && !unknown.equalsIgnoreCase(ip)) {
            return ip;
        }
        ip=request.getHeader("Proxy-Client-IP");
        if (ip != null && ip.length() > 0 && !unknown.equalsIgnoreCase(ip)) {
            return ip;
        }
        ip=request.getHeader("WL-Proxy-Client-IP");
        if (ip != null && ip.length() > 0 && !unknown.equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
