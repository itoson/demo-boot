package org.example.abc.pack_singleton;

/**
 * 单例模式02--懒汉式
 * 线程不安全
 */
public class Singleton02 {
    private static Singleton02 obj;

    private Singleton02() {
    }

    //
    public static Singleton02 getInstance() {
        if (obj == null) {
            obj = new Singleton02();
        }
        return obj;
    }
}
