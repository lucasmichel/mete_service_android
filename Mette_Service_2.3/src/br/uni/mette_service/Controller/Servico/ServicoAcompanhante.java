package br.uni.mette_service.Controller.Servico;

import java.io.Serializable;

public class ServicoAcompanhante implements Serializable {

	private int id;
	private String valor;
	int acompanhanteId;
	int servicoId;
	
	public ServicoAcompanhante(int id, String valor, int acompanhanteId,
			int servicoId) {
		super();
		this.id = id;
		this.valor = valor;
		this.acompanhanteId = acompanhanteId;
		this.servicoId = servicoId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public int getAcompanhanteId() {
		return acompanhanteId;
	}

	public void setAcompanhanteId(int acompanhanteId) {
		this.acompanhanteId = acompanhanteId;
	}

	public int getServicoId() {
		return servicoId;
	}

	public void setServicoId(int servicoId) {
		this.servicoId = servicoId;
	}

	public ServicoAcompanhante() {
		super();
	}
	
	
	
}
