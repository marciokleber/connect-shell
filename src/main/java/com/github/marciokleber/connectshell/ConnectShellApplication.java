package com.github.marciokleber.connectshell;

import com.github.marciokleber.connectshell.socket.SocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConnectShellApplication {

    public static void main(String[] args) {
        System.out.println("Aplicação iniciada com sucesso.");

        new Thread(() -> {
            SocketServer socketServer = new SocketServer(6000);  // Porta do servidor
            socketServer.start();
        }).start();

        SpringApplication.run(ConnectShellApplication.class, args);
    }

}
