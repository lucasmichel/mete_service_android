package br.uni.mette_service.Controller.Servico;

import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import br.uni.mette_service.Model.Servico;
import com.google.gson.Gson;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

public class ListaServicosActivity extends ListActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        new ServicosAsyncTask().execute();                                               
    }
    
class ServicosAsyncTask extends AsyncTask<Void, Void, Servico> {
    	
    	ProgressDialog dialog;
    	
    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
    		dialog = ProgressDialog.show(
    				ListaServicosActivity.this, 
    				"Aguarde", "Baixando Serviços", true, false);
    	}

    	@Override
    	protected Servico doInBackground(Void... params) {
    		
    		HttpClient cliente = new DefaultHttpClient();    	    		
    		
    		HttpGet get = new HttpGet(
    				"http://leonardogalvao.com.br/mete_service/src/" + "listarServicosDesc");
    		Servico servico = null;
    		try {
    			
    			//HttpPost envio
    			
				HttpResponse resposta = cliente.execute(get);
				InputStreamReader isr = new InputStreamReader(
						resposta.getEntity().getContent());
				
				Gson gson = new Gson();
				servico = gson.fromJson(isr, Servico.class);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
    		return servico;
    	}
    	
    	@Override
    	protected void onPostExecute(Servico dados) {
    		super.onPostExecute(dados);
    		dialog.dismiss();
    		if (dados != null){
    			setListAdapter(
    					new ServicoAdapter(ListaServicosActivity.this, 
    							dados.getDados()));
    			
    		}
    	}
    }
}