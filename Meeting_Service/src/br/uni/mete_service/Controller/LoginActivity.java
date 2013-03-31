package br.uni.mete_service.Controller;

import java.util.Date;

import java.util.Map;

import org.json.JSONException;

import br.uni.mete_service.R;
import br.uni.mete_service.Controller.Cliente.CadastroClienteActivity;
import br.uni.mete_service.model.Cliente;

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
	private Cliente cliente;
	private Map<String, String> userValuesMap;
	final Context context = this;

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
		
		if (edtEmail.getText().equals("") && edtSenha.getText().equals("")){
			return "ATENÇÃO: E-mail e Senha estão em branco!";
		}
		
		if (edtEmail.getText().equals("")){
			return "ATENÇÃO: E-mail está em branco!";
		}
		
		if (edtSenha.getText().equals("")){
			return "ATENÇÃO: Senha está em branco!";
		}
		
		return "CamposValidos";
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btnEntrar:
			
			boolean valicadao;
			valicadao = false;
			
			if (validarCampos().equals("CamposValidos")){				
				logar();								
			}else{
				AlertDialog dialog = new AlertDialog.Builder(this).
				setTitle("Notificação").
				setMessage(validarCampos()).		
				create();
				dialog.show();				
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

		/*
		 * if ((!edtEmail.getText().toString().equals("")) &&
		 * (!edtSenha.getText().toString().equals(""))) { if
		 * ((!cliente.getEmail().equals(edtEmail.getText().toString())) ||
		 * (!cliente.getSenha().equals( edtSenha.getText().toString()))) {
		 * Toast.makeText(this, "Nome de usuário incorreto ou senha incorreta",
		 * Toast.LENGTH_LONG).show(); } else {
		 */

		try {
			RepositorioCliente.getInstance().logarAndroid(cliente);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreferencesController.setUserPreferences(this, edtEmail.getText()
				.toString(), edtSenha.getText().toString());
		it = new Intent(this, HomeActivity.class);
		startActivity(it);
		finish();
		/*
		 * } } else { Toast.makeText(this, "Digite todos os campos",
		 * Toast.LENGTH_LONG) .show(); }
		 */

	}

}
