package br.uni.mete_service.Controller;

import java.util.Date;
import java.util.Map;

import br.uni.mete_service.R;
import br.uni.mete_service.model.Cliente;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	private EditText edtEmail, edtSenha;
	private TextView txtLinkCadastro;
	private Button btnLogin, btnCadastro;
	private Cliente user;
	private Map<String, String> userValuesMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		instantiateComponents();
		// esta parte será retirada quando o web service estiver pronto
		user = new Cliente(1, "Tiago", "030123433876", 1, "87290729",
				"gomes.tg@hotmail.com", "123456");

	}

	@SuppressLint("NewApi")
	@Override
	protected void onResume() {
		super.onResume();
		userValuesMap = PreferencesController.getUserPreferences(this);
		String userLogin = userValuesMap.get(PreferencesController.USER_EMAIL);
		String userSenha = userValuesMap.get(PreferencesController.USER_SENHA);

		if (!userLogin.isEmpty() && !userSenha.isEmpty()) {
			ClienteController.getInstance().setUsuarioLogged(user);
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
		btnCadastro.setOnClickListener(this);
		txtLinkCadastro.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btnEntrar:
			Intent it;
			Cliente userTemp = null;
			it = new Intent(this, ListarAcompanhanteActivity.class);
			it.putExtra("user", userTemp);
			startActivity(it);
			break;
		case R.id.textoCadastre:
			recuperarSenha();
			break;
		}
	}

	public void logar() {
		Intent it;

		if ((!edtEmail.getText().toString().equals(""))
				&& (!edtSenha.getText().toString().equals(""))) {

			if ((!user.getEmail().equals(edtEmail.getText().toString()))
					|| (!user.getSenha().equals(edtSenha.getText().toString()))) {

				Toast.makeText(this,
						"Nome de usuário incorreto ou senha incorreta",
						Toast.LENGTH_LONG).show();
			} else {

				ClienteController.getInstance().setUsuarioLogged(user);
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

	/*public Dialog createCustomDialog() {
		final Dialog dialog = new Dialog(this, R.style.Dialog);
		dialog.setContentView(R.layout.alert_dialog_text_entry);
		dialog.findViewById(R.dialog_email.btnRecuperarSenha)
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {

						 * EditText edtEmail = (EditText)
						 * dialog.findViewById(R.id.edtDialog); String email =
						 * edtEmail.getText().toString();
						 */
						/*
						 * Enviar e-mail posteriormente - isso deve ser feito
						 * quando o serviço de e-mail estiver pronto
						 

					}

				});

		dialog.findViewById(R.dialog_email.btnCancelar).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// não faz nada
						dialog.dismiss();
					}

				});
		return dialog;
	}*/

	public void recuperarSenha() {
	//	Dialog d = createCustomDialog();
	//	d.show();
	}

}
