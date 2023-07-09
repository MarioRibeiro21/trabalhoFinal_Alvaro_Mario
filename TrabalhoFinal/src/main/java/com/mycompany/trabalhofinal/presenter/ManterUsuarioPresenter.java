/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.presenter;

import com.mycompany.trabalhofinal.DAO.implement.UsuarioDAO;
import com.mycompany.trabalhofinal.model.Usuario;
import com.mycompany.trabalhofinal.observer.IObservable;
import com.mycompany.trabalhofinal.observer.IObserver;
import com.mycompany.trabalhofinal.presenter.state.ManterUsuarioCadastroState;
import com.mycompany.trabalhofinal.presenter.state.ManterUsuarioEdicaoState;
import com.mycompany.trabalhofinal.presenter.state.ManterUsuarioState;
import com.mycompany.trabalhofinal.view.ManterUsuarioView;
import com.pss.senha.validacao.ValidadorSenha;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class ManterUsuarioPresenter implements IObservable {

	private ManterUsuarioView cadastroView;
	
        private final List<IObserver> observers;
        private ManterUsuarioState state;

	public ManterUsuarioPresenter( JDesktopPane desktop, Usuario usuario, boolean first, boolean admin ) {
            observers = new ArrayList<>();
            init( desktop, usuario, first, admin );
	}

    public ManterUsuarioView getCadastroView() {
        return cadastroView;
    }
        
	public void init( JDesktopPane desktop, Usuario usuario, boolean first, boolean admin ) {

		cadastroView = new ManterUsuarioView();
		desktop.add( cadastroView );
                
                cadastroView.setLocation((desktop.getWidth() - cadastroView.getWidth()) / 2, (desktop.getHeight() - cadastroView.getHeight()) / 2);

		if( usuario != null ) {

			cadastroView.getjTxtFNome().setText( usuario.getNome() );
			cadastroView.getjTxtFEmail().setText( usuario.getEmail() );
			cadastroView.getjTxtFUsername().setText( usuario.getLogin() );
                        cadastroView.getjPassFSenha().setText(usuario.getSenha());
			cadastroView.getjRBtnAdmin().setSelected( usuario.isAdimin() );

		}

		if( first ) {
			cadastroView.getjRBtnAdmin().setSelected( true );
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

	public void incluir() {
            new ManterUsuarioCadastroState(this);

	}

	public void editar( Usuario usuario ) {

		new ManterUsuarioEdicaoState(this, usuario);
        }

	public void cancelar() {
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