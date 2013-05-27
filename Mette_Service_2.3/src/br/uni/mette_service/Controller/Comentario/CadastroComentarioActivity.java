package br.uni.mette_service.Controller.Comentario;

import java.util.ArrayList;
import java.util.List;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.LogarAndroidActivity;
import br.uni.mette_service.Controller.Cliente.CadastroClienteActivity;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Cliente;
import br.uni.mette_service.Model.Comentario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;
import br.uni.mette_service.Util.Validar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroComentarioActivity extends Activity implements OnClickListener {
	
	EditText edtComentario;
	Button btnComentarioSalvar;
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	Comentario comentario = new Comentario();
	Comentario comentarioClicado = new Comentario();
	List<Object> listaObj = new  ArrayList<Object>();
	Acompanhante acompanhanteClicada = new Acompanhante();
	Repositorio repositorio = new Repositorio();

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_comentario);
		
		adicionarfindView();
		adicionarListers();
		
		acompanhanteClicada =  (Acompanhante) getIntent().getSerializableExtra("acompanhanteSelecionada");
		comentarioClicado =  (Comentario) getIntent().getSerializableExtra("comentarioClicado");
	}
	
	private void executarCadastroComentario(Comentario comentario) {
		
		listaObj.clear();
		
		listaObj.add(comentario);
		
		modelo.setDados(listaObj);
		modelo.setMensagem("");
		modelo.setStatus("");
		
		new cadastroComentarioAsyncTask().execute();
		
		
	}

	private void adicionarListers() {
		this.btnComentarioSalvar.setOnClickListener(this);
	}

	private void adicionarfindView() {
		
		edtComentario = (EditText)findViewById(R.id.editComentario);
		btnComentarioSalvar = (Button)findViewById(R.id.btnComentarioSalvar);
		comentarioClicado = null;
	}

	public void onClick(DialogInterface arg0, int arg1) {}
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnComentarioSalvar:
			
			Validar validar = new Validar();
			comentario = new Comentario();
			
			if (validar.validarCampo(edtComentario) == true) {
				
				comentario.setId(0);
				comentario.setIdAcompanhante(acompanhanteClicada.getId());
				
				if (comentarioClicado == null) {
					
					comentario.setIdComentario(0);
					
				}else{
					
					comentario.setIdComentario(comentarioClicado.getIdComentario());
				}
				
				comentario.setComentario(edtComentario.getText().toString());			
				executarCadastroComentario(comentario);		
			}
		break;
		case R.id.btnSairComentario:
			finish();
		break;
		
		}
		}

	class cadastroComentarioAsyncTask extends AsyncTask<String, String, Modelo>  {
		ProgressDialog dialog;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(CadastroComentarioActivity.this,
					"Cadastrando...", "Aguarde...",
					true, false);
		}

		@Override
		protected Modelo doInBackground(String... params) {

			try
			{
				modeloRetorno = repositorio.acessarServidor("cadastrarComentario", modelo);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			return modeloRetorno;
		}

		@Override
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if (modeloRetorno.getStatus().equals("1"))
			{
				Toast toast = Toast.makeText(CadastroComentarioActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
			}else{				
				Toast toast = Toast.makeText(CadastroComentarioActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
				finish();
			}	
		}
	}
	}