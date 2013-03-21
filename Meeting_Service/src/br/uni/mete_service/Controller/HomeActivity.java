package br.uni.mete_service.Controller;

import br.uni.mete_service.R;

import br.uni.mete_service.Controller.Acompanhante.ListarAcompanhanteActivity;
import br.uni.mete_service.Controller.Cliente.CadastroClienteActivity;
import br.uni.mete_service.model.Cliente;
import br.uni.mete_service.model.repositorio.Cliente.ClienteController;
import br.uni.mete_service.util.PreferencesController;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends Activity implements OnClickListener {

	private Cliente cliente;
	private EditText CCnome, CCcpf, CCtelefone, CCemail, CCsenha;
	private Button btnTeste, CCavancar, CCvoltar;
	private boolean atualizar = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teste);

		this.CCnome = (EditText) findViewById(R.id.edtNomeCliente);
		this.CCcpf = (EditText) findViewById(R.id.edtCPFCliente);
		this.CCtelefone = (EditText) findViewById(R.id.edtTelefoneCliente);
		this.CCemail = (EditText) findViewById(R.id.edtEmailCliente);
		this.CCsenha = (EditText) findViewById(R.id.edtSenhaCliente);

		instantiateComponents();
	}

	public void instantiateComponents() {
		this.btnTeste = (Button) findViewById(R.id.btnTeste);
		this.btnTeste.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent it = null;
		switch (v.getId()) {
		case R.id.btnTeste:
			it = new Intent(this, ListarAcompanhanteActivity.class);
			break;
		}
		startActivity(it);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.homemenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {

		case R.menuComponentes.editar:
			editarPerfil();
			return true;
		case R.menuComponentes.sair:
			sair();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void sair() {

		//ClienteController.getInstance().setUsuarioLogged(null);
		Intent it;
		PreferencesController.clearUserPreferences(this);
		it = new Intent(this, LoginActivity.class);
		startActivity(it);
		finish();
	}

	public void editarPerfil() {
		
		
		Intent it;
		it = new Intent(this, CadastroClienteActivity.class);
		startActivity(it);
	}
}
