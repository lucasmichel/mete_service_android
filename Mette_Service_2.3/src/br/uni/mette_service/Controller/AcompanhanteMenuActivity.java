package br.uni.mette_service.Controller;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Acompanhante.AlterarStatusActivity;
import br.uni.mette_service.Controller.Acompanhante.CadastroAcompanhanteActivity;
import br.uni.mette_service.Model.Acompanhante;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.ImageView;

public class AcompanhanteMenuActivity extends Activity implements Serializable{
	
	Acompanhante acompanhanteRetorno, objacompanhante;
	
	//private String url;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//url = objacompanhante.getFoto().toString();
		setContentView(R.layout.activity_tela_acompanhante);
		ImageView imgView =(ImageView)findViewById(R.id.imageView1);
		Drawable drawable = LoadImageFromWebOperations("http://www.imagensdahora.com.br/clipart/cliparts_imagens/01Animais//tubarao_06.gif");
		imgView.setImageDrawable(drawable);

		Button btnCadastrar;
		
		btnCadastrar = (Button)findViewById(R.id.buttonCadastrar);
		
		//objacompanhante =   
		//		  (Acompanhante) getIntent().getSerializableExtra("objacompanhante");
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
}
