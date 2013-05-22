package br.uni.mette_service.Controller.Cliente;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Acompanhante.ListarAcompanhanteActivity;
import br.uni.mette_service.Controller.Cliente.CadastroClienteActivity.buscarClientePorIdAsyncTask;
import br.uni.mette_service.Controller.Encontro.CadastroEncontroActivity;
import br.uni.mette_service.Controller.Servico.ListaServicosActivity;
import br.uni.mette_service.Mapa.MapaActivity;
import br.uni.mette_service.Model.Cliente;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;
import br.uni.mette_service.Util.PreferencesController;
import android.app.Activity;
import android.app.ProgressDialog;
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
	
	private Cliente cliente;
	private EditText txtNome; 
	private EditText txtCpf; 
	private EditText txtTelefone; 
	private EditText txtEmail; 
	private EditText txtSenha;
		
	private TextView txtUsuarioLogado;	
	private Button btnTeste;
	private Button btnAvancar;
	private Button btnVoltar;
	private Button btnExcluir;
	private Button btnop2;
	private Button btnop3;	
	private Button btnMapa;
	private Button btnListarServicos;	
	
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();	
	Repositorio repositorio = new Repositorio();
	List<Object> listaCliente = new ArrayList();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);		
		usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuarioLogado");										
		adicionarFindView();			
		adicionarListers();
		this.txtUsuarioLogado.setText(usuarioLogado.getIdUsuario() + " - Olá, " + usuarioLogado.getEmail() + "!");
	}
	
	private void adicionarFindView() {
		this.txtNome = (EditText) findViewById(R.id.edtNomeCliente);
		this.txtCpf = (EditText) findViewById(R.id.edtCPFCliente);		
		this.txtEmail = (EditText) findViewById(R.id.edtEmailCliente);
		this.txtSenha = (EditText) findViewById(R.id.edtSenhaCliente);		
		this.btnop2 = (Button) findViewById(R.id.btnop2);
		this.btnEditar = (Button) findViewById(R.id.btneditar);
		this.btnExcluir = (Button) findViewById(R.id.btnop4);
		this.btnMapa = (Button) findViewById(R.id.btnMapa);
		this.txtUsuarioLogado = (TextView) findViewById(R.id.txtUsuarioLogado);
		this.btnTeste = (Button) findViewById(R.id.btnTeste);
		this.btnListarServicos = (Button) findViewById(R.id.btnListarServicos);
	}
	
	public void adicionarListers() {		
		this.btnTeste.setOnClickListener(this);
		this.btnop2.setOnClickListener(this);
		this.btnExcluir.setOnClickListener(this);
		this.btnEditar.setOnClickListener(this);
		this.btnMapa.setOnClickListener(this);
		this.btnListarServicos.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent it = null;
		switch (v.getId()) {
		case R.id.btneditar:
			it = new Intent(this, CadastroClienteActivity.class);		
			it.putExtra("usuarioLogado", usuarioLogado);	
			it.putExtra("eEdicao", eEdicao);			
			startActivity(it);
			break;		
		case R.id.btnop4:			
			Toast toast = Toast.makeText(ClienteMenuActivity.this, "Chamar Excluir", Toast.LENGTH_LONG);
			toast.show();			
			
			listaCliente.clear();			
			listaCliente.add(usuarioLogado);
			
			modelo.setDados(listaCliente);
			modelo.setMensagem("");
			modelo.setStatus("");			
			
			new excluirClientePorIdUsuarioAsyncTask().execute();
			
			break;	
		case R.id.btnTeste:
			it = new Intent(this, ListarAcompanhanteActivity.class);
			it.putExtra("usuarioLogado", usuarioLogado);
			startActivity(it);
			break;
		case R.id.btnop2:
			it = new Intent(this, CadastroEncontroActivity.class);			
			startActivity(it);
			break;
		case R.id.btnListarServicos:
			it = new Intent(this, ListaServicosActivity.class);					
			startActivity(it);
			break;
		case R.id.btnMapa:
			it = new Intent(this, MapaActivity.class);					
			startActivity(it);
			break;
		}				
	}	
	
	// excluirClientePorIdUsuario
		class excluirClientePorIdUsuarioAsyncTask extends AsyncTask<String, String, Modelo>  {
			ProgressDialog dialog;
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = ProgressDialog.show(ClienteMenuActivity.this,
						"Cadastrando...", "Aguarde...",
						true, false);
			}

			@Override
			protected Modelo doInBackground(String... params) {	
				try
				{
					Log.i("SOSTENES", "excluirClientePorIdUsuario");
					modeloRetorno = repositorio.acessarServidor("excluirClientePorIdUsuario", modelo);
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
					Toast toast = Toast.makeText(ClienteMenuActivity.this, "Erro no Servidor " + modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
					toast.show();
				}else{								
					Toast toast = Toast.makeText(ClienteMenuActivity.this, "Tudo Ok " + modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
					toast.show();					
				}	
			}
		}	
		// Fim / excluirClientePorIdUsuario
}