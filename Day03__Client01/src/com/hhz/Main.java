package com.hhz;


import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        EchoClient client = new EchoClient();
        try {
            client.startConnection("127.0.0.1", 7788);
            String response = client.sendMessage("hello server");
            System.out.println(response);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

