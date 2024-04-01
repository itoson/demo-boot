package org.example.abc.pack_singleton;

/**
 * 单例模式03--懒汉式/加锁
 * 线程安全，性能开销大
 */
public class Singleton03 {
    private static Singleton03 obj;

    private Singleton03() {
    }

    //加锁
     public synchronized static Singleton03 getInstance() {
        if (obj == null) {
            obj = new Singleton03();
        }
        return obj;
    }
}
