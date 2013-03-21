package br.uni.mete_service.model.repositorio;

public class ModelClass {
	
	
	
	protected ModelClass() {
		super();
	}
	
	private int status;
	private String menssagem;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMenssagem() {
		return menssagem;
	}
	public void setMenssagem(String menssagem) {
		this.menssagem = menssagem;
	}

}
