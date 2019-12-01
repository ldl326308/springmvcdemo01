package com.lm.springmvcdemo01.web;


import com.lm.springmvcdemo01.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdviceController {

    @RequestMapping("/advice")
    public String getSomething(@ModelAttribute("msg") String msg, Student student){
        System.out.println(student);
        throw new IllegalArgumentException("参数错误，来自@ModelAttribute的值：" + msg);
    }

}
