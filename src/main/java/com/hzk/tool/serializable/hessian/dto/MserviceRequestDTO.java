package com.hzk.tool.serializable.hessian.dto;

import java.io.Serializable;
import java.util.Map;

public class MserviceRequestDTO implements Serializable {

    private String name;

    private int age;

    private long money;

    private Map<String, String> paramMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }
}
