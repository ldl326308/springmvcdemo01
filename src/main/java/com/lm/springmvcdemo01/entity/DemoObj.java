package com.lm.springmvcdemo01.entity;

import org.springframework.stereotype.Component;

@Component
public class DemoObj {

    String start;
    String end;

    public DemoObj() {
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
