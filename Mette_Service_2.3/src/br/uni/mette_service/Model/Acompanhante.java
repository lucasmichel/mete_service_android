package br.uni.mette_service.Model;

import java.io.Serializable;

import org.json.JSONException;
//import org.json.JSONObject;

import br.uni.mette_service.Model.Repositorio.ModelClass;
import br.uni.mette_service.Model.Repositorio.Acompanhante.RepositorioAcompanhante;



public class Acompanhante extends Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idAcomp;
	private String nome,idade, altura,busto ,cintura, quadril, olhos, atendo, especialidade, horarioAtendimento, peso,statusAtendimento;
	//FOTO PARA TESTE NO LAYOUT
	private String fotoPerfil;
	private int pernoite;
	private int usuarioId, excluido;

	
	/////////////////////////////////////////////////////////////////
	
	
	public String getIdade() {
		return idade;
	}
	public int getIdAcomp() {
		return idAcomp;
	}
	public void setIdAcomp(int idAcomp) {
		this.idAcomp = idAcomp;
	}
	public int getExcluido() {
		return excluido;
	}
	public void setExcluido(int excluido) {
		this.excluido = excluido;
	}
	public int getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
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
	
	public Acompanhante(int idAcomp,String nome, String idade, String altura,
			String peso, String busto, String cintura, String quadril,
			String olhos, int pernoite,
			String atendo, String especialidade, String horarioAtendimento,
			String statusAtendimento, String fotoPerfil, String email, String senha,
			int excluido, int idPerfil, int id) {
		super();
		this.idAcomp = idAcomp;
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
		this.email = email;
		this.senha = senha;
		this.excluido = excluido;
		this.idPerfil = idPerfil;
		this.id = id;
	}
	
	public Acompanhante(){
		
	}

//	public Acompanhante logarAndroid(Acompanhante acompanhante) throws JSONException {
//		RepositorioAcompanhante bd = RepositorioAcompanhante.getInstance();
//		return bd.logarAndroid(this);
//	}

	public ModelClass cadastrarAcompanhante(ModelClass modelo) throws JSONException {
		RepositorioAcompanhante bd = RepositorioAcompanhante.getInstance();
		return bd.cadastrarAcompanhante(modelo);
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