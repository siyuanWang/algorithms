package com.wsy.learn.spring;

import com.wsy.learn.spring.placeholder.DataSource;
import com.wsy.learn.spring.placeholder.JSONUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * 测试入口
 */
public class Bootstrap {
    private ApplicationContext ac = new ClassPathXmlApplicationContext("application.xml");
    public void testPropertyPlaceHolder() {
        DataSource dataSource = (DataSource)ac.getBean("dataSource");
        System.out.println(dataSource);
    }

    public void testParseJson() {
        JSONUtil jsonUtil = (JSONUtil)ac.getBean("JSONUtil");
        //System.out.println(jsonUtil.getConfigAsMap());
        jsonUtil.getConfigAsMap();
    }

    public static void main(String[] args) {
        Bootstrap boot = new Bootstrap();
        boot.testPropertyPlaceHolder();
        boot.testParseJson();

    }
}
