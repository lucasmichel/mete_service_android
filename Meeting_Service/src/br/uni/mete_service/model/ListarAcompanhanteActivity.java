package br.uni.mete_service.model;

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
import org.json.JSONObject;

import br.uni.meeting_service.R;



import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

public class ListarAcompanhanteActivity extends ListActivity{

	AcompanhanteAdapter acomp_adapter;

	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.lista_acomp);
	}
	
	
	class AcompanhanteAsyncTask extends AsyncTask<Void, Void, AcompanhanteList> {

		ProgressDialog dialog;
		
		//inicio da thread
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(ListarAcompanhanteActivity.this, "Relaxe",
					"Caregando Lista", true, false);
		}
		
		@Override
		protected AcompanhanteList doInBackground(Void... params) {
			HttpClient cliente = new DefaultHttpClient();
			
			HttpGet get = new HttpGet(
					/*link do listar*/);
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
				

					acompList.getResults().add(a);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
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

