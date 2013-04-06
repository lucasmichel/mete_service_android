package br.uni.mette_service.Controller.Encontro;

import java.util.ArrayList;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Acompanhante.ListarAcompanhanteActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class EncontroActivity extends Activity implements OnClickListener{
	
	
	private EditText data, hora;
	private TextView txtLinkAdicionarAcompanhantes;
	private ListView lAcompanhantes, lServicos;
	
	private ArrayList<LinhaEncontro> linhas;
	private AdapterLinhaEncontro adapterLinhaEncontro;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acitivity_tela_cadastro_encontro);
		
		lAcompanhantes = (ListView) findViewById(R.id.listView1);
		lServicos = (ListView) findViewById(R.id.listView2);
		txtLinkAdicionarAcompanhantes = (TextView) findViewById(R.id.textView2);						
		txtLinkAdicionarAcompanhantes.setOnClickListener(this);		
		
		
		createListView();		
	}
	
    private void createListView() {
        //Criamos nossa lista que preenchera o ListView
        linhas = new ArrayList<LinhaEncontro>();
        
        Button b = null;
        RatingBar r = null;
        
        LinhaEncontro item1 = new LinhaEncontro("Valesca do Gagau", b,r);
        LinhaEncontro item2 = new LinhaEncontro("Kayka Boladinha", b,r);
        LinhaEncontro item3 = new LinhaEncontro("Negra Tesão", b,r);
        
        
 
        linhas.add(item1);
        linhas.add(item2);
        linhas.add(item3);
        
 
        //Cria o adapter
        adapterLinhaEncontro = new AdapterLinhaEncontro(this, linhas);
 
        //Define o Adapter
        lAcompanhantes.setAdapter(adapterLinhaEncontro);
        //Cor quando a lista é selecionada para ralagem.   
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Intent it = null;
		switch (v.getId()) {
		case R.id.textView2:
			it = new Intent(this, ListarAcompanhanteActivity.class);
			
			break;					
		}
		startActivity(it);
		
	}

}