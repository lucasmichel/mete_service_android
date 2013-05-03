package br.uni.mette_service.Controller.Cliente;

import java.util.ArrayList;
import java.util.List;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroClienteActivity extends Activity implements OnClickListener {

	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();	
	Repositorio repositorio = new Repositorio();
	List<Object> listaCliente = new ArrayList();
	Usuario usuarioLogado = null;	
	
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
		
		
		adicionarFindView();
		adicionarListers();
		
		verificarSeHaAlteracao(usuarioLogado);
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
	
	public void verificarSeHaAlteracao(Usuario u){		
		if (u != null){
			listaCliente.clear();
			listaCliente.add(u);
			
			modelo.setDados(listaCliente);
			modelo.setMensagem("");
			modelo.setStatus("");
			
			modeloRetorno = repositorio.acessarServidor("buscarClientePorId", modelo);
			
			
		}		
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
					new cadastroClienteAsyncTask().execute();}
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

	class cadastroClienteAsyncTask extends AsyncTask<String, String, Modelo> {
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
}