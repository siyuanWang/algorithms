package com.wsy.learn.spring;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@EnableScheduling
public class Main {

    public String a = "";

    public Main() {
        System.out.println("构造方法执行");
    }

    @PostConstruct
    private void init() {
        System.out.println("Main开始初始化");
        sync1();
        System.out.println("Main初始化完毕");
    }



    public String getA() {
        return a;
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void sync() {
        System.out.println("Main开始Sync");
        sync1();
        System.out.println("Main结束Sync" + a);
    }


    private void sync1() {
        a = "hahahaha";
    }

    @PreDestroy
    private void preDestroy() {
        System.out.println("释放资源");
    }
}
