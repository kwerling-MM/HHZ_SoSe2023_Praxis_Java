package com.hhz;

public class Main {

    static long startTime = 0;
    static long endTime = 0;

    static void startTimer() {
        startTime = System.nanoTime();
    }

    static void endTimer() {
        endTime = System.nanoTime();
    }

    static void timerResults() {
        long duration = endTime - startTime;
        System.out.println("The operation took \n"
                + duration + " nano seconds\n"
                + duration / 1000 + " micro seconds\n"
                + duration / 1000000 + " milli seconds\n"
                + duration / 1000000000 + " seconds\n");
    }

    public static void main(String[] args) {
        String sourceString = "1234".repeat(256);
        System.out.println("Source string length: " + sourceString.length());
        String resultString = "";

        // To get some idea of how long a loop takes
        System.out.println("Empty For-Loop, 100 iterations");
        startTimer();
        for (int i = 0; i < 100; i++) {
        }
        endTimer();
        timerResults();
        System.out.println("\n\n");


        System.out.println("Empty For-Loop, 1 million iterations");
        startTimer();
        for (int i = 0; i < 1000000; i++) {
        }
        endTimer();
        timerResults();
        System.out.println("\n\n");

        System.out.println("Concat string, 100 iterations");
        resultString = "";
        startTimer();
        for (int i = 0; i < 100; i++) {
            resultString = resultString + sourceString;
        }
        endTimer();
        System.out.println("Result string length: " + resultString.length());
        timerResults();
        System.out.println("\n\n");

        System.out.println("Concat string, 1000 iterations");
        resultString = "";
        startTimer();
        for (int i = 0; i < 1000; i++) {
            resultString = resultString + sourceString;
        }
        endTimer();
        System.out.println("Result string length: " + resultString.length());
        timerResults();
        System.out.println("\n\n");

        System.out.println("Concat string, 10000 iterations");
        resultString = "";
        startTimer();
        for (int i = 0; i < 10000; i++) {
            resultString = resultString + sourceString;
        }
        endTimer();
        System.out.println("Result string length: " + resultString.length());
        timerResults();
        System.out.println("\n\n");

        System.out.println("Concat string, 100000 iterations");
        resultString = "";
        startTimer();
        for (int i = 0; i < 100000; i++) {
           //  resultString = resultString + sourceString;
           //  Took 1010 secs on my machine.
        }
        endTimer();
        System.out.println("Result string length: " + resultString.length());
        timerResults();
        System.out.println("\n\n");


        System.out.println("Concat string -- Stringbuffer , 100000 iterations");
        resultString = "";
        StringBuffer sbuf = new StringBuffer();
        startTimer();
        for (int i = 0; i < 100000; i++) {
            sbuf.append(sourceString);
        }
        endTimer();
        resultString = sbuf.toString();
        System.out.println("Result string length: " + resultString.length());
        timerResults();
        System.out.println("\n\n");


        System.out.println("Concat string -- Stringbuffer 32, 100000 iterations");
        resultString = "";
        sbuf = new StringBuffer(32);
        startTimer();
        for (int i = 0; i < 100000; i++) {
            sbuf.append(sourceString);
        }
        endTimer();
        resultString = sbuf.toString();
        System.out.println("Result string length: " + resultString.length());
        timerResults();
        System.out.println("\n\n");


        System.out.println("Concat string -- Stringbuilder , 100000 iterations");
        resultString = "";
        StringBuilder sbuild = new StringBuilder();
        startTimer();
        for (int i = 0; i < 100000; i++) {
            sbuild.append(sourceString);
        }
        endTimer();
        resultString = sbuild.toString();
        System.out.println("Result string length: " + resultString.length());
        timerResults();
        System.out.println("\n\n");

    }





}
