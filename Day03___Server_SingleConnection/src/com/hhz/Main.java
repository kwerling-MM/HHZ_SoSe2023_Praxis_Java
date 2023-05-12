package com.hhz;


import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        EchoServer server=new EchoServer();
        try {
            server.start(7788);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

