/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.presenter;

import com.mycompany.trabalhofinal.DAO.implement.UsuarioDAO;
import com.mycompany.trabalhofinal.model.Usuario;
import com.mycompany.trabalhofinal.observer.IObservable;
import com.mycompany.trabalhofinal.observer.IObserver;
import com.mycompany.trabalhofinal.view.CadastroView;
import com.pss.senha.validacao.ValidadorSenha;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class CadastroPresenter implements IObservable {

	private CadastroView cadastroView;
	private UsuarioDAO usuarioDAO;
        private ValidadorSenha validadorSenha = new ValidadorSenha();
        private final List<IObserver> observers;

	public CadastroPresenter( JDesktopPane desktop, Usuario usuario, boolean first, boolean admin ) {
            observers = new ArrayList<>();
            init( desktop, usuario, first, admin );
	}

	private void init( JDesktopPane desktop, Usuario usuario, boolean first, boolean admin ) {

		cadastroView = new CadastroView();
		usuarioDAO = new UsuarioDAO();
		desktop.add( cadastroView );
                
                cadastroView.setLocation((desktop.getWidth() - cadastroView.getWidth()) / 2, (desktop.getHeight() - cadastroView.getHeight()) / 2);

		if( usuario != null ) {

			cadastroView.getjTxtFNome().setText( usuario.getNome() );
			cadastroView.getjTxtFEmail().setText( usuario.getEmail() );
			cadastroView.getjTxtFUsername().setText( usuario.getLogin() );
			cadastroView.getjRBtnAdmin().setSelected( usuario.isAdimin() );

		}

		if( first ) {
			cadastroView.getjRBtnAdmin().setSelected( true );
			cadastroView.getjRBtnAdmin().setEnabled( false );
		} else if( !admin ) {
			cadastroView.getjRBtnAdmin().setSelected( false );
			cadastroView.getjRBtnAdmin().setEnabled( false );
		}

		cadastroView.setVisible( true );

		cadastroView.getjBtnCadastrar().addActionListener( ( e ) -> {
			if( usuario == null )
				incluir();
			else
				editar( usuario );
		} );

		cadastroView.getjBtnCancelar().addActionListener( ( e ) -> {
			cancelar();
		} );
	}

	private void incluir() {

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

	private void editar( Usuario usuario ) {

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
                } else if (!resultadosValidacao.isEmpty()) {
                    JOptionPane.showMessageDialog(cadastroView, resultadosValidacao);
                } else {
                    try {
                        if( admin )
                            usuarioDAO.edit( new Usuario( nome, email, nomeUsuario, senha, true ) );
                        else
                            usuarioDAO.edit( new Usuario( nome, email, nomeUsuario, senha, false ) );
                        
                        JOptionPane.showMessageDialog( cadastroView, "Usuário editado com sucesso!" );
			cadastroView.dispose();
                    } catch ( Exception e ) {
                        JOptionPane.showMessageDialog( cadastroView, e.getMessage() );
                    }
                }
        }

	private void cancelar() {
		cadastroView.dispose();
	}

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Usuario usuario) {
        observers.forEach(u -> {
            u.update(usuario);
        });
    }

}