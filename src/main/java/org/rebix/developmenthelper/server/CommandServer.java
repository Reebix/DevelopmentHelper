package org.rebix.developmenthelper.server;

import java.net.ServerSocket;

public class CommandServer {
    public ServerSocket serverSocket;
    public Thread thread;

    public CommandServer() {
        try {
            serverSocket = new ServerSocket(36676);
            thread = new Thread(() -> {
                while (true) {
                    try {
                        if (serverSocket.isClosed() || thread.isInterrupted())
                            break;
                        new CommandServerThread(serverSocket.accept()).start();
                    } catch (Exception e) {
                        if (e.getMessage().equals("Socket closed"))
                            break;
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

    public void stop() {
        try {
            thread.interrupt();
            serverSocket.close();
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
