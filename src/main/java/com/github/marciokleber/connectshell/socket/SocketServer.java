package com.github.marciokleber.connectshell.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private int port;

    public SocketServer(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor de socket iniciado na porta " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor de socket: " + e.getMessage());
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);

            System.out.println("Mensagem recebida: " + reader.readLine());
        } catch (IOException e) {
            System.out.println("Erro ao processar a mensagem: " + e.getMessage());
        }
    }
}
