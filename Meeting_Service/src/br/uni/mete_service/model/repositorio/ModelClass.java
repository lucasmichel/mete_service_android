package br.uni.mete_service.model.repositorio;

public class ModelClass {

	protected ModelClass() {
		super();
	}

	private int status;
	private String mensagem;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
