package com.wsy.learn.spring.placeholder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 搜索模板
 * 目前支持上架
 */
public class Template {
    /**
     * 模板目录
     */
    private File in;
    /**
     * 原始模板
     */
    private Map<String, JSONObject> templates;
    /**
     * 字段类型hash
     * key:<templateName + "#" + field>
     * value: string,int,long,double,date
     */
    private Map<String, String> fieldTypeMap;

    private final String SPLIT = "_template";
    private final String TEMPLATE_FIELD_SPLIT = "#";
    private final String FIELD_SPLIT = ".";

    public void setIn(File in) {
        this.in = in;
    }

    public final String SKU_UP_0 = "sku_up_0";
    public final String COUPON_UP_0 = "coupon_up_0";

    /**
     * 初始化
     */
    private void init() {
        FileReader reader = null;
        BufferedReader br = null;
        try {
            URL url = this.getClass().getResource("/");
            System.out.println("<<<<<Template resource="+ url.getPath());
            if (in == null || !in.isDirectory()) {
                throw new RuntimeException("搜索模板入参必须是目录");
            }
            File[] files = in.listFiles();
            if (files == null) {
                throw new RuntimeException("搜索模板目录下不存在文件");
            }
            templates = new HashMap<>();
            fieldTypeMap = new HashMap<>();
            for (File file : files) {
                reader = new FileReader(file);
                br = new BufferedReader(reader);
                StringBuilder builder = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    builder.append(line);
                }
                //获得模板的名称
                String key = file.getName().split(SPLIT)[0];
                JSONObject origin = JSON.parseObject(builder.toString());
                templates.put(key, origin);
                Iterator<Map.Entry<String, Object>> it = origin.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Object> entry = it.next();
                    Object value = entry.getValue();
                    String fieldName = entry.getKey();
                    setFieldType(fieldTypeMap, key, fieldName, value);
                }
            }

            System.out.println(JSON.toJSONString(fieldTypeMap));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void setFieldType(Map<String, String> fieldType, String templateName, String fieldName, Object value) {
        if (value instanceof String) {
            fieldType.put(templateName + TEMPLATE_FIELD_SPLIT + fieldName, (String) value);
        } else {
            JSONObject nested = (JSONObject) value;
            Iterator<Map.Entry<String, Object>> it = nested.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String field = fieldName + FIELD_SPLIT + entry.getKey();
                setFieldType(fieldType, templateName, field, entry.getValue());
            }
        }
    }

    /**
     * 模板下字段是否存在
     *
     * @param templateName 模板名称
     * @param field        字段名称 PS：如果是nested，请自己拼装。比如 orientation.planId
     * @return
     */
    public boolean containField(String templateName, String field) {
        return fieldTypeMap.containsKey(templateName + TEMPLATE_FIELD_SPLIT + field);
    }

    public JSONObject getTemplates(String name) {

        return templates.get(name);
    }
}
