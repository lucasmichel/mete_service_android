package br.uni.mete_service.Controller.Acompanhante;



import br.uni.mete_service.R;
import br.uni.mete_service.Controller.LoginActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.InputStream;
import java.net.URL;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class TelaAcompanhanteActivity extends Activity {
	
	//Acompanhante objacompanhante;
	//private String url;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//url = objacompanhante.getFoto().toString();
		setContentView(R.layout.acitivity_tela_acompanhante);
		ImageView imgView =(ImageView)findViewById(R.id.imageView1);
		Drawable drawable = LoadImageFromWebOperations("http://www.imagensdahora.com.br/clipart/cliparts_imagens/01Animais//tubarao_06.gif");
		imgView.setImageDrawable(drawable);

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
		Intent it = new Intent(this, LoginActivity.class);
		startActivity(it);
	}
	
	public void editarPerfilClick(View v)
	{
		
	}
	
	public void alterarStatusClick(View v)
	{
		Intent it = new Intent(this, AlterarStatusActivity.class);
		startActivity(it);
	}
}
