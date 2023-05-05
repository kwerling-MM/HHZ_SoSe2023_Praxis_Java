package com.hhz;

public class Main {

    public static void main(String[] args) {
        boolean isInteger = false;
        int intVal = -1;
        for( int i = args.length; i>0;  i--) {
            try {
                intVal = Integer.parseInt( args[i - 1]) + 10;
                isInteger = true;
            } catch( Exception ex ) {
                isInteger = false;
            }
            if( isInteger ) {
                System.out.println("Param #" + i +":   " + intVal + "    INTEGER");
            } else {
                System.out.println("Param #" + i + ":   " + args[i - 1]);
            }
        }
    }
}