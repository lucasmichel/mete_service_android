package br.uni.mette_service.Controller.Acompanhante;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.LogarAndroidActivity;

import br.uni.mette_service.Controller.Avaliacao.ListarAvaliacaoActivity;
import br.uni.mette_service.Controller.Comentario.CadastroComentarioActivity;
import br.uni.mette_service.Controller.Comentario.ListarComentarioActivity;
import br.uni.mette_service.Controller.Encontro.ListarEncontrosAcompanhanteActivity;
import br.uni.mette_service.Controller.Servico.CadastroServicoActivity;

import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.ls.LSInput;

import com.google.gson.Gson;

import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AcompanhanteMenuActivity extends Activity implements
		OnClickListener {

	boolean eEdicao = true;
	Usuario usuarioLogado = new Usuario();
	Usuario editadoUsuarioLogado = new Usuario();
	private AlertDialog alerta;
	private Intent itLogin;
	private Button btnAlterarStatus;
	private Button btnCadastrarServico;
	private Button btnMeusServicos;
	private Button btnListarEncontros, btnListarAvaliacoes, btnListarComentarios;
	private int idAcompanhante;
	private TextView txtUsuarioLogado;
	List<Object> listaobj = new ArrayList<Object>();
	Acompanhante acompanhanteRetorno, acompanhanteLogada;
	Repositorio repositorio = new Repositorio();
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	Acompanhante acompExcluir = new Acompanhante();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_acompanhante);

		usuarioLogado = (Usuario) getIntent().getSerializableExtra(
				"usuarioLogado");
		
		adicionarFindView();
		adicionarListers();
		
		this.txtUsuarioLogado.setText(usuarioLogado.getIdUsuario() + " - Olá, "
				+ usuarioLogado.getEmail() + "!");

		executarBuscarAcompanhante(usuarioLogado);

		Log.i("SOSTENES", "ID ACOMPANHANTE EXCLUIR: " + idAcompanhante);

	}

	private void executarExcluirAcompanhantePorIdUsuario(Usuario usuarioLogado) {

		Usuario usuarioExcluir = new Usuario();
		usuarioExcluir.setIdUsuario(usuarioLogado.getIdUsuario());

		listaobj.clear();
		listaobj.add(usuarioExcluir);

		modelo.setDados(listaobj);
		modelo.setMensagem("");
		modelo.setStatus("");

		new excluirAcompanhanteAsyncTask().execute();

	}

	private void executarBuscarAcompanhante(Usuario usuarioLogado) {

		listaobj.clear();
		acompExcluir.setId(usuarioLogado.getIdUsuario());
		listaobj.add(acompExcluir);

		modelo.setDados(listaobj);
		modelo.setMensagem("");
		modelo.setStatus("");

		new buscarAcompanhantePorIdAsyncTask().execute();

	}

	private void executarExcluirAcompanhante(int idAcompanhante) {

		acompExcluir = new Acompanhante();
		acompExcluir.setId(idAcompanhante);

		listaobj.clear();
		listaobj.add(acompExcluir);
		modelo.setDados(listaobj);
		modelo.setMensagem("");
		modelo.setStatus("");

		new excluirAcompanhanteAsyncTask().execute();

	}

	private void adicionarListers() {
			
		this.btnListarEncontros.setOnClickListener(this);
		this.btnListarComentarios.setOnClickListener(this);
		this.btnListarAvaliacoes.setOnClickListener(this);
		this.btnCadastrarServico.setOnClickListener(this);
		this.btnMeusServicos.setOnClickListener(this);
	}

	private void adicionarFindView() {

		
		this.btnListarEncontros = (Button) findViewById(R.id.btnListarEncontros);
		this.btnListarComentarios = (Button) findViewById(R.id.btnListarComentarios);
		this.btnListarAvaliacoes = (Button) findViewById(R.id.btnListarAvaliacoes);
		this.btnCadastrarServico = (Button) findViewById(R.id.btnCadastrarServico);
		this.txtUsuarioLogado = (TextView) findViewById(R.id.txtUsuarioLogadoAcomp);
		this.btnMeusServicos = (Button) findViewById(R.id.btnMeusServicos);
	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (resultCode == RESULT_OK && requestCode == 1) {
			Usuario editadoUsuarioLogado = (Usuario) intent
					.getSerializableExtra("editadoUsuarioLogado");
			this.txtUsuarioLogado.setText(editadoUsuarioLogado.getIdUsuario()
					+ " - Olá, " + editadoUsuarioLogado.getEmail() + "!");

			usuarioLogado = editadoUsuarioLogado;

		}
	}

	// PASSAR O CLICK DOS BOTOES PARA ONCLIKLISTENER

	public void onClick(DialogInterface arg0, int arg1) {
	}

	public void onClick(View v) {
		Intent it = null;
		switch (v.getId()) {
//		case R.id.btnAlterarStatus:
//			it = new Intent(this, AlterarStatusActivity.class);
//			startActivity(it);
//			break;
		case R.id.btnListarComentarios:
			Log.i("SOSTENES", "Entrou no listar");
			Acompanhante acomp1 = new Acompanhante();
			acomp1.setId(idAcompanhante);
			it = new Intent(this, ListarComentarioActivity.class);
			it.putExtra("idAcompanhante", acomp1);
			startActivity(it);
			break;
			
		case R.id.btnListarAvaliacoes:	
			Log.i("SOSTENES", "Entrou no listarAvaliacoes");
			Acompanhante acomp3 = new Acompanhante();
			acomp3.setId(idAcompanhante);
			it = new Intent(this, ListarAvaliacaoActivity.class);
			it.putExtra("idAcompanhante", acomp3);
			startActivity(it);
			break;
			
		case R.id.btnListarEncontros:
			Acompanhante acomp4 = new Acompanhante();
			acomp4.setId(idAcompanhante);
			it = new Intent(this, ListarEncontrosAcompanhanteActivity.class);
			it.putExtra("usuarioLogado", usuarioLogado);
			it.putExtra("idAcompanhante", acomp4);
			startActivity(it);
			break;
			
		case R.id.btnCadastrarServico:
			it = new Intent(this, CadastroServicoActivity.class);
			Acompanhante acompCadasServico = new Acompanhante();
			acompCadasServico.setId(idAcompanhante);
			it.putExtra("acompanhanteLogada", acompCadasServico);
			startActivity(it);
			break;
			
		case R.id.btnMeusServicos:
			// boolean para saber na tela de listar serviços que chamou
			boolean listarServicosAcomp = true;
			it = new Intent(this, ListaServicosAcompActivity.class);
			Acompanhante acomp = new Acompanhante();
			acomp.setId(idAcompanhante);
			it.putExtra("idAcompanhante", acomp);
			it.putExtra("acompanhanteListarServicos", listarServicosAcomp);
			startActivity(it);

			break;
		}

	}

	class excluirAcompanhanteAsyncTask extends
			AsyncTask<String, String, Modelo> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(AcompanhanteMenuActivity.this,
					"Excluindo Perfil...", "Aguarde !", true, false);
		}

		@Override
		protected Modelo doInBackground(String... params) {
			try {
				modeloRetorno = repositorio.acessarServidor(
						"excluirAcompanhantePorIdUsuario", modelo);
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
				Toast toast = Toast.makeText(AcompanhanteMenuActivity.this,
						modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
				finish();

			} else {
				Intent it = new Intent(AcompanhanteMenuActivity.this,
						LogarAndroidActivity.class);
				startActivity(it);
				Toast toast = Toast.makeText(AcompanhanteMenuActivity.this,
						modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
				finish();
			}
		}
	}

	class buscarAcompanhantePorIdAsyncTask extends
			AsyncTask<String, String, Modelo> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(AcompanhanteMenuActivity.this,
					"Carregando Dados...", "Aguarde...", true, false);
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
			dialog.dismiss();
			if (modeloRetorno.getStatus().equals("1")) {
				Toast toast = Toast.makeText(AcompanhanteMenuActivity.this,
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

					idAcompanhante = jsonObject.getInt("id");
					

				} catch (JSONException e) {
				}

				Toast toast = Toast.makeText(AcompanhanteMenuActivity.this,
						modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();

			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.layout.activity_options_acomp, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent it = null;
		switch (item.getItemId()) {
		case R.id.editarPerfil:
			it = new Intent(this, CadastroAcompanhanteActivity.class);
			it.putExtra("usuarioLogado", usuarioLogado);
			it.putExtra("eEdicao", eEdicao);
			startActivityForResult(it, 1);
			break;
		case R.id.ExcluirPerfil:
			android.content.DialogInterface.OnClickListener trataDialog = new android.content.DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					executarExcluirAcompanhantePorIdUsuario(usuarioLogado);
				}
			};

			AlertDialog alert = new AlertDialog.Builder(this)
					.setTitle("Confirmação")
					.setMessage("Deseja realmente excluir?")
					.setPositiveButton("Sim", trataDialog)
					.setNegativeButton("Não", null).create();
			alert.show();
			break;
		case R.id.enviarFotos:
			it = new Intent(this, FotoAcompanhanteActivity.class);
			it.putExtra("usuarioLogado", usuarioLogado);
			startActivity(it);
			break;
		case R.id.exibirGaleria:
			it = new Intent(this, GaleriaFotosActivity.class);
			it.putExtra("usuarioLogado", usuarioLogado);
			startActivity(it);
			break;
		case R.id.Sair:
			AlertDialog.Builder builder = new AlertDialog.Builder(AcompanhanteMenuActivity.this);
			builder.setTitle("Já vai sair ? Fique mais um pouco.").setIcon(R.drawable.dialog_stop);
			builder.setMessage("Deseja realmente sair da aplicação?");
			builder.setPositiveButton("Sim",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							itLogin = new Intent(AcompanhanteMenuActivity.this,LogarAndroidActivity.class);
							startActivity(itLogin);
							Toast.makeText(AcompanhanteMenuActivity.this,"Bye..Bye", Toast.LENGTH_LONG).show();
							finish();
						}
					});
			builder.setNegativeButton("Não",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
						}
					});
			alerta = builder.create();
			alerta.show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
