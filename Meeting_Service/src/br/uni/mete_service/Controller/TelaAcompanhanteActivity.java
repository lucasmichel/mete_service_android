package br.uni.mete_service.Controller;


import br.uni.meeting_service.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class TelaAcompanhanteActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acitivity_tela_acompanhante);

	}
	
	public void sairClick(View v)
	{
		finish();
	}
	
	public void editarPerfilClick(View v)
	{
		
	}
	
	public void alterarStatusClick(View v)
	{
		
	}
}
