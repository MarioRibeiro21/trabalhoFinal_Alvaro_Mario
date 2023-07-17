/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.presenter;


import com.mycompany.trabalhofinal.model.Usuario;
import com.mycompany.trabalhofinal.observer.IObserver;
import com.mycompany.trabalhofinal.view.PrincipalView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Mario
 */
public class PrincipalPresenter implements IObserver {

    private PrincipalView principalView;
    private Usuario usuario = null;
    
    public PrincipalPresenter() throws IOException {
        init();
    }
    
    private void init(){
        
        principalView = new PrincipalView();
        principalView.setVisible(true);
        
        stateOFF();
        
        if(this.usuario == null)
            new LoginPresenter(principalView.getjDesktopPane(), principalView.getButtonGroup1()).addObserver(this);
        
        
        principalView.getjMenuCadastrar().addActionListener((e) -> {
            try {
                cadastrar();
            } catch (IOException ex) {
                Logger.getLogger(PrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
                
        principalView.getjMenuBuscar().addActionListener((e) -> {
            try {
                buscar();
            } catch (IOException ex) {
                Logger.getLogger(PrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
        
        principalView.getjMenuAlterar1().addActionListener((e) ->{
         try {
                alterarDados();
            } catch (IOException ex) {
                Logger.getLogger(PrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }   
        });
        
        principalView.getBtnNotificacao().addActionListener((e) ->{
            try {
                abrirNotificacoes();
            } catch (IOException ex) {
                Logger.getLogger(PrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        
    }
    
    private void cadastrar() throws IOException {
        new ManterUsuarioPresenter(principalView.getjDesktopPane(), null, false, this.usuario, principalView.getButtonGroup1());   
    }
    
    private void buscar() throws IOException, Exception {  
        new BuscarUsuarioPresenter(principalView.getjDesktopPane(), this.usuario, principalView.getButtonGroup1());       
    }
    
    private void alterarDados()throws IOException{
          new ManterUsuarioPresenter(principalView.getjDesktopPane() , usuario, false, this.usuario, principalView.getButtonGroup1());
    } 
    
    
    private void stateAdmON() {
        
        principalView.getJMenuBar().setVisible(true);
        principalView.getBtnNotificacao().setVisible(true);
        principalView.getLblUsuarioLogado().setVisible(true);
        
    }
    
    private void stateUsuarioON() {
        
        principalView.getJMenuBar().setVisible(true);
        principalView.getBtnNotificacao().setVisible(true);
        principalView.getLblUsuarioLogado().setVisible(true);
        principalView.getjMenuUsuario().setEnabled(false);
        
    }
    
    private void stateOFF() {
        
        principalView.getJMenuBar().setVisible(false);
        principalView.getBtnNotificacao().setVisible(false);
        principalView.getLblUsuarioLogado().setVisible(false);
        
    }
    
    private void abrirNotificacoes() throws Exception{
        new NotificacaoPresenter (principalView.getjDesktopPane(), usuario, principalView.getButtonGroup1());
    }

    @Override
    public void update(Usuario usuario) {
        
        if(usuario == null) {
            stateOFF();
        } else {
            this.usuario = usuario;
            if(usuario.isAdimin()) {
                stateAdmON();
                principalView.getLblUsuarioLogado().setText(usuario.getNome() + " - Administrador");
            } else {
                stateUsuarioON();
                principalView.getLblUsuarioLogado().setText(usuario.getNome() + " - Usuário");
            }
        }
        
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}