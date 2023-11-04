package ru.netology;

import java.io.IOException;

public class Main {
    private static final int SERVER_PORT = 9999;
    private static final int THREAD_POOL_SIZE = 64;

    public static void main(String[] args) {
        Server server = new Server(SERVER_PORT, THREAD_POOL_SIZE);

        // adding handlers (code for processing)
        server.addHandler("GET", "/messages", (request, responseStream) -> {
            try {
                server.responseWithoutContent(responseStream, "404", "Not Found");
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
        System.out.println("Server started.");
    }
}