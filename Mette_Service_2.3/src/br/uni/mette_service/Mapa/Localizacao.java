package br.uni.mette_service.Mapa;

public class Localizacao {
	private String latitude, longitude;

	
	public Localizacao() {
		super();
	}

	public Localizacao(String latitude, String longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

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
}
