package br.uni.mette_service.Mapa;

public class Localizacao {
	private String latitude, longitude;
	private int id ,servicoAcompanhanteId;
	private String enderecoFormatado;
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getServicoAcompanhanteId() {
		return servicoAcompanhanteId;
	}
	public void setServicoAcompanhanteId(int servicoAcompanhanteId) {
		this.servicoAcompanhanteId = servicoAcompanhanteId;
	}
	public String getEnderecoFormatado() {
		return enderecoFormatado;
	}
	public void setEnderecoFormatado(String enderecoFormatado) {
		this.enderecoFormatado = enderecoFormatado;
	}
	public Localizacao(String latitude, String longitude, int id,
			int servicoAcompanhanteId, String enderecoFormatado) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.id = id;
		this.servicoAcompanhanteId = servicoAcompanhanteId;
		this.enderecoFormatado = enderecoFormatado;
	}
	public Localizacao() {
		super();
	}

}
