package org.example.abc.pack07_reflect;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Properties prop = new Properties();
        prop.load(Main.class.getClassLoader().getResourceAsStream("setting.properties"));
        Object obj = Class.forName(prop.getProperty("currClass")).newInstance();
        for (Method method : obj.getClass().getDeclaredMethods()) {
            if(method.getName().equals(prop.getProperty("currMethod"))){
                method.invoke(obj);
            }
        }

    }
}
