package com.mycompany.trabalhofinal.command;

import com.mycompany.trabalhofinal.model.Notificacao;
import com.mycompany.trabalhofinal.state.Lido;



public class LerCommand implements INotificacaoCommand {

    Notificacao notificacao;
    
    public LerCommand(Notificacao notificacao){
        this.notificacao.setEstado(new Lido(notificacao));
    }
    
    @Override
    public void execute() {
        //ir pra presenter que manda abrir a mensagem aqui
    }

}
