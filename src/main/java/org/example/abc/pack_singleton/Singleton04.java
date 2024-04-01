package org.example.abc.pack_singleton;

/**
 * 单例模式04--懒汉式/双检锁
 * 线程安全，性能开销较小
 */
public class Singleton04 {
    private static Singleton04 obj;

    private Singleton04() {
    }

    //
    public synchronized static Singleton04 getInstance() {
        if (obj == null) {
            synchronized (Singleton04.class) {
                if (obj == null) {
                    obj = new Singleton04();
                }
            }
        }
        return obj;
    }
}
