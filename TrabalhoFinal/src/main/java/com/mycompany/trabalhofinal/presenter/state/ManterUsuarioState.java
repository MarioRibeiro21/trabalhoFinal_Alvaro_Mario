/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.presenter.state;

import com.mycompany.trabalhofinal.model.Usuario;
import com.mycompany.trabalhofinal.presenter.ManterUsuarioPresenter;


/**
 *
 * @author Mario
 */
public abstract class ManterUsuarioState {
    
    protected ManterUsuarioPresenter manterUsuario;

    public ManterUsuarioState(ManterUsuarioPresenter manterUsuario) {
        this.manterUsuario = manterUsuario;
    }
    public void incluir() throws Exception{
        throw new Exception("Ação não permitida aqui!");
    }
    
    public void editar( Usuario usuario )throws Exception{
        throw new Exception("Ação não permitida aqui!");
    }
    
    
}
