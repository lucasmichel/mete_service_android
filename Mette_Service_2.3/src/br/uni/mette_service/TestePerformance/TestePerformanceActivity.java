package br.uni.mette_service.TestePerformance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.EscolhaDoUsuarioActivity;
import br.uni.mette_service.Controller.LogarAndroidActivity;
import br.uni.mette_service.Controller.TermoUsoActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TestePerformanceActivity extends Activity implements
		OnClickListener {

	private Spinner spinnerAcoes;
	private Button btnTestarPerformance;
	private List<String> acoes = new ArrayList<String>();
	private String acao;
	private int rHoras, rMinutos, rSegundos, rMilesegundos;
	private int horasI, minutosI, segundosI, milesegundosI;

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

		acoes.add("cadastrarCliente");
		acoes.add("excluirCliente");
		acoes.add("logarAndroid");

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, acoes);
		ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
		spinnerArrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spinnerAcoes.setAdapter(spinnerArrayAdapter);

		// /
		// Método do Spinner para capturar o item selecionado
		spinnerAcoes
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parent, View v,int posicao, long id) {
						// pega nome pela posição
						acao = parent.getItemAtPosition(posicao).toString();
						// imprime um Toast na tela com o nome que foi selecionado
						Toast.makeText(TestePerformanceActivity.this,"Nome Selecionado: " + acao, Toast.LENGTH_LONG).show();
					}

					public void onNothingSelected(AdapterView<?> parent) {

					}
				});
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:

			if (acao.equals("cadastrarCliente")) {
				CadastraCliente cadastraCliente = new CadastraCliente();
				java.text.DateFormat tempo = new java.text.SimpleDateFormat("HH:mm:ss.SSS");
				Calendar dataInicio = Calendar.getInstance();
				
				for (int i = 0; i < 10; i++) {
					Thread threadDocadastraCliente = new Thread(cadastraCliente);
					threadDocadastraCliente.start();
				
				}
				Calendar dataFim = Calendar.getInstance();
				long diferenca = dataFim.getTimeInMillis()- dataInicio.getTimeInMillis();
				tempo.setTimeZone(TimeZone.getTimeZone("UTC"));
				System.out.println("TOTAL DE TEMPO PARA REALIZAÇÃO DA TAREFA: "+ tempo.format(diferenca));

			} else {
				Toast toast = Toast.makeText(TestePerformanceActivity.this,
						"Ainda não implementado!", Toast.LENGTH_LONG);
				toast.show();
			}
			break;
		}
	}
}
