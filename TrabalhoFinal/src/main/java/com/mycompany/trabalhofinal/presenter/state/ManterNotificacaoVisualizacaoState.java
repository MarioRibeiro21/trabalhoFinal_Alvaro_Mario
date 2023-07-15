/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.presenter.state;

import com.mycompany.trabalhofinal.DAO.implement.NotificacaoDAO;
import com.mycompany.trabalhofinal.DAO.implement.UsuarioDAO;
import com.mycompany.trabalhofinal.model.Notificacao;
import com.mycompany.trabalhofinal.presenter.ManterNotificacaoPresenter;

/**
 *
 * @author Mario
 */
public class ManterNotificacaoVisualizacaoState extends ManterNotificacaoState{
    
    public ManterNotificacaoVisualizacaoState(ManterNotificacaoPresenter manterNotificacao,Notificacao notificacao) {
        super(manterNotificacao);
        visualizar(notificacao);
    }
    
    public void visualizar(Notificacao notificacao){
        var view = manterNotificacao.getView();
        
        view.getjBtenviar().setEnabled(false);
        view.getjComboBox1().setEnabled(false);
        view.getjTmensagem().setEditable(false);
        view.setTitle("Tela de Visualização");
        view.getjLabel5().setText("Visuallzação");
        view.getjLabel1().setText(" Remetente: ");
        view.getjLabel2().setText(" Mensagem: ");
        view.getjComboBox1().setSelectedItem(manterNotificacao.getNotificacao().getUsuario());
        view.getjTmensagem().setText(manterNotificacao.getNotificacao().getMensagem());
    }

    @Override
    public void cadastro() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
