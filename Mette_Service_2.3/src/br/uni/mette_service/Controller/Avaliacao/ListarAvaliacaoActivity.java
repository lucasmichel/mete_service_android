package br.uni.mette_service.Controller.Avaliacao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.uni.mette_service.R;
import org.json.JSONArray;
import org.json.JSONObject;

import br.uni.mette_service.Controller.Comentario.ListarComentarioActivity;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.AvaliacaoAcompanhante;
import br.uni.mette_service.Model.Comentario;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

import com.google.gson.Gson;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ListarAvaliacaoActivity extends ListActivity 
implements OnClickListener{
	//---------
	private Button btnVoltarAvaliacao, btnExcluirAvaliacao;
	AvaliacaoAcompanhante avaliacaoClicado = new AvaliacaoAcompanhante();
	Usuario usuarioLogado = new Usuario();
	List<Object> listaObj = new ArrayList<Object>();
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	Acompanhante acompanhante = new Acompanhante();
	Acompanhante acompBuscar = new Acompanhante();

	
	Repositorio repositorio = new Repositorio();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listar_avaliacoes);
	
				
		usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuarioLogado");
		acompanhante = (Acompanhante) getIntent().getSerializableExtra("idAcompanhante");
		
		montarBuscaAvaliacaoPorAcompanhante(acompanhante);                                    
	}
	
	
	private void montarBuscaAvaliacaoPorAcompanhante(Acompanhante acompanhante) {
		
		listaObj.clear();
		
		acompBuscar = new Acompanhante();
		acompBuscar.setId(acompanhante.getId());
		listaObj.add(acompBuscar);
		
		modelo.setDados(listaObj);
		modelo.setMensagem("");
		modelo.setStatus("");
		
		new AvaliacaoAsyncTask().execute();           
			
	}


	private void adicionarFindView() {
		this.btnVoltarAvaliacao = (Button) findViewById(R.id.btnVoltarAvaliacao);
		this.btnExcluirAvaliacao = (Button) findViewById(R.id.btnExcluirAvaliacao);
	}
	public void adicionarListers() {
		this.btnVoltarAvaliacao.setOnClickListener(this);
		this.btnExcluirAvaliacao.setOnClickListener(this);

	}

	public void onClick(View vw) {
		Intent it = null;
		switch (vw.getId()) {
		case R.id.btnVoltarAvaliacao:
			finish();
		break;
		case R.id.btnExcluirAvaliacao:
			android.content.DialogInterface.OnClickListener trataDialog = new android.content.DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					executarExcluirAvaliacaoPorId(avaliacaoClicado);
				}
			};
		break;
		}
	}

	protected void executarExcluirAvaliacaoPorId(AvaliacaoAcompanhante avaliacaoClicado) {
		AvaliacaoAcompanhante avaliacaoMontar = new AvaliacaoAcompanhante(); 
		avaliacaoMontar.setId(avaliacaoClicado.getId());
		
		listaObj.clear();
		listaObj.add(avaliacaoMontar);
		
		
		modelo = new Modelo();
		modelo.setDados(listaObj);
		modelo.setMensagem("");
		modelo.setStatus("");
		
		new excluirAvaliacaoPorIdAsyncTask().execute();
		
	}

	class excluirAvaliacaoPorIdAsyncTask extends AsyncTask<String, String, Modelo> {
	ProgressDialog dialog;
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = ProgressDialog.show(ListarAvaliacaoActivity.this,
				"Excluindo Avaliacao...", "Aguarde !", true, false);
	}
	
	@Override
	protected Modelo doInBackground(String... params) {
		try {
			modeloRetorno = repositorio.acessarServidor(
					"excluirComentarioPorId", modelo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modeloRetorno;
	}
	
	@Override
	protected void onPostExecute(Modelo result) {
		super.onPostExecute(result);
		dialog.dismiss();
			if (modeloRetorno.getStatus().equals("1")) {
				Toast toast = Toast.makeText(ListarAvaliacaoActivity.this,
						modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
				finish();
		
			} else {
				Intent it = new Intent(ListarAvaliacaoActivity.this,
						ListarAvaliacaoActivity.class);
				startActivity(it);
				Toast toast = Toast.makeText(ListarAvaliacaoActivity.this,
						modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
				finish();
			}
	}
	}
	
	class AvaliacaoAsyncTask extends AsyncTask<Void, Void, Modelo> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(
					ListarAvaliacaoActivity.this, 
					"Aguarde", "Carregando Avaliacoes...", true, false);
		}

		@Override
		protected Modelo doInBackground(Void... params) {

			Modelo avaliacaoRetorno = new Modelo();

			avaliacaoRetorno = repositorio.acessarServidor("listarAvaliacoesPorIdAcompanhante", modelo);

			return avaliacaoRetorno;
		}

		@Override
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);



			for ( int i = 0; i < result.getDados().size(); ++i){


				Object dadosObject = result.getDados().get(i);	
				Gson gson = new Gson();
				JSONObject jsonObject = null;
				List<AvaliacaoAcompanhante> addAvaliacao = new ArrayList<AvaliacaoAcompanhante>();

				try {
					JSONArray jsonArray = new JSONArray(gson.toJson(dadosObject));
					for ( int x = 0; x < jsonArray.length(); ++x){
						jsonObject = jsonArray.getJSONObject(x);

						AvaliacaoAcompanhante avaliacaoRetorno = new AvaliacaoAcompanhante();

						avaliacaoRetorno.setId(jsonObject.getInt("id"));
						avaliacaoRetorno.setIdCliente(jsonObject.getInt("idCliente"));
						avaliacaoRetorno.setIdAcompanhante(jsonObject.getInt("idAcompanhante"));
						avaliacaoRetorno.setNota(jsonObject.getInt("nota"));
						


						Log.i("SOSTENES", i +"..." + avaliacaoRetorno.getId() + "..."+ avaliacaoRetorno.getNota());

						addAvaliacao.add(avaliacaoRetorno);
					}
					//----
					// FAZ UMA LISTA DOS SERVIÇOS PASSANDO A LISTA DE SERVIÇOS ADICIONADA.
					//----
					setListAdapter(
							new AvaliacaoAdapter(ListarAvaliacaoActivity.this, 
									addAvaliacao));


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
		avaliacaoClicado = (AvaliacaoAcompanhante) l.getItemAtPosition(position);
		
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