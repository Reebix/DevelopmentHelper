package org.rebix.developmenthelper.server;

import java.net.ServerSocket;

public class CommandServer {
    public static ServerSocket serverSocket;
    public static Thread thread;

    public CommandServer() {
        try {
            serverSocket = new ServerSocket(36676);
            thread = new Thread(() -> {
                while (true) {
                    try {
                        if (serverSocket.isClosed())
                            break;
                        new CommandServerThread(serverSocket.accept()).start();
                    } catch (Exception e) {
                        //noinspection CallToPrintStackTrace
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    public static void stop() {
        try {
            thread.interrupt();
            serverSocket.close();
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
