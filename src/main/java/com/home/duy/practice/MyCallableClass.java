package com.home.duy.practice;

import java.util.concurrent.Callable;

public class MyCallableClass implements Callable<Integer> {

    public Integer somethingRandom(){
        System.out.println("before increment: "+a);
        a++;
        System.out.println("after increment: "+a);
        return a;
    }

    int a = new Integer(2);
    @Override
    public Integer call() {
        synchronized (this) {
            return somethingRandom();
        }
    }
}
