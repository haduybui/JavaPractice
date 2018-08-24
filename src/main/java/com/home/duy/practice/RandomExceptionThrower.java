package com.home.duy.practice;

public class RandomExceptionThrower extends RuntimeException{

    public String randomStringThrower(String input) throws RuntimeException{
        try {
            input = "haha";
            return input;
        } catch (Exception e){
            throw new RuntimeException("Thrower Throwing haha");
        }
    }
}
