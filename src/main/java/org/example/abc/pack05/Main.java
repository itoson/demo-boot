package org.example.abc.pack05;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Father father = new FatherImpl();
        Father fatherProxy = (Father) Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{Father.class}, new MyHandler(father));
        fatherProxy.m2();
    }
}
