package com.lm.springmvcdemo01.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  //声明此类是控制器
public class HelloController {

    @RequestMapping("/index")
    public String hello(){
        return "index";
    }

}


