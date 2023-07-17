/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.command;

import com.mycompany.trabalhofinal.model.Notificacao;

/**
 *
 * @author Mario e Álvaro
 */
public abstract class Comando {
    
    Notificacao notificacao;
    
    public Comando(Notificacao n) {
        this.notificacao = n;
    }

    public abstract void mudarStatus();
}
