package com.hhz;

public class Main {

    public static void main(String[] args) {
        for( int i = args.length; i>0;  i--) {
            System.out.println("Param #" + i +":   " + args[i-1]);
        }
    }
}
