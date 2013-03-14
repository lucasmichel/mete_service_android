package Controller;



import mete_service.model.Acompanhante;

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
	private EditText edtEspecialidadeAcomp;
	private EditText edtHorarioAtentAcomp;


	private Acompanhante objacompanhante;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_acompanhante);
		
		inicializar();
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

