package com.home.duy.practice;

public class StringUtil {

    private NestedStringUtil nestedStringUtil = new NestedStringUtil();

    public NestedStringUtil getNestedStringUtil() {
        return nestedStringUtil;
    }

    public String toString(){
        InnerStringUtil util = new InnerStringUtil();
        return util.returnRandomStringFromInner();
    }

    public static class NestedStringUtil{
        public String returnRandomString(){
            return "Hello From Nested";
        }
    }

    public class InnerStringUtil {
        public String returnRandomStringFromInner(){
            return "Hello From Nested";
        }

    }

    RandomInterface randomInterface = new RandomInterface() {
        @Override
        public int add(int a, int b) {
            return a + b;
        }
    };


}
