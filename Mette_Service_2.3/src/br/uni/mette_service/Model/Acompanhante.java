package br.uni.mette_service.Model;

public class Acompanhante extends Usuario {	

	private int id;
	private String nome;
	private String idade;
	private String altura;
	private String peso;
	private String busto;
	private String cintura;
	private String quadril;
	private String olhos;
	private String pernoite;
	private String atendo;
	private String especialidade;
	private String horarioAtendimento;
	private String excluido;
	
	public Acompanhante(){}
	public Acompanhante(int id, String nome, String idade, String altura,
			String peso, String busto, String cintura, String quadril,
			String olhos, String pernoite, String atendo, String especialidade,
			String horarioAtendimento, String excluido) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.altura = altura;
		this.peso = peso;
		this.busto = busto;
		this.cintura = cintura;
		this.quadril = quadril;
		this.olhos = olhos;
		this.pernoite = pernoite;
		this.atendo = atendo;
		this.especialidade = especialidade;
		this.horarioAtendimento = horarioAtendimento;
		this.excluido = excluido;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
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

	public String getExcluido() {
		return excluido;
	}

	public void setExcluido(String excluido) {
		this.excluido = excluido;
	}
}
