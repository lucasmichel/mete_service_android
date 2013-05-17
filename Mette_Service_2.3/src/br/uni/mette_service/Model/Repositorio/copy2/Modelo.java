package br.uni.mette_service.Model.Repositorio.copy2;
import java.io.Serializable;
import java.util.List;

public class Modelo implements Serializable{
	
	private String mensagem;
	private String status;
	private List<Object> dados;	
	
	public Modelo(){		
	}
	
	public Modelo(String mensagem, String status, List<Object> dados){
		this.mensagem = mensagem;
		this.status = status;
		this.dados = dados;		
	}
	
	public List<Object> getDados() {
		return dados;
	}

	public void setDados(List<Object> dados) {
		this.dados = dados;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}