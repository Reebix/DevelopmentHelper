import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        // The command to send to the server
        String command = "reload";

        try (Socket socket = new Socket("localhost", 36676)) {
            socket.getOutputStream().write(command.getBytes());
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}