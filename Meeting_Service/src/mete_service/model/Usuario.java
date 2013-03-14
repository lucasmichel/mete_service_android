package mete_service.model;

public class Usuario {

	private int id;
	private String senha;
	private String email;

	public Usuario() {

	}

	public Usuario(int id, String senha, String email) {
		super();
		this.id = id;
		this.senha = senha;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
