package com.mycompany.trabalhofinal.state;

import com.mycompany.trabalhofinal.model.Notificacao;

/**
 * @author Mario Ribeiro e Álvaro Moret
 */

public class NaoLido extends NotificacaoState{
    
    public NaoLido(Notificacao notificacao){
        super(notificacao);
    }
    
    @Override
    public void visualizar(){
        notificacao.setEstado(new Lido(notificacao));
    }

}
