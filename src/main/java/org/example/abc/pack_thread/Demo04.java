package org.example.abc.pack_thread;

public class Demo04 {
    private static int num = 0;

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.start();
        t2.start();

    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                num++;
                System.out.println(num);
            }

        }
    }
}
