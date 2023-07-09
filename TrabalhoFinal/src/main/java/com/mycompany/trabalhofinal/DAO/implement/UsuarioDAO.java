package com.mycompany.trabalhofinal.DAO.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.trabalhofinal.factory.ConnectionSQLite;
import com.mycompany.trabalhofinal.model.Usuario;

/**
 *
 * @author Mario e Álvaro
 */
public class UsuarioDAO {

	public static void createTableUsuario() throws Exception {
		Connection conn = null;
	
		try {
			String SQL = "CREATE TABLE IF NOT EXISTS Usuario(" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "nome VARCHAR NOT NULL," + "login VARCHAR NOT NULL UNIQUE, " + "email VARCHAR NOT NULL UNIQUE," + "senha VARCHAR NOT NULL, " + "isAdmin INT DEFAULT 0 " + "dataCadastro TEXT " +")";

			conn = ConnectionSQLite.connect();
			Statement stmt = conn.createStatement();

			stmt.execute( SQL );
                        
                        stmt.close();
			conn.close();

		} catch ( SQLException e ) {
			throw new Exception( "Erro ao criar tabela usuario: " + e.getMessage() );
		}
	}

	public Usuario getById( int id ) throws Exception {
		Usuario retorno = null;
		Connection conn = null;
		PreparedStatement ps = null;
		try {

			String SQL = "SELECT * FROM Usuario WHERE id = ? ;";

			conn = ConnectionSQLite.connect();
			ps = conn.prepareStatement( SQL );

			ps.setInt( 1, id );
			ResultSet rs = ps.executeQuery();

			if( rs.next() ) {
				var idUsuario = rs.getInt( "id" );
				var senha = rs.getString( "senha" );
				var login = rs.getString( "login" );
				var isAdmin = rs.getBoolean( "isAdmin" );
				var nome = rs.getString( "nome" );
				var email = rs.getString( "email" );
				var dataCadastro = rs.getString( "dataCadastro" );

				retorno = new Usuario( idUsuario, nome, email, login, senha, isAdmin, dataCadastro );
			}

			return retorno;

		} catch ( Exception e ) {

			throw new Exception( "Erro ao buscar" );
		} finally {
			ps.close();
			conn.close();

		}
	}

	public Usuario findByLoginAndSenha( String login, String senha ) throws Exception {
		Usuario retorno = null;
		Connection conn = null;
		PreparedStatement ps = null;
		try {

			String SQL = "SELECT * FROM Usuario " + " WHERE login = ? AND senha = ? ;";

			conn = ConnectionSQLite.connect();
			ps = conn.prepareStatement( SQL );

			ps.setString( 1, login );
			ps.setString( 2, senha );
			ResultSet rs = ps.executeQuery();

			while( rs.next() ) {
				retorno = new Usuario();
				retorno.setId( rs.getInt( "id" ) );
				retorno.setLogin( rs.getString( "login" ) );
				retorno.setSenha( rs.getString( "senha" ) );
				retorno.setAdimin( rs.getBoolean( "isAdmin" ) );
				retorno.setEmail( rs.getString( "email" ) );
				retorno.setNome( rs.getString( "nome" ) );
                                retorno.setDataCadastro(rs.getString("dataCadastro"));
			}

			return retorno;

		} catch ( Exception e ) {

			throw new Exception( "Erro ao buscar" );
		} finally {
			ps.close();
			conn.close();

		}
	}

	public List<Usuario> findAll() throws Exception {
		List<Usuario> retorno = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		try {

			String SQL = "SELECT * FROM Usuario u ;";

			conn = ConnectionSQLite.connect();
			ps = conn.prepareStatement( SQL );

			ResultSet rs = ps.executeQuery();

			while( rs.next() ) {
				retorno.add( new Usuario( rs.getInt( "id" ), rs.getString( "nome" ), rs.getString( "email" ), rs.getString( "login" ), rs.getString( "senha" ), rs.getBoolean( "isAdmin" ), rs.getString( "dataCadastro" )  ) );
			}

			return retorno;

		} catch ( Exception e ) {
			throw new Exception( "Erro ao buscar" );
		} finally {
			ps.close();
			conn.close();

		}
	}

