package br.uni.mette_service.Controller.Encontro;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Acompanhante.ListarAcompanhanteActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class CadastroEncontroActivity extends Activity implements OnClickListener {

	private TextView txtLinkAdicionarAcompanhante;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_encontro);
		
		adicionarFindView();
		adicionarListers();
	}
		
	private void adicionarFindView() {
		this.txtLinkAdicionarAcompanhante = (TextView) findViewById(R.id.textView2);
	}

	public void adicionarListers() {
		this.txtLinkAdicionarAcompanhante.setOnClickListener(this);		
	}

	public void onClick(View v) {
		Intent it = null;		
		switch (v.getId()) 		
		{
		case R.id.textView2:	
			it = new Intent(this, ListarAcompanhanteActivity.class);
			it.putExtra("eSolicitacaoDeEncontro", true);
			startActivity(it);
		break;
		}
	}		
}