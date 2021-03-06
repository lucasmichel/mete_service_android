package br.uni.mete_service.Controller.Cliente;

import org.json.JSONException;

import br.uni.mete_service.R;

import br.uni.mete_service.Controller.HomeActivity;
import br.uni.mete_service.Controller.LoginActivity;
import br.uni.mete_service.model.Cliente;
import br.uni.mete_service.model.repositorio.Cliente.RepositorioCliente;
import br.uni.mete_service.util.Mask;
import br.uni.mete_service.util.PreferencesController;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroClienteActivity extends Activity implements
		OnClickListener {

	private Cliente cliente;
	private EditText CCnome, CCcpf, CCtelefone, CCemail, CCsenha;
	private Button CCavancar, CCvoltar;
	private boolean atualizar = false;

	Cliente clienteRetorno = new Cliente();
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_cliente);

		this.CCavancar = (Button) findViewById(R.id.btnAvancar);
		this.CCvoltar = (Button) findViewById(R.id.btnVoltar);
		this.CCavancar.setOnClickListener(this);
		this.CCvoltar.setOnClickListener(this);

		this.CCnome = (EditText) findViewById(R.id.edtNomeCliente);
		this.CCcpf = (EditText) findViewById(R.id.edtCPFCliente);
		// se usar vai passar os .(ponto) para a validação
		// CCcpf.addTextChangedListener(Mask.insert("###.###.###-##", CCcpf));
		this.CCtelefone = (EditText) findViewById(R.id.edtTelefoneCliente);
		CCtelefone.addTextChangedListener(Mask.insert("(###)####-####",CCtelefone));
		this.CCemail = (EditText) findViewById(R.id.edtEmailCliente);
		this.CCsenha = (EditText) findViewById(R.id.edtSenhaCliente);

		inicializacaoVerificacao();

	}

	private void inicializacaoVerificacao() {

		if (getIntent().getSerializableExtra("cliente") != null) {
			cliente = (Cliente) getIntent().getSerializableExtra("cliente");
			this.CCnome.setText(cliente.getNome());
			this.CCcpf.setText(cliente.getCpf());
			this.CCtelefone.setText(cliente.getTelefone());
			this.CCemail.setText(cliente.getEmail());
			this.CCsenha.setText(cliente.getSenha());
			atualizar = true;
		}
	}

	public void notificar(String s){		
		AlertDialog dialog = new AlertDialog.Builder(this)
		.setTitle("Notificação")
		.setMessage(s)
		.create();
		dialog.show();		
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAvancar:
			ValidarCliente validar = new ValidarCliente();
			Cliente clienteValidado = new Cliente();

			clienteValidado.setNome(CCnome.getText().toString());
			clienteValidado.setCpf(CCcpf.getText().toString());
			clienteValidado.setTelefone(CCtelefone.getText().toString());
			clienteValidado.setEmail(CCemail.getText().toString());
			clienteValidado.setSenha(CCsenha.getText().toString());

			ValidarCliente contr = new ValidarCliente();

			if (validar.validarCampo(CCnome) && validar.validarCampo(CCcpf)
					&& validar.validarCampo(CCtelefone)
					&& validar.validarCampo(CCemail)
					&& validar.validarCampo(CCsenha) == true) {

				if (!contr.validarCampos(clienteValidado).toString().equals("CamposValidos")) {					
						notificar(contr.validarCampos(clienteValidado));				
				} else {
					new cadastrarClienteAsyncTask().execute();					
					
					Log.i("SOSTENES", "dps de chamar task): " + clienteRetorno.getStatus());
					Log.i("SOSTENES", "dps de chamar task): " + clienteRetorno.getMensagem());
				}
			}
			break;
		case R.id.btnVoltar:
			finish();
			break;
		default:
			break;
		}
	}

	class cadastrarClienteAsyncTask extends AsyncTask<String, String, Cliente> {
		ProgressDialog dialog;
		//Cliente clienteRetorno = new Cliente();

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(CadastroClienteActivity.this,"Calma amiguinho.", "Salvando", true, false);
		}

		@Override
		protected Cliente doInBackground(String... params) {

			Cliente cliente = new Cliente();
			cliente.setNome(CCnome.getText().toString());
			cliente.setCpf(CCcpf.getText().toString());
			cliente.setTipo("1");
			cliente.setTelefone(CCtelefone.getText().toString());
			cliente.setEmail(CCemail.getText().toString());
			cliente.setSenha(CCsenha.getText().toString());
			cliente.setStatus(0);
			cliente.setMensagem("");

			try {

				Log.i("CPFF", "cpf: " + cliente.getCpf() + "telefone: "	+ cliente.getTelefone());
				
				clienteRetorno = cliente.cadastrarCliente(cliente);
				
				

			} catch (JSONException e) {
				Log.i("pedro: ", "ERROOO!!" + e);
				e.printStackTrace();
							
			}
			return cliente;
		}

		@Override
		protected void onPostExecute(Cliente result) {
			super.onPostExecute(result);
			dialog.dismiss();						
						
			if (clienteRetorno.getStatus() == 1){ //SOSTENES/Exibir Alerta...

				Toast toast = Toast.makeText(CadastroClienteActivity.this, clienteRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
			}

			if (clienteRetorno.getStatus() == 0){ //SOSTENES/Cadastrado...
			
				Toast toast = Toast.makeText(CadastroClienteActivity.this, clienteRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();			
				
				Intent it = new Intent(CadastroClienteActivity.this, LoginActivity.class);
				startActivity(it);
				finish();
			}
			
			
		}
	}

}
