package br.uni.mette_service.Controller;

import br.uni.mette_service.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TermoUsoActivity extends Activity{
 @Override
 	protected void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	setContentView(R.layout.termo_uso_activity);
	EditText termoUso = (EditText) findViewById(R.id.TermoUso);
	
	termoUso.setText("Mette Service n�o se resposabiliza pelos atos aqui presentes," +
			"declarando que o usuario � maior de idade ( + 18 anos )..." +
			"Fa�a bom uso do servi�o aqui prestado..." + "Use camisinha");
 	}
 
 
 	public void voltar(View v) {
 		finish();
		
	}
}
