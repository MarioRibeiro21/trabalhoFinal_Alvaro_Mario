/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.trabalhofinal.log;

import com.mycompany.trabalhofinal.model.Usuario;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Mario
 */
public interface IAdapterExport {
     void escrever(Usuario usuarioLogado, String operacao, String nome) throws IOException;
}
