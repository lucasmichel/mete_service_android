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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class ListarAcompanhanteActivity extends ListActivity{

	AcompanhanteAdapter acompAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//executa a chamada assincronica da thread
		new MeninasAsyncTask().execute();
		
		setContentView(R.layout.lista_acomp);

	}

	class MeninasAsyncTask extends AsyncTask<Void, Void, AcompanhanteList> {

		ProgressDialog dialog;
		
		//inicio da thread
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(ListarAcompanhanteActivity.this, "Calma amiguinho.",
					"Sua lista de acompanhante logo ser� carregada.", true, false);
		}

		//execução propriamnete dita da thread
		@Override
		protected AcompanhanteList doInBackground(Void... params) {
			HttpClient cliente = new DefaultHttpClient();

			HttpGet get = new HttpGet(
					"https://dl.dropbox.com/s/itwq2o3knlomodo/js.json");
			AcompanhanteList acompanhanteList = new AcompanhanteList();
			try {
				HttpResponse resposta = cliente.execute(get);

				JSONArray jsonAray = new JSONArray(toString(resposta
						.getEntity().getContent()));
				for (int i = 0; i < jsonAray.length(); i++) {
					JSONObject objeto = jsonAray.getJSONObject(i);

					Acompanhante m = new Acompanhante();
//					m.setId(objeto.getInt("id"));
					m.setNome(objeto.getString("nome"));
					m.setEspecialidade(objeto.getString("especialidade"));
					m.setIdade(objeto.getString("idade"));
					m.setStatus(objeto.getString("status"));

					acompanhanteList.getResults().add(m);

					Log.i("pedro", "nomes:" + objeto.getString("nome"));
					Log.i("pedro", "------------------------");
					Log.i("pedro", "nomes:" + objeto.getInt("idade"));
				}

			} catch (Exception e) {
				e.printStackTrace();
				Log.i("pedro", "ERRO:::" + e);
			}
			return acompanhanteList;
		}
		
		//executada quando a thread é concluida
		@Override
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
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		// TODO Auto-generated method stub
//		super.onListItemClick(l, v, position, id);
//		Acompanhante acompanhantes = (Acompanhante) l.getItemAtPosition(position);
//
//		Intent it = new Intent(this, InformacoesAcomp.class);
//		it.putExtra("acompan", acomp);
//		startActivity(it);
//	}

		


}

