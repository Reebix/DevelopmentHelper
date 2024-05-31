package org.rebix.developmenthelper.server;

import org.rebix.developmenthelper.DevelopmentHelper;

import java.net.Socket;

public class CommandServerThread extends Thread {
    final Socket socket;

    public CommandServerThread(Socket accept) {
        socket = accept;
        System.out.println("New connection from " + socket.getInetAddress().getHostAddress());
    }

    public void run() {
        try {
            while (true) {
                byte[] bytes = new byte[1024];
                int read = socket.getInputStream().read(bytes);
                if (read == -1) {
                    break;
                }
                String command = new String(bytes, 0, read);
                onReceive(command);
            }
        } catch (Exception e) {
            if (e.getMessage().equals("Connection reset")) {
                System.out.println("Connection closed");
            } else {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        }
    }

    public void onReceive(String command) {
        DevelopmentHelper.commands.add(command);
    }
}
