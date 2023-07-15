/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.trabalhofinal;

import com.mycompany.trabalhofinal.DAO.implement.NotificacaoDAO;
import com.mycompany.trabalhofinal.DAO.implement.UsuarioDAO;
import com.mycompany.trabalhofinal.presenter.PrincipalPresenter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mario
 */
public class TrabalhoFinal {

    public static void main(String[] args) throws Exception {
        try {
        UsuarioDAO.createTableUsuario();
        NotificacaoDAO.createTableNotificacao();
        new PrincipalPresenter();
        }catch (Exception e) {
                Logger.getLogger(TrabalhoFinal.class.getName()).log(Level.SEVERE, null, e);
            }
    }
}
