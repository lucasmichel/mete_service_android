package br.uni.mette_service.Controller.Cliente;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.LogarAndroidActivity;
import br.uni.mette_service.Controller.TermoUsoActivity;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroClienteActivity extends Activity implements OnClickListener {
	
	boolean eEdicao;
	int idClienteEditar = 0;
	Cliente cliente = new Cliente();
	
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();	
	Repositorio repositorio = new Repositorio();
	List<Object> listaCliente = new ArrayList();
	Usuario usuarioLogado = new Usuario();	
	Usuario editadoUsuarioLogado = new Usuario();
	
	private EditText CCnome;
	private EditText CCcpf;
	private EditText CCemail; 
	private EditText CCsenha;
	private EditText ConfSenhaCli;	
	private Button CCavancar;
	private Button CCvoltar;
	private TextView textLinkTermo;
	private CheckBox checkTermoUso;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_cliente);
		//Verifica se a Activity foi chamada para alteração
		eEdicao = getIntent().getBooleanExtra("eEdicao",false);		
		
		usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuarioLogado");					
		adicionarFindView();
		adicionarListers();		
		if (eEdicao){
			executarAlteracao(usuarioLogado);
		}else{
			Toast toast = Toast.makeText(CadastroClienteActivity.this, "Activity NÃO foi chamada para Edição.", Toast.LENGTH_LONG);
			toast.show();
		}
	}
	
	private void adicionarFindView() {
		this.CCavancar = (Button) findViewById(R.id.btnAvancar);
		this.CCvoltar = (Button) findViewById(R.id.btnVoltar);		
		this.CCnome = (EditText) findViewById(R.id.edtNomeCliente);
		this.CCcpf = (EditText) findViewById(R.id.edtCPFCliente);		
		this.CCemail = (EditText) findViewById(R.id.edtEmailCliente);
		this.CCsenha = (EditText) findViewById(R.id.edtSenhaCliente);
		this.ConfSenhaCli = (EditText) findViewById(R.id.edtConfSenhaCliente);
		this.textLinkTermo = (TextView) findViewById(R.id.textoTermoUso);
		this.checkTermoUso = (CheckBox) findViewById(R.id.CBTermoUso);
	}

	public void adicionarListers() {
		this.textLinkTermo.setOnClickListener(this);
		this.CCavancar.setOnClickListener(this);
		this.CCvoltar.setOnClickListener(this);		
	}
	
	public void executarAlteracao(Usuario usuarioLogado){				
			Toast toast = Toast.makeText(CadastroClienteActivity.this, "Activity FOI chamada para Edição.", Toast.LENGTH_LONG);
			toast.show();						
			CCavancar.setText(R.string.alterar);
			listaCliente.clear();
			cliente.setId(usuarioLogado.getIdUsuario());						
			listaCliente.add(cliente);		
			
			modelo.setDados(listaCliente);
			modelo.setMensagem("");
			modelo.setStatus("");									
			new buscarClientePorIdAsyncTask().execute();		
	}
	
	public void onClick(DialogInterface arg0, int arg1) {}
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAvancar:	
			listaCliente.clear();
			
			Validar validar = new Validar();
			Cliente clienteValidado = new Cliente();
			clienteValidado.setNome(CCnome.getText().toString());
			clienteValidado.setCpf(CCcpf.getText().toString());			
			clienteValidado.setEmail(CCemail.getText().toString());
			clienteValidado.setSenha(CCsenha.getText().toString());
			Validar contr = new Validar();
			if (validar.validarCampo(CCnome) && validar.validarCampo(CCcpf)
					&& validar.validarCampo(CCemail)
					&& validar.validarCampo(CCsenha) == true) {
				if (!contr.validarCampos(clienteValidado).toString().equals("CamposValidos")) {					
						AlertDialog dialog = new AlertDialog.Builder(this)
						.setTitle("Notificação")
						.setMessage(contr.validarCampos(clienteValidado))
						.create();
						dialog.show();							
				}
				if (!checkTermoUso.isChecked()){
					Toast.makeText(CadastroClienteActivity.this, "Selecione o Termo!",
							Toast.LENGTH_LONG).show();
				}
				else {
					if (eEdicao)
					{
						
						//
						
						listaCliente.clear();
						Cliente clienteEditar = new Cliente();
						
						clienteEditar.setId(idClienteEditar);
						clienteEditar.setIdUsuario(usuarioLogado.getIdUsuario());
						clienteEditar.setNome(CCnome.getText().toString());
						clienteEditar.setCpf(CCcpf.getText().toString());
						clienteEditar.setEmail(CCemail.getText().toString());
						clienteEditar.setSenha(CCsenha.getText().toString());
						
						Toast toast = Toast.makeText(CadastroClienteActivity.this,"Id: " + clienteEditar.getId() + "IdUsuario: " + clienteEditar.getIdUsuario(), Toast.LENGTH_LONG);
						toast.show();						
						
						listaCliente.add(clienteEditar);
						
						modelo.setDados(listaCliente);
						modelo.setMensagem("");
						modelo.setStatus("");
						
						new editarClienteAsyncTask().execute();
					}else{							
						new cadastroClienteAsyncTask().execute();
					}
				}
				}
			break;
		case R.id.btnVoltar:
			finish();
			break;
		case R.id.textoTermoUso:
			Intent it = new Intent(this, TermoUsoActivity.class);
			startActivity(it);
			break;
		}
	}

	class cadastroClienteAsyncTask extends AsyncTask<String, String, Modelo>  {
		ProgressDialog dialog;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(CadastroClienteActivity.this,
					"Cadastrando...", "Aguarde...",
					true, false);
		}

		@Override
		protected Modelo doInBackground(String... params) {

			Cliente cliente = new Cliente();			
			cliente.setNome(CCnome.getText().toString());
			cliente.setCpf(CCcpf.getText().toString());			
			cliente.setEmail(CCemail.getText().toString());
			cliente.setSenha(CCsenha.getText().toString());
			listaCliente.add(cliente);
			modelo.setDados(listaCliente);
			modelo.setMensagem("");
			modelo.setStatus("");
			try
			{
				modeloRetorno = repositorio.acessarServidor("cadastrarCliente", modelo);
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
				Toast toast = Toast.makeText(CadastroClienteActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
			}else{				
				Intent it = new Intent(CadastroClienteActivity.this, LogarAndroidActivity.class);
				startActivity(it);
				Toast toast = Toast.makeText(CadastroClienteActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
				finish();
			}	
		}
	}
	// buscarClientePorIdUsuario
	class buscarClientePorIdAsyncTask extends AsyncTask<String, String, Modelo>  {
		ProgressDialog dialog;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(CadastroClienteActivity.this,
					"Cadastrando...", "Aguarde...",
					true, false);
		}

		@Override
		protected Modelo doInBackground(String... params) {	
			try
			{
				modeloRetorno = repositorio.acessarServidor("buscarClientePorIdUsuario", modelo);
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
				Toast toast = Toast.makeText(CadastroClienteActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
			}else{				
				Object dadosObject = modeloRetorno.getDados().get(0);
				JSONObject jsonObject = null;
				Gson gson = new Gson();
				
				try {
					jsonObject = new JSONObject(gson.toJson(dadosObject));
					
					Log.i("SOSTENES", "RETORNO PARA MONTAR NA TELA" + gson.toJson(dadosObject));
					CCnome.setText(jsonObject.getString("\u0000Cliente\u0000nome"));					
					CCcpf.setText(jsonObject.getString("\u0000Cliente\u0000cpf"));
					idClienteEditar = jsonObject.getInt("\u0000Cliente\u0000id");
					CCemail.setText(usuarioLogado.getEmail());
					checkTermoUso.setChecked(true);					
					} catch (JSONException e) {
					}				
				
				Toast toast = Toast.makeText(CadastroClienteActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
				
			}	
		}
	}	
	
	/////
	// editarCliente
		class editarClienteAsyncTask extends AsyncTask<String, String, Modelo>  {
			ProgressDialog dialog;
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = ProgressDialog.show(CadastroClienteActivity.this,
						"Cadastrando...", "Aguarde...",
						true, false);
			}

			@Override
			protected Modelo doInBackground(String... params) {	
				try
				{
					modeloRetorno = repositorio.acessarServidor("editarCliente", modelo);
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
					Toast toast = Toast.makeText(CadastroClienteActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
					toast.show();
				}else{
					Object dadosObject = modeloRetorno.getDados().get(1);
					JSONObject jsonObject = null;
					Gson gson = new Gson();
					
					try {
						
						//MONTAR O NOVO USUARIO LOGADO
						
						jsonObject = new JSONObject(gson.toJson(dadosObject));
						
						Log.i("SOSTENES", "RETORNO USUARIO LOGADO: " + gson.toJson(dadosObject));
						
						editadoUsuarioLogado.setEmail(jsonObject.getString("\u0000Usuario\u0000email"));
						editadoUsuarioLogado.setIdUsuario(jsonObject.getInt("\u0000Usuario\u0000id"));
						editadoUsuarioLogado.setSenha(jsonObject.getString("\u0000Usuario\u0000senha"));
						editadoUsuarioLogado.setIdPerfil(usuarioLogado.getIdPerfil());
						
						Log.i("SOSTENES", "RETORNO USUARIO LOGADO" + editadoUsuarioLogado.getEmail() + "" + editadoUsuarioLogado.getIdUsuario());
						
						// RETORNAR PARA O ONACTIVITYRESULT
						Intent intent = getIntent();
						intent.putExtra("editadoUsuarioLogado", editadoUsuarioLogado);
						setResult(RESULT_OK, intent);
						finish();
						
						
						
					}catch (JSONException e) {
						
					
					Toast toast = Toast.makeText(CadastroClienteActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
					toast.show();
					finish();
					
				}	
			}
		}	
		}
		
		//////
}