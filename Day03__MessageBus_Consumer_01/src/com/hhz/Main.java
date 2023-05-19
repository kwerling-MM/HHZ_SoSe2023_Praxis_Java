package com.hhz;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import java.nio.charset.StandardCharsets;

public class Main {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
            try  (  Connection connection = factory.newConnection();
                    Channel channel = connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                    System.out.println(" [x] Received '" + message + "'");
                };
                 channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

                while( true ) {
                    Thread.sleep(200);
                }

             } catch (Exception e) {
            System.err.println( e.getMessage());
            System.err.println( e );
            e.printStackTrace();
        }
    }

}
