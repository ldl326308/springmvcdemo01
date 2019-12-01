package com.lm.springmvcdemo01.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 继承HandlerInterceptorAdapter，重写需要的方法，实现请求拦截
 */

public class DemoInterceptor extends HandlerInterceptorAdapter {

    /**
     * 此方法在请求发生前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }

    /**
     * 此方法在请求完成后执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        System.out.println("本次请求处理的时间：" + (System.currentTimeMillis() - startTime) + "ms");
    }
}
