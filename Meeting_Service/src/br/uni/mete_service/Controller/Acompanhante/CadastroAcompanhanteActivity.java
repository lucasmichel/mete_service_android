package br.uni.mete_service.Controller.Acompanhante;



import org.json.JSONException;

import br.uni.mete_service.R;
import br.uni.mete_service.Controller.HomeActivity;


import br.uni.mete_service.model.Acompanhante;
import br.uni.mete_service.model.repositorio.Acompanhante.RepositorioAcompanhante;
import br.uni.mete_service.util.Mask;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;



public class CadastroAcompanhanteActivity extends Activity 
{
	
	private EditText edtNomeAcomp;
	private EditText edtIdadeAcomp;
	private EditText edtPesoAcomp;
	private EditText edtBustoAcomp;
	private EditText edtAlturaAcomp;
	private EditText edtCinturaAcomp;
	private EditText edtQuadrilAcomp;
	private EditText edtOlhosAcomp;
	private EditText edtPernoiteAcomp;
	private EditText edtEspecialidadeAcomp;
	private EditText edtHorario_AtendimentoAcomp;
	private EditText edtAtendoAcomp;
	private EditText edtFotoAcomp;
	private EditText edtSenhaAcomp;
	private EditText edtEmailAcomp;
	

	

	private Acompanhante objacompanhante;
	RepositorioAcompanhante repositorioAcompanhante;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_acompanhante);
		
		inicializar();
		
		//objacompanhante = (Acompanhante)getIntent().getSerializableExtra("acompanhanteLogado");
		
		boolean emEdicao = objacompanhante != null;
		
		if(emEdicao){
		
		edtNomeAcomp.setText(objacompanhante.getNome());
		edtIdadeAcomp.setText(objacompanhante.getIdade());
		edtAlturaAcomp.setText(objacompanhante.getAltura());
		edtPesoAcomp.setText(objacompanhante.getPeso());
		edtBustoAcomp.setText(objacompanhante.getBusto());
		edtCinturaAcomp.setText(objacompanhante.getCintura());
		edtQuadrilAcomp.setText(objacompanhante.getQuadril());
		edtOlhosAcomp.setText(objacompanhante.getOlhos());
		edtPernoiteAcomp.setText(objacompanhante.getPernoite());
		edtEspecialidadeAcomp.setText(objacompanhante.getEspecialidade());
		edtHorario_AtendimentoAcomp.setText(objacompanhante.getHorario_atendimento());
		edtAtendoAcomp.setText(objacompanhante.getAtendo());
		edtFotoAcomp.setText(objacompanhante.getFoto());
		edtEmailAcomp.setText(objacompanhante.getEmail());
		edtSenhaAcomp.setText(objacompanhante.getSenha());	
		}
	}
	
	
	private void inicializar() {
		edtNomeAcomp = (EditText)findViewById(R.id.editNomeAcomp);
		edtIdadeAcomp = (EditText)findViewById(R.id.editIdadeAcomp);
		edtPesoAcomp = (EditText)findViewById(R.id.editPesoAcomp);
		edtPesoAcomp.addTextChangedListener(Mask.insert(
				"##", edtPesoAcomp ));
		edtBustoAcomp = (EditText)findViewById(R.id.editBustoAcomp);
		edtBustoAcomp.addTextChangedListener(Mask.insert(
				"##", edtBustoAcomp ));
		edtAlturaAcomp = (EditText)findViewById(R.id.editAlturaAcomp);
		edtAlturaAcomp.addTextChangedListener(Mask.insert(
				"#.##", edtAlturaAcomp ));
		edtCinturaAcomp = (EditText)findViewById(R.id.editCinturaAcomp);
		edtCinturaAcomp.addTextChangedListener(Mask.insert(
				"##", edtCinturaAcomp ));
		edtQuadrilAcomp = (EditText)findViewById(R.id.editQuadrilAcomp);
		edtQuadrilAcomp.addTextChangedListener(Mask.insert(
				"##", edtQuadrilAcomp ));
		edtOlhosAcomp = (EditText)findViewById(R.id.editOlhosAcomp);
		edtPernoiteAcomp= (EditText)findViewById(R.id.editPernoiteAcomp);
		edtPernoiteAcomp.addTextChangedListener(Mask.insert(
				"###.##", edtPernoiteAcomp ));
		edtEspecialidadeAcomp = (EditText)findViewById(R.id.editEspecialidadeAcomp);
		edtHorario_AtendimentoAcomp = (EditText)findViewById(R.id.editHorarioAtentAcomp);
		edtHorario_AtendimentoAcomp.addTextChangedListener(Mask.insert(
				"##:##", edtHorario_AtendimentoAcomp ));
		edtAtendoAcomp = (EditText)findViewById(R.id.editAtendoAcomp);
		edtFotoAcomp = (EditText)findViewById(R.id.editFotoAcomp);
		edtEmailAcomp = (EditText)findViewById(R.id.editEmailAcomp);
		edtSenhaAcomp = (EditText)findViewById(R.id.editSenhaAcomp);
		
	}


	public void salvarClick(View v) 
	{
		
		ValidarAcompanhante validar = new ValidarAcompanhante();
		ValidarAcompanhante acompvalid = new ValidarAcompanhante();
		Acompanhante acompanhanteValidado = new Acompanhante();
		
		acompanhanteValidado.setNome(edtNomeAcomp.getText().toString());
		acompanhanteValidado.setIdade(edtIdadeAcomp.getText().toString());
		acompanhanteValidado.setPeso(edtPesoAcomp.getText().toString());
		acompanhanteValidado.setBusto(edtBustoAcomp.getText().toString());
		acompanhanteValidado.setAltura(edtAlturaAcomp.getText().toString());
		acompanhanteValidado.setCintura(edtCinturaAcomp.getText().toString());
		acompanhanteValidado.setQuadril(edtQuadrilAcomp.getText().toString());
		acompanhanteValidado.setOlhos(edtOlhosAcomp.getText().toString());
		acompanhanteValidado.setPernoite(edtPernoiteAcomp.getText().toString());
		acompanhanteValidado.setEspecialidade(edtEspecialidadeAcomp.getText().toString());
		acompanhanteValidado.setHorario_atendimento(edtHorario_AtendimentoAcomp.getText().toString());
		acompanhanteValidado.setAtendo(edtAtendoAcomp.getText().toString());
		//acompanhanteValidado.setNome(edtFotoAcomp.getText().toString());
		acompanhanteValidado.setEmail(edtEmailAcomp.getText().toString());
		acompanhanteValidado.setSenha(edtSenhaAcomp.getText().toString());
		
//		String nome          		= edtNomeAcomp.getText().toString();
//		String idade         		= edtIdadeAcomp.getText().toString();
//		String peso          		= edtPesoAcomp.getText().toString();
//		String busto         		= edtBustoAcomp.getText().toString();
//		String altura        		= edtAlturaAcomp.getText().toString();
//		String cintura       		= edtCinturaAcomp.getText().toString();
//		String quadril       		= edtQuadrilAcomp.getText().toString();
//		String olhos         		= edtOlhosAcomp.getText().toString();
//		String pernoite      		= edtPernoiteAcomp.getText().toString();
//		String especialidade 		= edtEspecialidadeAcomp.getText().toString();
//		String horario_atendimento  = edtHorario_AtendimentoAcomp.getText().toString();
//		String atendo 		 		= edtAtendoAcomp.getText().toString();
//		String statusAt = "Dispon�vel";
//		String foto 				= edtFotoAcomp.getText().toString();
//		String email				= edtEmailAcomp.getText().toString();
//		String senha				= edtSenhaAcomp.getText().toString();
//		String tipo 				= "2";
		
		
		
		if (	validar.validarCampo(edtNomeAcomp) && 
				validar.validarCampo(edtIdadeAcomp)&& 
				validar.validarCampo(edtAlturaAcomp) &&
				validar.validarCampo(edtPesoAcomp) && 
				validar.validarCampo(edtBustoAcomp) &&
				validar.validarCampo(edtCinturaAcomp) &&
				validar.validarCampo(edtQuadrilAcomp) &&
				validar.validarCampo(edtOlhosAcomp) &&
				validar.validarCampo(edtPernoiteAcomp) &&
				validar.validarCampo(edtEspecialidadeAcomp) &&
				validar.validarCampo(edtAtendoAcomp) &&
				validar.validarCampo(edtHorario_AtendimentoAcomp) &&
				//STATUS STARTA COMO DISPONIVEL
				//FOTO N�O � OBRIGAT�RIA
				validar.validarCampo(edtEmailAcomp) &&
				validar.validarCampo(edtSenhaAcomp)== true)
		{
			
			if (!acompvalid.validarCampos(acompanhanteValidado).toString().equals("CamposValidos"))
			{	
				AlertDialog dialog = new AlertDialog.Builder(this).
				setTitle("Notifica��o").
				setMessage(acompvalid.validarCampos(acompanhanteValidado)).		
				create();
				dialog.show();
			
			}else{
				new cadastrarAcompanhanteAsyncTask().execute();						
			}
		}
	}
	
	public void cancelarClick(View v)
	{
		finish();
	}
	
		
	class cadastrarAcompanhanteAsyncTask extends AsyncTask<String, String, Acompanhante>{
			ProgressDialog dialog;
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = ProgressDialog.show(CadastroAcompanhanteActivity.this, "Calma calma safadinha.",
						"Salvando", true, false);
			}
			
			@Override
			protected Acompanhante doInBackground(String... params) {
				
				String nome          		= edtNomeAcomp.getText().toString();
				String idade         		= edtIdadeAcomp.getText().toString();
				String peso          		= edtPesoAcomp.getText().toString();
				String busto         		= edtBustoAcomp.getText().toString();
				String altura        		= edtAlturaAcomp.getText().toString();
				String cintura       		= edtCinturaAcomp.getText().toString();
				String quadril       		= edtQuadrilAcomp.getText().toString();
				String olhos         		= edtOlhosAcomp.getText().toString();
				String pernoite      		= edtPernoiteAcomp.getText().toString();
				String especialidade 		= edtEspecialidadeAcomp.getText().toString();
				String horario_atendimento  = edtHorario_AtendimentoAcomp.getText().toString();
				String atendo 		 		= edtAtendoAcomp.getText().toString();
				String statusAt = "Dispon�vel";
				String foto 				= edtFotoAcomp.getText().toString();
				String email				= edtEmailAcomp.getText().toString();
				String senha				= edtSenhaAcomp.getText().toString();
				String tipo 				= "2";
				
				if (objacompanhante == null){
					objacompanhante = new Acompanhante
							(idade, nome, altura, busto, 
							cintura, quadril, olhos, pernoite
							,especialidade, horario_atendimento, peso, atendo, 
							statusAt, foto, email, senha, tipo);}
				
				try 
				
				{
					
					Acompanhante acompanhanteRetorno = new Acompanhante();

					acompanhanteRetorno = objacompanhante.cadastrarAcompanhante(objacompanhante);

					
				} catch (JSONException e) 
				{
					Log.i("pedro: ", "ERROOO!!" + e);
					e.printStackTrace();
				}
				return objacompanhante;
			}
			@Override
			protected void onPostExecute(Acompanhante result) 
			{
				super.onPostExecute(result);
				dialog.dismiss();
				Intent it = new Intent(CadastroAcompanhanteActivity.this ,HomeActivity.class);
				setResult(RESULT_OK, it);
				finish();
			}	
		
	}
	}		

