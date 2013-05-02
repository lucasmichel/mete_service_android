package br.uni.mette_service.Controller.Avaliacao;

import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Cliente;

public class AvaliacaoAcompanhante {

	private int nota;
	private Acompanhante acompanhante;
	private Cliente cliente;

	

	public AvaliacaoAcompanhante(int nota, Acompanhante acompanhante,
			Cliente cliente) {
		super();
		this.nota = nota;
		this.acompanhante = acompanhante;
		this.cliente = cliente;
	}

	public int getNota() {
	return nota;

	}

	public void setNota(int nota) {
	this.nota = nota;

	}

	public Acompanhante getAcompanhante() {
	return acompanhante;
	}
	
	public void setAcompanhante(Acompanhante acompanhante) {
	this.acompanhante = acompanhante;
	}
	
	public Cliente getCliente() {
	return cliente;
	}
public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}



}
