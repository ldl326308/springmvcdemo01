package com.lm.springmvcdemo01.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class SseController {

    private long time = 1;

    /**
     * 输出媒体类型为 text/event-stream，是服务器端SSE的支持
     *
     * @return
     */
    @RequestMapping(value = "/push", produces = "text/event-stream;charset=utf-8")
    @ResponseBody
    public String push() {
        //本例演示每隔5秒推送一次消息到浏览器
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:Testing 第 " + (time++) + "次请求....\n\n";
    }

}
