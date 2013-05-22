package br.uni.mette_service.Controller.Encontro;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Acompanhante.ListarAcompanhanteActivity;
import br.uni.mette_service.Controller.Cliente.CadastroClienteActivity;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Encontro;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroEncontroActivity extends Activity implements OnClickListener {

	private TextView txtLinkAdicionarAcompanhante;
	Encontro encontro;
	Usuario usuarioLogado = new Usuario();
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();	
	Repositorio repositorio = new Repositorio();
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_encontro);
		usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuarioLogado");
		
		adicionarFindView();
		adicionarListers();
	}
		
	private void adicionarFindView() {
		this.txtLinkAdicionarAcompanhante = (TextView) findViewById(R.id.textView2);
	}

	public void adicionarListers() {
		this.txtLinkAdicionarAcompanhante.setOnClickListener(this);		
	}
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 1) {
			
			Acompanhante acompanhanteSelecionada = (Acompanhante) data.getSerializableExtra("ACOMPANHANTE_SELECIONADA");
			adicionarAcompanhanteAoListView(acompanhanteSelecionada);

			Toast toast = Toast.makeText(CadastroEncontroActivity.this, "Foi adicionada ao Encontro a Acompanhante " 
					+ acompanhanteSelecionada.getNome() + " de Id " 
					+ acompanhanteSelecionada.getId(), Toast.LENGTH_LONG);
			toast.show();         
		}
	}
		
	private String adicionarAcompanhanteAoListView(Acompanhante acompanhante){
		
		return "";
	}

	public void onClick(View v) {
		Intent it = null;		
		switch (v.getId()) 		
		{
		case R.id.textView2:	
			it = new Intent(this, ListarAcompanhanteActivity.class);
			it.putExtra("eSolicitacaoDeEncontro", true);
			
			startActivityForResult(it, 1);			
		break;
		}
	}		
}