package com.wsy.learn.spring.placeholder;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * 把classpath下的Json文件赋值给bean
 */
public class JSONUtil {

    File in;

    public File getIn() {
        return in;
    }

    public void setIn(File in) {
        this.in = in;
    }

    public String getConfigAsMap() {
        try {
            FileReader reader = new FileReader(in);
            BufferedReader br = new BufferedReader (reader);

            StringBuilder builder = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            System.out.println(JSON.parseObject(builder.toString()).toJSONString());
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error )))>>>>>";
    }
}
