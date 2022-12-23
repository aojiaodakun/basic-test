package com.hzk.test;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = -8088742348807697485L;

    private String name;

    public UserDTO(){
        System.out.println("construct");
    }

    public String getName() {
        System.out.println("getName");
        return name;
    }

    public void setName(String name) {
        System.out.println("setName");
        this.name = name;
    }
}
