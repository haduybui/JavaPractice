package com.home.duy.practice;

public class MyRunnableClass implements Runnable {
    int a = 0;
    public int doSomething(){
        a++;
        return a;
    }
    public void run() {
        synchronized (this) {
            System.out.println("inside run() method");
            doSomething();
            System.out.println(a);
            System.out.println("finish wtih a=" + a + " at: " + System.currentTimeMillis());
        }
    }
}
