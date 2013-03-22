package br.uni.mete_service.Controller.Cliente;

import br.uni.mete_service.R;

import br.uni.mete_service.Controller.HomeActivity;
import br.uni.mete_service.model.Cliente;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroClienteActivity extends Activity implements
		OnClickListener {

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
		// rep = new RepositorioCliente(this);
		// cliente = new Cliente(1, "Tiago", "030123433876", 1, "87290729",
		// "gomes.tg@hotmail.com", "123456");
		inicializacaoVerificacao();

	}

	private void inicializacaoVerificacao() {

		if (getIntent().getSerializableExtra("cliente") != null) {
			cliente = (Cliente) getIntent().getSerializableExtra("cliente");
			this.CCnome.setText(cliente.getNome());
			this.CCcpf.setText(cliente.getCpf());
			this.CCtelefone.setText(cliente.getTelefone());
			this.CCemail.setText(cliente.getEmail());
			this.CCsenha.setText(cliente.getSenha());
			atualizar = true;
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAvancar:
			// if (atualizar) { 
			Cliente cliente = new Cliente();
			cliente.setNome(CCnome.getText().toString());
			cliente.setCpf(CCcpf.getText().toString());
			cliente.setTipo("1");
			cliente.setTelefone(CCtelefone.getText().toString());
			cliente.setEmail(CCemail.getText().toString());
			cliente.setSenha(CCsenha.getText().toString());
			cliente.setStatus(0);
			cliente.setMensagem("");
			try {
				Toast.makeText(getApplicationContext(),
						"nome " + cliente.getNome().toString(),
						Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(),
						"cpf " + cliente.getCpf().toString(),
						Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(),
						"telefone " + cliente.getTelefone().toString(),
						Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(),
						"email " + cliente.getEmail().toString(),
						Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(),
						"Senha " + cliente.getSenha().toString(),
						Toast.LENGTH_SHORT).show();
				
				Cliente userRetorno = new Cliente();

				userRetorno = (Cliente) cliente.cadastrarCliente(cliente);

				Toast.makeText(getApplicationContext(),
						userRetorno.getMensagem().toString(), Toast.LENGTH_LONG)
						.show();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * } else { Cliente c = new Cliente();
			 * c.setNome(this.CCnome.getText().toString());
			 * c.setCpf(this.CCcpf.getText().toString());
			 * c.setTelefone(this.CCtelefone.getText().toString());
			 * c.setEmail(this.CCemail.getText().toString());
			 * c.setSenha(this.CCsenha.getText().toString()); c.setTipo(1); //
			 * controler.inserirCliente(c); Intent it = new Intent(this,
			 * HomeActivity.class); setResult(RESULT_OK, it); }
			 */
			finish();
			break;
		case R.id.btnVoltar:

			break;
		default:
			break;
		}
	}
}
