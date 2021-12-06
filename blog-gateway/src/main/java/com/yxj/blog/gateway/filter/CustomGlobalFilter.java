package com.yxj.blog.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.yxj.blog.common.Error.ErrorCode;
import com.yxj.blog.common.entity.SysUser;
import com.yxj.blog.common.vo.Result;
import com.yxj.blog.gateway.feign.AuthClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局过滤器
 *
 * @author 从前慢
 * @Component 注册为spring实例
 * @Slf4j 生成日志
 */
@Component
@Slf4j
public class CustomGlobalFilter implements GlobalFilter, Ordered {
    @Resource
    private AuthClient authClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        ServerHttpResponse serverHttpResponse = exchange.getResponse();

        String token = serverHttpRequest.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        URI uri = serverHttpRequest.getURI();
        HttpMethod method = serverHttpRequest.getMethod();
        log.info("=================request start========================");
        log.info("request uri:{}", uri);
        log.info("request method:{}", method);
        log.info("token:{}", token);
        log.info("====================request end===========================");

        /**
         * 以下为需要认证接口
         */

        List<String> list = new ArrayList<>();
        list.add("/logout");
        list.add("/user/currentUser");
        list.add("/comments/create/change");
        list.add("/articles/publish");
        String str = exchange.getRequest().getPath().value();
        if (list.contains(str)) {
            if (StringUtils.isBlank(token)) {
                Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
                serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

                DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JSON.toJSONString(result).getBytes());
                return serverHttpResponse.writeWith(Flux.just(dataBuffer));
            }
            SysUser sysUser = authClient.checkToken(token);
            if (sysUser == null) {
                Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
                serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

                DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JSON.toJSONString(result).getBytes());
                return serverHttpResponse.writeWith(Flux.just(dataBuffer));
            }


            //ServerHttpRequest request = exchange.getRequest();
            //ServerHttpRequest req = request.mutate().headers(header -> header.add(HttpHeaders.AUTHORIZATION, token)).build();
            //ServerWebExchange webExchange = exchange.mutate().request(req).build();
            //return chain.filter(webExchange);
        }


        return chain.filter(exchange);


        ///**
        // * 以下不用token认证
        // */
        //List<String> list = new ArrayList<>();
        ///**
        // * 注册
        // */
        //list.add("/register");
        ///**
        // * 登录
        // */
        //list.add("/login");
        ///**
        // * 首页文章
        // */
        //list.add("/articles");
        ///**
        // * 最热文章
        // */
        //list.add("/articles/hot");
        ///**
        // * 最新文章
        // */
        //list.add("/articles/new");
        ///**
        // * 最热标签
        // */
        //list.add("/tags/hot");
        ///**
        // * 文章归档
        // */
        //list.add("/articles/listArchives");
        ///**
        // * 文章详细信息
        // */
        //list.add("/articles/view");
        ///**
        // * 文章分类大概
        // */
        //list.add("/categorys");
        ///**
        // * 文章分类详细
        // */
        //list.add("/categorys/detail");
        /////**
        //// * 分类相关信息
        //// */
        ////list.add("/categorys/detail/{id}");
        ///**
        // * 文章标签大概
        // */
        //list.add("/tags");
        ///**
        // * 文章标签详细
        // */
        //list.add("/tags/detail");
        //list.add("/tags/detail/{id}");
        //String str = exchange.getRequest().getPath().value();
        //if (str.startsWith("/articles/view")) {
        //    return chain.filter(exchange);
        //} else if (str.startsWith("/comments/article")) {
        //    return chain.filter(exchange);
        //} else if (str.startsWith("/categorys/detail")) {
        //    return chain.filter(exchange);
        //}else if (str.startsWith("/tags/detail")){
        //    return chain.filter(exchange);
        //}else {
        //    if (!(list.contains(str))) {
        //        if (StringUtils.isBlank(token)) {
        //            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
        //            serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        //
        //            DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JSON.toJSONString(result).getBytes());
        //            return serverHttpResponse.writeWith(Flux.just(dataBuffer));
        //        }
        //        SysUser sysUser = authClient.checkToken(token);
        //        if (sysUser == null) {
        //            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
        //            serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        //
        //            DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JSON.toJSONString(result).getBytes());
        //            return serverHttpResponse.writeWith(Flux.just(dataBuffer));
        //        }
        //        UserThreadLocal.put(sysUser);
        //    }
        //}
        //
        //return chain.filter(exchange);

    }


    /**
     * filter 数字越小filter越先执行
     * -1  最先执行
     *
     * @return
     */
    @Override
    public int getOrder() {
        return -1;
    }
}