package com.mycompany.trabalhofinal.model;

import com.mycompany.trabalhofinal.command.Comando;
import java.time.LocalDateTime;

public class Notificacao {

	private int id;
	private Usuario usuario;
	private String mensagem;
        private boolean visualizada;
        private LocalDateTime data;
        private Comando comando;

    

	public Notificacao( int id, Usuario usuario, String mensagem ) {
		this.id = id;
		this.usuario = usuario;
		this.mensagem = mensagem;
	}

    public Notificacao(int id, Usuario usuario, String mensagem, boolean visualizada) {
        this.id = id;
        this.usuario = usuario;
        this.mensagem = mensagem;
        this.visualizada = visualizada;
    }

	public Notificacao( Usuario usuario, String mensagem ) {
		this.usuario = usuario;
		this.mensagem = mensagem;
	}

	public Notificacao() {}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario( Usuario usuario ) {
		this.usuario = usuario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem( String mensagem ) {
		this.mensagem = mensagem;
	}

        public boolean isVisualizada() {
        return visualizada;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
    

    public void setVisualizada(boolean visualizada) {
        this.visualizada = visualizada;
    }

    public void setComando(Comando comando) {
        this.comando = comando;
    }
    
    public void executarComando(){
        this.comando.mudarStatus();
    }
}
