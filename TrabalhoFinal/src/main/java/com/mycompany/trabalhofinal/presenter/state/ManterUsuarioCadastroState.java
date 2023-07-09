/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.presenter.state;

import com.mycompany.trabalhofinal.DAO.implement.UsuarioDAO;
import com.mycompany.trabalhofinal.model.Usuario;
import com.mycompany.trabalhofinal.presenter.ManterUsuarioPresenter;
import com.pss.senha.validacao.ValidadorSenha;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class ManterUsuarioCadastroState extends ManterUsuarioState{
    
    private ValidadorSenha validadorSenha = new ValidadorSenha();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public ManterUsuarioCadastroState(ManterUsuarioPresenter manterUsuario) {
        super(manterUsuario);
        cadastro();
    }
    
    private void cadastro(){
                

                var cadastroView = manterUsuario.getCadastroView();
		var nome = cadastroView.getjTxtFNome().getText();
		var email = cadastroView.getjTxtFEmail().getText();
		var nomeUsuario = cadastroView.getjTxtFUsername().getText();
		var senha = String.valueOf( cadastroView.getjPassFSenha().getPassword() );
		var admin = cadastroView.getjRBtnAdmin().isSelected();            
                
                var resultadosValidacao = validadorSenha.validar(senha);
                
                if (nome.isBlank() || nome.isEmpty()) {
                    JOptionPane.showMessageDialog(cadastroView, "Insira um nome de usuário");
                } else if (nomeUsuario.isBlank() || nomeUsuario.isEmpty()) {
                    JOptionPane.showMessageDialog(cadastroView, "Insira um nome de usuário");
                } else if (email.isBlank() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(cadastroView, "Insira um e-mail");
                } else if (!resultadosValidacao.isEmpty())
                    JOptionPane.showMessageDialog(cadastroView, resultadosValidacao);
                else {
                    try {
                        
                        if( admin )
                            usuarioDAO.insert( new Usuario( nome, email, nomeUsuario, senha, true ) );
                        else
                            usuarioDAO.insert( new Usuario( nome, email, nomeUsuario, senha, false ) );
                        
                        JOptionPane.showMessageDialog( cadastroView, "Cadastro realizado com sucesso!" );
			cadastroView.dispose();
                    } catch ( Exception e ) {
                        JOptionPane.showMessageDialog( cadastroView, e.getMessage() );
                    }
                }
    }
}
