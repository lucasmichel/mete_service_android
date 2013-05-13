package br.uni.mette_service.Controller.Avaliacao;

import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Cliente;

public class AvaliacaoAcompanhante {

	private int id;
	private int nota;
	private int idCliente;
	private int idAcompanhante;
	
	public AvaliacaoAcompanhante(){}

	public AvaliacaoAcompanhante(int id, int nota, int idCliente,
			int idAcompanhante) {
		super();
		this.id = id;
		this.nota = nota;
		this.idCliente = idCliente;
		this.idAcompanhante = idAcompanhante;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdAcompanhante() {
		return idAcompanhante;
	}

	public void setIdAcompanhante(int idAcompanhante) {
		this.idAcompanhante = idAcompanhante;
	}
	
	
	
	
	
	

	
}