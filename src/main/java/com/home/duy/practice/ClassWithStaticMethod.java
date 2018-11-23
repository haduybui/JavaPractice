package com.home.duy.practice;

import java.util.LinkedList;
import java.util.List;

public class ClassWithStaticMethod {
    public static String duyName(){
        return "Ha Duy";
    }

    public List<String> getUsersName(){
        List<String> listUserName = new LinkedList<>();
        listUserName.add("Duy");
        listUserName.add("Thao");
        return listUserName;
    }

    public static String thaoName(String name){
        return name;
    }

    public static int justForHamcrest(Integer a){
        return a*a;
    }
}
