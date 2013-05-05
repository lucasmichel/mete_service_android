package br.uni.mette_service.Controller.Servico;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.ClienteMenuActivity;
import br.uni.mette_service.Controller.Cliente.CadastroClienteActivity;
import br.uni.mette_service.Mapa.MapaActivity;
import br.uni.mette_service.Model.Servico;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ListaServicosActivity extends ListActivity implements OnClickListener {

	private Button btnVoltar;
	
	Repositorio repositorio = new Repositorio();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        
        new ServicosAsyncTask().execute();                                               
    }
    private void adicionarFindView() {
		this.btnVoltar = (Button) findViewById(R.id.btnVoltar);
	}
    public void adicionarListers() {
		this.btnVoltar.setOnClickListener(this);

	}
  
	public void onClick(View v) {
		Intent it = null;
		switch (v.getId()) {
		case R.id.btnVoltar:
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
    				ListaServicosActivity.this, 
    				"Aguarde", "Baixando Serviços", true, false);
    	}

    	@Override
    	protected Modelo doInBackground(Void... params) {
    		
    		Modelo servicoRetorno = new Modelo();
			Modelo modelo = new Modelo();

			servicoRetorno = repositorio.acessarServidor("listarServicos", modelo);

			return servicoRetorno;
    	}
    	
    	@Override
    	protected void onPostExecute(Modelo result) {
    		super.onPostExecute(result);

    		
    		
    		for ( int i = 0; i < result.getDados().size(); ++i){
				
    			
    			Object dadosObject = result.getDados().get(i);	
    			Gson gson = new Gson();
    			JSONObject jsonObject = null;
    			List<Servico> addServico = new ArrayList<Servico>();

    			try {
    				JSONArray jsonArray = new JSONArray(gson.toJson(dadosObject));
    				for ( int x = 0; x < jsonArray.length(); ++x){
    					jsonObject = jsonArray.getJSONObject(x);
    					
    					Servico serv = new Servico();
    					
    					serv.setNome(jsonObject.getString("nome"));
        				serv.setId(jsonObject.getString("id"));

        				Log.i("PEDRO", i +"..." + serv.getId() + "..."+ serv.getNome());
        				
        				addServico.add(serv);
    				}
    				//----
    				// FAZ UMA LISTA DOS SERVIÇOS PASSANDO A LISTA DE SERVIÇOS ADICIONADA.
    				//----
    	    			setListAdapter(
    	    					new ServicoAdapter(ListaServicosActivity.this, 
    	    							addServico));
    				

    			}catch (Exception e) {
    				e.printStackTrace();			
    			}
    		
    			}
    		dialog.dismiss();
    	}
    }

	protected void onListItemClick(ListView l, View v, int position, long id) {
	// TODO Auto-generated method stub
	super.onListItemClick(l, v, position, id);
	Servico servico = (Servico) l.getItemAtPosition(position);

	Intent it = new Intent(this, MapaActivity.class);
	it.putExtra("servico", servico);
	startActivity(it);
}

	private String toString1(InputStream is) throws IOException {

	byte[] bytes = new byte[1024];
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	int lidos;
	while ((lidos = is.read(bytes)) > 0) {
		baos.write(bytes, 0, lidos);
	}
	return new String(baos.toByteArray());
}

}