package com.ecit;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyFactorybean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        Integer user = new Integer(1000);
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return Integer.class;
    }
}
