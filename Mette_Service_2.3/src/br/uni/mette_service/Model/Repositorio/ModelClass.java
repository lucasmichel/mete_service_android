package br.uni.mette_service.Model.Repositorio;

import java.util.List;


public class ModelClass {

	private String mensagem;
	private int status;
	private List<Object> dados;
	
	
	public ModelClass(){		
	}
	
	public ModelClass(String mensagem, int status, List<Object> dados){
		this.mensagem = mensagem;
		this.status = status;
		this.dados = dados;		
	}
	
	public List<Object> getDados() {
		return dados;
	}

	public void setDados(List<Object> dados) {
		this.dados = dados;
	}

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
