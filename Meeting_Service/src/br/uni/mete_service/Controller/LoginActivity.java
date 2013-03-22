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
		instantiateComponents();
		// esta parte será retirada quando o web service estiver pronto
		// cliente = new Cliente(1, "Tiago", "030123433876", 1,
		// "87290729","gomes.tg@hotmail.com", "123456");

	}

	@SuppressLint("NewApi")
	@Override
	protected void onResume() {
		super.onResume();
		userValuesMap = PreferencesController.getUserPreferences(this);
		String userLogin = userValuesMap.get(PreferencesController.USER_EMAIL);
		String userSenha = userValuesMap.get(PreferencesController.USER_SENHA);

		if (!userLogin.isEmpty() && !userSenha.isEmpty()) {
			try {
				RepositorioCliente.getInstance().logarAndroid(cliente);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Intent it = new Intent(this, HomeActivity.class);
			startActivity(it);
			finish();

		}
	}

	public void instantiateComponents() {

		edtEmail = (EditText) findViewById(R.id.loginEmail);
		edtSenha = (EditText) findViewById(R.id.loginSenha);
		btnLogin = (Button) findViewById(R.id.btnEntrar);
		txtLinkCadastro = (TextView) findViewById(R.id.textoCadastre);

		btnLogin.setOnClickListener(this);
		txtLinkCadastro.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btnEntrar:
			logar();
			break;
		case R.id.textoCadastre:
			Cliente cli = null;
			Intent it = new Intent(this, EscolhaDoUsuarioActivity.class);
			it.putExtra("cliente", cli);
			startActivity(it);
			break;
		}
	}

	

	public void logar() {
		Intent it;

		if ((!edtEmail.getText().toString().equals(""))
				&& (!edtSenha.getText().toString().equals(""))) {
			if ((!cliente.getEmail().equals(edtEmail.getText().toString()))
					|| (!cliente.getSenha().equals(
							edtSenha.getText().toString()))) {
				Toast.makeText(this,
						"Nome de usuário incorreto ou senha incorreta",
						Toast.LENGTH_LONG).show();
			} else {

				try {
					RepositorioCliente.getInstance().logarAndroid(cliente);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PreferencesController.setUserPreferences(this, edtEmail
						.getText().toString(), edtSenha.getText().toString());
				it = new Intent(this, HomeActivity.class);
				startActivity(it);
				finish();
			}
		} else {
			Toast.makeText(this, "Digite todos os campos", Toast.LENGTH_LONG)
					.show();
		}

	}

}
