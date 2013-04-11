package br.uni.mette_service.Model;

import java.util.List;

import br.uni.mette_service.Model.Repositorio.ModelClass;

public class Servico extends ModelClass {

	private String id;
	private String nome;
	private List<Servico> dados; //testando com Servico //sóstenes
	
	public Servico() {
		super();
	}
	
	public Servico(String id, String nome, int status, String mensagem, List<Servico> dados) {
		super();
		this.id = id;
		this.nome = nome;	
		this.dados = dados;		
		this.setMensagem(mensagem);
		this.setStatus(status);		
	}
	
	public List<Servico> getDados() { //testando com Servico //sóstenes
		return dados;
	}

	public void setDados(List<Servico> dados) { //testando com Servico //sóstenes
		this.dados = dados;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
