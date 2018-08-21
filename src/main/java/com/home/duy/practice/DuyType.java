package com.home.duy.practice;

public class DuyType implements Comparable<DuyType>{
    public String getValue() {
        return value;
    }

    String value;
    @Override
    public int compareTo(DuyType other){
       return this.getValue().compareToIgnoreCase(other.getValue());
    }


    public boolean equals(DuyType other){
        return this.getValue().equalsIgnoreCase(other.getValue());
    }
}
