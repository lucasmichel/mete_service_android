package br.uni.mete_service.Controller;




import br.uni.mete_service.model.Acompanhante;

import com.example.meeting_service.R;

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
		
	}
	
	public void cancelarClick(View v)
	{
		finish();
	}
}

