/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.presenter;

import com.mycompany.trabalhofinal.DAO.implement.NotificacaoDAO;
import com.mycompany.trabalhofinal.DAO.implement.UsuarioDAO;
import com.mycompany.trabalhofinal.command.ComandoVisualizar;
import com.mycompany.trabalhofinal.log.AdapterExportJson;
import com.mycompany.trabalhofinal.log.IAdapterExport;
import com.mycompany.trabalhofinal.model.Notificacao;
import com.mycompany.trabalhofinal.model.Usuario;
import com.mycompany.trabalhofinal.presenter.state.ManterNotificacaoCadastroState;
import com.mycompany.trabalhofinal.presenter.state.ManterNotificacaoState;
import com.mycompany.trabalhofinal.presenter.state.ManterNotificacaoVisualizacaoState;
import com.mycompany.trabalhofinal.presenter.state.ManterUsuarioVisualizacaoState;
import com.mycompany.trabalhofinal.view.NotificacaoView;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class NotificacaoPresenter {

    private NotificacaoView view;
    private NotificacaoDAO dao;
    private List<Notificacao> notificacoes;
    private Usuario usuarioLogado;
    private IAdapterExport adapter;

    public NotificacaoPresenter(JDesktopPane desktop, Usuario usuario) throws IOException, Exception {
        this.usuarioLogado=usuario;
        init(desktop);
    }

    private void init(JDesktopPane desktop) throws Exception {
        view = new NotificacaoView();
        dao = new NotificacaoDAO();

        desktop.add(view);
        view.setVisible(true);

        buscarNotificacoes(usuarioLogado.getId());
        
        if(!usuarioLogado.isAdimin()){
            view.getjBnovaNotificacao().setEnabled(false);
        }

        view.getjBvisualizarNotificacao().addActionListener((e) -> {
            try {
                visualizar(desktop);
            } catch (Exception ex) {
                Logger.getLogger(BuscarUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        view.getjBnovaNotificacao().addActionListener((e) -> {
            try {
                criarMensagem(desktop);
            } catch (Exception ex) {
                Logger.getLogger(BuscarUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    private void visualizar(JDesktopPane desktop) throws Exception {

        var linha = view.getjTnotificacoes().getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(view, "É necessário selecionar uma linha primeiro");
        } else {
            var id = Integer.valueOf(view.getjTnotificacoes().getValueAt(linha, 0).toString());
            var notificacao = dao.getById(id);
            var manter = new ManterNotificacaoPresenter(desktop, notificacao, null,usuarioLogado);
            manter.setEstado(new ManterNotificacaoVisualizacaoState(manter, notificacao));
            notificacao.setComando(new ComandoVisualizar(notificacao));
            notificacao.executarComando();
            manter.init(desktop, notificacao);
            adapter = new AdapterExportJson();
            adapter.escrever(usuarioLogado, "VISUALIZACAO_MENSAGEM", notificacao.getUsuario().getNome());
        }
    }

    private void criarMensagem(JDesktopPane desktop) throws Exception {
        var manter = new ManterNotificacaoPresenter(desktop, null, null,usuarioLogado);
        manter.setEstado(new ManterNotificacaoCadastroState(manter));
        manter.init(desktop, null);
    }

    private void setNotificacoes(List<Notificacao> notificacoes) throws Exception {
        if (notificacoes == null) {
            throw new RuntimeException("Lista de usuários nula.");
        }
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        for(Notificacao n: notificacoes){
            n.setUsuario(usuarioDAO.getById(n.getUsuario().getId()));
        }
        this.notificacoes = notificacoes;
    }

    private void buscarNotificacoes(int idUsuarioLogado) throws Exception {
        setNotificacoes(dao.getAllByIdUsuario(idUsuarioLogado));

        loadTable();
    }

    private void loadTable() throws Exception {

        view.getjTnotificacoes().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        var tabela = new DefaultTableModel(new Object[][]{}, new String[]{"Id", "Nome", "Data de Envio", "Status"}) {

            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };

        tabela.setNumRows(0);

        for (Notificacao n : notificacoes) {
            var status = n.isVisualizada() ? "Visualizada" : "Não visualizada";

            tabela.addRow(new Object[]{n.getId(), n.getUsuario().getNome(), n.getData(), status});
        }

        view.getjTnotificacoes().setModel(tabela);
    }
}
