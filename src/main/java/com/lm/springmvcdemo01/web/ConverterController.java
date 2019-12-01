package com.lm.springmvcdemo01.web;

import com.lm.springmvcdemo01.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConverterController {

    @RequestMapping(value = "/converter", method = RequestMethod.POST, produces = "application/x-wisely")
    @ResponseBody
    public Student convert(@RequestBody Student student) {
        return student;
    }

}
