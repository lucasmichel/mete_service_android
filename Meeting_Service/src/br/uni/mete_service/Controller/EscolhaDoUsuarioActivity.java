package br.uni.mete_service.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import br.uni.mete_service.R;
import br.uni.mete_service.Controller.Acompanhante.CadastroAcompanhanteActivity;
import br.uni.mete_service.Controller.Cliente.CadastroClienteActivity;
import br.uni.mete_service.model.Acompanhante;
import br.uni.mete_service.model.Cliente;

public class EscolhaDoUsuarioActivity extends Activity implements
		OnClickListener {

	private ImageButton btnHomem, btnMulher;
	private Cliente cli;
	private Acompanhante aco;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_escolherusuario);

		this.btnHomem = (ImageButton) findViewById(R.id.homem);
		this.btnMulher = (ImageButton) findViewById(R.id.mulher);

		btnHomem.setOnClickListener(this);
		btnMulher.setOnClickListener(this);
		
		//oi

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.homem:
			Intent ith = new Intent(this, CadastroClienteActivity.class);
			startActivity(ith);
			break;
		case R.id.mulher:
			Intent it = new Intent(this, CadastroAcompanhanteActivity.class);
			startActivity(it);
			break;
		}
	}

}
