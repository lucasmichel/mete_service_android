package br.uni.mette_service.Controller.Cliente;

import org.json.JSONException;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.TermoUsoActivity;
import br.uni.mette_service.Model.Cliente;
import br.uni.mette_service.Util.Mask;
import br.uni.mette_service.Util.Validar;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroClienteActivity extends Activity implements
		OnClickListener {

	private Cliente cliente;
	private EditText CCnome, CCcpf, CCtelefone, CCemail, CCsenha, ConfSenhaCli ;
	private Button CCavancar, CCvoltar;
	private TextView textLinkTermo;
	private CheckBox checkTermoUso;
	private boolean atualizar = false;

	//ModelClass clienteRetorno;
	
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
		this.ConfSenhaCli = (EditText) findViewById(R.id.edtConfSenhaCliente);
		
		this.textLinkTermo = (TextView) findViewById(R.id.textoTermoUso);
		this.checkTermoUso = (CheckBox) findViewById(R.id.CBTermoUso);

		textLinkTermo.setOnClickListener(this);

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
			Validar validar = new Validar();
			Cliente clienteValidado = new Cliente();

			clienteValidado.setNome(CCnome.getText().toString());
			clienteValidado.setCpf(CCcpf.getText().toString());
			clienteValidado.setTelefone(CCtelefone.getText().toString());
			clienteValidado.setEmail(CCemail.getText().toString());
			clienteValidado.setSenha(CCsenha.getText().toString());

			Validar contr = new Validar();

			if (validar.validarCampo(CCnome) && validar.validarCampo(CCcpf)
					&& validar.validarCampo(CCtelefone)
					&& validar.validarCampo(CCemail)
					&& validar.validarCampo(CCsenha) == true) {

				if (!contr.validarCampos(clienteValidado).toString().equals("CamposValidos")) {					
						notificar(contr.validarCampos(clienteValidado));
	
						
				}
				if (!checkTermoUso.isChecked()){
					Toast.makeText(CadastroClienteActivity.this, "selecione o termo",
							Toast.LENGTH_LONG).show();
				}
				else {
					new cadastrarClienteAsyncTask().execute();					
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

			Cliente objCliente = new Cliente();
			objCliente.setNome(CCnome.getText().toString());
			objCliente.setCpf(CCcpf.getText().toString());
			objCliente.setTelefone(CCtelefone.getText().toString());
			objCliente.setEmail(CCemail.getText().toString());
			objCliente.setSenha(CCsenha.getText().toString());
//			cliente.setStatus(0);
//			cliente.setMensagem("");

			try {

				// Toast.makeText(getApplicationContext(), "TESTE DA GOMA",
				// Toast.LENGTH_SHORT).show();

				Log.i("envio", objCliente.toString());

				//clienteRetorno = objCliente.cadastrarCliente(objCliente);

				// Toast.makeText(getApplicationContext(),
				// userRetorno.getMensagem().toString(),
				// Toast.LENGTH_LONG).show();


			} catch (Exception e) {
				Log.i("pedro: ", "ERROOO!!" + e);
				e.printStackTrace();
							
			}
			return cliente;
		}

		@Override
		protected void onPostExecute(Cliente result) {
			super.onPostExecute(result);
			dialog.dismiss();						
						
//			if (clienteRetorno.getStatus() == 1){ //SOSTENES/Exibir Alerta...
//
//				Toast toast = Toast.makeText(CadastroClienteActivity.this, clienteRetorno.getMensagem(), Toast.LENGTH_LONG);
//				toast.show();
//			}
//
//			if (clienteRetorno.getStatus() == 0){ //SOSTENES/Cadastrado...
//			
//				Toast toast = Toast.makeText(CadastroClienteActivity.this, clienteRetorno.getMensagem(), Toast.LENGTH_LONG);
//				toast.show();			
//				
//				Intent it = new Intent(CadastroClienteActivity.this, LoginActivity.class);
//				startActivity(it);
				finish();
			}
			
			
		}
	}


