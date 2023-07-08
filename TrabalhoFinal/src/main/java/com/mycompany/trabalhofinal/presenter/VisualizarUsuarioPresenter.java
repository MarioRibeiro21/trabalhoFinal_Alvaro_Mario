package com.mycompany.trabalhofinal.presenter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

import com.mycompany.trabalhofinal.DAO.implement.UsuarioDAO;
import com.mycompany.trabalhofinal.model.Usuario;
import com.mycompany.trabalhofinal.view.VisualizarUsuarioView;

public class VisualizarUsuarioPresenter {

	private VisualizarUsuarioView visualizarView;
	private UsuarioDAO usuarioDAO;

	public VisualizarUsuarioPresenter( JDesktopPane desktop, Usuario usuario ) throws IOException {
		init( desktop, usuario );
	}

	private void init( JDesktopPane desktop, Usuario usuario ) {

		visualizarView = new VisualizarUsuarioView();
		usuarioDAO = new UsuarioDAO();
		desktop.add( visualizarView );

		visualizarView.getLblNome().setText( usuario.getNome() );
		visualizarView.getLblUsername().setText( usuario.getLogin() );
		visualizarView.getLblEmail().setText( usuario.getEmail() );
		visualizarView.getjRBtnAdmin().setSelected( usuario.isAdimin() );
		visualizarView.getjRBtnAdmin().setEnabled( false );

		visualizarView.setVisible( true );

		visualizarView.getjBtnEditar().addActionListener( ( e ) -> {
			try {
				editar( desktop, usuario );
			} catch ( IOException ex ) {
				Logger.getLogger( VisualizarUsuarioPresenter.class.getName() ).log( Level.SEVERE, null, ex );
			}
		} );

		visualizarView.getjBtnExcluir().addActionListener( ( e ) -> {
			excluir( usuario );
		} );

		visualizarView.getjBtnCancelar().addActionListener( ( e ) -> {
			cancelar();
		} );
	}

	private void editar( JDesktopPane desktop, Usuario usuario ) throws IOException {
		new CadastroPresenter(desktop, usuario, false, usuario.isAdimin());

	}

	private void excluir( Usuario usuario ) {

		// (Verificar admin)
		String[] options = { "Sim", "Não" };
		int retorno = JOptionPane.showOptionDialog( visualizarView, "Tem certeza que deseja remover o usuário " + usuario.getLogin() + "?", "Remover usuário", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, null, options, options[1] );
		if( retorno == 0 ) {
			try {
				usuarioDAO.delete( usuario.getId() );
				JOptionPane.showMessageDialog( visualizarView, "Usuário excluido com sucesso!" );
			} catch ( Exception e ) {
				JOptionPane.showMessageDialog( visualizarView, e.getMessage() );
			}

			visualizarView.dispose();
		}

	}

	private void cancelar() {
		visualizarView.dispose();
	}

}
