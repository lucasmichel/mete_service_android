package br.uni.mette_service.Controller;




import org.json.JSONException;


import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Acompanhante.TelaAcompanhanteActivity;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.ModelClass;
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
	final Context context = this;
	
	ModelClass usuarioRetorno, modelo;
	

	
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
//	public void logar() {
//		Intent it;		
//		
//		usuario.setEmail(edtEmail.getText().toString());
//		usuario.setSenha(edtSenha.getText().toString());	
//
//		
//		
//		try {			
////			Toast.makeText(getApplicationContext(), "TESTE DA GOMA", Toast.LENGTH_SHORT).show();
//			
//			
//			usuarioRetorno = usuario.logarAndroid(usuario);
//			
////			Toast.makeText(getApplicationContext(), userRetorno.getMensagem().toString(), Toast.LENGTH_LONG).show();
//			
//			
//			
//			//Toast.makeText(getApplicationContext(), "TESTE DA GOMA2", Toast.LENGTH_SHORT).show();					 
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//	}
	
	class logarAsysncTask extends AsyncTask<Void, Void, ModelClass>{

		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(LoginActivity.this, "LOADING:",
					"Efetuando o Login do Usuario!", true, false);
			super.onPreExecute();
		}
		
		@Override
		protected ModelClass doInBackground(Void... params) {
//			logar();
			
			String email = edtEmail.getText().toString();
			String senha = edtSenha.getText().toString();
			
			Usuario usuario = new Usuario(0, email, senha, 0);
			
			try {
				
				usuarioRetorno = new ModelClass();
				usuarioRetorno = usuario.logarAndroid(usuario);
				
				Toast.makeText(getApplicationContext(), usuarioRetorno.getMensagem().toString(), Toast.LENGTH_LONG).show();
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Log.i("envio", usuarioRetorno.getMensagem().toString());

			return usuarioRetorno;
			
			
		}
		
		@Override
		protected void onPostExecute(ModelClass result) {
			super.onPostExecute(result);
			dialog.dismiss();
			// AINDA NÃO SEI QUAL idPerfil PERTENCE A CADA UM, PORTANTO ESTÃO COMO EXEMPLOS ESSES VALORES
			
			Usuario user = (Usuario) usuarioRetorno.getDados().get(0);
			if ((usuarioRetorno.getStatus() == 0) && (user.getIdPerfil() == 1)){ 
						
				PreferencesController.setUserPreferences(LoginActivity.this, edtEmail.getText()
						.toString(), edtSenha.getText().toString());
				Intent it = new Intent(LoginActivity.this, HomeActivity.class);			
				it.putExtra("usuarioLogado", edtEmail.getText().toString());
				startActivity(it);
				
				
				finish();
			} else if((usuarioRetorno.getStatus() == 0) &&  (user.getIdPerfil() == 2)){
				
				PreferencesController.setUserPreferences(LoginActivity.this, edtEmail.getText()
						.toString(), edtSenha.getText().toString());
				Intent it = new Intent(LoginActivity.this, TelaAcompanhanteActivity.class);			
				it.putExtra("usuarioLogado", edtEmail.getText().toString());
				startActivity(it);
			}
		}
	}

}
