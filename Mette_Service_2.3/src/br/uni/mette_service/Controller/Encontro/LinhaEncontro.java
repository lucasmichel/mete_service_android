package br.uni.mette_service.Controller.Encontro;

import android.widget.Button;
import android.widget.RatingBar;

public class LinhaEncontro {

	private String texto;
    private Button button;
    private RatingBar ratingBar;
    
    public LinhaEncontro() {
    }
    public LinhaEncontro(String texto, Button button, RatingBar ratingBar) {
        this.texto = texto;
        this.button = button;
        this.ratingBar = ratingBar;
    }
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Button getButton() {
		return button;
	}
	public void setButton(Button button) {
		this.button = button;
	}
	public RatingBar getRatingBar() {
		return ratingBar;
	}
	public void setRatingBar(RatingBar ratingBar) {
		this.ratingBar = ratingBar;
	}
	
    
}
