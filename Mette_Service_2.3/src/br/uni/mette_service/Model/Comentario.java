package br.uni.mette_service.Model;

import java.io.Serializable;

public class Comentario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
//	private int comentarioId;
	private String comentario;
	private int acompanhanteId;
	private int clienteId;
	
	public Comentario(){
		
	}
	
	public Comentario(int id, String comentario,
			int acompanhanteId, int clienteId) {
		super();
		this.id = id;
//		this.comentarioId = comentarioId;
		this.comentario = comentario;
		this.acompanhanteId = acompanhanteId;
		this.clienteId = clienteId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	public int getComentarioId() {
//		return comentarioId;
//	}
//	public void setComentarioId(int comentarioId) {
//		this.comentarioId = comentarioId;
//	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getAcompanhanteId() {
		return acompanhanteId;
	}
	public void setAcompanhanteId(int acompanhanteId) {
		this.acompanhanteId = acompanhanteId;
	}
	
	public int getIdCliente() {
		return clienteId;
	}
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	
}
