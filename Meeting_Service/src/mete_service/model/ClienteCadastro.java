package mete_service.model;

import com.example.meeting_service.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ClienteCadastro extends Activity {

	private EditText CCnome, CCcpf, CCtelefone, CCemail, CCsenha;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cliente);

		CCnome = (EditText) findViewById(R.id.edtNomeCliente);
		CCcpf = (EditText) findViewById(R.id.edtCPFCliente);
		CCtelefone = (EditText) findViewById(R.id.edtTelefoneCliente);
		CCemail = (EditText) findViewById(R.id.edtEmailCliente);
		CCsenha = (EditText)findViewById(R.id.edtSenhaCliente);
		// Log.i("teste",
		// getIntent().getExtras().getString("sexo_1").toString());
	}
}
