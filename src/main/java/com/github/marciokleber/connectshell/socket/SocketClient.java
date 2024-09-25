package com.github.marciokleber.connectshell.socket;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

@Component
public class SocketClient {
    Socket socket = new Socket("localhost", 3000);
    PrintStream out = new PrintStream(socket.getOutputStream());

    public SocketClient() throws IOException {
    }

    public void send(String message) {
        out.println(message);
    }

}
