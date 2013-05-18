package br.uni.mette_service.TestePerformance;

import java.util.ArrayList;
import java.util.List;
import br.uni.mette_service.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class TestePerformanceActivity extends Activity implements
		OnClickListener {

	private Spinner spinnerAcoes;
	private Button btnTestarPerformance;
	private List<String> acoes = new ArrayList<String>();
	String acao;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testeperformance);

		adicionarFindView();
		adicionarListers();
		preencherSpinner();
	}

	private void adicionarFindView() {
		this.btnTestarPerformance = (Button) findViewById(R.id.button1);
		this.spinnerAcoes = (Spinner) findViewById(R.id.spinner1);
	}

	public void adicionarListers() {
		this.btnTestarPerformance.setOnClickListener(this);
	}

	private void preencherSpinner() {
		acoes.add("logarAndroid");
		acoes.add("cadastrarAcompanhante");
		acoes.add("cadastrarCliente");
		acoes.add("listarAcompanhante");
		acoes.add("listarServicos");
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, acoes);
		ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
		spinnerArrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spinnerAcoes.setAdapter(spinnerArrayAdapter);
		spinnerAcoes
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent, View v,int posicao, long id) {
						acao = parent.getItemAtPosition(posicao).toString();
						Toast.makeText(TestePerformanceActivity.this,"Nome Selecionado: " + acao, Toast.LENGTH_LONG).show();
					}
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			
			int qtd_tentativas = 100;
			//Para fazer testes alterar apenas aqui. (Quantidade).
						
			if (acao.equals("logarAndroid")) {
				LogarAndroid logarAndroid = new LogarAndroid();
				for (int i = 0; i < qtd_tentativas; i++) {					
					Thread threadDologarAndroid = new Thread(logarAndroid);
					threadDologarAndroid.start();
				}
			}
			if (acao.equals("listarAcompanhante")) {
				ListarAcompanhante listarAcompanhante = new ListarAcompanhante();
				for (int i = 0; i < qtd_tentativas; i++) {					
					Thread threadDolistarAcompanhante = new Thread(listarAcompanhante);
					threadDolistarAcompanhante.start();
				}
			}
			if (acao.equals("listarServicos")) {
				ListarServicos listarServicos = new ListarServicos();
				for (int i = 0; i < qtd_tentativas; i++) {					
					Thread threadDolistarServicos = new Thread(listarServicos);
					threadDolistarServicos.start();
				}
			}
			if (acao.equals("cadastrarCliente")) {
				CadastrarCliente cadastrarCliente = new CadastrarCliente();
				for (int i = 0; i < qtd_tentativas; i++) {					
					Thread threadDocadastrarCliente = new Thread(cadastrarCliente);
					threadDocadastrarCliente.start();
				}
			}
			if (acao.equals("cadastrarAcompanhante")) {
				CadastrarAcompanhante cadastrarAcompanhante = new CadastrarAcompanhante();
				for (int i = 0; i < qtd_tentativas; i++) {					
					Thread threadDocadastrarAcompanhante = new Thread(cadastrarAcompanhante);
					threadDocadastrarAcompanhante.start();
				}
			}		
								
			break;
		}
	}
}