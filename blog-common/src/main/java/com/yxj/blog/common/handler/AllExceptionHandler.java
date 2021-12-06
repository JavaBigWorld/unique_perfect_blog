package com.yxj.blog.common.handler;

import com.yxj.blog.common.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


/**
 * 对加了@Controller注解的方法进行拦截处理 AOP的实现
 * @author 从前慢
 * @RestController 返回json数据
 * @ControllerAdvice 异常处理注解
 * @ExceptionHandler 异常处理器
 */
@RestController
@ControllerAdvice
public class AllExceptionHandler {
    /**
     * 统一异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){
        ex.printStackTrace();
        return Result.fail(-999,"系统异常");
    }

}