package br.uni.mete_service.Controller.Acompanhante;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.uni.mete_service.R;
import br.uni.mete_service.model.Acompanhante;
import br.uni.mete_service.model.AcompanhanteAdapter;
import br.uni.mete_service.model.AcompanhanteList;



import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class ListarAcompanhanteActivity extends ListActivity{

	AcompanhanteAdapter acomp_adapter;
	

	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	new AcompanhanteAsyncTask().execute();
//	setContentView(R.layout.lista_acomp);
	
	}
	
	
	 class AcompanhanteAsyncTask extends AsyncTask<String, Void, AcompanhanteList> {

		ProgressDialog dialog;
		
		//inicio da thread
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(ListarAcompanhanteActivity.this, "Relaxe",
					"Caregando Lista", true, false);
			
		}
		
		protected AcompanhanteList doInBackground(String... params) {
			HttpClient cliente = new DefaultHttpClient();
			
			HttpGet get = new HttpGet(
					"https://dl-web.dropbox.com/get/js.json?w=AAAEEtGh-m2H3VaGG4PFiOwy0l9_B79caOs-5iWSaLDl_A");
			
			AcompanhanteList acompList = new AcompanhanteList();
			
			try {
				
				HttpResponse resposta = cliente.execute(get);

				JSONArray jsonAray = new JSONArray(toString(resposta
						.getEntity().getContent()));
				for (int i = 0; i < jsonAray.length(); i++) {
					JSONObject objeto = jsonAray.getJSONObject(i);

					Acompanhante a = new Acompanhante();
					
					a.setId(objeto.getInt("id"));
					a.setNome(objeto.getString("nome"));
					a.setIdade(objeto.getInt("idade"));
					a.setEspecialidade(objeto.getString("especilidades"));
					a.setStatus(objeto.getString("status"));
					
					// pegar idade foto e status
					Log.i("NGVL",   
						       "id="+ objeto.getString("nome"));

					acompList.getResults().add(a);
				}

			} catch (Exception e) {
				e.printStackTrace();
				Log.i("pedro","O erro foi:  " + e);			}
			return acompList;
		}
		
		protected void onPostExecute(AcompanhanteList result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if (result != null) {
				setListAdapter(new AcompanhanteAdapter(ListarAcompanhanteActivity.this,
						result.getResults()));

			}
		}
		
		
				
		private String toString(InputStream is) throws IOException {

			byte[] bytes = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int lidos;
			while ((lidos = is.read(bytes)) > 0) {
				baos.write(bytes, 0, lidos);
			}
			return new String(baos.toByteArray());
		}

			
	}

}

