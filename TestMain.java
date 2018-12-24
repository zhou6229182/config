package com.springcloud.demo.app.ss;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        List<Test> list = new ArrayList<>();
        Test one = new Test();
        one.setName("张三");
        one.setTime(new Timestamp(new Date().getTime()));
        Test two = new Test();
        two.setName("李四");
        two.setTime(new Timestamp(new Date().getTime()));
        list.add(one);
        list.add(two);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Timestamp.class, new TimestampJsonValueProcessor());
        String json = JSONArray.fromObject(list, jsonConfig).toString();
        System.out.println(json);

        JSONArray array = JSONArray.fromObject(json);
        JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher());
        List<Test> newList = JSONArray.toList(array, new Test(), new JsonConfig());
        for (Test t : newList) {
            System.out.println(t.getTime());
        }
    }
}
