package org.example.abc.pack_thread;

public class Demo02 {
    public static void main(String[] args) {
        Company cmp1 = new Company("蚂蚁金服");
        Company cmp2 = new Company("花旗银行");
        cmp1.start();
        cmp2.start();
    }

}

class Company extends Thread {
    private static int num = 1;

    public Company(String name) {
        this.setName(name);
    }

    public void employee() {
        synchronized (Company.class) {
            System.out.println(Thread.currentThread().getName() + ": 招走了 " + num + " 号员工");
            num++;
        }
    }
    //synchronized的锁定对象
    //--加在方法上 实际是锁定this当前对象
    //--锁定 类.class
    //--锁定 一个静态obj

    @Override
    public void run() {
        while (true) {
            try {
                employee();
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

