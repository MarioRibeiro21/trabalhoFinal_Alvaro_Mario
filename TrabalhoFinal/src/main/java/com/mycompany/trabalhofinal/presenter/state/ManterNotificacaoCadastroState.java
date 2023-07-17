/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.presenter.state;

import com.mycompany.trabalhofinal.DAO.implement.UsuarioDAO;
import com.mycompany.trabalhofinal.model.Notificacao;
import com.mycompany.trabalhofinal.presenter.ManterNotificacaoPresenter;

/**
 *
 * @author Mario e Álvaro
 */
public class ManterNotificacaoCadastroState extends ManterNotificacaoState{
     
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public ManterNotificacaoCadastroState(ManterNotificacaoPresenter manterNotificacao) throws Exception {        
        super(manterNotificacao);
        cadastro();
    }
    
    public void cadastro(){
        var view = manterNotificacao.getView();
        
        
        view.setTitle("Enviar Notificação");
        
        view.getjLabel5().setText("Enviar Notificação");
        view.getjLabel1().setText(" Escolha o destinatário: ");
        view.getjLabel2().setText(" digite sua mensagem: ");
        
    }
    
    public void visualizar(Notificacao notificacao) {
    }
    
}
