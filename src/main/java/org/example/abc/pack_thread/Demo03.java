package org.example.abc.pack_thread;

public class Demo03 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                synchronized (Demo03.class) {
                    System.out.println("A");
                    try {
                        Demo03.class.notify();
                        Demo03.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                synchronized (Demo03.class) {
                    System.out.println("B");
                    try {
                        Demo03.class.notify();
                        Demo03.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t2.start();

    }
}
