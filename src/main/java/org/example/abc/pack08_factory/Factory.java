package org.example.abc.pack08_factory;

import java.io.IOException;
import java.util.Properties;

public class Factory {
    public static Police getPolice() {
        Properties prop = new Properties();
        try {
            prop.load(Factory.class.getClassLoader().getResourceAsStream("setting.properties"));
            return (Police) Class.forName(prop.getProperty("curr")).newInstance();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
