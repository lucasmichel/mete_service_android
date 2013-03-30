package br.uni.mete_service.Controller.Acompanhante;



import br.uni.mete_service.R;
import br.uni.mete_service.model.Acompanhante;
import br.uni.mete_service.model.repositorio.Acompanhante.RepositorioAcompanhante;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


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
		edtBustoAcomp = (EditText)findViewById(R.id.editBustoAcomp);
		edtAlturaAcomp = (EditText)findViewById(R.id.editAlturaAcomp);
		edtCinturaAcomp = (EditText)findViewById(R.id.editCinturaAcomp);
		edtQuadrilAcomp = (EditText)findViewById(R.id.editQuadrilAcomp);
		edtOlhosAcomp = (EditText)findViewById(R.id.editOlhosAcomp);
		edtPernoiteAcomp= (EditText)findViewById(R.id.editPernoiteAcomp);
		edtEspecialidadeAcomp = (EditText)findViewById(R.id.editEspecialidadeAcomp);
		edtHorario_AtendimentoAcomp = (EditText)findViewById(R.id.editHorarioAtentAcomp);
		edtAtendoAcomp = (EditText)findViewById(R.id.editAtendoAcomp);
		edtFotoAcomp = (EditText)findViewById(R.id.editFotoAcomp);
		edtEmailAcomp = (EditText)findViewById(R.id.editEmailAcomp);
		edtSenhaAcomp = (EditText)findViewById(R.id.editSenhaAcomp);
		
	}


	public void salvarClick(View v) {
		
		
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
		String statusAt = "Disponível";
		String foto 				= edtFotoAcomp.getText().toString();
		String email				= edtEmailAcomp.getText().toString();
		String senha				= edtSenhaAcomp.getText().toString();
		String tipo 				= "Acompanhante";
		
		
		if (objacompanhante == null){
			objacompanhante = new Acompanhante(idade, nome, altura, busto, cintura, quadril, olhos, pernoite
					, especialidade, horario_atendimento, peso, atendo, statusAt, foto, email, senha, tipo);
		
		//RepositorioAcompanhante bd = RepositorioAcompanhante.getInstance();
		//bd.cadastrarAcompanhante(objacompanhante);
		
//		try {
//			acomp.cadastrarAcompanhante(objacompanhante);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Toast.makeText(this, "Vaga criada com sucesso!", Toast.LENGTH_LONG).show();
		
		Intent it = new Intent(this, TelaAcompanhanteActivity.class);
		startActivity(it);
		
		} else {
			objacompanhante.setNome(nome);
			objacompanhante.setIdade(idade);
			objacompanhante.setPeso(peso);
			objacompanhante.setBusto(busto);
			objacompanhante.setAltura(altura);
			objacompanhante.setCintura(cintura);
			objacompanhante.setQuadril(quadril);
			objacompanhante.setOlhos(olhos);
			objacompanhante.setPernoite(pernoite);
			objacompanhante.setEspecialidade(especialidade);
			objacompanhante.setHorario_atendimento(horario_atendimento);
			objacompanhante.setAtendo(atendo);
			objacompanhante.setStatusAt(statusAt);
			objacompanhante.setFoto(foto);
			objacompanhante.setEmail(email);
			objacompanhante.setSenha(senha);
			
			
		//	repo.alterarAcompanhante(objacompanhante);
			//Toast.makeText(this, "Vaga Alterada com sucesso!", Toast.LENGTH_LONG).show();
		}
		
	}
	
	public void cancelarClick(View v)
	{
		finish();
	}
}

