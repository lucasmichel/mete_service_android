package br.uni.mette_service.Controller.Acompanhante;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.uni.mette_service.R;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;
import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GaleriaFotosActivity extends Activity implements OnClickListener {

	private Gallery gallery;
	private Uri[] imagens;
	private Usuario usuarioLogado = new Usuario();
	List<Object> listaAcompanhante = new ArrayList();
	Acompanhante acompanhante = new Acompanhante();
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	Repositorio repositorio = new Repositorio();
	Acompanhante buscarAcompanhante = new Acompanhante();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_foto);

		usuarioLogado = (Usuario) getIntent().getSerializableExtra(
				"usuarioLogado");
		buscarAcompanhante(usuarioLogado);
		gallery = (Gallery) findViewById(R.id.galeriafts);

		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int posicao,
					long id) {
				ImageView imgView = new ImageView(GaleriaFotosActivity.this);
				imgView.setImageURI(imagens[posicao]);
				Toast t = new Toast(GaleriaFotosActivity.this);
				t.setView(imgView);
				t.show();
			}

		});
	}

	private void buscarAcompanhante(Usuario usuarioLogado) {
		listaAcompanhante.clear();
		acompanhante.setId(usuarioLogado.getIdUsuario());
		listaAcompanhante.add(acompanhante);

		modelo.setDados(listaAcompanhante);
		modelo.setMensagem("");
		modelo.setStatus("");
		new buscarAcompanhantePorIdAsyncTask().execute();
	}

	class buscarAcompanhantePorIdAsyncTask extends
			AsyncTask<String, String, Modelo> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Modelo doInBackground(String... params) {
			try {
				modeloRetorno = repositorio.acessarServidor(
						"buscarAcompanhantePorIdUsuario", modelo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return modeloRetorno;
		}

		@Override
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);

			if (modeloRetorno.getStatus().equals("1")) {
				Toast toast = Toast.makeText(GaleriaFotosActivity.this,
						modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
			} else {
				Object dadosObject = modeloRetorno.getDados().get(0);
				JSONObject jsonObject = null;
				Gson gson = new Gson();
				try {
					jsonObject = new JSONObject(gson.toJson(dadosObject));
					buscarAcompanhante.setId(jsonObject
							.getInt("\u0000Acompanhante\u0000id"));

				} catch (JSONException e) {
					e.getMessage();
				}
			}
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
