package com.mycompany.trabalhofinal.presenter;

import com.mycompany.trabalhofinal.DAO.implement.UsuarioDAO;
import com.mycompany.trabalhofinal.model.Usuario;
import com.mycompany.trabalhofinal.observer.IObservable;
import com.mycompany.trabalhofinal.observer.IObserver;
import com.mycompany.trabalhofinal.view.LoginView;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class LoginPresenter implements IObservable {
    
    private LoginView loginView;
    private UsuarioDAO usuarioDAO;
    private final List<IObserver> observers;
    
    

    public LoginPresenter(JDesktopPane desktop) {
        observers = new ArrayList<>();
        init(desktop);
    }
    
    private void init(JDesktopPane desktop){
        
        loginView = new LoginView();
        usuarioDAO = new UsuarioDAO();
        desktop.add(loginView);
        
        loginView.setLocation((desktop.getWidth() - loginView.getWidth()) / 2, (desktop.getHeight() - loginView.getHeight()) / 2);
        
        
        loginView.setVisible(true);


        loginView.getBtnCadastrar().addActionListener((e) -> {
            cadastrar(desktop);
        });
        
        loginView.getBtnLogin().addActionListener((e) -> {
            login();
        });
    }
    
    
    private void cadastrar(JDesktopPane desktop) {
        try {
            if (usuarioDAO.findAll().isEmpty())
                new ManterUsuarioPresenter(desktop, null, true, null);
            else
                new ManterUsuarioPresenter(desktop, null, false, null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(loginView, "Erro ao cadastrar: " + e.getMessage());
        }
    }
    
    private void login() {
        
        var username = loginView.getTxtUsername().getText();
        var senha = String.valueOf(loginView.getTxtSenha().getPassword());

        if (username.isBlank() || username.isEmpty()) {
            JOptionPane.showMessageDialog(loginView, "Insira um nome de usuário");
            
        } else if (senha.isBlank() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(loginView, "Insira uma senha.");
            
        } else {
            try {
                var usuario = usuarioDAO.findByLoginAndSenha(username, senha);

                if (usuario == null) {
                    JOptionPane.showMessageDialog(loginView, "Nome de usuário ou senha inválidos. Tente novamente");
                    loginView.getTxtSenha().setText("");
                } else {
                    notifyObservers(usuario);
                    loginView.dispose();
                }
            } catch (Exception e) {

                JOptionPane.showMessageDialog(loginView, "Erro ao logar" + e.getMessage());

                loginView.getTxtSenha().setText("");

            }
        }
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

