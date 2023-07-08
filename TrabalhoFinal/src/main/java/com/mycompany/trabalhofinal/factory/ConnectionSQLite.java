/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.factory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mario
 */
public class ConnectionSQLite {
    
    private static final String URL = "jdbc:sqlite:src/main/java/com/mycompany/trabalhofinal/DAO/db/gerenciadorLogin.db";

	public static Connection connect() throws SQLException {

		return DriverManager.getConnection( URL );

	}

	public static void checkDiretorioDb() {
		File diretorio = new File( "db/" );

		if( !diretorio.exists() ) {
			diretorio.mkdirs();
		}
	}
}
