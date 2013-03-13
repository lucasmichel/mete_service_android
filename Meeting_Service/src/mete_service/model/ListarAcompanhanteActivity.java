package mete_service.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import br.edu.unibratec.Proxenetarecife.ListaMeninasActivity;
import br.edu.unibratec.Proxenetarecife.Meninas;
import br.edu.unibratec.Proxenetarecife.MeninasAdapter;
import br.edu.unibratec.Proxenetarecife.MeninasList;

import com.example.meeting_service.R;


import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

public class ListarAcompanhanteActivity extends ListActivity{

	Acomp_Adapter acomp_adapter;

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
					
					a.setNome(objeto.getString("nome"));
				

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
				setListAdapter(new MeninasAdapter(ListarAcompanhanteActivity.this,
						result.getResults()));

			}
		}
			
	}

}

