package com.yxj.blog.comment.handler;

import com.yxj.blog.comment.feign.AuthClient;
import com.yxj.blog.comment.utils.UserThreadLocal;
import com.yxj.blog.common.entity.SysUser;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * @author 从前慢
 * @Component 注册为spring实例
 * @Resource 生成的类实例交给SpringIOC容器管理
 * @Override 方法重写标识
 */
@Component
public class CustomInterceptor implements HandlerInterceptor {

    @Resource
    private AuthClient authClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 在执行controller方法（Handler）之前进行执行
         * 1 需要判断请求的接口路径是否为HandlerMethod（Controller方法）
         * 2 判断token是否为空，如果为空，未登录
         * 3 如果token不为空，登录验证loginService CheckToken
         * 4 如果认证成功，放行即可
         */

        if (!(handler instanceof HandlerMethod)) {
            /**
             * handler 可能是RequestResourceHandler SpringBoot 访问静态资源默认去classpath下的static目录去查询
             */
            return true;
        }

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        SysUser sysUser = authClient.checkToken(token);
        UserThreadLocal.put(sysUser);
        return true;
    }




    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /**
         * 如果不删除ThreadLocal用户的信息，会有内存泄漏的风险
         */
        UserThreadLocal.remove();
    }
}
