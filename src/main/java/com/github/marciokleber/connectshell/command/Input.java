package com.github.marciokleber.connectshell.command;

import com.github.marciokleber.connectshell.socket.SocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class Input {

    @Autowired
    private SocketClient socketClient;

    @ShellMethod("Processa a mensagem recebida diretamente.")
    public void m(@ShellOption(defaultValue = "") String mensagem) {
        socketClient.send(mensagem);
    }
}
