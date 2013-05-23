package br.uni.mette_service.Model;

import java.io.Serializable;

public class Encontro implements Serializable{

	private int clienteId;
	private String dataHora;
		
	public Encontro(){}
	
	public Encontro(int clienteId, String dataHora) {
		super();
		this.clienteId = clienteId;
		this.dataHora = dataHora;
	}
	
	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
}
