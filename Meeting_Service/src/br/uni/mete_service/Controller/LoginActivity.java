package br.uni.mete_service.Controller;

import java.util.Date;

import java.util.Map;

import org.json.JSONException;
import br.uni.mete_service.R;
import br.uni.mete_service.Controller.Cliente.CadastroClienteActivity;
import br.uni.mete_service.model.Cliente;
import br.uni.mete_service.model.Usuario;

import br.uni.mete_service.model.repositorio.Cliente.RepositorioCliente;
import br.uni.mete_service.util.PreferencesController;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
			verificacao = "ATEN��O: Campo: E-mail em Branco!";
		}
				
		if (edtSenha.getText().toString().equals("") && !(edtEmail.getText().toString().equals(""))){
			verificacao = "ATEN��O: Campo: Senha em Branco!";
		}
		
		if (edtEmail.getText().toString().equals("") && edtSenha.getText().toString().equals("")){
			verificacao = "ATEN��O: Campos: E-mail e Senha em Branco!";
		}
		
		return verificacao;
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btnEntrar:
			if (!validarCampos().toString().equals("CamposValidos")){				
						AlertDialog dialog = new AlertDialog.Builder(this).
						setTitle("Notifica��o").
						setMessage(validarCampos()).		
						create();
						dialog.show();																		
			}else{
				logar();		
				
				Log.i("SOSTENES", "Retorno Json (cliente.getStatus()): " + clienteRetorno.getStatus());
				Log.i("SOSTENES", "Retorno Json (cliente.getMensagem()): " + clienteRetorno.getMensagem());			
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
		
		
		
		if (clienteRetorno.getStatus() == 1){ //SOSTENES/Exibir Alerta...

			Toast toast = Toast.makeText(this, clienteRetorno.getMensagem(), Toast.LENGTH_LONG);
			toast.show();					
		}

		if (clienteRetorno.getStatus() == 0){ //SOSTENES/Logar...
					
			PreferencesController.setUserPreferences(this, edtEmail.getText()
					.toString(), edtSenha.getText().toString());
			it = new Intent(this, HomeActivity.class);			
			it.putExtra("usuarioLogado", edtEmail.getText().toString());
			startActivity(it);
			
			
			finish();
		}
	
		
	}

}
