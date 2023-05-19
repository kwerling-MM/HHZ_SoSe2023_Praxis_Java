package com.hhz;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    public static void main(String[] args) {
	    HttpClient client = HttpClient.newHttpClient();

        try {
            postmanEchoGet(client);
            postmanMyIP(client);
            postmanPost(client);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }



    static void  postmanEchoGet(HttpClient client) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri( new URI("https://postman-echo.com/get") )
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("httpGetRequest: " + response.body());
        System.out.println("httpGetRequest status code: " + response.statusCode());
        System.out.println("");
    }

    static void  postmanEchoGetWithParameter(HttpClient client) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri( new URI("https://postman-echo.com/get?Eins=1") )
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("httpGetRequestWithParameter: " + response.body());
        System.out.println("httpGetRequest status code: " + response.statusCode());
        System.out.println("");
    }

    static void  postmanMyIP(HttpClient client) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri( new URI("https://postman-echo.com/ip") )
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("My IP Address: " + response.body());
        System.out.println("httpGetRequest status code: " + response.statusCode());
        System.out.println("");
    }

    static void  postmanPost(HttpClient client) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri( new URI("https://postman-echo.com/post") )
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .POST(HttpRequest.BodyPublishers
                        .ofString("Greeting to the Postman Web Service."))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("POST Request response: " + response.body());
        System.out.println("httpGetRequest status code: " + response.statusCode());
        System.out.println("");
    }

}
