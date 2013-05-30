package br.uni.mette_service.Controller.Avaliacao;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.LogarAndroidActivity;
import br.uni.mette_service.Controller.TermoUsoActivity;
import br.uni.mette_service.Controller.Cliente.CadastroClienteActivity;
import br.uni.mette_service.Controller.Comentario.CadastroComentarioActivity;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.AvaliacaoAcompanhante;
import br.uni.mette_service.Model.Cliente;
import br.uni.mette_service.Model.Usuario;
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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class CadastroAvaliacaoActivity extends Activity implements
		OnClickListener {

	// ////////////////////////////////////

	AvaliacaoAcompanhante avaliacaoAcomp = new AvaliacaoAcompanhante();
	Usuario usuarioLogado = new Usuario();

	Acompanhante acompanhanteClicada = new Acompanhante();
	Acompanhante acompanhanteId = new Acompanhante();
	float f;

	public int clienteId;
	Cliente cliente = new Cliente();

	ArrayList<Object> listaObj = new ArrayList<Object>();

	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();

	Repositorio repositorio = new Repositorio();

	// ////////////////////////////////// VARIÁVEIS REFERENTES AO LAYOUT

	Button btnAvaliarAcompanhante;
	Button btnSair;
	RatingBar rtngNota;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_avaliar_acompanhante);

		adicionarFindView();
		adicionarListers();

		usuarioLogado = (Usuario) getIntent().getSerializableExtra(
				"usuarioLogado");
		acompanhanteClicada = (Acompanhante) getIntent().getSerializableExtra(
				"acompanhanteSelecionada");
		
		executarBuscaCliente(usuarioLogado);

	}

	private void adicionarListers() {
		this.btnAvaliarAcompanhante.setOnClickListener(this);
		this.btnSair.setOnClickListener(this);
		this.rtngNota.setOnClickListener(this);

	}

	private void adicionarFindView() {
		this.btnAvaliarAcompanhante = (Button) findViewById(R.id.btnAvaliarAcomp);
		this.btnSair = (Button) findViewById(R.id.btnSairAvaliar);
		this.rtngNota = (RatingBar) findViewById(R.id.ratingBarAvaliacao);

	}

	private void executarBuscaCliente(Usuario usuarioLogado) {
		// Toast toast = Toast.makeText(CadastroAcompanhanteActivity.this,
		// "Activity FOI chamada para Edição.", Toast.LENGTH_LONG);
		// toast.show();

		listaObj.clear();
		cliente = new Cliente();
		
		
		cliente.setId(usuarioLogado.getIdUsuario());
		listaObj.add(cliente);

		modelo.setDados(listaObj);
		modelo.setMensagem("");
		modelo.setStatus("");
		
		new buscarClientePorIdAsyncTask().execute();

	}

	private void executarCadastroAvaliacao(AvaliacaoAcompanhante avaliacao) {

		listaObj.clear();

		listaObj.add(avaliacao);
		modelo.setDados(listaObj);
		modelo.setMensagem("");
		modelo.setStatus("");

		new cadastroAvaliacaoAsyncTask().execute();

	}

	class buscarClientePorIdAsyncTask extends AsyncTask<String, String, Modelo> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(CadastroAvaliacaoActivity.this,
					"Cadastrando...", "Aguarde...", true, false);
		}

		@Override
		protected Modelo doInBackground(String... params) {
			try {
				modeloRetorno = repositorio.acessarServidor(
						"buscarClientePorIdUsuario", modelo);
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
				Toast toast = Toast.makeText(CadastroAvaliacaoActivity.this,
						modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
			} else {
				Object dadosObject = modeloRetorno.getDados().get(0);
				JSONObject jsonObject = null;
				Gson gson = new Gson();

				try {
					jsonObject = new JSONObject(gson.toJson(dadosObject));

					Log.i("SOSTENES",
							"RETORNO PARA MONTAR NA TELA"
									+ gson.toJson(dadosObject));

					clienteId = jsonObject.getInt("id");

				} catch (JSONException e) {
				}

				Toast toast = Toast.makeText(CadastroAvaliacaoActivity.this,
						modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();

			}
		}
	}

	// cadastroAvaliacao

	class cadastroAvaliacaoAsyncTask extends AsyncTask<String, String, Modelo> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(CadastroAvaliacaoActivity.this,
					"Cadastrando...", "Aguarde...", true, false);
		}

		@Override
		protected Modelo doInBackground(String... params) {

			try {
				modeloRetorno = repositorio.acessarServidor(
						"cadastrarAvaliacao", modelo);
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
				Toast toast = Toast.makeText(CadastroAvaliacaoActivity.this,
						modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
			} else {

				Toast toast = Toast.makeText(CadastroAvaliacaoActivity.this,
						modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
				finish();
			}
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAvaliarAcomp:

			f = rtngNota.getRating();
			if (f == 0) {
				Toast toast = Toast.makeText(CadastroAvaliacaoActivity.this,
						"Avalie a Acompanhante!", Toast.LENGTH_LONG);
				toast.show();
			} else {

				AvaliacaoAcompanhante avaliacao = new AvaliacaoAcompanhante();
				f = rtngNota.getRating();

				avaliacao.setId(0);
				avaliacao.setNota((int) f);
				avaliacao.setIdCliente(clienteId);
				avaliacao.setIdAcompanhante(acompanhanteClicada.getId());

				executarCadastroAvaliacao(avaliacao);
			}
			break;
		// case R.id.btnSair:
		// finish();
		// break;
		}
	}

}
