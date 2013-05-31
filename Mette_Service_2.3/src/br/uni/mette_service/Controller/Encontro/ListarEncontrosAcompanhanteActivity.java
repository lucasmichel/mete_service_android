package br.uni.mette_service.Controller.Encontro;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.uni.mette_service.R;
import org.json.JSONArray;
import org.json.JSONObject;

import br.uni.mette_service.Controller.LogarAndroidActivity;
import br.uni.mette_service.Controller.Acompanhante.AcompanhanteMenuActivity;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Comentario;
import br.uni.mette_service.Model.Encontro;
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

public class ListarEncontrosAcompanhanteActivity extends ListActivity 
implements OnClickListener{
	//---------
	private Button btnGerenciarEncontro, btnVoltarEncontros;
	Encontro encontroClicado = new Encontro();
	Usuario usuarioLogado = new Usuario();
	
	List<Object> listaObj = new ArrayList<Object>();
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	
	Acompanhante acompanhante = new Acompanhante();
	Acompanhante acompBuscar = new Acompanhante();

	Repositorio repositorio = new Repositorio();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listar_encontros);
	
				
		usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuarioLogado");
		acompanhante = (Acompanhante) getIntent().getSerializableExtra("idAcompanhante");
		
		montarBuscaEncontrosPorAcompanhante(acompanhante);                                    
	}
	
	
	private void montarBuscaEncontrosPorAcompanhante(Acompanhante acompanhante) {
		
		listaObj.clear();
		
		acompBuscar = new Acompanhante();
		acompBuscar.setId(acompanhante.getId());
		listaObj.add(acompBuscar);
		
		modelo.setDados(listaObj);
		modelo.setMensagem("");
		modelo.setStatus("");
		
		new EncontrosPorIdAcompanhanteAsyncTask().execute();           
			
	}


	private void adicionarFindView() {
		this.btnVoltarEncontros = (Button) findViewById(R.id.btnVoltarEncontros);
		this.btnGerenciarEncontro = (Button) findViewById(R.id.btnGerenciarEncontro);
	}
	public void adicionarListers() {
		this.btnGerenciarEncontro.setOnClickListener(this);
		this.btnVoltarEncontros.setOnClickListener(this);
	}

	public void onClick(View vw) {
		Intent it = null;
		switch (vw.getId()) {
		case R.id.btnVoltarEncontros:
			it = new Intent(this, AcompanhanteMenuActivity.class);
			startActivity(it);
			finish();
		break;
		case R.id.btnGerenciarEncontro:
			android.content.DialogInterface.OnClickListener trataDialogAprovar = new android.content.DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					executarAprovarEncontro(encontroClicado);
				}
			};
			
			android.content.DialogInterface.OnClickListener trataDialogRejeitar = new android.content.DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					executarRejeitarEncontro(encontroClicado);
				}
			};

			AlertDialog alert = new AlertDialog.Builder(this)
					.setTitle("Confirmação")
					.setMessage("Deseja realmente excluir?")
					.setPositiveButton("Aprovar", trataDialogAprovar)
					.setNegativeButton("Rejeitar", trataDialogRejeitar).create();
			alert.show();
			break;
		}
	}


	protected void executarRejeitarEncontro(Encontro encontroClicado) {
				
		Encontro encontroRejeitado = new Encontro();
		encontroRejeitado = encontroClicado;
				
		// PRECISO DOS CAMPOS DE ENCONTRO PARA DAR PROSEEGUIMENTO AO MÉTODO.
				
		listaObj.clear();
		
		
		listaObj.add(encontroRejeitado);
				
				
		modelo = new Modelo();
		modelo.setDados(listaObj);
		modelo.setMensagem("");
		modelo.setStatus("");
				
//		new rejeitarEncontroAsyncTask().execute();
				
	}
	
	protected void executarAprovarEncontro(Encontro encontroClicado) {
		
		Encontro encontroAprovado = new Encontro();
		encontroAprovado = encontroClicado;
		
		// PRECISO DOS CAMPOS DE ENCONTRO PARA DAR PROSSEGUIMENTO AO MÉTODO
		
		listaObj.clear();
		listaObj.add(encontroAprovado);
		
		
		modelo = new Modelo();
		modelo.setDados(listaObj);
		modelo.setMensagem("");
		modelo.setStatus("");
		
//		new aprovarEncontroAsyncTask().execute();
		
}
	
	//MÉTODO PARA CRIAR UM OBJETO A PARTIR DO ITEM CLICADO NA LISTA
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		
		super.onListItemClick(l, v, position, id);
		encontroClicado = (Encontro) l.getItemAtPosition(position);
		
	}

		
	class EncontrosPorIdAcompanhanteAsyncTask extends AsyncTask<Void, Void, Modelo> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(
					ListarEncontrosAcompanhanteActivity.this, 
					"Aguarde", "Carregando Encontros...", true, false);
		}

		@Override
		protected Modelo doInBackground(Void... params) {

			modeloRetorno = new Modelo();

			modeloRetorno = repositorio.acessarServidor("listarEncontrosPorIdAcompanhante", modelo);

			return modeloRetorno;
		}

		@Override
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);


			if (modeloRetorno.getStatus().equals("1"))
			{
				Toast toast = Toast.makeText(ListarEncontrosAcompanhanteActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
				finish();
			
			}else{
				
			
			for ( int i = 0; i < result.getDados().size(); ++i){


				Object dadosObject = result.getDados().get(i);	
				Gson gson = new Gson();
				JSONObject jsonObject = null;
				List<Encontro> addEncontro = new ArrayList<Encontro>();

				try {
					JSONArray jsonArray = new JSONArray(gson.toJson(dadosObject));
					for ( int x = 0; x < jsonArray.length(); ++x){
						jsonObject = jsonArray.getJSONObject(x);

						Encontro encontroRetorno = new Encontro();

//						encontroRetorno.setId(jsonObject.getInt("id"));
//						encontroRetorno.setComentario(jsonObject.getString("comentario"));
//						encontroRetorno.setIdAcompanhante(jsonObject.getInt("idAcompanhante"));
//						encontroRetorno.setIdComentario(jsonObject.getInt("idComentario"));
//						


//						Log.i("SOSTENES", i +"..." + comentarioRetorno.getId() + "..."+ comentarioRetorno.getComentario());

						addEncontro.add(encontroRetorno);
					}
					//----
					// FAZ UMA LISTA DOS SERVIÇOS PASSANDO A LISTA DE SERVIÇOS ADICIONADA.
					//----
					setListAdapter(
							new EncontroAdapter(ListarEncontrosAcompanhanteActivity.this, 
									addEncontro));


				}catch (Exception e) {
					e.printStackTrace();			
				}

			}
			dialog.dismiss();
		}
			
		}
	}

}