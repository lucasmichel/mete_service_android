package mete_service.model;

import mete_service.model.repositorio.RepositorioCliente;


import com.example.meeting_service.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ClienteCadastro extends Activity implements OnClickListener {

	private RepositorioCliente rep;
	private Cliente cliente;
	private EditText CCnome, CCcpf, CCtelefone, CCemail, CCsenha;
	private Button CCavancar, CCvoltar;
	private boolean atualizar = false;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cliente);

		this.CCavancar = (Button) findViewById(R.id.btnAvancar);
		this.CCvoltar = (Button) findViewById(R.id.btnVoltar);
		this.CCavancar.setOnClickListener(this);
		this.CCvoltar.setOnClickListener(this);

		this.CCnome = (EditText) findViewById(R.id.edtNomeCliente);
		this.CCcpf = (EditText) findViewById(R.id.edtCPFCliente);
		this.CCtelefone = (EditText) findViewById(R.id.edtTelefoneCliente);
		this.CCemail = (EditText) findViewById(R.id.edtEmailCliente);
		this.CCsenha = (EditText) findViewById(R.id.edtSenhaCliente);
		// Log.i("teste",
		// getIntent().getExtras().getString("sexo_1").toString());
		rep = new RepositorioCliente(this);

		if (getIntent().getSerializableExtra("cliente") != null) {
			cliente = (Cliente) getIntent().getSerializableExtra("cliente");
			this.CCnome.setText(cliente.getNome());
			this.CCcpf.setText(cliente.getCpf());
			this.CCtelefone.setText(cliente.getTelefone());
			this.CCsenha.setText(cliente.getSenha());
			atualizar = true;
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAvancar:
			if (atualizar) {
				cliente.setNome(this.CCnome.getText().toString());
				cliente.setCpf(this.CCcpf.getText().toString());
				cliente.setTelefone(this.CCtelefone.getText().toString());
				cliente.setEmail(this.CCemail.getText().toString());
				cliente.setSenha(this.CCsenha.getText().toString());
				rep.alterar(cliente);
				setResult(RESULT_OK);

			} else {
				Cliente c = new Cliente(this.CCnome.getText().toString(),
										this.CCcpf.getText().toString(), 
										this.CCtelefone.getText().toString(), 
										this.CCemail.getText().toString(), 
										this.CCsenha.getText().toString());
				rep.inserir(c);
				Intent it = new Intent();
				it.putExtra("cliente", c);
				setResult(RESULT_OK, it);
			}
			finish();
			break;
		case R.id.btnVoltar:

			break;
		default:
			break;
		}

	}
}
