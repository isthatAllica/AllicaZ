package edu.whu.demo;

import edu.whu.framework.InitMethod;

//使用@InitMethod注解的类
public class MyClass {
    String status="offline";

    @InitMethod
    public void init(){
        this.status="online";
        System.out.println("init()方法已被调用");
    }

    public String getStatus(){
        return status;
    }

}
