package br.uni.mette_service.Controller;


import br.uni.mette_service.R;
import br.uni.mette_service.R.id;
import br.uni.mette_service.R.layout;
import br.uni.mette_service.Controller.Acompanhante.ListarAcompanhanteActivity;
import br.uni.mette_service.Controller.Cliente.CadastroClienteActivity;
import br.uni.mette_service.Controller.Encontro.EncontroActivity;
import br.uni.mette_service.Model.Cliente;
import br.uni.mette_service.Util.PreferencesController;
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
import android.widget.TextView;

public class HomeActivity extends Activity implements OnClickListener {

	private Cliente cliente;
	private EditText CCnome, CCcpf, CCtelefone, CCemail, CCsenha;
	private Button btnTeste, CCavancar, CCvoltar;
	private Button btnop2, btnop3, btnop4, btnsobre;
	
	private boolean atualizar = false;
	private TextView txtUsuarioLogado;
	String usuarioLogado;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		usuarioLogado = getIntent().getStringExtra("usuarioLogado").toString() + "!";
		
		this.CCnome = (EditText) findViewById(R.id.edtNomeCliente);
		this.CCcpf = (EditText) findViewById(R.id.edtCPFCliente);
		this.CCtelefone = (EditText) findViewById(R.id.edtTelefoneCliente);
		this.CCemail = (EditText) findViewById(R.id.edtEmailCliente);
		this.CCsenha = (EditText) findViewById(R.id.edtSenhaCliente);
		
		this.btnop2 = (Button) findViewById(R.id.btnop2);
		this.btnop3 = (Button) findViewById(R.id.btnop3);
		this.btnop4 = (Button) findViewById(R.id.btnop4);
		this.btnsobre = (Button) findViewById(R.id.btnsobre);
		
		this.txtUsuarioLogado = (TextView) findViewById(R.id.txtUsuarioLogado);
		txtUsuarioLogado.setText((txtUsuarioLogado.getText().toString()) + " " + usuarioLogado);
		
		instantiateComponents();
	}

	public void instantiateComponents() {
		this.btnTeste = (Button) findViewById(R.id.btnTeste);
		this.btnTeste.setOnClickListener(this);
		this.btnop2.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent it = null;
		switch (v.getId()) {
		case R.id.btnTeste:
			it = new Intent(this, ListarAcompanhanteActivity.class);
			break;			
		case R.id.btnop2:
			it = new Intent(this, EncontroActivity.class);
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

		// ClienteController.getInstance().setUsuarioLogged(null);
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
