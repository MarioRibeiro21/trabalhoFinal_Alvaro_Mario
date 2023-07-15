/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.presenter.state;

import com.mycompany.trabalhofinal.model.Notificacao;
import com.mycompany.trabalhofinal.presenter.ManterNotificacaoPresenter;

/**
 *
 * @author Mario
 */
public abstract class ManterNotificacaoState {

    protected ManterNotificacaoPresenter manterNotificacao;

    public ManterNotificacaoState(ManterNotificacaoPresenter manterNotificacao) {
        this.manterNotificacao = manterNotificacao;
    }

    public abstract void cadastro() throws Exception ;

    public abstract void visualizar(Notificacao notificacao) throws Exception;
}
