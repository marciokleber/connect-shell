package com.github.marciokleber.connectshell.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        try (InputStream input = clientSocket.getInputStream();
             OutputStream output = clientSocket.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead = input.read(buffer);

            String receivedMessage = new String(buffer, 0, bytesRead);
            System.out.println("Mensagem recebida: " + receivedMessage);

            String responseMessage = "Servidor recebeu: " + receivedMessage;
            output.write(responseMessage.getBytes());

        } catch (IOException e) {
            System.out.println("Erro ao lidar com o cliente: " + e.getMessage());
        }
    }
}
