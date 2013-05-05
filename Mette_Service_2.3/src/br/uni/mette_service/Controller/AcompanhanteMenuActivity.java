package br.uni.mette_service.Controller;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Acompanhante.AlterarStatusActivity;
import br.uni.mette_service.Controller.Acompanhante.CadastroAcompanhanteActivity;
import br.uni.mette_service.Controller.Servico.ListaServicosActivity;
import br.uni.mette_service.Mapa.MapaActivity;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Servico;
import br.uni.mette_service.Model.Usuario;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.InputStream;
import java.net.URL;

import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AcompanhanteMenuActivity extends Activity{
	
	Usuario usuarioLogado = new Usuario();
	Acompanhante acompanhanteRetorno, objacompanhante;

	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		usuarioLogado =  (Usuario) getIntent().getSerializableExtra("usuarioLogado");
		
		
		
		setContentView(R.layout.activity_tela_acompanhante);
		ImageView imgView =(ImageView)findViewById(R.id.imageView1);
		Drawable drawable = LoadImageFromWebOperations("http://www.imagensdahora.com.br/clipart/cliparts_imagens/01Animais//tubarao_06.gif");
		imgView.setImageDrawable(drawable);

		Button btnCadastrar;
		
		btnCadastrar = (Button)findViewById(R.id.buttonCadastrar);

	}
	
	
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
	

	public void sairClick(View v)
	{
		finish();
		//Intent it = new Intent(this, LoginActivity.class);
		//startActivity(it);
	}
	
	public void editarPerfilClick(View v)
	{
		Intent it = new Intent(this, CadastroAcompanhanteActivity.class);
		it.putExtra("acompanhanteLogado", objacompanhante);
		startActivity(it);
	}
	
	public void excluirPerfilClick(View v)
	{
		OnClickListener trataDialog = new OnClickListener() 
		{
			
			public void onClick(DialogInterface dialog, int which) 
			{
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
	
	}
	
	public void alterarStatusClick(View v)
	{
		Intent it = new Intent(this, AlterarStatusActivity.class);
		startActivity(it);
	}
	
	public void cadastrarLocalizaClick(View v)
	{
		Intent it = new Intent(this, ListaServicosActivity.class);
		startActivity(it);
	}

	// PASSAR O CLICK DOS BOTOES PARA ONCLIKLISTENER
	
//	public void onClick(DialogInterface arg0, int arg1) {}
//	public void onClick(View v) {
//		Intent it = null;
//		switch (v.getId()) {
//		
//		case R.id.btnAlterarStatus:	
//			it = new Intent(this, AlterarStatusActivity.class);
//			startActivity(it);
//			Toast.makeText(this, "aquiii", Toast.LENGTH_LONG).show();
//			break;
//			
//		case R.id.btnExcluirPerfil:
//			Toast.makeText(this, "aquiii22", Toast.LENGTH_LONG).show();
////			OnClickListener trataDialog = new OnClickListener() 
////			{
////				
////				public void onClick(DialogInterface dialog, int which) 
////				{
////					
////					// Vai execultar a AssyncTask
////				//	acompanhanteRetorno = 
////				//			objacompanhante.excluirAcompanhante(objacompanhante);
////				}
////			};
////			
////			AlertDialog alert = new AlertDialog.Builder(this)
////			.setTitle("Confirmação")
////			.setMessage("Deseja realmente excluir?")
////			.setPositiveButton("Sim", trataDialog)
////			.setNegativeButton("Não", null).create();
////		alert.show();
//			
//			
//			break;
//			
//		case R.id.btnEditarPerfil:
//			
//			it = new Intent(this, CadastroAcompanhanteActivity.class);
//			it.putExtra("acompanhanteLogado", objacompanhante);
//			startActivity(it);
//			
//			break;
//			
//		case R.id.btnSair:
//			Toast.makeText(this, "aquiii3", Toast.LENGTH_LONG).show();
//			finish();
//			
//			break;
//		case R.id.btnCadastrarLocalizacao:
//			it = new Intent(this, MapaActivity.class);
//			startActivity(it);
//			
//			break;
//		}
//	}

}
