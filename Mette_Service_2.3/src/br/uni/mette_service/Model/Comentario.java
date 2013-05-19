package br.uni.mette_service.Model;

public class Comentario {

	private int id;
	private int idComentario;
	private String comentario;
	private int idAcompanhante;
	
	public Comentario(){
		
	}
	
	public Comentario(int id, int idComentario, String comentario,
			int idAcompanhante) {
		super();
		this.id = id;
		this.idComentario = idComentario;
		this.comentario = comentario;
		this.idAcompanhante = idAcompanhante;
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
	
	
	
}
