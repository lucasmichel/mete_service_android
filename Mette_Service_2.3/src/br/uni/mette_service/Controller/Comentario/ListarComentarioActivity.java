package br.uni.mette_service.Controller.Comentario;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.uni.mette_service.R;
import org.json.JSONArray;
import org.json.JSONObject;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.LogarAndroidActivity;
import br.uni.mette_service.Controller.Acompanhante.AcompanhanteMenuActivity;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Comentario;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

import com.google.gson.Gson;

import android.app.AlertDialog;
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

public class ListarComentarioActivity extends ListActivity 
implements OnClickListener{
	//---------
	private Button btnVoltarComentarios, btnExcluirComentario;
	Comentario comentarioClicado = new Comentario();
	Usuario usuarioLogado = new Usuario();
	
	List<Object> listaObj = new ArrayList<Object>();
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	Acompanhante acompanhante = new Acompanhante();
	Acompanhante acompBuscar = new Acompanhante();

	Repositorio repositorio = new Repositorio();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listar_comentarios);
	
				
		usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuarioLogado");
		acompanhante = (Acompanhante) getIntent().getSerializableExtra("idAcompanhante");
		
		montarBuscaComentariosPorAcompanhante(acompanhante);                                    
	}
	
	
	private void montarBuscaComentariosPorAcompanhante(Acompanhante acompanhante) {
		
		listaObj.clear();
		
		acompBuscar = new Acompanhante();
		acompBuscar.setId(acompanhante.getId());
		listaObj.add(acompBuscar);
		
		modelo.setDados(listaObj);
		modelo.setMensagem("");
		modelo.setStatus("");
		
		new ComentariosAsyncTask().execute();           
			
	}


	private void adicionarFindView() {
		this.btnVoltarComentarios = (Button) findViewById(R.id.btnVoltarComentarios);
		this.btnExcluirComentario = (Button) findViewById(R.id.btnExcluirComentario);
	}
	public void adicionarListers() {
		this.btnVoltarComentarios.setOnClickListener(this);
		this.btnExcluirComentario.setOnClickListener(this);
	}

	public void onClick(View vw) {
		Intent it = null;
		switch (vw.getId()) {
		case R.id.btnVoltarComentarios:
			finish();
		break;
		case R.id.btnResponder:
			it = new Intent(this, CadastroComentarioActivity.class);
			it.putExtra("comentarioClicado", comentarioClicado);
			startActivity(it);
		break;
		case R.id.btnExcluirComentario:
			android.content.DialogInterface.OnClickListener trataDialog = new android.content.DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					executarExcluirComentarioPorId(comentarioClicado);
				}
			};

			AlertDialog alert = new AlertDialog.Builder(this)
					.setTitle("Confirmação")
					.setMessage("Deseja realmente excluir?")
					.setPositiveButton("Sim", trataDialog)
					.setNegativeButton("Não", null).create();
			alert.show();
			break;
		}
	}


	protected void executarExcluirComentarioPorId(Comentario comentarioClicado) {
				Comentario comentarioMontar = new Comentario(); 
				comentarioMontar.setId(comentarioClicado.getId());
				
				listaObj.clear();
				listaObj.add(comentarioMontar);
				
				
				modelo = new Modelo();
				modelo.setDados(listaObj);
				modelo.setMensagem("");
				modelo.setStatus("");
				
				new excluirComentarioPorIdAsyncTask().execute();
				
	}
	
	//MÉTODO PARA CRIAR UM OBJETO A PARTIR DO ITEM CLICADO NA LISTA
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		
		super.onListItemClick(l, v, position, id);
		comentarioClicado = (Comentario) l.getItemAtPosition(position);
		
	}

		class excluirComentarioPorIdAsyncTask extends AsyncTask<String, String, Modelo> {
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(ListarComentarioActivity.this,
					"Excluindo Comentario...", "Aguarde !", true, false);
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
					Toast toast = Toast.makeText(ListarComentarioActivity.this,
							modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
					toast.show();
					finish();
			
				} else {
					Intent it = new Intent(ListarComentarioActivity.this,
							ListarComentarioActivity.class);
					startActivity(it);
					Toast toast = Toast.makeText(ListarComentarioActivity.this,
							modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
					toast.show();
					finish();
				}
		}
		}

	class ComentariosAsyncTask extends AsyncTask<Void, Void, Modelo> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(
					ListarComentarioActivity.this, 
					"Aguarde", "Carregando Comentarios...", true, false);
		}

		@Override
		protected Modelo doInBackground(Void... params) {

			Modelo comentarioRetorno = new Modelo();

			comentarioRetorno = repositorio.acessarServidor("listarComentariosPorIdAcompanhante", modelo);

			return comentarioRetorno;
		}

		@Override
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);



			for ( int i = 0; i < result.getDados().size(); ++i){


				Object dadosObject = result.getDados().get(i);	
				Gson gson = new Gson();
				JSONObject jsonObject = null;
				List<Comentario> addComentario = new ArrayList<Comentario>();

				try {
					JSONArray jsonArray = new JSONArray(gson.toJson(dadosObject));
					for ( int x = 0; x < jsonArray.length(); ++x){
						jsonObject = jsonArray.getJSONObject(x);

						Comentario comentarioRetorno = new Comentario();

						comentarioRetorno.setId(jsonObject.getInt("id"));
						comentarioRetorno.setComentario(jsonObject.getString("comentario"));
						comentarioRetorno.setIdAcompanhante(jsonObject.getInt("idAcompanhante"));
						comentarioRetorno.setIdComentario(jsonObject.getInt("idComentario"));
						


						Log.i("SOSTENES", i +"..." + comentarioRetorno.getId() + "..."+ comentarioRetorno.getComentario());

						addComentario.add(comentarioRetorno);
					}
					//----
					// FAZ UMA LISTA DOS SERVIÇOS PASSANDO A LISTA DE SERVIÇOS ADICIONADA.
					//----
					setListAdapter(
							new ComentarioAdapter(ListarComentarioActivity.this, 
									addComentario));


				}catch (Exception e) {
					e.printStackTrace();			
				}

			}
			dialog.dismiss();
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