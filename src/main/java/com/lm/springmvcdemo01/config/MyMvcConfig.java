package com.lm.springmvcdemo01.config;

import com.lm.springmvcdemo01.advice.ExceptionHandlerAdvice;
import com.lm.springmvcdemo01.interceptor.DemoInterceptor;
import com.lm.springmvcdemo01.messageconverter.MyMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * @EnableWebMvc 开启Spring MVC 支持
 *
 * WebMvcConfigurationSupport 继承此类，重写其方法可对SpringMVC进行配置，并且不需要注解@EnableWebMVC
 *
 * implements WebMvcConfigurer ： 不会覆盖@EnableAutoConfiguration关于WebMvcAutoConfiguration的配置
 * @EnableWebMvc + implements WebMvcConfigurer ： 会覆盖@EnableAutoConfiguration关于WebMvcAutoConfiguration的配置
 * extends WebMvcConfigurationSupport ：会覆盖@EnableAutoConfiguration关于WebMvcAutoConfiguration的配置
 * extends DelegatingWebMvcConfiguration ：会覆盖@EnableAutoConfiguration关于WebMvcAutoConfiguration的配置
 */

@EnableScheduling //开启定时任务
@Configuration
//@EnableWebMvc //开启Spring MVC 支持，此注解会带入一些默认配置
@ComponentScan("com.lm.springmvcdemo01")
public class MyMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 映射器
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    /**
     * 配置拦截器的Bean
     * @return
     */
    @Bean
    public DemoInterceptor interceptor(){
        return new DemoInterceptor();
    }

    /**
     *  重写此方法以添加Spring MVC拦截器控制器调用的预处理和后处理
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        registry.addInterceptor(interceptor());
    }

    /**
     * 静态资源访问配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/images/");
        System.out.println("添加静态资源映射的路径....");
    }

    /**
     * 如果请求只是简单的页面跳转，那么可以通过此方法，进行配置，开发中更简洁，方便
     * @param registry
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/toUpload").setViewName("upload");
        registry.addViewController("/con").setViewName("converter");
        registry.addViewController("/sse").setViewName("sse");
        registry.addViewController("/async").setViewName("async");
    }

    /**
     * 路径匹配参数配置
     * @param configurer
     */
    @Override
    protected void configurePathMatch(PathMatchConfigurer configurer) {
        //如果不设置此步骤，访问路径/hello.xx时，点后面的xx将忽略，将视为访问/hello
        configurer.setUseSuffixPatternMatch(false);
    }

    /**
     * 注册用于文件上传的Bean
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(1000000000);
        return resolver;
    }

    /**
     * 注册自定义的MessageConverter类
     */
    @Bean
    public MyMessageConverter messageConverter(){
        return new MyMessageConverter();
    }

    /**
     * 注册自定义的MessageConverter有两种方式：
     *  1、在重写的extendMessageConverters方法中注册，仅添加一个自定义的HTTPMessageConverter，
     *      不覆盖默认注册的HTTPMessageConverter
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(messageConverter());
    }

    /**
     *  2、在重写的configureMessageConverters中添加，重写会覆盖Spring MVC 默认注册的多个HTTPMessageConverter
     */
//    @Override
//    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(messageConverter());
//    }
}
