package com.hzk.test;

public class B extends A{

    static {
        System.out.println("static B");
    }

    @Override
    public void method(){
        System.out.println("B");
    }

}
