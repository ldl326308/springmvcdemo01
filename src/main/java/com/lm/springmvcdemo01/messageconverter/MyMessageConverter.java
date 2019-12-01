package com.lm.springmvcdemo01.messageconverter;

import com.lm.springmvcdemo01.entity.Student;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 继承AbstractHttpMessageConverter来实现自定义的HTTPMessageConverter
 */

public class MyMessageConverter extends AbstractHttpMessageConverter<Student> {

    public MyMessageConverter(){
        //新建自定义媒体类型 application/x-wisely
        super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
    }

    /**
     * 表明HttpMessageConverter只处理Student这个类
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        return Student.class.isAssignableFrom(clazz);
    }

    /**
     * 重写此方法，处理请求的数据
     */
    @Override
    protected Student readInternal(Class<? extends Student> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");
        return new Student(Integer.parseInt(tempArr[0]), tempArr[1]);
    }

    /**
     * 将输出到Response的数据进行处理，这里我们家里“Hello：”
     */
    @Override
    protected void writeInternal(Student student, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "Hello:" + student.getId() + "-" + student.getName();
        outputMessage.getBody().write(out.getBytes());
    }
}
