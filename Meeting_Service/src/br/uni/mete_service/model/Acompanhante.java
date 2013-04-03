package br.uni.mete_service.model;

import java.io.Serializable;

import org.json.JSONException;
//import org.json.JSONObject;


import br.uni.mete_service.model.repositorio.Acompanhante.RepositorioAcompanhante;

public class Acompanhante extends Usuario implements Serializable{

	private String nome,idade, altura,busto ,cintura, quadril, olhos, atendo; 
	int pernoite;
	private String especialidade, horario_atendimento,peso;
	private String statusAt;
	//FOTO PARA TESTE NO LAYOUT
	private String foto;
	
	////////////////// GETTERS AND SETTERS REFERENTES A SUPERCLASSE
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getSenha(){
		return senha;
	}
	
	public void setSenha(String senha){
		this.senha = senha;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
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
	public int getPernoite() {
		return pernoite;
	}
	public void setPernoite(int pernoite) {
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
	public String getHorario_atendimento() {
		return horario_atendimento;
	}
	public void setHorario_atendimento(String horario_atendimento) {
		this.horario_atendimento = horario_atendimento;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getStatusAt() {
		return statusAt;
	}
	public void setStatusAt(String statusAt) {
		this.statusAt = statusAt;
	}
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public Acompanhante(String idade, String nome, String altura,
			String busto, String cintura, String quadril, String olhos,
			int pernoite, String especialidade,
			String horario_atendimento, String peso, String atendo, String statusAt, String foto, String email, String senha, String tipo) {
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
		this.horario_atendimento = horario_atendimento;
		this.peso = peso;
		this.statusAt = statusAt;
		this.foto = foto;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
	}
	
	public Acompanhante(){
		
	}

	public Acompanhante logarAndroid(Acompanhante acompanhante) throws JSONException {
		RepositorioAcompanhante bd = RepositorioAcompanhante.getInstance();
		return bd.logarAndroid(this);
	}

	public Acompanhante cadastrarAcompanhante(Acompanhante Acompanhante) throws JSONException {
		RepositorioAcompanhante bd = RepositorioAcompanhante.getInstance();
		return bd.cadastrarAcompanhante(this);
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