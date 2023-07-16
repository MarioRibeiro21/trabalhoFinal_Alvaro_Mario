package com.mycompany.trabalhofinal.DAO.implement;

import com.mycompany.trabalhofinal.factory.ConnectionSQLite;
import com.mycompany.trabalhofinal.model.Notificacao;
import com.mycompany.trabalhofinal.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoDAO {

    public static void createTableNotificacao() throws SQLException {
        var query = "CREATE TABLE IF NOT EXISTS Notificacao(" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "id_usuario INTEGER NOT NULL REFERENCES Usuario (id)," + " " + "mensagem VARCHAR NOT NULL, " + "visualizada INT DEFAULT 0, " + "data VARCHAR NOT NULL" + ")";
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionSQLite.connect();
            stmt = conn.createStatement();

            stmt.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela notificações: " + e.getMessage());
        } finally {
            stmt.close();
            conn.close();

        }
    }

    public void insert(Notificacao notificacao) throws Exception {
        Connection conn = null;
        Statement stmt = null;

        PreparedStatement ps = null;
        try {

            String SQL = "INSERT INTO Notificacao(mensagem, id_usuario, data) VALUES (?, ?, ?) ";

            conn = ConnectionSQLite.connect();
            stmt = conn.createStatement();

            ps = conn.prepareStatement(SQL);
            ps.setString(1, notificacao.getMensagem());
            ps.setInt(2, notificacao.getUsuario().getId());
            ps.setString(3, LocalDateTime.now().toString());
            ps.executeUpdate();

            stmt.close();
            conn.close();

        } catch (Exception e) {
            throw new Exception("Erro ao inserir");
        } finally {
            ps.close();
            conn.close();
            stmt.close();

        }
    }
    
    public void update(Notificacao notificacao) throws Exception {
        Connection conn = null;
        Statement stmt = null;

        PreparedStatement ps = null;
        try {

            String SQL = "UPDATE Notificacao SET visualizada = ? where id = ? ";

            conn = ConnectionSQLite.connect();
            stmt = conn.createStatement();

            ps = conn.prepareStatement(SQL);
            ps.setBoolean(1, notificacao.isVisualizada());
            ps.setInt(2, notificacao.getId());
            ps.executeUpdate();

            stmt.close();
            conn.close();

        } catch (Exception e) {
            throw new Exception("Erro ao inserir");
        } finally {
            ps.close();
            conn.close();
            stmt.close();

        }
    }
    
    

    public void delete(Notificacao notificacao) throws Exception {
        Connection conn = null;
        Statement stmt = null;

        PreparedStatement ps = null;
        try {

            String SQL = "DELETE FROM Notificacao WHERE id = ? AND id_usuario = ?;";

            conn = ConnectionSQLite.connect();
            stmt = conn.createStatement();

            ps = conn.prepareStatement(SQL);
            ps.setInt(1, notificacao.getId());
            ps.setInt(1, notificacao.getUsuario().getId());
            ps.executeUpdate();

            stmt.close();
            conn.close();

        } catch (Exception e) {
            throw new Exception("Erro ao excluir");
        } finally {
            ps.close();
            conn.close();
            stmt.close();

        }
    }

    public void deleteAllByIdUsuario(int idUsuario) throws Exception {
        Connection conn = null;
        Statement stmt = null;

        PreparedStatement ps = null;
        try {

            String SQL = "DELETE FROM Notificacao WHERE id_usuario = ?;";

            conn = ConnectionSQLite.connect();
            stmt = conn.createStatement();

            ps = conn.prepareStatement(SQL);
            ps.setInt(1, idUsuario);
            ps.executeUpdate();

            stmt.close();
            conn.close();
        } catch (Exception e) {
            throw new Exception("Erro ao excluir");
        } finally {
            ps.close();
            conn.close();
            stmt.close();

        }
    }

    public List<Notificacao> getAllByIdUsuario(int idUsuario) throws Exception {
        List<Notificacao> notificacoes = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;

        PreparedStatement ps = null;
        try {

            String SQL = "SELECT n.id, n.mensagem, n.id_usuario, n.visualizada, n.data FROM Notificacao n " + " WHERE n.id_usuario = ?;";

            conn = ConnectionSQLite.connect();
            stmt = conn.createStatement();

            ps = conn.prepareStatement(SQL);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                Notificacao n = new Notificacao();
                n.setId(rs.getInt(1));
                n.setMensagem(rs.getString(2));
                n.setUsuario(u.comId(rs.getInt(3)));
                n.setVisualizada(rs.getBoolean(4));
                n.setData(LocalDateTime.parse(rs.getString(5)));
                notificacoes.add(n);
            }

            stmt.close();
            conn.close();

            return notificacoes;

        } catch (Exception e) {
            throw new Exception("Erro ao buscar");
        } finally {
            ps.close();
            conn.close();
            stmt.close();

        }
    }

    public Notificacao getById(int idNotificacao) throws Exception {
        Notificacao n = new Notificacao();
        Connection conn = null;
        Statement stmt = null;

        PreparedStatement ps = null;
        try {

            String SQL = "SELECT n.id, n.mensagem, n.id_usuario FROM Notificacao n " + " WHERE n.id = ?;";

            conn = ConnectionSQLite.connect();
            stmt = conn.createStatement();

            ps = conn.prepareStatement(SQL);
            ps.setInt(1, idNotificacao);
            ResultSet rs = ps.executeQuery();

            Usuario u = new Usuario();
            n.setId(rs.getInt(1));
            n.setMensagem(rs.getString(2));
            n.setUsuario(u.comId(rs.getInt(3)));

            stmt.close();
            conn.close();

            return n;

        } catch (Exception e) {
            throw new Exception("Erro ao buscar");
        } finally {
            ps.close();
            conn.close();
            stmt.close();

        }
    }
    
    public int countNotificacoesPendenteByUsuario(int idUsuario) throws Exception {
        int qtdPendentes;
        Connection conn = null;
        Statement stmt = null;

        PreparedStatement ps = null;
        try {

            String SQL = "SELECT count(1) FROM Notificacao n " + " WHERE n.id_usuario = ? and n.visualizada= 0;";

            conn = ConnectionSQLite.connect();
            stmt = conn.createStatement();

            ps = conn.prepareStatement(SQL);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            Usuario u = new Usuario();
            qtdPendentes= (rs.getInt(1));

            stmt.close();
            conn.close();

            return qtdPendentes;

        } catch (Exception e) {
            throw new Exception("Erro ao buscar");
        } finally {
            ps.close();
            conn.close();
            stmt.close();

        }
    }
    
    public int countNotificacoesVisualizadasByUsuario(int idUsuario) throws Exception {
        int qtdPendentes;
        Connection conn = null;
        Statement stmt = null;

        PreparedStatement ps = null;
        try {

            String SQL = "SELECT count(1) FROM Notificacao n " + " WHERE n.id_usuario = ? and n.visualizada= 1;";

            conn = ConnectionSQLite.connect();
            stmt = conn.createStatement();

            ps = conn.prepareStatement(SQL);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            Usuario u = new Usuario();
            qtdPendentes= (rs.getInt(1));

            stmt.close();
            conn.close();

            return qtdPendentes;

        } catch (Exception e) {
            throw new Exception("Erro ao buscar");
        } finally {
            ps.close();
            conn.close();
            stmt.close();

        }
    }
    
}
