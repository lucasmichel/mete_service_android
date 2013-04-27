package br.uni.mette_service.Model;

import java.io.Serializable;

import org.json.JSONException;
//import org.json.JSONObject;

import br.uni.mette_service.Model.Repositorio.ModelClass;
import br.uni.mette_service.Model.Repositorio.Acompanhante.AcompanhanteList;
import br.uni.mette_service.Model.Repositorio.Acompanhante.RepositorioAcompanhante;



public class Acompanhante extends Usuario implements Serializable{

	private String nome,idade, altura,busto ,cintura, quadril, olhos, atendo; 
	String pernoite,especialidade, horarioAtendimento, peso,statusAtendimento;
	//FOTO PARA TESTE NO LAYOUT
	private String fotoPerfil;

	
	/////////////////////////////////////////////////////////////////
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getBusto() {
		return busto;
	}
	public void setBusto(String busto) {
		this.busto = busto;
	}
	public String getCintura() {
		return cintura;
	}
	public void setCintura(String cintura) {
		this.cintura = cintura;
	}
	public String getQuadril() {
		return quadril;
	}
	public void setQuadril(String quadril) {
		this.quadril = quadril;
	}
	public String getOlhos() {
		return olhos;
	}
	public void setOlhos(String olhos) {
		this.olhos = olhos;
	}
	public String getPernoite() {
		return pernoite;
	}
	public void setPernoite(String pernoite) {
		this.pernoite = pernoite;
	}
	public String getAtendo() {
		return atendo;
	}
	public void setAtendo(String atendo) {
		this.atendo = atendo;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public String getHorarioAtendimento() {
		return horarioAtendimento;
	}
	public void setHorarioAtendimento(String horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getStatusAtendimento() {
		return statusAtendimento;
	}
	public void setStatusAtendimento(String statusAtendimento) {
		this.statusAtendimento = statusAtendimento;
	}
	public String getFotoPerfil() {
		return fotoPerfil;
	}
	
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	
	public Acompanhante(String idade, String nome, String altura,
			String busto, String cintura, String quadril, String olhos,
			String pernoite, String especialidade,
			String horarioAtendimento, String peso, String atendo, String statusAtendimento, String fotoPerfil) {
		super();
		this.idade = idade;
		this.nome = nome;
		this.altura = altura;
		this.busto = busto;
		this.cintura = cintura;
		this.quadril = quadril;
		this.olhos = olhos;
		this.pernoite = pernoite;
		this.atendo = atendo;
		this.especialidade = especialidade;
		this.horarioAtendimento = horarioAtendimento;
		this.peso = peso;
		this.statusAtendimento = statusAtendimento;
		this.fotoPerfil = fotoPerfil;
	}
	
	public Acompanhante(){
		
	}

//	public Acompanhante logarAndroid(Acompanhante acompanhante) throws JSONException {
//		RepositorioAcompanhante bd = RepositorioAcompanhante.getInstance();
//		return bd.logarAndroid(this);
//	}

	public ModelClass cadastrarAcompanhante(Acompanhante Acompanhante) throws JSONException {
		RepositorioAcompanhante bd = RepositorioAcompanhante.getInstance();
		return bd.cadastrarAcompanhante(this);
	}

	public Acompanhante excluirAcompanhante(Acompanhante objacompanhante) {
		RepositorioAcompanhante bd = RepositorioAcompanhante.getInstance();
		return bd.excluirAcompanhante(this);
	}

	public Acompanhante editarAcompanhante(Acompanhante objacompanhante) {
		RepositorioAcompanhante bd = RepositorioAcompanhante.getInstance();
		return bd.editarAcompanhante(this);
	}
	

//	private JSONObject converteParaJson() throws JSONException {
//		JSONObject json = new JSONObject();
//		JSONObject manJson = new JSONObject();
//		manJson.put("email", this.getEmail());
//		manJson.put("senha", this.getSenha());
//		json.put("nomeJson", manJson);
//		return json;
//	}
	
	
	

	
}