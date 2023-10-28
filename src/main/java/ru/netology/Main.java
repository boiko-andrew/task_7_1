package ru.netology;

public class Main {
    private static final int SERVER_PORT = 9999;
    private static final int THREAD_POOL_SIZE = 64;

    public static void main(String[] args) {
        Server server = new Server(SERVER_PORT, THREAD_POOL_SIZE);
        server.start();
    }
}