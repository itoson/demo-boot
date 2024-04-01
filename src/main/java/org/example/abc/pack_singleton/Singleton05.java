package org.example.abc.pack_singleton;

/**
 * 单例模式05--静态内部类
 * 线程安全
 */
public class Singleton05 {
    private Singleton05() {
    }

    public static Singleton05 getInstance() {
        return Singleton.obj;
    }

    private static class Singleton {
        private static final Singleton05 obj = new Singleton05();
    }
}
