package org.example.abc.pack_singleton;

/**
 * 单例模式1--饿汉式
 * 即时加载，线程安全
 * 可能造成内存浪费
 */
public class Singleton01 {
    //初始化时就实例化对象
    private static final Singleton01 obj = new Singleton01();

    //构造方法私有化，使外部不能随意创建对象
    private Singleton01() {
    }

    //获取对象的方法
    public static Singleton01 getInstance() {
        return obj;
    }

}
