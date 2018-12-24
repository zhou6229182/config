package com.springcloud.demo.app.ss;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TimestampJsonValueProcessor implements JsonValueProcessor {
    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return process(o);
    }

    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
        return process(o);
    }

    private Object process(Object bean) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) bean);
    }
}
