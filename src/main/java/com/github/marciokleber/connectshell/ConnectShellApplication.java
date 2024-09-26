package com.github.marciokleber.connectshell;

import com.github.marciokleber.connectshell.socket.SocketClient;
import com.github.marciokleber.connectshell.socket.SocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ConnectShellApplication {

    public static void main(String[] args) throws IOException {
        System.out.println("Aplicação iniciada com sucesso.");
        SpringApplication.run(ConnectShellApplication.class, args);

        new Thread(() -> {
            SocketServer socketServer = new SocketServer(3000);  // Porta do servidor
            socketServer.start();
        }).start();

        SocketClient socketClient = new SocketClient();
        socketClient.connectToServer();
    }

}
