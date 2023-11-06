package ru.netology;

import java.io.IOException;

public class Main {
    private static final int SERVER_PORT = 9999;
    private static final int THREAD_POOL_SIZE = 64;


    public static void main(String[] args) throws InterruptedException {
        Server server = new Server(SERVER_PORT, THREAD_POOL_SIZE);

        // add handlers
        server.addHandler("GET", "/messages", (request, responseStream) -> {
            try {
                server.responseWithoutContent(responseStream, "200", "OK");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        server.addHandler("POST", "/messages", (request, responseStream) ->
                server.responseWithoutContent(responseStream, "503", "Service Unavailable"));

        server.addHandler("GET", "/", ((request, outputStream) ->
                server.defaultHandler(outputStream, "index.html")));

        // Start
        server.start();
    }
}