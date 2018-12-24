package com.springcloud.demo.app.ss;

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.object.AbstractObjectMorpher;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimestampMorpher extends AbstractObjectMorpher {

    @Override
    public Object morph(Object value) {
        if (value == null) {
            return null;
        }
        if (Timestamp.class.isAssignableFrom(value.getClass())) {
            return value;
        }
        if (!supports(value.getClass())) {
            throw new MorphException(value.getClass() + " 是不支持的类型");
        }
        String strValue = (String) value;
        try {
            return new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strValue.toLowerCase()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Class morphsTo() {
        return Timestamp.class;
    }

    @Override
    public boolean supports(Class clazz) {
        return String.class.isAssignableFrom(clazz);
    }
}
