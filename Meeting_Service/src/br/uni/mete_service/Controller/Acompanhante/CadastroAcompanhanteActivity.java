package br.uni.mete_service.Controller.Acompanhante;

import br.uni.mete_service.R;
import br.uni.mete_service.model.Acompanhante;
import br.uni.mete_service.model.repositorio.acompanhante.RepositorioAcompanhante;


import android.app.Activity;
import android.os.Bundle;
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
	private EditText edtHorarioAtentAcomp;


	private Acompanhante objacompanhante;
	
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
		edtHorarioAtentAcomp.setText(objacompanhante.getHorario_atentimento());
			
		}
	}
	
	
	private void inicializar() {
		edtNomeAcomp = (EditText)findViewById(R.id.AcompNome);
		edtIdadeAcomp = (EditText)findViewById(R.id.AcompIdade);
		edtPesoAcomp = (EditText)findViewById(R.id.AcompPeso);
		edtBustoAcomp = (EditText)findViewById(R.id.AcompBusto);
		edtAlturaAcomp = (EditText)findViewById(R.id.AcompAltura);
		edtCinturaAcomp = (EditText)findViewById(R.id.AcompCintura);
		edtQuadrilAcomp = (EditText)findViewById(R.id.AcompQuadril);
		edtOlhosAcomp = (EditText)findViewById(R.id.AcompOlhos);
		edtPernoiteAcomp= (EditText)findViewById(R.id.AcompPernoite);
		edtEspecialidadeAcomp = (EditText)findViewById(R.id.AcompEspecialidades);
		edtHorarioAtentAcomp = (EditText)findViewById(R.id.AcompHorarioAtent);
		
	}


	public void salvarClick(View v)
	{
		String nome          = edtNomeAcomp.getText().toString();
		String idade         = edtIdadeAcomp.getText().toString();
		String peso          = edtPesoAcomp.getText().toString();
		String busto         = edtBustoAcomp.getText().toString();
		String altura        = edtAlturaAcomp.getText().toString();
		String cintura       = edtCinturaAcomp.getText().toString();
		String quadril       = edtQuadrilAcomp.getText().toString();
		String olhos         = edtOlhosAcomp.getText().toString();
		String pernoite      = edtPernoiteAcomp.getText().toString();
		String especialidade = edtEspecialidadeAcomp.getText().toString();
		String horarioatent  = edtHorarioAtentAcomp.getText().toString();
		String atendo = "Disponível";
		String status = "Disponível";
		String foto = "url";
		
		RepositorioAcompanhante repo = new RepositorioAcompanhante();
		
		if (objacompanhante == null){
			objacompanhante = new Acompanhante(idade, nome, peso, busto, altura, cintura, quadril, olhos
					, pernoite, especialidade, horarioatent, atendo, status, foto);
			repo.inserirAcompanhante(objacompanhante);
//			Toast.makeText(this, "Vaga criada com sucesso!", Toast.LENGTH_LONG).show();
		} else {
			objacompanhante.setNome(nome);
			objacompanhante.setIdade(idade);
			objacompanhante.setPeso(peso);
			objacompanhante.setBusto(busto);
			objacompanhante.setAltura(altura);
			objacompanhante.setNome(cintura);
			objacompanhante.setNome(quadril);
			objacompanhante.setNome(olhos);
			objacompanhante.setNome(pernoite);
			objacompanhante.setNome(especialidade);
			objacompanhante.setNome(horarioatent);
			objacompanhante.setAtendo(atendo);
			objacompanhante.setStatus(status);
			
			
			repo.alterarAcompanhante(objacompanhante);
			//Toast.makeText(this, "Vaga Alterada com sucesso!", Toast.LENGTH_LONG).show();
		}
		
	}
	
	public void cancelarClick(View v)
	{
		finish();
	}
}

