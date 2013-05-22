package br.uni.mette_service.Controller.Acompanhante;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.uni.mette_service.R;
import br.uni.mette_service.Model.Acompanhante;
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

public class ListarAcompanhanteActivity extends ListActivity 
implements OnClickListener{
	//---------
	private Button btnVoltar;
	Usuario usuarioLogado = new Usuario();
	boolean eSolicitacaoDeEncontro = false;

	Repositorio repositorio = new Repositorio();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);
		eSolicitacaoDeEncontro = getIntent().getBooleanExtra("eSolicitacaoDeEncontro",false);
				
		usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuarioLogado");
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
					ListarAcompanhanteActivity.this, 
					"Aguarde", "Baixando Acompanhantes", true, false);
		}

		@Override
		protected Modelo doInBackground(Void... params) {

			Modelo servicoRetorno = new Modelo();
			Modelo modelo = new Modelo();

			servicoRetorno = repositorio.acessarServidor("listarAcompanhante", modelo);

			return servicoRetorno;
		}

		@Override
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);



			for ( int i = 0; i < result.getDados().size(); ++i){


				Object dadosObject = result.getDados().get(i);	
				Gson gson = new Gson();
				JSONObject jsonObject = null;
				List<Acompanhante> addAcomp = new ArrayList<Acompanhante>();

				try {
					JSONArray jsonArray = new JSONArray(gson.toJson(dadosObject));
					for ( int x = 0; x < jsonArray.length(); ++x){
						jsonObject = jsonArray.getJSONObject(x);

						Acompanhante acompanhanteRetorno = new Acompanhante();

						acompanhanteRetorno.setId(jsonObject.getInt("id"));
						acompanhanteRetorno.setNome(jsonObject.getString("nome"));
						acompanhanteRetorno.setIdade(jsonObject.getString("idade"));
						acompanhanteRetorno.setAltura(jsonObject.getString("altura"));
						acompanhanteRetorno.setPeso(jsonObject.getString("peso"));
						acompanhanteRetorno.setBusto(jsonObject.getString("busto"));
						acompanhanteRetorno.setCintura(jsonObject.getString("cintura"));
						acompanhanteRetorno.setQuadril(jsonObject.getString("quadril"));
						acompanhanteRetorno.setOlhos(jsonObject.getString("olhos"));
						acompanhanteRetorno.setPernoite(jsonObject.getInt("pernoite"));
						acompanhanteRetorno.setAtendo(jsonObject.getString("atendo"));
						acompanhanteRetorno.setEspecialidade(jsonObject.getString("especialidade"));
						acompanhanteRetorno.setHorarioAtendimento(jsonObject.getString("horario_atendimento"));
						acompanhanteRetorno.setIdUsuario(jsonObject.getInt("usuarios_id"));
						acompanhanteRetorno.setIdPerfil(jsonObject.getInt("usuarios_id_perfil"));


						Log.i("SOSTENES", i +"..." + acompanhanteRetorno.getNome() + "..."+ acompanhanteRetorno.getIdade());

						addAcomp.add(acompanhanteRetorno);
					}
					//----
					// FAZ UMA LISTA DOS SERVIÇOS PASSANDO A LISTA DE SERVIÇOS ADICIONADA.
					//----
					setListAdapter(
							new AcompanhanteAdapter(ListarAcompanhanteActivity.this, 
									addAcomp));


				}catch (Exception e) {
					e.printStackTrace();			
				}

			}
			dialog.dismiss();
		}
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		
		if (eSolicitacaoDeEncontro)
		{
			
		}
		else
		{
			super.onListItemClick(l, v, position, id);
			Acompanhante acompanhante = (Acompanhante) l.getItemAtPosition(position);

			Intent it = new Intent(this, DadosAcompanhanteActivity.class);
			it.putExtra("acompanhante", acompanhante);
			it.putExtra("usuarioLogado", usuarioLogado);
			startActivity(it);
		}
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