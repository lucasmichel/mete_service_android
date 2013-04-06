package br.uni.mette_service.Model;

import br.uni.mette_service.Model.Repositorio.ModelClass;
import android.provider.ContactsContract.Data;

public class Encontro extends ModelClass {

	private String id;
	private String cliente_id;
	private String data;
	private String hora;
	
	public Encontro() {
		super();
	}
	
	public Encontro(String id, String cliente_id, String data, String hora,
			int status, String mensagem) {
		super();
		this.id = id;
		this.cliente_id = cliente_id;
		this.data = data;
		this.hora = hora;
		this.setMensagem(mensagem);
		this.setStatus(status);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(String cliente_id) {
		this.cliente_id = cliente_id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}	
}