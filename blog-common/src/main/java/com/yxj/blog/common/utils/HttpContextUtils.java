package com.yxj.blog.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author 从前慢
 */
public class HttpContextUtils {
   public static HttpServletRequest getHttpServletRequest() {

       return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
   }
}
