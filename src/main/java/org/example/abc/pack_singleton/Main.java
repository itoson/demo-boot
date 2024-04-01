package org.example.abc.pack_singleton;

public class Main {
    public static void main(String[] args) {
        Singleton05 instance1 = Singleton05.getInstance();
        Singleton05 instance2 = Singleton05.getInstance();
        Singleton05 instance3 = Singleton05.getInstance();
        Singleton05 instance4 = Singleton05.getInstance();
        Singleton05 instance5 = Singleton05.getInstance();

        System.out.println(instance1);
        System.out.println(instance2);
        System.out.println(instance3);
        System.out.println(instance4);
        System.out.println(instance5);

    }
}
