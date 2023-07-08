package com.mycompany.trabalhofinal.state;

import com.mycompany.trabalhofinal.model.Notificacao;

/**
 * @author Mario Ribeiro e Álvaro Moret
 */

public abstract class NotificacaoState {
    protected Notificacao notificacao;
    
    public NotificacaoState(Notificacao notificacao){
        this.notificacao = notificacao;
    }
    
    public void visualizar(){
        throw new RuntimeException("Notificação já visualizada");
    }
}
