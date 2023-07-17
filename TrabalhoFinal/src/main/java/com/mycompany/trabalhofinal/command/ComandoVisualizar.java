/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.command;

import com.mycompany.trabalhofinal.DAO.implement.NotificacaoDAO;
import com.mycompany.trabalhofinal.model.Notificacao;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mario e Álvaro
 */
public class ComandoVisualizar extends Comando{

    public ComandoVisualizar(Notificacao notificacao) {
        super(notificacao);
    }

    @Override
    public void mudarStatus() {
        if(!notificacao.isVisualizada()){
            notificacao.setVisualizada(true);
        }
        NotificacaoDAO dao = new NotificacaoDAO(); 
        
        try {
            dao.update(notificacao);
        } catch (Exception ex) {
            Logger.getLogger(ComandoVisualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
