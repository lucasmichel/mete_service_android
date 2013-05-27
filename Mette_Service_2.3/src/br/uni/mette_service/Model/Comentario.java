package br.uni.mette_service.Model;

import java.io.Serializable;

public class Comentario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int idComentario;
	private String comentario;
	private int idAcompanhante;
	private int idCliente;
	
	public Comentario(){
		
	}
	
	public Comentario(int id, int idComentario, String comentario,
			int idAcompanhante, int idCliente) {
		super();
		this.id = id;
		this.idComentario = idComentario;
		this.comentario = comentario;
		this.idAcompanhante = idAcompanhante;
		this.idCliente = idCliente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdComentario() {
		return idComentario;
	}
	public void setIdComentario(int idComentario) {
		this.idComentario = idComentario;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getIdAcompanhante() {
		return idAcompanhante;
	}
	public void setIdAcompanhante(int idAcompanhante) {
		this.idAcompanhante = idAcompanhante;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
}
