package br.uni.mette_service.Model;

import java.util.List;
public class Servico  {

	private String id;
	private String nome;
	private List<Object> dados; //testando com Servico //sóstenes
	
	public Servico() {
		super();
	}
	
	public Servico(String id, String nome, int status, String mensagem, List<Object> dados) {
		super();
		this.id = id;
		this.nome = nome;	
		this.dados = dados;			
	}
	
	public List<Object> getDados() { //testando com Servico //sóstenes
		return dados;
	}

	public void setDados(List<Object> dados) { //testando com Servico //sóstenes
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
