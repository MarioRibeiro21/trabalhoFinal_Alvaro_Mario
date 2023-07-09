package com.mycompany.trabalhofinal.DAO.implement;

import com.mycompany.trabalhofinal.factory.ConnectionSQLite;
import com.mycompany.trabalhofinal.model.Notificacao;
import com.mycompany.trabalhofinal.model.Usuario;
import com.mycompany.trabalhofinal.state.Lido;
import com.mycompany.trabalhofinal.state.NaoLido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class NotificacaoDAO {

	public static void createTableNotificacao() throws SQLException {
		var query = "CREATE TABLE IF NOT EXISTS Notificacao(" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "id_usuario INTEGER NOT NULL REFERENCES Usuario (id)," + " " + "mensagem VARCHAR NOT NULL, " + "visto BOOLEAN NOT NULL CHECK(visto IN (0, 1))" + ")";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ConnectionSQLite.connect();
			stmt = conn.createStatement();

			stmt.execute( query );

		} catch ( SQLException e ) {
			throw new RuntimeException( "Erro ao criar tabela notificações: " + e.getMessage() );
		} finally {
			stmt.close();
			conn.close();

		}
	}

	public void insert( Notificacao notificacao ) throws Exception {
		Connection conn = null;
		Statement stmt = null;

		PreparedStatement ps = null;
		try {

			String SQL = "INSERT INTO Notificacao(mensagem, id_usuario, visto) VALUES (?, ?, 0) ";

			conn = ConnectionSQLite.connect();
			stmt = conn.createStatement();

			ps = conn.prepareStatement( SQL );
			ps.setString( 1, notificacao.getMensagem() );
			ps.setInt( 2, notificacao.getUsuario().getId() );
			ps.executeUpdate();

			stmt.close();
			conn.close();

		} catch ( Exception e ) {
			throw new Exception( "Erro ao inserir" );
		} finally {
			ps.close();
			conn.close();
			stmt.close();

		}
	}
        
        public void visualizar( Notificacao notificacao ) throws Exception{
            Connection conn = null;
		Statement stmt = null;

		PreparedStatement ps = null;
		try {

			String SQL = "UPDATE Notificacao SET visto = 1 WHERE id = ?;";

			conn = ConnectionSQLite.connect();
			stmt = conn.createStatement();

			ps = conn.prepareStatement( SQL );
			ps.setInt( 1, notificacao.getId() );
			ps.executeUpdate();

			stmt.close();
			conn.close();

		} catch ( Exception e ) {
			throw new Exception( "Erro ao visualizar" );
		} finally {
			ps.close();
			conn.close();
			stmt.close();

		}
        }

	public void delete( Notificacao notificacao ) throws Exception {
		Connection conn = null;
		Statement stmt = null;

		PreparedStatement ps = null;
		try {

			String SQL = "DELETE FROM Notificacao WHERE id = ? AND id_usuario = ?;";

			conn = ConnectionSQLite.connect();
			stmt = conn.createStatement();

			ps = conn.prepareStatement( SQL );
			ps.setInt( 1, notificacao.getId() );
			ps.setInt( 2, notificacao.getUsuario().getId() );
			ps.executeUpdate();

			stmt.close();
			conn.close();

		} catch ( Exception e ) {
			throw new Exception( "Erro ao excluir" );
		} finally {
			ps.close();
			conn.close();
			stmt.close();

		}
	}

	public void deleteAllByIdUsuario( int idUsuario ) throws Exception {
		Connection conn = null;
		Statement stmt = null;

		PreparedStatement ps = null;
		try {

			String SQL = "DELETE FROM Notificacao WHERE id_usuario = ?;";

			conn = ConnectionSQLite.connect();
			stmt = conn.createStatement();

			ps = conn.prepareStatement( SQL );
			ps.setInt( 1, idUsuario );
			ps.executeUpdate();

			stmt.close();
			conn.close();
		} catch ( Exception e ) {
			throw new Exception( "Erro ao excluir" );
		} finally {
			ps.close();
			conn.close();
			stmt.close();

		}
	}
        
        public int getCountLidaByIdUsuario( int idUsuario ) throws Exception{
            Connection conn = null;
		Statement stmt = null;

		PreparedStatement ps = null;
		try {

			String SQL = "SELECT COUNT(n.id) FROM Notificacao n " + " WHERE n.id_usuario = ? AND n.visto = 1;";

			conn = ConnectionSQLite.connect();
			stmt = conn.createStatement();

			ps = conn.prepareStatement( SQL );
			ps.setInt( 1, idUsuario );
			ResultSet rs = ps.executeQuery();

			stmt.close();
			conn.close();

			return rs.getInt(1);

		} catch ( Exception e ) {
			throw new Exception( "Erro ao buscar" );
		} finally {
			ps.close();
			conn.close();
			stmt.close();

		}
        }

        public int getCountByIdUsuario( int idUsuario ) throws Exception{
            Connection conn = null;
		Statement stmt = null;

		PreparedStatement ps = null;
		try {

			String SQL = "SELECT COUNT(n.id) FROM Notificacao n " + " WHERE n.id_usuario = ?;";

			conn = ConnectionSQLite.connect();
			stmt = conn.createStatement();

			ps = conn.prepareStatement( SQL );
			ps.setInt( 1, idUsuario );
			ResultSet rs = ps.executeQuery();

			stmt.close();
			conn.close();

			return rs.getInt(1);

		} catch ( Exception e ) {
			throw new Exception( "Erro ao buscar" );
		} finally {
			ps.close();
			conn.close();
			stmt.close();

		}
        }
        
	public List<Notificacao> getAllByIdUsuario( int idUsuario ) throws Exception {
		List<Notificacao> notificacoes = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;

		PreparedStatement ps = null;
		try {

			String SQL = "SELECT n.id, n.mensagem, n.id_usuario, n.visto FROM Notificacao n " + " WHERE n.id_usuario = ?;";

			conn = ConnectionSQLite.connect();
			stmt = conn.createStatement();

			ps = conn.prepareStatement( SQL );
			ps.setInt( 1, idUsuario );
			ResultSet rs = ps.executeQuery();

			while( rs.next() ) {
				Usuario u = new Usuario();
				Notificacao n = new Notificacao();
				n.setId( rs.getInt( 1 ) );
				n.setMensagem( rs.getString( 2 ) );
				n.setUsuario( u.comId( rs.getInt( 3 ) ) );
                                if(rs.getBoolean(4)){
                                    n.setEstado(new Lido(n));
                                }else{
                                    n.setEstado(new NaoLido(n));
                                }
				notificacoes.add( n );
			}

			stmt.close();
			conn.close();

			return notificacoes;

		} catch ( Exception e ) {
			throw new Exception( "Erro ao buscar" );
		} finally {
			ps.close();
			conn.close();
			stmt.close();

		}
	}
}
