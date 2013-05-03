package br.uni.mette_service.Controller;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Acompanhante.CadastroAcompanhanteActivity;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

public class LogarAndroidActivity extends Activity implements OnClickListener {

	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();	
	Repositorio repositorio = new Repositorio();
	List<Object> listaUsuario = new ArrayList();
	
	private EditText edtEmail;
	private EditText edtSenha;
	
	private TextView txtLinkCadastro;
	private Button btnLogin;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);				
		adicionarFindView();
		adicionarListers();				
	}
	
	private void adicionarFindView() {
		this.edtEmail = (EditText) findViewById(R.id.loginEmail);
		this.edtSenha = (EditText) findViewById(R.id.loginSenha);
		this.btnLogin = (Button) findViewById(R.id.btnEntrar);
		this.txtLinkCadastro = (TextView) findViewById(R.id.textoCadastre);		
	}
	public void adicionarListers() {		
		this.btnLogin.setOnClickListener(this);
		this.txtLinkCadastro.setOnClickListener(this);
	}
	
	public void onClick(DialogInterface arg0, int arg1) {}	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnEntrar:
			listaUsuario.clear();
			if (!validarCampos().toString().equals("CamposValidos")){				
						AlertDialog dialog = new AlertDialog.Builder(this).
						setTitle("Notificação").
						setMessage(validarCampos()).		
						create();
						dialog.show();																		
			}else{				
				new logarAndroidAsysncTask().execute();							
			}
			break;
		case R.id.textoCadastre:
			Intent it = new Intent(this, EscolhaDoUsuarioActivity.class);
			startActivity(it);			
			break;
		}
	}
	
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
	
	class logarAndroidAsysncTask extends AsyncTask<Void, Void, Modelo>{

		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(LogarAndroidActivity.this, "Aguarde",
					"Efetuando Autenticação...", true, false);
			super.onPreExecute();
		}
		
		@Override
		protected Modelo doInBackground(Void... params) {
			
			Usuario usuario = new Usuario();
			usuario.setEmail(edtEmail.getText().toString());
			usuario.setSenha(edtSenha.getText().toString());
			
			listaUsuario.add(usuario);
			
			modelo.setDados(listaUsuario);
			modelo.setMensagem("");
			modelo.setStatus("");
			
			try {								
				modeloRetorno = repositorio.acessarServidor("logarAndroid", modelo);				
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
				Toast toast = Toast.makeText(LogarAndroidActivity.this,
											 modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
			} else {
				Usuario usuarioLogado = new Usuario();
				Object dadosObject = modeloRetorno.getDados().get(0);
				Gson gson = new Gson();
			
				JSONObject jsonObject = null;
				try {
					jsonObject = new JSONObject(gson.toJson(dadosObject));				
					usuarioLogado.setIdUsuario(jsonObject.getInt("id"));
					usuarioLogado.setIdPerfil(jsonObject.getInt("idPerfil"));
					usuarioLogado.setEmail(jsonObject.getString("email"));
					usuarioLogado.setSenha(jsonObject.getString("senha"));
					
					Log.i("SOSTENES", "ID Usuario Logado: " + usuarioLogado.getIdPerfil());
				} catch (JSONException e) {
					e.printStackTrace();
				}			
					//Se usuarioRetorno.getUsuarioIdPerfil() é igual à 1, trata-se de Administrador.
					if ((modeloRetorno.getStatus().equals("0")) && (usuarioLogado.getIdPerfil() == 1)){										
						Intent it1 = new Intent(LogarAndroidActivity.this, ClienteMenuActivity.class);			
						it1.putExtra("usuarioLogado", usuarioLogado);
						startActivity(it1);								
						finish();					
					//Se usuarioRetorno.getUsuarioIdPerfil() é igual à 2, trata-se de Cliente.
					} else if ((modeloRetorno.getStatus().equals("0")) && (usuarioLogado.getIdPerfil() == 2)){										
						Intent it2 = new Intent(LogarAndroidActivity.this, ClienteMenuActivity.class);			
						it2.putExtra("usuarioLogado", usuarioLogado);
						startActivity(it2);								
						finish();
					//Se usuarioRetorno.getUsuarioIdPerfil() é igual à 3, trata-se de Acompanhante.
					} else if((modeloRetorno.getStatus().equals("0")) &&  (usuarioLogado.getIdPerfil() == 3)){						
						Intent it3 = new Intent(LogarAndroidActivity.this, AcompanhanteMenuActivity.class);			
						it3.putExtra("usuarioLogado", usuarioLogado);
						startActivity(it3);
						Toast toast = Toast.makeText(LogarAndroidActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
						toast.show();							
						finish();
					}
			}
			
		 }
	}	
}