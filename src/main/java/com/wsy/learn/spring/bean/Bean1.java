package com.wsy.learn.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Bean1 {
    @Autowired
    private Main main;

    @PostConstruct
    private void init() {
        System.out.println("Bean1开始初始化");
        for (int i = 0; i < 10; i++) {
            System.out.println(main.getA());
        }
        System.out.println("Bean1初始化完毕");
    }
}
