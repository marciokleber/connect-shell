package com.github.marciokleber.connectshell.socket;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

@Component
public class SocketClient {
    private Socket socket;
    private PrintStream out;

    public SocketClient() throws IOException {
        connectToServer();
    }

    private void connectToServer() {
        while (socket == null || socket.isClosed()) {
            try {
                System.out.println("Connecting...");
                socket = new Socket("localhost", 6000);
                out = new PrintStream(socket.getOutputStream());
                System.out.println("Connected!");
            } catch (IOException e) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void send(String message) {
        if (out != null) {
            out.println(message);
        } else {
            System.out.println("Não conectado ao servidor. Mensagem não enviada.");
        }
    }
}
