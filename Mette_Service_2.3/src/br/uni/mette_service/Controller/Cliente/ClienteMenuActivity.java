package br.uni.mette_service.Controller.Cliente;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.LogarAndroidActivity;
import br.uni.mette_service.Controller.Acompanhante.ListarAcompanhanteActivity;
import br.uni.mette_service.Controller.Encontro.CadastroEncontroActivity;
import br.uni.mette_service.Controller.Encontro.ListarEncontrosClienteActivity;
import br.uni.mette_service.Controller.Servico.ListaServicosActivity;
import br.uni.mette_service.Mapa.MapaActivity;
import br.uni.mette_service.Model.Cliente;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ClienteMenuActivity extends Activity implements OnClickListener {

	boolean eEdicao = true;
	Usuario usuarioLogado = new Usuario();
	private Button btnEditar;
	private Intent itLogin;
	private AlertDialog alerta;
	private int idCliente;
	
	
	private Cliente cliente;
	private EditText txtNome;
	private EditText txtCpf;
	private EditText txtTelefone;
	private EditText txtEmail;
	private EditText txtSenha;

	private TextView txtUsuarioLogado;
	private Button btnTeste;
	private Button btnop2;
	private Button btnListarServicos, btnListarEncontros;

	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	Repositorio repositorio = new Repositorio();
	List<Object> listaCliente = new ArrayList();
	List<Object> listaObj = new ArrayList<Object>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_cliente);
		usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuarioLogado");
		itLogin = new Intent(this, LogarAndroidActivity.class);
		
		adicionarFindView();
		adicionarListers();
		
		this.txtUsuarioLogado.setText(usuarioLogado.getIdUsuario() + " - Ol�, "
				+ usuarioLogado.getEmail() + "!");
		
		executarBuscarCliente(usuarioLogado);
	}
	
	private void executarBuscarCliente(Usuario usuarioLogado) {

	
		listaObj.clear();
		cliente = new Cliente();
		
		cliente.setId(usuarioLogado.getIdUsuario());
		listaObj.add(cliente);

		modelo.setDados(listaObj);
		modelo.setMensagem("");
		modelo.setStatus("");

		new buscarClientePorIdAsyncTask().execute();

	}

	private void adicionarFindView() {
		this.txtNome = (EditText) findViewById(R.id.edtNomeCliente);
		this.txtCpf = (EditText) findViewById(R.id.edtCPFCliente);
		this.txtEmail = (EditText) findViewById(R.id.edtEmailCliente);
		this.txtSenha = (EditText) findViewById(R.id.edtSenhaCliente);
		this.btnop2 = (Button) findViewById(R.id.btnop2);
		this.txtUsuarioLogado = (TextView) findViewById(R.id.txtUsuarioLogado);
		this.btnTeste = (Button) findViewById(R.id.btnTeste);
		this.btnListarServicos = (Button) findViewById(R.id.btnListarServicos);
		this.btnListarEncontros = (Button) findViewById(R.id.btnListarEncontrosCliente);

	}

	public void adicionarListers() {
		this.btnTeste.setOnClickListener(this);
		this.btnop2.setOnClickListener(this);
		this.btnListarServicos.setOnClickListener(this);
		this.btnListarEncontros.setOnClickListener(this);
	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (resultCode == RESULT_OK && requestCode == 1) {
			Usuario editadoUsuarioLogado = (Usuario) intent
					.getSerializableExtra("editadoUsuarioLogado");
			this.txtUsuarioLogado.setText(editadoUsuarioLogado.getIdUsuario()
					+ " - Ol�, " + editadoUsuarioLogado.getEmail() + "!");
			usuarioLogado = editadoUsuarioLogado;
		}
	}

	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				listaCliente.clear();
				listaCliente.add(usuarioLogado);
				modelo.setDados(listaCliente);
				modelo.setMensagem("");
				modelo.setStatus("");
				new excluirClientePorIdUsuarioAsyncTask().execute();
				break;
			case DialogInterface.BUTTON_NEGATIVE:
				Toast toast = Toast.makeText(ClienteMenuActivity.this,
						"Exclus�o Cancelada", Toast.LENGTH_LONG);
				toast.show();
				break;
			}
		}
	};

	public void onClick(View v) {
		Intent it = null;
		switch (v.getId()) {

		case R.id.btnTeste:
			it = new Intent(this, ListarAcompanhanteActivity.class);
			it.putExtra("usuarioLogado", usuarioLogado);
			startActivity(it);
			break;
		case R.id.btnop2:
			it = new Intent(this, CadastroEncontroActivity.class);
			it.putExtra("usuarioLogado", usuarioLogado);
			startActivity(it);
			break;
		case R.id.btnListarServicos:
			it = new Intent(this, ListaServicosActivity.class);
			startActivity(it);
			break;
		case R.id.btnListarEncontrosCliente:
			Cliente clienteListar = new Cliente();
			clienteListar.setId(idCliente);
			it = new Intent(this, ListarEncontrosClienteActivity.class);
			it.putExtra("usuarioLogado", usuarioLogado);
			it.putExtra("idClienteLogado", clienteListar);
			startActivity(it);
			break;
		}
	}
	
	//BUSCAR CLIENTE POR ID USUARIO
	class buscarClientePorIdAsyncTask extends AsyncTask<String, String, Modelo> {
	ProgressDialog dialog;
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = ProgressDialog.show(ClienteMenuActivity.this,
				"Carregando Dados...", "Aguarde...", true, false);
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
			Toast toast = Toast.makeText(ClienteMenuActivity.this,
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
	
				idCliente = jsonObject.getInt("id");
				
	
			} catch (JSONException e) {
			}
	
			Toast toast = Toast.makeText(ClienteMenuActivity.this,
					modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
			toast.show();
	
		}
	}
	
	}


	// excluirClientePorIdUsuario
	class excluirClientePorIdUsuarioAsyncTask extends
			AsyncTask<String, String, Modelo> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(ClienteMenuActivity.this,
					"Cadastrando...", "Aguarde...", true, false);
		}

		@Override
		protected Modelo doInBackground(String... params) {
			try {
				Log.i("SOSTENES", "excluirClientePorIdUsuario");
				modeloRetorno = repositorio.acessarServidor(
						"excluirClientePorIdUsuario", modelo);
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
				Toast toast = Toast.makeText(ClienteMenuActivity.this,
						"Erro no Servidor " + modeloRetorno.getMensagem(),
						Toast.LENGTH_LONG);
				toast.show();
			} else {
				Toast toast = Toast.makeText(ClienteMenuActivity.this,
						"Tudo Ok " + modeloRetorno.getMensagem(),
						Toast.LENGTH_LONG);
				toast.show();
				finish();
				startActivity(itLogin);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.layout.activity_options_cliente, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent it = null;
		switch (item.getItemId()) {
		case R.id.editarPerfilCliente:
			it = new Intent(this, CadastroClienteActivity.class);
			it.putExtra("usuarioLogado", usuarioLogado);
			it.putExtra("eEdicao", eEdicao);
			startActivityForResult(it, 1);
			break;
		case R.id.ExcluirPerfilCliente:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Voc� realmente deseja excluir sua conta?")
					.setPositiveButton("Sim", dialogClickListener)
					.setNegativeButton("N�o", dialogClickListener).show();
			break;
		case R.id.MapaCliente:
			it = new Intent(this, MapaActivity.class);
			startActivity(it);
			break;
		case R.id.SairCliente:
			AlertDialog.Builder builderSair = new AlertDialog.Builder(
					ClienteMenuActivity.this);
			builderSair.setTitle("J� vai sair ? Fique mais um pouco.");
			builderSair.setMessage("Deseja realmente sair da aplica��o?");
			builderSair.setPositiveButton("Sim",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							startActivity(itLogin);
							Toast.makeText(ClienteMenuActivity.this,
									"Bye..Bye", Toast.LENGTH_LONG).show();
							finish();

						}
					});
			builderSair.setNegativeButton("N�o",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
						}
					});
			alerta = builderSair.create();
			alerta.show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}