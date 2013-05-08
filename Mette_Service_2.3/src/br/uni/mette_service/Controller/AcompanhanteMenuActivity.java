package br.uni.mette_service.Controller;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Acompanhante.AlterarStatusActivity;
import br.uni.mette_service.Controller.Acompanhante.CadastroAcompanhanteActivity;
import br.uni.mette_service.Controller.Acompanhante.FotoAcompanhanteActivity;
import br.uni.mette_service.Mapa.MapaActivity;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Usuario;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.InputStream;
import java.net.URL;

import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AcompanhanteMenuActivity extends Activity implements OnClickListener{
	
	boolean eEdicao = true;
	Usuario usuarioLogado = new Usuario();
	

	private Button btnCadastrar;
	private Button btnEditarPerfil;
	private Button btnExcluirPerfil;
	private Button btnAlterarStatus;
	private Button btnSair;
	private Button btnCadastrarLocalizacao;
	private Button btnCadastrarFoto;
	
	private TextView txtUsuarioLogado;	
	
	Acompanhante acompanhanteRetorno, objacompanhante;

	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_acompanhante);
		
		usuarioLogado =  (Usuario) getIntent().getSerializableExtra("usuarioLogado");
		adicionarFindView();			
		adicionarListers();
		this.txtUsuarioLogado.setText(usuarioLogado.getIdUsuario() + " - Olá, " + usuarioLogado.getEmail() + "!");
		
		
		
		// IMAGEM DE PERFIL POR URL - VAI SER ALTERADO 
		ImageView imgView =(ImageView)findViewById(R.id.imageView1);
		Drawable drawable = LoadImageFromWebOperations("http://www.imagensdahora.com.br/clipart/cliparts_imagens/01Animais//tubarao_06.gif");
		imgView.setImageDrawable(drawable);
		//
		
		
	}
	
	
		private void adicionarListers() {
			this.btnCadastrarFoto.setOnClickListener(this);
			this.btnAlterarStatus.setOnClickListener(this);
			this.btnExcluirPerfil.setOnClickListener(this); 
			this.btnEditarPerfil.setOnClickListener(this); 
			this.btnSair.setOnClickListener(this); 
			this.btnCadastrarLocalizacao.setOnClickListener(this); 
			
	}

		private void adicionarFindView() {
			this.btnCadastrarFoto = (Button) findViewById(R.id.btnCadastrarFoto);
			this.btnAlterarStatus = (Button) findViewById(R.id.btnAlterarStatus);
			this.btnEditarPerfil = (Button) findViewById(R.id.btnEditarPerfil);
			this.btnExcluirPerfil = (Button) findViewById(R.id.btnExcluirPerfil);
			this.btnSair = (Button) findViewById(R.id.btnSair);
			this.btnCadastrarLocalizacao = (Button) findViewById(R.id.btnCadastrarLocalizacao);
			this.txtUsuarioLogado = (TextView) findViewById(R.id.txtUsuarioLogadoAcomp);
	}

////// MÉTODO PARA CARREGAR URL - FUTURAMENTE SERÁ RETIRADO
		private Drawable LoadImageFromWebOperations(String url)
		  {
		      try
		      {
		          InputStream is = (InputStream) new URL(url).getContent();
		          Drawable d = Drawable.createFromStream(is, "src name");
		          return d;
		      }catch (Exception e) {
		          System.out.println("Exc="+e);
		          return null;
		      }
		  }
	

	
	// PASSAR O CLICK DOS BOTOES PARA ONCLIKLISTENER
	
	public void onClick(DialogInterface arg0, int arg1) {}
	public void onClick(View v) {
		Intent it = null;
		switch (v.getId()) {
		
		case R.id.btnCadastrarFoto:	
			it = new Intent(this, FotoAcompanhanteActivity.class);
			it.putExtra("usuarioLogado", usuarioLogado);
			startActivity(it);
			Toast.makeText(this, "Chamou Cadastrar Foto", Toast.LENGTH_LONG).show();
			break;
			
		case R.id.btnAlterarStatus:	
			it = new Intent(this, AlterarStatusActivity.class);
			startActivity(it);
			Toast.makeText(this, "Entrou no Alterar", Toast.LENGTH_LONG).show();
			break;
			
		case R.id.btnExcluirPerfil:
			Toast.makeText(this, "Entrou no Excluir", Toast.LENGTH_LONG).show();
			android.content.DialogInterface.OnClickListener trataDialog = new android.content.DialogInterface.OnClickListener() 
			{
				
				public void onClick(DialogInterface dialog, int which) 
				{
					
					// Vai execultar a AssyncTask
				//	acompanhanteRetorno = 
				//			objacompanhante.excluirAcompanhante(objacompanhante);
				}
			};
			
			AlertDialog alert = new AlertDialog.Builder(this)
			.setTitle("Confirmação")
			.setMessage("Deseja realmente excluir?")
			.setPositiveButton("Sim", trataDialog)
			.setNegativeButton("Não", null).create();
		alert.show();
			
			
			break;
			
		case R.id.btnEditarPerfil:
			
			it = new Intent(this, CadastroAcompanhanteActivity.class);
			it.putExtra("usuarioLogado", usuarioLogado);
			it.putExtra("eEdicao", eEdicao);	
			startActivity(it);
			
			break;
			
		case R.id.btnSair:
			Toast.makeText(this, "Saindo", Toast.LENGTH_LONG).show();
			finish();
			
			break;
			
		case R.id.btnCadastrarLocalizacao:
			it = new Intent(this, MapaActivity.class);
			startActivity(it);
			
			break;
		}
}

}
