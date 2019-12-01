package com.lm.springmvcdemo01.web;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String upload(MultipartFile file, HttpServletRequest request) {
        String fileName = UUID.randomUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File endFile = new File(request.getServletContext().getRealPath("") + "images\\" + fileName);
        try {
            FileUtils.writeByteArrayToFile(endFile, file.getBytes());
            return "<img src='http://localhost:8080/images/" + fileName +"'/>";
        } catch (IOException e) {
            e.printStackTrace();
            return "<p style='color:red;'>wrong</p>";
        }
    }

}
