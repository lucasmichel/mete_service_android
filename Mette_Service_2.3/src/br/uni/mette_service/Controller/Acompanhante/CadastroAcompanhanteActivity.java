package br.uni.mette_service.Controller.Acompanhante;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;


import br.uni.mette_service.R;
import br.uni.mette_service.Controller.LogarAndroidActivity;

import br.uni.mette_service.Model.Acompanhante;

import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;
import br.uni.mette_service.Util.Mask;
import br.uni.mette_service.Util.Validar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CadastroAcompanhanteActivity extends Activity implements OnClickListener{

	Acompanhante acompanhante = new Acompanhante();
	Usuario usuarioLogado = new Usuario();
	Usuario editadoUsuarioLogado = new Usuario();
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();	
	Acompanhante acompanhanteRetorno= new Acompanhante();
	Repositorio repositorio = new Repositorio();
	List<Object> listaAcompanhante = new ArrayList<Object>();
	int idAcompanhanteEditar = 0;
	int usuarioIdTeste;
	
	boolean eEdicao;
	private EditText edtNomeAcomp;
	private EditText edtIdadeAcomp;
	private EditText edtPesoAcomp;
	private EditText edtBustoAcomp;
	private EditText edtAlturaAcomp;
	private EditText edtCinturaAcomp;
	private EditText edtQuadrilAcomp;
	private EditText edtOlhosAcomp;
	private EditText edtEspecialidadeAcomp;
	private EditText edtHorarioAtendimentoAcomp;
	private EditText edtFotoAcomp;
	private EditText edtSenhaAcomp; 
	private EditText edtEmailAcomp;
	private RadioButton rdSim;
	private RadioButton rdNao;
	private RadioButton rdhomem;
	private RadioButton rdmulher;
	private RadioButton rdambos;
	private Button btnCadastrar;
	private Button btnCancelar;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_acompanhante);		
		adicionarFindView();
		adicionarListers();
		
		usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuarioLogado");
		eEdicao = getIntent().getBooleanExtra("eEdicao",false);		
		
		if (eEdicao){
			executarAlteracao(usuarioLogado);
//		}else{
//			Toast toast = Toast.makeText(CadastroAcompanhanteActivity.this, "Activity NÃO foi chamada para Edição.", Toast.LENGTH_LONG);
//			toast.show();
		}
	}
	
	private void executarAlteracao(Usuario usuarioLogado) {
//		Toast toast = Toast.makeText(CadastroAcompanhanteActivity.this, "Activity FOI chamada para Edição.", Toast.LENGTH_LONG);
//		toast.show();	
		btnCadastrar.setText("Editar");
		listaAcompanhante.clear();
		acompanhante.setId(usuarioLogado.getIdUsuario());						
		listaAcompanhante.add(acompanhante);		
		
		modelo.setDados(listaAcompanhante);
		modelo.setMensagem("");
		modelo.setStatus("");									
		
		new buscarAcompanhantePorIdAsyncTask().execute();
		
	}
	
	private void adicionarFindView() {
		edtNomeAcomp = (EditText) findViewById(R.id.editNomeAcomp);
		edtIdadeAcomp = (EditText) findViewById(R.id.editIdadeAcomp);
		edtPesoAcomp = (EditText) findViewById(R.id.editPesoAcomp);
		edtPesoAcomp.addTextChangedListener(Mask.insert("##", edtPesoAcomp));
		edtBustoAcomp = (EditText) findViewById(R.id.editBustoAcomp);
		edtBustoAcomp.addTextChangedListener(Mask.insert("##", edtBustoAcomp));
		edtAlturaAcomp = (EditText) findViewById(R.id.editAlturaAcomp);
		edtAlturaAcomp.addTextChangedListener(Mask.insert("#.##",edtAlturaAcomp));
		edtCinturaAcomp = (EditText) findViewById(R.id.editCinturaAcomp);
		edtCinturaAcomp.addTextChangedListener(Mask.insert("##",edtCinturaAcomp));
		edtQuadrilAcomp = (EditText) findViewById(R.id.editQuadrilAcomp);
		edtQuadrilAcomp.addTextChangedListener(Mask.insert("##", edtQuadrilAcomp));
		edtOlhosAcomp = (EditText) findViewById(R.id.editOlhosAcomp);
		edtEspecialidadeAcomp = (EditText) findViewById(R.id.editEspecialidadeAcomp);
		edtHorarioAtendimentoAcomp = (EditText) findViewById(R.id.editHorarioAtentAcomp);
		edtHorarioAtendimentoAcomp.addTextChangedListener(Mask.insert("##:##", edtHorarioAtendimentoAcomp));
		edtEmailAcomp = (EditText) findViewById(R.id.editEmailAcomp);
		edtSenhaAcomp = (EditText) findViewById(R.id.editSenhaAcomp);
		rdSim = (RadioButton) findViewById(R.id.rdSim);
		rdNao = (RadioButton) findViewById(R.id.rdNao);
		rdhomem = (RadioButton) findViewById(R.id.rdhomem);
		rdmulher = (RadioButton) findViewById(R.id.rdmulher);
		rdambos = (RadioButton) findViewById(R.id.rdambos);
		
		btnCadastrar = (Button) findViewById(R.id.buttonCadastrar);
		btnCancelar = (Button) findViewById(R.id.buttonCancelar);
		rdSim.setChecked(true);
		rdhomem.setChecked(true);
	}
	
	public void adicionarListers() {		
		this.btnCadastrar.setOnClickListener(this);
		this.btnCancelar.setOnClickListener(this);
	}
	
	public void onClick(DialogInterface arg0, int arg1) {}
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonCadastrar:	
			listaAcompanhante.clear();
			Validar validar = new Validar();
			Validar acompvalid = new Validar();
			Acompanhante acompanhanteValidado = new Acompanhante();
			acompanhanteValidado.setNome(edtNomeAcomp.getText().toString());
			acompanhanteValidado.setIdade(edtIdadeAcomp.getText().toString());
			acompanhanteValidado.setPeso(edtPesoAcomp.getText().toString());
			acompanhanteValidado.setBusto(edtBustoAcomp.getText().toString());
			acompanhanteValidado.setAltura(edtAlturaAcomp.getText().toString());
			acompanhanteValidado.setCintura(edtCinturaAcomp.getText().toString());
			acompanhanteValidado.setQuadril(edtQuadrilAcomp.getText().toString());
			acompanhanteValidado.setOlhos(edtOlhosAcomp.getText().toString());		
			acompanhanteValidado.setEspecialidade(edtEspecialidadeAcomp.getText().toString());
			acompanhanteValidado.setHorarioAtendimento(edtHorarioAtendimentoAcomp.getText().toString());		
			acompanhanteValidado.setEmail(edtEmailAcomp.getText().toString());
			acompanhanteValidado.setSenha(edtSenhaAcomp.getText().toString());
				if (validar.validarCampo(edtNomeAcomp)
					&& validar.validarCampo(edtIdadeAcomp)
					&& validar.validarCampo(edtAlturaAcomp)
					&& validar.validarCampo(edtPesoAcomp)
					&& validar.validarCampo(edtBustoAcomp)
					&& validar.validarCampo(edtCinturaAcomp)
					&& validar.validarCampo(edtQuadrilAcomp)
					&& validar.validarCampo(edtOlhosAcomp)
					&&validar.validarCampo(edtEspecialidadeAcomp)
					&& validar.validarCampo(edtHorarioAtendimentoAcomp)
					&&validar.validarCampo(edtEmailAcomp)
					&& validar.validarCampo(edtSenhaAcomp) == true) {
				if (!acompvalid.validarCampos(acompanhanteValidado).toString()
					.equals("CamposValidos")) {
					AlertDialog dialog = new AlertDialog.Builder(this)
					.setTitle("Notificação")
					.setMessage(
					acompvalid.validarCampos(acompanhanteValidado))
					.create();
					dialog.show();
				} 
				else{ 
					if(eEdicao){
						
						listaAcompanhante.clear();
						Acompanhante acompanhanteEditar = new Acompanhante();
						
						//ID's
						acompanhanteEditar.setId(idAcompanhanteEditar);
						acompanhanteEditar.setIdUsuario(usuarioIdTeste);
						
						Log.i("SOSTENES", "Id: " + idAcompanhanteEditar + " " + "Id Acomp IdUsuario: " + usuarioIdTeste);
						
						acompanhanteEditar.setNome(edtNomeAcomp.getText().toString());
						acompanhanteEditar.setIdade(edtIdadeAcomp.getText().toString());
						acompanhanteEditar.setPeso(edtPesoAcomp.getText().toString());
						acompanhanteEditar.setBusto(edtBustoAcomp.getText().toString());
						acompanhanteEditar.setAltura(edtAlturaAcomp.getText().toString());
						acompanhanteEditar.setCintura(edtCinturaAcomp.getText().toString());
						acompanhanteEditar.setQuadril(edtQuadrilAcomp.getText().toString());
						acompanhanteEditar.setOlhos(edtOlhosAcomp.getText().toString());
						/// PERNOITE
						if (rdNao.isChecked()) {
							acompanhanteEditar.setPernoite(0);
						} else if (rdSim.isChecked()) {
						   	acompanhanteEditar.setPernoite(1);
						}
						//
						
						// ATENDO
						if (rdhomem.isChecked()) {
							acompanhanteEditar.setAtendo("Homens");
						} else if (rdmulher.isChecked()) {
							acompanhanteEditar.setAtendo("Mulheres");					
						} else if (rdambos.isChecked()) {
							acompanhanteEditar.setAtendo("Ambos");				
						}
						//
						
						acompanhanteEditar.setEspecialidade(edtEspecialidadeAcomp.getText().toString());
						acompanhanteEditar.setHorarioAtendimento(edtHorarioAtendimentoAcomp.getText().toString());
						acompanhanteEditar.setEmail(edtEmailAcomp.getText().toString());
						acompanhanteEditar.setSenha(edtSenhaAcomp.getText().toString());	
						
						Toast toast = Toast.makeText(CadastroAcompanhanteActivity.this,"Id: " + acompanhanteEditar.getId() + "IdUsuario: " + acompanhanteEditar.getIdUsuario(), Toast.LENGTH_LONG);
						toast.show();						
						
						listaAcompanhante.add(acompanhanteEditar);
						
						modelo.setDados(listaAcompanhante);
						modelo.setMensagem("");
						modelo.setStatus("");
						
						new editarAcompanhanteAsyncTask().execute();
						
					} else { 
					
						new cadastroAcompanhanteAsyncTask().execute();
					}
				}
				}
			break;
		case R.id.buttonCancelar:
			finish();
		break;
		}
	}
	
	class cadastroAcompanhanteAsyncTask extends AsyncTask<String, String, Modelo> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(CadastroAcompanhanteActivity.this,
					"Cadastrando...", "Aguarde...",
					true, false);
		}

		@Override
		protected Modelo doInBackground(String... params) {

			Acompanhante acompanhante = new Acompanhante();
			
			acompanhante.setNome(edtNomeAcomp.getText().toString());
			acompanhante.setIdade(edtIdadeAcomp.getText().toString());
			acompanhante.setPeso(edtPesoAcomp.getText().toString());
			acompanhante.setBusto(edtBustoAcomp.getText().toString());
			acompanhante.setAltura(edtAlturaAcomp.getText().toString());
			acompanhante.setCintura(edtCinturaAcomp.getText().toString());
			acompanhante.setQuadril(edtQuadrilAcomp.getText().toString());
			acompanhante.setOlhos(edtOlhosAcomp.getText().toString());
			
			if (rdNao.isChecked()) {
				acompanhante.setPernoite(0);
			} else if (rdSim.isChecked()) {
			   	acompanhante.setPernoite(1);
			}
			if (rdhomem.isChecked()) {
				acompanhante.setAtendo("Homens");
			} else if (rdmulher.isChecked()) {
				acompanhante.setAtendo("Mulheres");					
			} else if (rdambos.isChecked()) {
				acompanhante.setAtendo("Ambos");				
			}
			acompanhante.setEspecialidade(edtEspecialidadeAcomp.getText().toString());
			acompanhante.setHorarioAtendimento(edtHorarioAtendimentoAcomp.getText().toString());
			acompanhante.setEmail(edtEmailAcomp.getText().toString());
			acompanhante.setSenha(edtSenhaAcomp.getText().toString());						
			
			// ADICIONANDO DADOS DA TELA NO OBJETO MODELO
			
			listaAcompanhante.add(acompanhante);
			modelo.setDados(listaAcompanhante);
			modelo.setMensagem("");
			modelo.setStatus("");
			try
			{
				modeloRetorno = repositorio.acessarServidor("cadastrarAcompanhante", modelo);

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
				Toast toast = Toast.makeText(CadastroAcompanhanteActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
							
				finish();
			}else{				
				Intent it = new Intent(CadastroAcompanhanteActivity.this, LogarAndroidActivity.class);
				startActivity(it);
				Toast toast = Toast.makeText(CadastroAcompanhanteActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
				finish();
			}	
		}
	}
	
	class buscarAcompanhantePorIdAsyncTask extends AsyncTask<String, String, Modelo>  
	{
			ProgressDialog dialog;
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = ProgressDialog.show(CadastroAcompanhanteActivity.this,
						"Carregando Dados...", "Aguarde...",
						true, false);
			}


			@Override
			protected Modelo doInBackground(String... params) 
			{
				try
				{
					modeloRetorno = repositorio.acessarServidor("buscarAcompanhantePorIdUsuario", modelo);
				
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
					Toast toast = Toast.makeText(CadastroAcompanhanteActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
					toast.show();
				}else{				
					Object dadosObject = modeloRetorno.getDados().get(0);
					JSONObject jsonObject = null;
					Gson gson = new Gson();
					
					try {
						jsonObject = new JSONObject(gson.toJson(dadosObject));
						
						Log.i("SOSTENES", "RETORNO PARA MONTAR NA TELA" + gson.toJson(dadosObject));
						
						
						idAcompanhanteEditar = jsonObject.getInt("id");
						
						Log.i("SOSTENES", "idAcomp" + idAcompanhanteEditar);
						
						edtNomeAcomp.setText(jsonObject.getString("nome"));
						edtIdadeAcomp.setText(jsonObject.getString("idade"));
						edtAlturaAcomp.setText(jsonObject.getString	("altura"));
						edtBustoAcomp.setText(jsonObject.getString("busto"));
						edtPesoAcomp.setText(jsonObject.getString("peso"));
						edtCinturaAcomp.setText(jsonObject.getString("peso"));
						edtQuadrilAcomp.setText(jsonObject.getString("quadril"));
						edtOlhosAcomp.setText(jsonObject.getString("olhos"));
				
						String atendo = jsonObject.getString("atendo");
						
						if (atendo.equalsIgnoreCase("Homens")){
						rdhomem.setChecked(true);
	
						}else if(atendo.equalsIgnoreCase("Mulheres")){
						rdmulher.setChecked(true);	
				
						}else{
						rdambos.setChecked(true);
							
						}
						
						int pernoite = jsonObject.getInt("pernoite");
						if (pernoite == 1){
							rdSim.setChecked(true);
						}else if (pernoite == 0){
							rdNao.setChecked(true);
						}
					

						edtEspecialidadeAcomp.setText(jsonObject.getString("especialidade"));
						edtHorarioAtendimentoAcomp.setText(jsonObject.getString("horarioAtendimento"));
						edtEmailAcomp.setText(usuarioLogado.getEmail());
						usuarioIdTeste = jsonObject.getInt("usuarioId");
						
					} catch (JSONException e) {
					}				
					
					Toast toast = Toast.makeText(CadastroAcompanhanteActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
					toast.show();
				
				}	
			}
			
		
	}
	
	// editarCliente
			class editarAcompanhanteAsyncTask extends AsyncTask<String, String, Modelo>  {
				ProgressDialog dialog;
				@Override
				protected void onPreExecute() {
					super.onPreExecute();
					dialog = ProgressDialog.show(CadastroAcompanhanteActivity.this,
							"Atualizando Informações...", "Aguarde !",
							true, false);
				}

				@Override
				protected Modelo doInBackground(String... params) {	
					try
					{
						modeloRetorno = repositorio.acessarServidor("editarAcompanhante", modelo);
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
						Toast toast = Toast.makeText(CadastroAcompanhanteActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
						toast.show();
					}else{
						Object dadosObject = modeloRetorno.getDados().get(0);
						JSONObject jsonObject = null;
						Gson gson = new Gson();
						
						try {
							
							//MONTAR O NOVO USUARIO LOGADO
							
							jsonObject = new JSONObject(gson.toJson(dadosObject));
							
							Log.i("SOSTENES", "RETORNO USUARIO LOGADO: " + gson.toJson(dadosObject));
							
							editadoUsuarioLogado.setEmail(jsonObject.getString("email"));
							editadoUsuarioLogado.setIdUsuario(jsonObject.getInt("id"));
							editadoUsuarioLogado.setSenha(jsonObject.getString("senha"));
							editadoUsuarioLogado.setIdPerfil(usuarioLogado.getIdPerfil());
							
							Log.i("SOSTENES", "RETORNO USUARIO LOGADO" + editadoUsuarioLogado.getEmail() + "" + editadoUsuarioLogado.getIdUsuario());
							
							// RETORNAR PARA O ONACTIVITYRESULT
							Intent intent = getIntent();
							intent.putExtra("editadoUsuarioLogado", editadoUsuarioLogado);
							setResult(RESULT_OK, intent);
							finish();
							
							
							
						}catch (JSONException e) {
							
						
						Toast toast = Toast.makeText(CadastroAcompanhanteActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
						toast.show();
						finish();
						
					}	
				}
			}	
			}
			//////	
		

	}




