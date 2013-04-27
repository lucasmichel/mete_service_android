package br.uni.mette_service.Controller.Acompanhante;

import java.io.Serializable;
import org.json.JSONException;
import br.uni.mette_service.R;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Repositorio.ModelClass;
import br.uni.mette_service.Model.Repositorio.Acompanhante.RepositorioAcompanhante;
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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CadastroAcompanhanteActivity extends Activity implements
		Serializable {

	private EditText edtNomeAcomp, edtIdadeAcomp, edtPesoAcomp, edtBustoAcomp,
			edtAlturaAcomp, edtCinturaAcomp, edtQuadrilAcomp, edtOlhosAcomp,
			edtEspecialidadeAcomp, edtHorarioAtendimentoAcomp, edtFotoAcomp,
			edtSenhaAcomp, edtEmailAcomp;
	private RadioButton rdSim, rdNao, rdhomem, rdmulher, rdambos;

	public Acompanhante objacompanhante;
	RepositorioAcompanhante repositorioAcompanhante;
	ModelClass userRetorno;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_acompanhante);
		inicializar();
		objacompanhante = (Acompanhante) getIntent().getSerializableExtra("acompanhanteLogado");
		boolean emEdicao = objacompanhante != null;

		if (emEdicao) {

			edtNomeAcomp.setText(objacompanhante.getNome());
			edtIdadeAcomp.setText(objacompanhante.getIdade());
			edtAlturaAcomp.setText(objacompanhante.getAltura());
			edtPesoAcomp.setText(objacompanhante.getPeso());
			edtBustoAcomp.setText(objacompanhante.getBusto());
			edtCinturaAcomp.setText(objacompanhante.getCintura());
			edtQuadrilAcomp.setText(objacompanhante.getQuadril());
			edtOlhosAcomp.setText(objacompanhante.getOlhos());

			if (objacompanhante.getPernoite() == "Não") {
				rdNao.setChecked(true);
			} else if (objacompanhante.getPernoite() == "Sim") {
				rdSim.setChecked(true);
			}
			if (objacompanhante.getAtendo() == "Homens") {
				rdhomem.setChecked(true);
			} else if (objacompanhante.getAtendo() == "Mulheres") {
				rdmulher.setChecked(true);
			} else if (objacompanhante.getAtendo() == "Ambos") {
				rdambos.setChecked(true);

			}
			edtEspecialidadeAcomp.setText(objacompanhante.getEspecialidade());
			edtHorarioAtendimentoAcomp.setText(objacompanhante.getHorarioAtendimento());
			edtFotoAcomp.setText(objacompanhante.getFotoPerfil());
			edtEmailAcomp.setText(objacompanhante.getEmail());
			edtSenhaAcomp.setText(objacompanhante.getSenha());
		}
	}

	private void inicializar() {
		edtNomeAcomp = (EditText) findViewById(R.id.editNomeAcomp);
		edtIdadeAcomp = (EditText) findViewById(R.id.editIdadeAcomp);
		edtPesoAcomp = (EditText) findViewById(R.id.editPesoAcomp);
		edtPesoAcomp.addTextChangedListener(Mask.insert("##", edtPesoAcomp));
		edtBustoAcomp = (EditText) findViewById(R.id.editBustoAcomp);
		edtBustoAcomp.addTextChangedListener(Mask.insert("##", edtBustoAcomp));
		edtAlturaAcomp = (EditText) findViewById(R.id.editAlturaAcomp);
		edtAlturaAcomp.addTextChangedListener(Mask.insert("#.##",
				edtAlturaAcomp));
		edtCinturaAcomp = (EditText) findViewById(R.id.editCinturaAcomp);
		edtCinturaAcomp.addTextChangedListener(Mask.insert("##",
				edtCinturaAcomp));
		edtQuadrilAcomp = (EditText) findViewById(R.id.editQuadrilAcomp);
		edtQuadrilAcomp.addTextChangedListener(Mask.insert("##",
				edtQuadrilAcomp));
		edtOlhosAcomp = (EditText) findViewById(R.id.editOlhosAcomp);
		edtEspecialidadeAcomp = (EditText) findViewById(R.id.editEspecialidadeAcomp);
		edtHorarioAtendimentoAcomp = (EditText) findViewById(R.id.editHorarioAtentAcomp);
		edtHorarioAtendimentoAcomp.addTextChangedListener(Mask.insert("##:##",
				edtHorarioAtendimentoAcomp));
		edtFotoAcomp = (EditText) findViewById(R.id.editFotoAcomp);
		edtEmailAcomp = (EditText) findViewById(R.id.editEmailAcomp);
		edtSenhaAcomp = (EditText) findViewById(R.id.editSenhaAcomp);
		rdSim = (RadioButton) findViewById(R.id.rdSim);
		rdNao = (RadioButton) findViewById(R.id.rdNao);
		rdhomem = (RadioButton) findViewById(R.id.rdhomem);
		rdmulher = (RadioButton) findViewById(R.id.rdmulher);
		rdambos = (RadioButton) findViewById(R.id.rdambos);
		rdSim.setChecked(true);
		rdhomem.setChecked(true);

	}

	public void salvarClick(View v) {

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
		// acompanhanteValidado.setPernoite(edtPernoiteAcomp.geti().toString());
		acompanhanteValidado.setEspecialidade(edtEspecialidadeAcomp.getText()
				.toString());
		acompanhanteValidado.setHorarioAtendimento(edtHorarioAtendimentoAcomp
				.getText().toString());
		// acompanhanteValidado.setNome(edtFotoAcomp.getText().toString());
		acompanhanteValidado.setEmail(edtEmailAcomp.getText().toString());
		acompanhanteValidado.setSenha(edtSenhaAcomp.getText().toString());

		// String nome = edtNomeAcomp.getText().toString();
		// String idade = edtIdadeAcomp.getText().toString();
		// String peso = edtPesoAcomp.getText().toString();
		// String busto = edtBustoAcomp.getText().toString();
		// String altura = edtAlturaAcomp.getText().toString();
		// String cintura = edtCinturaAcomp.getText().toString();
		// String quadril = edtQuadrilAcomp.getText().toString();
		// String olhos = edtOlhosAcomp.getText().toString();
		// String pernoite = edtPernoiteAcomp.getText().toString();
		// String especialidade = edtEspecialidadeAcomp.getText().toString();
		// String horario_atendimento =
		// edtHorario_AtendimentoAcomp.getText().toString();
		// String atendo = edtAtendoAcomp.getText().toString();
		// String statusAt = "Disponível";
		// String foto = edtFotoAcomp.getText().toString();
		// String email = edtEmailAcomp.getText().toString();
		// String senha = edtSenhaAcomp.getText().toString();
		// String tipo = "2";

		if (validar.validarCampo(edtNomeAcomp)
				&& validar.validarCampo(edtIdadeAcomp)
				&& validar.validarCampo(edtAlturaAcomp)
				&& validar.validarCampo(edtPesoAcomp)
				&& validar.validarCampo(edtBustoAcomp)
				&& validar.validarCampo(edtCinturaAcomp)
				&& validar.validarCampo(edtQuadrilAcomp)
				&& validar.validarCampo(edtOlhosAcomp)
				&&
				// validar.validarCampo(edtPernoiteAcomp) &&
				validar.validarCampo(edtEspecialidadeAcomp)
				&& validar.validarCampo(edtHorarioAtendimentoAcomp)
				&&
				// STATUS STARTA COMO DISPONIVEL
				// FOTO NÃO É OBRIGATÓRIA
				validar.validarCampo(edtEmailAcomp)
				&& validar.validarCampo(edtSenhaAcomp) == true) {

			if (!acompvalid.validarCampos(acompanhanteValidado).toString()
					.equals("CamposValidos")) {
				AlertDialog dialog = new AlertDialog.Builder(this)
						.setTitle("Notificação")
						.setMessage(
								acompvalid.validarCampos(acompanhanteValidado))
						.create();
				dialog.show();

			} else {
				new cadastrarAcompanhanteAsyncTask().execute();
			}
		}
	}

	public void cancelarClick(View v) {
		finish();
	}

	class cadastrarAcompanhanteAsyncTask extends
			AsyncTask<String, String, Acompanhante> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(CadastroAcompanhanteActivity.this,
					"Cadastrando: pode levar alguns segundos..", "Salvando",
					true, false);
		}

		@Override
		protected Acompanhante doInBackground(String... params) {

			String nome = edtNomeAcomp.getText().toString();
			String idade = edtIdadeAcomp.getText().toString();
			String peso = edtPesoAcomp.getText().toString();
			String busto = edtBustoAcomp.getText().toString();
			String altura = edtAlturaAcomp.getText().toString();
			String cintura = edtCinturaAcomp.getText().toString();
			String quadril = edtQuadrilAcomp.getText().toString();
			String olhos = edtOlhosAcomp.getText().toString();

			String pernoite = "Sim";
			if (rdNao.isChecked()) {
				pernoite = "Nao";
			} else if (rdSim.isChecked()) {
				pernoite = "Sim";
			}

			String atendo = "Homens";
			if (rdhomem.isChecked()) {
				atendo = "Homens";
			} else if (rdmulher.isChecked()) {
				atendo = "Mulheres";
			} else if (rdambos.isChecked()) {
				atendo = "Ambos";
			}

			String especialidade = edtEspecialidadeAcomp.getText().toString();
			String horarioAtendimento = edtHorarioAtendimentoAcomp.getText()
					.toString();
			String statusAtendimento = "Disponível";
			String fotoPerfil = edtFotoAcomp.getText().toString();
			String email = edtEmailAcomp.getText().toString();
			String senha = edtSenhaAcomp.getText().toString();
			// String tipo = "2";

			if (objacompanhante == null) {
				objacompanhante = new Acompanhante(idade, nome, altura, busto,
						cintura, quadril, olhos, pernoite, especialidade,
						horarioAtendimento, peso, atendo, statusAtendimento,
						fotoPerfil, email, senha);
			}

			try

			{

				// Toast.makeText(getApplicationContext(), "TESTE DA GOMA",
				// Toast.LENGTH_SHORT).show();

				Log.i("envio", objacompanhante.toString());

				userRetorno = objacompanhante
						.cadastrarAcompanhante(objacompanhante);

				// Toast.makeText(getApplicationContext(),
				// userRetorno.getMensagem().toString(),
				// Toast.LENGTH_LONG).show();

				Log.i("envio", userRetorno.getMensagem().toString());

			} catch (JSONException e) {
				Log.i("pedro: ", "ERROOO!!" + e);
				e.printStackTrace();
			}
			return objacompanhante;

		}

		@Override
		protected void onPostExecute(Acompanhante result) {
			super.onPostExecute(result);
			dialog.dismiss();

			Toast toast = Toast.makeText(CadastroAcompanhanteActivity.this,
					userRetorno.getMensagem(), Toast.LENGTH_LONG);
			toast.show();

			Intent it = new Intent(CadastroAcompanhanteActivity.this,
					TelaAcompanhanteActivity.class);
			it.putExtra("objacompanhante", objacompanhante);
			startActivity(it);
			// setResult(RESULT_OK, it);
			// finish();
		}

	}

}
