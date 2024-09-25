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
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String message;
            // Mantém a conexão aberta enquanto o cliente envia mensagens
            while ((message = reader.readLine()) != null) {
                System.out.println("Pong: " + message);
            }
            // O loop será quebrado quando o cliente fechar a conexão
            System.out.println("Pong desconectado.");
        } catch (IOException e) {
            System.out.println("Erro ao processar a mensagem: " + e.getMessage());
        } finally {
            try {
                // Certifica-se de que o socket do cliente seja fechado após a desconexão
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar a conexão do cliente: " + e.getMessage());
            }
        }
    }
}
