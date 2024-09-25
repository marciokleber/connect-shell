package com.github.marciokleber.connectshell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class Input {

    @ShellMethod("Processa a mensagem recebida diretamente.")
    public String m(@ShellOption(defaultValue = "") String mensagem) {
        if (mensagem.isBlank()) {
            return "Por favor, insira uma mensagem.";
        }

        // Processa a mensagem como você desejar
        return "Você enviou: " + mensagem;
    }
}
