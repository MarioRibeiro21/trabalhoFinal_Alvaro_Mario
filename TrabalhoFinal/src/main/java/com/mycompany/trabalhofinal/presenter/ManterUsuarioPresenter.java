/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.presenter;

import com.mycompany.trabalhofinal.log.AdapterExportCsv;
import com.mycompany.trabalhofinal.log.AdapterExportJson;
import com.mycompany.trabalhofinal.log.IAdapterExport;
import com.mycompany.trabalhofinal.model.Usuario;
import com.mycompany.trabalhofinal.observer.IObservable;
import com.mycompany.trabalhofinal.observer.IObserver;
import com.mycompany.trabalhofinal.presenter.state.ManterUsuarioCadastroState;
import com.mycompany.trabalhofinal.presenter.state.ManterUsuarioEdicaoState;

import com.mycompany.trabalhofinal.view.ManterUsuarioView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;

/**
 *
 * @author Mario
 */
public class ManterUsuarioPresenter implements IObservable {

    private ManterUsuarioView cadastroView;

    private final List<IObserver> observers;

    private IAdapterExport adapter;
    private Usuario usuarioLogado;

    public ManterUsuarioPresenter(JDesktopPane desktop, Usuario usuario, boolean first, Usuario usuarioLogado) {
        observers = new ArrayList<>();
        this.usuarioLogado = usuarioLogado;
        init(desktop, usuario, first, usuarioLogado);
    }

    public ManterUsuarioView getCadastroView() {
        return cadastroView;
    }

    public void init(JDesktopPane desktop, Usuario usuario, boolean first, Usuario usuarioLogado) {

        cadastroView = new ManterUsuarioView();
        desktop.add(cadastroView);

        cadastroView.setLocation((desktop.getWidth() - cadastroView.getWidth()) / 2, (desktop.getHeight() - cadastroView.getHeight()) / 2);

        if (usuario != null) {

            cadastroView.getjTxtFNome().setText(usuario.getNome());
            cadastroView.getjTxtFEmail().setText(usuario.getEmail());
            cadastroView.getjTxtFUsername().setText(usuario.getLogin());
            cadastroView.getjPassFSenha().setText(usuario.getSenha());
            cadastroView.getjRBtnAdmin().setSelected(usuario.isAdimin());

            if (!usuarioLogado.isAdimin()) {
                cadastroView.getjRBtnAdmin().setSelected(false);
                cadastroView.getjRBtnAdmin().setEnabled(false);
                cadastroView.getjPassFSenha().setEnabled(false);
                cadastroView.getjTxtFEmail().setEnabled(false);
                cadastroView.getjTxtFUsername().setEnabled(false);
            }
        } else {
            cadastroView.getjRBtnAdmin().setSelected(false);
            cadastroView.getjRBtnAdmin().setEnabled(false);
        }

        if (first) {
            cadastroView.getjRBtnAdmin().setSelected(true);
            cadastroView.getjRBtnAdmin().setEnabled(false);
        }

        cadastroView.setVisible(true);

        cadastroView.getjBtnCadastrar().addActionListener((e) -> {
            if (usuario == null)
				try {
                incluir();
            } catch (IOException ex) {
                Logger.getLogger(ManterUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
            } else
				try {
                editar(usuario);
            } catch (IOException ex) {
                Logger.getLogger(ManterUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        cadastroView.getjBtnCancelar().addActionListener((e) -> {
            cancelar();
        });
    }

    public void incluir() throws IOException {
        new ManterUsuarioCadastroState(this);
        adapter = new AdapterExportJson();
        adapter.escrever(this.usuarioLogado, "INCLUSAO", null);
        adapter = new AdapterExportCsv();
        adapter.escrever(this.usuarioLogado, "INCLUSAO", null);
    }

    public void editar(Usuario usuario) throws IOException {

        new ManterUsuarioEdicaoState(this, usuario);
        adapter = new AdapterExportJson();
        adapter.escrever(this.usuarioLogado, "EDICAO", usuario.getNome());
        adapter = new AdapterExportCsv();
        adapter.escrever(this.usuarioLogado, "EDICAO", usuario.getNome());
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
