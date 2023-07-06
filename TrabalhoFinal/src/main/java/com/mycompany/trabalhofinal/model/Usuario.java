/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal.model;

/**
 *
 * @author Mario
 */
public class Usuario {

    private int id;
    private String nome;
    private String login;
    private String senha;
    private String email;
    private boolean isAdimin;

    public Usuario(int id, String nome, String email, String login, String senha, boolean isAdimin) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.isAdimin = isAdimin;
    }

    public Usuario(String nome, String email, String login, String senha, boolean isAdimin) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.isAdimin = isAdimin;
    }

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdimin() {
        return isAdimin;
    }

    public void setAdimin(boolean isAdimin) {
        this.isAdimin = isAdimin;
    }

    public Usuario comId(int id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
