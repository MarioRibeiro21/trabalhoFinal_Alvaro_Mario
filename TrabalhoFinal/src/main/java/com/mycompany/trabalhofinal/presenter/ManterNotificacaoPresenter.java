/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.presenter;

import com.mycompany.trabalhofinal.DAO.implement.NotificacaoDAO;
import com.mycompany.trabalhofinal.DAO.implement.UsuarioDAO;
import com.mycompany.trabalhofinal.log.AdapterExportCsv;
import com.mycompany.trabalhofinal.log.AdapterExportJson;
import com.mycompany.trabalhofinal.log.IAdapterExport;
import com.mycompany.trabalhofinal.model.Notificacao;
import com.mycompany.trabalhofinal.model.Usuario;
import com.mycompany.trabalhofinal.observer.IObservable;
import com.mycompany.trabalhofinal.observer.IObserver;
import com.mycompany.trabalhofinal.presenter.state.ManterNotificacaoState;
import com.mycompany.trabalhofinal.view.ManterNotificacaoView;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class ManterNotificacaoPresenter implements IObservable {

    private ManterNotificacaoView view;
    private NotificacaoDAO dao;
    private Notificacao notificacao;
    private ManterNotificacaoState estado;
    private Usuario usuarioLogado;

    public ManterNotificacaoPresenter(JDesktopPane desktop, Notificacao notificacao, ManterNotificacaoState estado, Usuario usuarioLogado, ButtonGroup log) {
        this.notificacao = notificacao;
        this.usuarioLogado = usuarioLogado;
        view = new ManterNotificacaoView();
    }

    public void init(JDesktopPane desktop, Notificacao notificacao, ButtonGroup log) throws Exception {

        dao = new NotificacaoDAO();

        desktop.add(view);

        view.setLocation((desktop.getWidth() - view.getWidth()) / 2, (desktop.getHeight() - view.getHeight()) / 2);

        if (notificacao != null) {

            view.getjComboBox1().setSelectedItem(notificacao.getUsuario());
            view.getjTmensagem().setText(notificacao.getMensagem());

        }

        view.setVisible(true);

        view.getjBtenviar().addActionListener((e) -> {
            try {
                enviar(log);
            } catch (Exception ex) {
                Logger.getLogger(ManterNotificacaoPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        view.getjBtnCancelar().addActionListener((e) -> {
            cancelar();
        });
    }

    private void enviar(ButtonGroup log) throws Exception {
        var mensagem = view.getjTmensagem().getText();
        Usuario usuario = (Usuario) view.getjComboBox1().getSelectedItem();

        if (mensagem == null || mensagem.isEmpty()) {
            throw new RuntimeException("Mensagem vazia!");
        }
        if (usuario == null) {
            throw new RuntimeException("Usuário não selecionado!");
        }

        dao.insert(new Notificacao(usuario, mensagem));

        IAdapterExport adapter = new AdapterExportJson();
        switch(log.getSelection().getActionCommand()){
            case "CSV":
                adapter = new AdapterExportCsv();
                break;
            
            case "JSON":
                adapter = new AdapterExportJson();
                break;
        }        
        adapter.escrever(this.usuarioLogado, "ENVIO_MENSAGEM", usuario.getNome());

        JOptionPane.showMessageDialog(view, "mensagem enviada com sucesso!");

    }

    private void cancelar() {
        view.dispose();
    }

    public ManterNotificacaoView getView() {
        return view;
    }

    public Notificacao getNotificacao() {
        return notificacao;
    }

    public ManterNotificacaoState getEstado() {
        return estado;
    }

    public void setEstado(ManterNotificacaoState estado) {
        this.estado = estado;
    }

    @Override
    public void addObserver(IObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeObserver(IObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void notifyObservers(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
