package com.wsy.learn.spring.placeholder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * org.springframework.context.support.AbstractApplicationContext
 * #invokeBeanFactoryPostProcessors(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)
 * 在application refresh的时候，会添加进去 properties
 */
@Component
public class DataSource {

    /**
     * 驱动类
     */
    @Value(value = "${driveClass}")
    private String driveClass;

    /**
     * jdbc地址
     */
    @Value("${url}")
    private String url;

    /**
     * 用户名
     */
    @Value("#{appConfig['userName']}")
    private String userName;

    /**
     * 密码
     */
    @Value("#{appConfig['config1']}")
    private String password;

    public String getDriveClass() {
        return driveClass;
    }

    public void setDriveClass(String driveClass) {
        this.driveClass = driveClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DataSource [driveClass=" + driveClass + ", url=" + url + ", userName=" + userName + ", password=" + password + "]";
    }

}
