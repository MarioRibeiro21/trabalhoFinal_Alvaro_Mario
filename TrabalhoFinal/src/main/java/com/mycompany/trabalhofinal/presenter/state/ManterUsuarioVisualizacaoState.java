/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.presenter.state;

import com.mycompany.trabalhofinal.DAO.implement.UsuarioDAO;
import com.mycompany.trabalhofinal.presenter.ManterUsuarioPresenter;
import com.mycompany.trabalhofinal.view.ManterUsuarioView;

/**
 *
 * @author Mario
 */
public class ManterUsuarioVisualizacaoState extends ManterUsuarioState{
    
    private UsuarioDAO usuarioDAO;
    
    public ManterUsuarioVisualizacaoState(ManterUsuarioPresenter manterUsuario) {
        super(manterUsuario);
        
        ManterUsuarioView view = manterUsuario.getCadastroView();
        view.getjBtnCadastrar().setEnabled(false);
        view.getjTxtFUsername().setEditable(false);
        view.getjTxtFNome().setEditable(false);
        view.getjTxtFEmail().setEditable(false);
        view.getjPassFSenha().setEditable(false);
        view.getjRBtnAdmin().setEnabled( false );
        view.setTitle("Tela de Visualização");
        view.getjLabel5().setText("Visuallzação");
        
    }
    
}
