# Development Helper

this plugin host a server on the minecraft server
the default port is 36676

a small java class like:

```java
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
```

can be used to send commands to the server, in this case it reloads the server

**Warning: This is a development plugin if anyone knows about it being on the server they effectively have console
permissions**

1.21 incoming