package br.uni.mette_service.Controller.Acompanhante;

import br.uni.mette_service.R;
import br.uni.mette_service.Mapa.MapaActivity;
import br.uni.mette_service.Model.Acompanhante;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DadosAcompanhanteActivity extends Activity implements OnClickListener {

	boolean mapaAcompSelecionada = true;
	private TextView nome, idade, peso, altura, busto, cintura,
	atendo, quadril, olhos, pernoite, horario_atent;
	ImageView status;
	private Acompanhante dadosAcompanhante;
	private Button btnAvaliar, btnMapa , btnVoltar;
	
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dados_acomp);

		adicionarFindView();			
		adicionarListers();
		
	dadosAcompanhante = (Acompanhante) getIntent().getSerializableExtra("acompanhante");
//	new informacaoMeninasAsyncTask().execute();
	
	nome.setText(dadosAcompanhante.getNome());
	idade.setText(dadosAcompanhante.getIdade());
	altura.setText(dadosAcompanhante.getAltura());
	busto.setText(dadosAcompanhante.getBusto());
	atendo.setText(dadosAcompanhante.getAtendo());
	cintura.setText(dadosAcompanhante.getCintura());
	quadril.setText(dadosAcompanhante.getQuadril());
	olhos.setText(dadosAcompanhante.getOlhos());
//	pernoite.setText(dadosAcompanhante.getPernoite());
	horario_atent.setText(dadosAcompanhante.getHorarioAtendimento());
	peso.setText(dadosAcompanhante.getPeso());
	
	if ( dadosAcompanhante.getPernoite() == 0){
		pernoite.setText("Fa�o pernoite");
	}else {
		pernoite.setText("N�o fa�o pernoite");
	}
	//ADICIONAR OS FINDVIEW E OS BOTOES PARA CLICK

}

	private void adicionarFindView() {
		
		this.nome = (TextView) this.findViewById(R.id.txtNomeAcomp);
		this.idade = (TextView) this.findViewById(R.id.txtIdadeAcomp);
		this.altura = (TextView) this.findViewById(R.id.txtAlturaAcomp);
		this.busto = (TextView) this.findViewById(R.id.txtBustoAcomp);
		this.atendo = (TextView) this.findViewById(R.id.txtAtentoAcomp);
		this.cintura = (TextView) this.findViewById(R.id.txtCinturaAcomp);
		this.quadril = (TextView) this.findViewById(R.id.txtQuadrilAcomp);
		this.olhos = (TextView) this.findViewById(R.id.txtOlhosAcomp);
		this.pernoite = (TextView) this.findViewById(R.id.txtPernoiteAcomp);
		this.horario_atent = (TextView) this.findViewById(R.id.txtHorarioAcomp);
		this.peso = (TextView) this.findViewById(R.id.txtPesoAcomp);
		this.status = (ImageView) this.findViewById(R.id.imageStatus);
		//--BOTOES--
		this.btnAvaliar = (Button) this.findViewById(R.id.btnAvaliar);
		this.btnMapa = (Button) this.findViewById(R.id.btnMapa);
		this.btnVoltar = (Button) this.findViewById(R.id.btnVoltarDados);
		
}
	
	public void adicionarListers() {

		this.btnAvaliar.setOnClickListener(this);
		this.btnMapa.setOnClickListener(this);
		this.btnVoltar.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		Intent it = null;
		switch (v.getId()) {
		case R.id.btnAvaliar:
			
			break;	
		case R.id.btnMapa:
			it = new Intent (this, MapaActivity.class);
			it.putExtra("acompanhanteSelecionada", dadosAcompanhante);
			it.putExtra("mapaAcompSelecionada", mapaAcompSelecionada);
			startActivity(it);
			break;	
		case R.id.btnVoltar:
			finish();
			break;
		
		}
	}

}
