/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.log;


import com.mycompany.trabalhofinal.model.Usuario;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import org.jfree.data.json.impl.JSONObject;

/**
 *
 * @author Mario
 */
public class AdapterExportJson implements IAdapterExport {

    private static final String caminho = "Log.json";
    private static  FileWriter writeFile ;

    public AdapterExportJson() throws IOException {
        this.writeFile =  new FileWriter(caminho);
    }


    @Override
    public void escrever(Usuario usuarioLogado, String operacao, String nome) throws IOException {
 //Cria um Objeto JSON
        JSONObject jsonObject = new JSONObject();
            //Armazena dados em um Objeto JSON
            jsonObject.put("operacao", operacao);
            jsonObject.put("nome", nome);
            jsonObject.put("data", LocalDateTime.now());
            if(usuarioLogado != null){
                jsonObject.put("usuario", usuarioLogado.getNome());
            }else jsonObject.put("usuario",null);
            
            writeFile.write(jsonObject.toJSONString());

            writeFile.close();
        }
        
}