	public Usuario findByLogin( String login ) throws Exception {
		Usuario retorno = null;
		Connection conn = null;
		PreparedStatement ps = null;
		try {

			String SQL = "SELECT * FROM Usuario u WHERE u.login LIKE ?;";

			conn = ConnectionSQLite.connect();
			ps = conn.prepareStatement( SQL );

			ps.setString( 1, login );
			ResultSet rs = ps.executeQuery();

			while( rs.next() ) {
				retorno = new Usuario();
				retorno.setId( rs.getInt( "id" ) );
				retorno.setLogin( rs.getString( "login" ) );
				retorno.setSenha( rs.getString( "senha" ) );
				retorno.setAdimin( rs.getBoolean( "isAdmin" ) );
				retorno.setEmail( rs.getString( "email" ) );
				retorno.setNome( rs.getString( "nome" ) );
                                retorno.setDataCadastro(rs.getString("dataCadastro"));
			}

			return retorno;

		} catch ( Exception e ) {
			throw new Exception( "Erro ao buscar" );
		} finally {
			ps.close();
			conn.close();

		}
	}

	public void insert( Usuario usuario ) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {

			String SQL = "INSERT INTO Usuario(nome, login, email, senha, isAdmin, dataCadastro) " + " VALUES(?, ?, ?, ?, ?, datetime('localtime')); ";
			conn = ConnectionSQLite.connect();
			ps = conn.prepareStatement( SQL );
			ps.setString( 1, usuario.getNome() );
			ps.setString( 2, usuario.getLogin() );
			ps.setString( 3, usuario.getEmail() );
			ps.setString( 4, usuario.getSenha() );
			ps.setBoolean( 5, usuario.isAdimin() );
			ps.executeUpdate();

		} catch ( Exception e ) {
			throw new Exception( "Erro ao inserir" );
		} finally {
			ps.close();
			conn.close();

		}
	}

	public void edit( Usuario usuario ) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {

			String SQL = "UPDATE Usuario SET login = ? , senha = ?, isAdmin = ?, nome = ?, email = ?  WHERE id = ?;";

			conn = ConnectionSQLite.connect();
			ps = conn.prepareStatement( SQL );

			var user = findByLogin( usuario.getLogin() );
			ps.setString( 1, usuario.getLogin() );
			ps.setString( 2, usuario.getSenha() );
			ps.setBoolean( 3, usuario.isAdimin() );
			ps.setString( 4, usuario.getNome() );
			ps.setString( 5, usuario.getEmail() );
			ps.setInt( 6, user.getId() );

			ps.executeUpdate();

		} catch ( Exception e ) {
			throw new Exception( "Erro ao atualizar" );
		} finally {
			ps.close();
			conn.close();

		}
	}

	public void delete( int id ) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {

			String SQL = "DELETE FROM Usuario WHERE id = ?";

			conn = ConnectionSQLite.connect();
			ps = conn.prepareStatement( SQL );

			ps.setInt( 1, id );
			ps.executeUpdate();

		} catch ( Exception e ) {
			throw new Exception( e );
		} finally {
			ps.close();
			conn.close();

		}
	}

	public List<Usuario> search( String query, String substr ) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectionSQLite.connect();
			ps = conn.prepareStatement( query );

			ps.setString( 1, "%" + substr + "%" );

			rs = ps.executeQuery();

			List<Usuario> usuarios = new ArrayList<>();

			while( rs.next() ) {
				var id = rs.getInt( "id" );
				var nome = rs.getString( "nome" );
				var email = rs.getString( "email" );
				var login = rs.getString( "login" );
				var senha = rs.getString( "senha" );
				var isAdmin = rs.getBoolean( "isAdmin" );
				var dataCadastro = rs.getString( "dataCadastro" );

				usuarios.add( new Usuario( id, nome, email, login, senha, isAdmin, dataCadastro ) );
			}

			return usuarios;
		} catch ( SQLException e ) {
			throw new RuntimeException( "Erro ao buscar usuário: " + e.getMessage() );
		} finally {
			ps.close();
			conn.close();
			rs.close();

		}
	}

}
