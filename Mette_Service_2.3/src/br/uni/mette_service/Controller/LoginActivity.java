package br.uni.mette_service.Controller;

import java.util.Date;

import java.util.Map;

import org.json.JSONException;

import br.uni.mette_service.R;
import br.uni.mette_service.R.id;
import br.uni.mette_service.R.layout;
import br.uni.mette_service.Model.Cliente;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Util.PreferencesController;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
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

public class LoginActivity extends Activity implements OnClickListener {

	private EditText edtEmail, edtSenha;
	private TextView txtLinkCadastro;
	private Button btnLogin;	
	private Map<String, String> userValuesMap;
	final Context context = this;
	
	Cliente clienteRetorno = new Cliente();
	Cliente cliente = new Cliente();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		edtEmail = (EditText) findViewById(R.id.loginEmail);
		edtSenha = (EditText) findViewById(R.id.loginSenha);
		btnLogin = (Button) findViewById(R.id.btnEntrar);
		txtLinkCadastro = (TextView) findViewById(R.id.textoCadastre);

		btnLogin.setOnClickListener(this);
		txtLinkCadastro.setOnClickListener(this);
	}

	@SuppressLint("NewApi")
	public String validarCampos(){
		
		String verificacao;
		verificacao = "CamposValidos";
				
		if (edtEmail.getText().toString().equals("") && !(edtSenha.getText().toString().equals(""))){
			verificacao = "ATENÇÃO: Campo: E-mail em Branco!";
		}
				
		if (edtSenha.getText().toString().equals("") && !(edtEmail.getText().toString().equals(""))){
			verificacao = "ATENÇÃO: Campo: Senha em Branco!";
		}
		
		if (edtEmail.getText().toString().equals("") && edtSenha.getText().toString().equals("")){
			verificacao = "ATENÇÃO: Campos: E-mail e Senha em Branco!";
		}
		
		return verificacao;
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btnEntrar:
			if (!validarCampos().toString().equals("CamposValidos")){				
						AlertDialog dialog = new AlertDialog.Builder(this).
						setTitle("Notificação").
						setMessage(validarCampos()).		
						create();
						dialog.show();																		
			}else{
				
				new logarAsysncTask().execute();
							
			}
			break;
		case R.id.textoCadastre:
			Intent it = new Intent(this, EscolhaDoUsuarioActivity.class);
			startActivity(it);
			break;
		}
	}
	public void logar() {
		Intent it;		
		
		cliente.setEmail(edtEmail.getText().toString());
		cliente.setSenha(edtSenha.getText().toString());	
		cliente.setStatus(0);
		cliente.setMensagem("");
		
		
		try {			
			clienteRetorno = cliente.logarAndroid(cliente);							 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		
	
		
	}
	
	class logarAsysncTask extends AsyncTask<Void, Void, Usuario>{

		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(LoginActivity.this, "Carregando",
					"Seu login logo sera concluido.", true, false);
			super.onPreExecute();
		}
		
		@Override
		protected Usuario doInBackground(Void... params) {
			logar();		
			
			Log.i("SOSTENES", "Retorno Json (cliente.getStatus()): " + clienteRetorno.getStatus());
			Log.i("SOSTENES", "Retorno Json (cliente.getMensagem()): " + clienteRetorno.getMensagem());
			
			return clienteRetorno;
			
			
		}
		
		@Override
		protected void onPostExecute(Usuario result) {
			super.onPostExecute(result);
			dialog.dismiss();

			if (clienteRetorno.getStatus() == 1){ //SOSTENES/Exibir Alerta...
	//
				Toast toast = Toast.makeText(LoginActivity.this, clienteRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();					
			}

			if (clienteRetorno.getStatus() == 0){ //SOSTENES/Logar...
						
				PreferencesController.setUserPreferences(LoginActivity.this, edtEmail.getText()
						.toString(), edtSenha.getText().toString());
				Intent it = new Intent(LoginActivity.this, HomeActivity.class);			
				it.putExtra("usuarioLogado", edtEmail.getText().toString());
				startActivity(it);
				
				
				finish();
			}
		}
	}

}
