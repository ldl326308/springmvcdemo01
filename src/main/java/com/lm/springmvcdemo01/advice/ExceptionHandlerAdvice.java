package com.lm.springmvcdemo01.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 注解@ControllerAdvice声明一个控制器建言，此注解组合了@Component注解，自动注册为Spring的Bean
 */

@ControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * @ExceptionHandler 在此处定义为全局处理，value值定义过滤条件，在这里拦截所有Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request){
        System.out.println("ExceptionHandlerAdvice捕获到Exception错误。。。");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }

    /**
     * @ModelAttribute 注解将键值对添加到全局，所有注解的@RequestMapping的方法都可以获取到此键值对的值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("msg", "这是@ModelAttribute传递的msg的值");
    }

    /**
     * @InitBinder 此注解定制WebDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        //忽略传入的名字为id的值
        webDataBinder.setDisallowedFields("id");
    }

}
