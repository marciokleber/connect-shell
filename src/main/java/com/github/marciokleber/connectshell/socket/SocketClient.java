package com.github.marciokleber.connectshell.socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    private Socket socket;
    private PrintStream out;

    public SocketClient() throws IOException {}

    public void connectToServer() {

        Scanner scanner = new Scanner(System.in);

        while (socket == null || socket.isClosed()) {
            try {
                System.out.println("Conectando...");
                socket = new Socket("localhost", 3000);
                out = new PrintStream(socket.getOutputStream());
                System.out.println("conectado! - Digite 'exit()' para encerrar conexão! ");

                startSendingMessages(scanner);

            } catch (IOException e) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void startSendingMessages(Scanner scanner) {
        while (!socket.isClosed()) {
            String message = scanner.nextLine();

            if (message.equalsIgnoreCase("exit()")) {
                closeConnection();
                break;
            }

            if (out != null) {
                out.println(message);
            } else {
                System.out.println("Não conectado ao servidor. Mensagem não enviada.");
            }
        }
    }
    public void closeConnection() {
        try {
            if (out != null) {
                out.close();
                System.exit(0);
            }
            if (socket != null && !socket.isClosed()) {
                socket.close(); // Fecha o socket
            }
            System.out.println("Conexão encerrada.");
        } catch (IOException e) {
            System.out.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}