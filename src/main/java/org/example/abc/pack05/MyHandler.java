package org.example.abc.pack05;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler implements InvocationHandler {
    Object obj;

    public MyHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("准备执行" + method.getName());
        Object o = method.invoke(obj);
        System.out.println("结束执行" + method.getName());
        return o;
    }

}
