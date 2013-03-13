package mete_service.model;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Acompanhante extends Activity {

	private int id, idade;
	private String nome, altura,busto ,cintura, quadril, olhos, pernoite, atendo;
	private String especialidade, horario_atentimento,peso;
	//FOTO PARA TESTE NO LAYOUT
	private String foto;
	
	
	public Acompanhante(int id, int idade, String nome, String altura,
			String busto, String cintura, String quadril, String olhos,
			String pernoite, String atendo, String especialidade,
			String horario_atentimento, String peso, String foto) {
		super();
		this.id = id;
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
		this.horario_atentimento = horario_atentimento;
		this.peso = peso;
		this.foto = foto;
	}
	public Acompanhante() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
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
	public String getHorario_atentimento() {
		return horario_atentimento;
	}
	public void setHorario_atentimento(String horario_atentimento) {
		this.horario_atentimento = horario_atentimento;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
}