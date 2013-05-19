package br.uni.mette_service.Controller.Acompanhante;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.uni.mette_service.R;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

import com.google.gson.Gson;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class ListarEncontrosAprovados extends ListActivity 
										implements OnClickListener{
	//---------
private Button btnVoltarApr;
Usuario usuarioLogado = new Usuario();

	Repositorio repositorio = new Repositorio();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aprovados);
        
        usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuarioLogado");
        new ServicosAsyncTask().execute();                                               
    }
    private void adicionarFindView() {
		this.btnVoltarApr = (Button) findViewById(R.id.btnVoltarPnd);
	}
    public void adicionarListers() {
		this.btnVoltarApr.setOnClickListener(this);

	}
  
	public void onClick(View v) {
		Intent it = null;
		switch (v.getId()) {
		case R.id.btnVoltarApr:
				finish();
			break;	
		}
	}
    
    
    class ServicosAsyncTask extends AsyncTask<Void, Void, Modelo> {
    	
    	ProgressDialog dialog;
    	
    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
    		dialog = ProgressDialog.show(
    				ListarEncontrosAprovados.this, 
    				"Aguarde", "Baixando Encontros Aprovados...", true, false);
    	}

    	@Override
    	protected Modelo doInBackground(Void... params) {
    		
    		Modelo encontroRetorno = new Modelo();
			Modelo modelo = new Modelo();

			encontroRetorno = repositorio.acessarServidor("listarEncontrosAprovadosPorIdAcompanhante", modelo);

			return encontroRetorno;
    	}
    	
    	@Override
    	protected void onPostExecute(Modelo result) {
    		super.onPostExecute(result);

    		
    		
    		for ( int i = 0; i < result.getDados().size(); ++i){
				
    			
    			Object dadosObject = result.getDados().get(i);	
    			Gson gson = new Gson();
    			JSONObject jsonObject = null;
//    			List<Encontro> addEncontro = new ArrayList<Acompanhante>();

    			try {
    				JSONArray jsonArray = new JSONArray(gson.toJson(dadosObject));
    				for ( int x = 0; x < jsonArray.length(); ++x){
    					jsonObject = jsonArray.getJSONObject(x);
    					
//    					Encontro encontroResult = new Acompanhante();
    					
    					//sets no encontro Retorno a partir do objectJson
    					

        				//Log.i("SOSTENES", i +"..." + encontroResult.getNome() + "..."+ encontroResult.getIdade());
        				
//        				addEncontro.add(encontroResult);
    				}
    				//----
    				// FAZ UMA LISTA DOS SERVIÇOS PASSANDO A LISTA DE SERVIÇOS ADICIONADA.
    				//----
//    	    			setListAdapter(
//    	    					new AcompanhanteAdapter(ListarEncontrosAprovados.this, 
//    	    							addEncontro));
    				

    			}catch (Exception e) {
    				e.printStackTrace();			
    			}
    		
    			}
    		dialog.dismiss();
    	}
    }

//	protected void onListItemClick(ListView l, View v, int position, long id) {
//	// TODO Auto-generated method stub
//	super.onListItemClick(l, v, position, id);
//	Acompanhante acompanhante = (Acompanhante) l.getItemAtPosition(position);
//
//	Intent it = new Intent(this, DadosAcompanhanteActivity.class);
//	it.putExtra("acompanhante", acompanhante);
//	it.putExtra("usuarioLogado", usuarioLogado);
//	startActivity(it);
//}
}