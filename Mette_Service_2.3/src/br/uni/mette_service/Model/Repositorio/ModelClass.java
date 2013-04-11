package br.uni.mette_service.Model.Repositorio;

import java.util.List;

import br.uni.mette_service.Model.Servico;
public class ModelClass {

	protected ModelClass() {
		super();
	}

	private int status;
	private String mensagem;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
