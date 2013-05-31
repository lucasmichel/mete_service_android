package br.uni.mette_service.Controller.Encontro;

import java.io.Serializable;

public class ServicoAcompanhanteEncontro implements Serializable{
	
	private int servicoAcompanhanteId;
	private int clienteId;
	private int encontroId;

	public ServicoAcompanhanteEncontro(){}

	public ServicoAcompanhanteEncontro(int servicoAcompanhanteId, int clienteId,
			int encontroId) {
		super();
		this.servicoAcompanhanteId = servicoAcompanhanteId;
		this.clienteId = clienteId;
		this.encontroId = encontroId;
	}
	
	public int getServicoAcompanhanteId() {
		return servicoAcompanhanteId;
	}

	public void setServicoAcompanhanteId(int servicoAcompanhanteId) {
		this.servicoAcompanhanteId = servicoAcompanhanteId;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public int getEncontroId() {
		return encontroId;
	}

	public void setEncontroId(int encontroId) {
		this.encontroId = encontroId;
	}	
}