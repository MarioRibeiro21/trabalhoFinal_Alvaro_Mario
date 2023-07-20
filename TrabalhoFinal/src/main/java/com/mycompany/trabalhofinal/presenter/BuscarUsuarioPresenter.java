package com.mycompany.trabalhofinal.presenter;

import com.mycompany.trabalhofinal.DAO.implement.NotificacaoDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.mycompany.trabalhofinal.DAO.implement.UsuarioDAO;
import com.mycompany.trabalhofinal.log.AdapterExportCsv;
import com.mycompany.trabalhofinal.log.AdapterExportJson;
import com.mycompany.trabalhofinal.log.IAdapterExport;
import com.mycompany.trabalhofinal.model.Usuario;
import com.mycompany.trabalhofinal.presenter.state.ManterUsuarioVisualizacaoState;
import com.mycompany.trabalhofinal.view.BuscarUsuarioView;
import javax.swing.ButtonGroup;

public class BuscarUsuarioPresenter {

    private BuscarUsuarioView buscarUsuarioView;
    private UsuarioDAO usuarioDAO;
    private List<Usuario> usuarios;
    private Usuario usuarioLogado;
    private IAdapterExport adapter;
    private NotificacaoDAO notificacaoDAO;

    public BuscarUsuarioPresenter(JDesktopPane desktop, Usuario usuario, ButtonGroup log) throws IOException, Exception {
        this.usuarioLogado = usuario;
        init(desktop, log);
    }

    private void init(JDesktopPane desktop, ButtonGroup log) throws Exception {

        buscarUsuarioView = new BuscarUsuarioView();
        usuarioDAO = new UsuarioDAO();
        desktop.add(buscarUsuarioView);
        buscarUsuarioView.setVisible(true);

        buscarUsuarioView.getjBtnBuscar().addActionListener((e) -> {
            try {
                buscar();
            } catch (Exception ex) {
                Logger.getLogger(BuscarUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        buscarUsuarioView.getjBtnNovo().addActionListener((e) -> {
            try {
                cadastrar(desktop, log);
            } catch (IOException ex) {
                Logger.getLogger(BuscarUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });

        buscarUsuarioView.getjBtnVisualizar().addActionListener((e) -> {
            try {
                visualizar(desktop, log);
            } catch (Exception ex) {
                Logger.getLogger(BuscarUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        buscarUsuarioView.getjEditar2().addActionListener((e) -> {
            try {
                editar(desktop, log);
                
            } catch (Exception ex) {
                Logger.getLogger(BuscarUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        buscarUsuarioView.getjExcluir().addActionListener((e) ->{
             try {
                excluir(log);
            } catch (Exception ex) {
                Logger.getLogger(BuscarUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } );

        buscarUsuarioView.getjBtnFechar().addActionListener((e) -> {
            fechar();
        });
        

    }

    private void buscar() throws Exception {

        var substr = buscarUsuarioView.getjTextField().getText();

        if (substr.isBlank() || substr.isEmpty()) {
            setUsuarios(usuarioDAO.findAll());
        } else {

            var query = "";
            switch (buscarUsuarioView.getCmbxCampo().getSelectedIndex()) {
                case 0:
                    query = "SELECT * FROM Usuario WHERE CAST(id AS VARCHAR) like CAST(? AS VARCHAR) ORDER BY nome";
                    break;
                case 1:
                    query = "SELECT * FROM Usuario WHERE nome LIKE ? ORDER BY nome";
                    break;
                case 2:
                    query = "SELECT * FROM Usuario WHERE login LIKE ? ORDER BY nome";
                    break;
                default:
                    throw new RuntimeException("Campo de pesquisa inválido!");
            }

            setUsuarios(usuarioDAO.search(query, substr));
        }
        loadTable();
    }

    private void cadastrar(JDesktopPane desktop, ButtonGroup log) throws IOException {
        new ManterUsuarioPresenter(desktop, null, false, this.usuarioLogado, log);
    }

    private void visualizar(JDesktopPane desktop, ButtonGroup log) throws Exception {

        var linha = buscarUsuarioView.getjTable().getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(buscarUsuarioView, "É necessário selecionar uma linha primeiro");
        } else {
            var id = Integer.valueOf(buscarUsuarioView.getjTable().getValueAt(linha, 0).toString());
            var usuario = usuarioDAO.getById(id);
            new ManterUsuarioVisualizacaoState(new ManterUsuarioPresenter(desktop, usuario, false, this.usuarioLogado, log));
            
            switch(log.getSelection().getActionCommand()){
                case "CSV":
                    adapter = new AdapterExportCsv();
                    break;

                case "JSON":
                    adapter = new AdapterExportJson();
                    break;
            }
            adapter.escrever(this.usuarioLogado, "VISUALIZACAO", usuario.getNome());
        }
    }

    private void editar(JDesktopPane desktop, ButtonGroup log) throws Exception {
        var linha = buscarUsuarioView.getjTable().getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(buscarUsuarioView, "É necessário selecionar uma linha primeiro");
        } else {
            var id = Integer.valueOf(buscarUsuarioView.getjTable().getValueAt(linha, 0).toString());
            var usuario = usuarioDAO.getById(id);
            new ManterUsuarioPresenter(desktop, usuario, false, this.usuarioLogado, log);
        }
        buscar();
    }

    private void excluir(ButtonGroup log) throws IOException, Exception{
         var linha = buscarUsuarioView.getjTable().getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(buscarUsuarioView, "É necessário selecionar uma linha primeiro");
        } else {
            var id = Integer.valueOf(buscarUsuarioView.getjTable().getValueAt(linha, 0).toString());
            if(id == usuarioLogado.getId()){
                 JOptionPane.showMessageDialog(buscarUsuarioView, "Você não pode se auto deletar");
                 return;
            }
            var nome = usuarioDAO.getById(id).getNome();
            usuarioDAO.delete(id);
            
            switch(log.getSelection().getActionCommand()){
                case "CSV":
                    adapter = new AdapterExportCsv();
                    break;

                case "JSON":
                    adapter = new AdapterExportJson();
                    break;
            }            
            adapter.escrever(this.usuarioLogado, "EXCLUSAO", nome);
            JOptionPane.showMessageDialog( buscarUsuarioView, "Usuário excluído com sucesso!" );
             buscar();
        }
    }
    
    
    private void fechar() {
        buscarUsuarioView.dispose();
    }

    private void loadTable() throws Exception {

        buscarUsuarioView.getjTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        notificacaoDAO = new NotificacaoDAO();
                
        var tabela = new DefaultTableModel(new Object[][]{}, new String[]{"Id", "Nome", "Nome de Usuário", "Data inserção","mensagens Visualizadas", "Mensagens em aberto", "Tipo"}) {

            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };

        tabela.setNumRows(0);

        for (Usuario u : usuarios) {
            var tipo = u.isAdimin() ? "Administrador" : "Usuário";
            
            var visualizadas = notificacaoDAO.countNotificacoesVisualizadasByUsuario(u.getId());
            var pendentes = notificacaoDAO.countNotificacoesPendenteByUsuario(u.getId());
            
            tabela.addRow(new Object[]{u.getId(), u.getNome(), u.getLogin(), u.getData(), visualizadas, pendentes, tipo});
        }

        buscarUsuarioView.getjTable().setModel(tabela);
    }

    private void setUsuarios(List<Usuario> usuarios) {
        if (usuarios == null) {
            throw new RuntimeException("Lista de usuários nula.");
        } else {
            this.usuarios = usuarios;
        }
    }

}
