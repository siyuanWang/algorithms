package com.wsy.learn.spring.placeholder;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * 把classpath下的Json文件赋值给bean
 */
@Component
public class JSONUtil {

    File in;

    public void getConfigAsMap() {
        try {
            if(in == null || !in.isDirectory()) {
                throw new RuntimeException("template is not a dir");
            }
            File[] files = in.listFiles();
            if(files == null) {
                throw new RuntimeException("dir file list is empty");
            }
            for (File file : files) {
                FileReader reader = new FileReader(file);
                BufferedReader br = new BufferedReader(reader);

                StringBuilder builder = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    builder.append(line);
                }
                System.out.println(JSON.parseObject(builder.toString()).toJSONString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setIn(File in) {
        this.in = in;
    }
}
