package br.uni.mette_service.Controller;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Cliente.CadastroClienteActivity;
import br.uni.mette_service.Controller.Servico.ListaServicosActivity;
import br.uni.mette_service.Mapa.MapaActivity;
import br.uni.mette_service.Model.Cliente;
import br.uni.mette_service.Model.Usuario;
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
import android.widget.Toast;

public class ClienteMenuActivity extends Activity implements OnClickListener {

	boolean eEdicao = true;
	Usuario usuarioLogado = new Usuario();
	private Button btnEditar;
	
	private Cliente cliente;
	private EditText CCnome, CCcpf, CCtelefone, CCemail, CCsenha;
	private Button btnTeste, CCavancar, CCvoltar;
	private Button btnop2, btnop3, btnop4, btnMapa;
	private TextView txtUsuarioLogado;	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);		
		usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuarioLogado");										
		adicionarFindView();			
		adicionarListers();
		this.txtUsuarioLogado.setText(usuarioLogado.getIdUsuario() + " - Ol�, " + usuarioLogado.getEmail() + "!");
	}
	
	private void adicionarFindView() {
		this.CCnome = (EditText) findViewById(R.id.edtNomeCliente);
		this.CCcpf = (EditText) findViewById(R.id.edtCPFCliente);		
		this.CCemail = (EditText) findViewById(R.id.edtEmailCliente);
		this.CCsenha = (EditText) findViewById(R.id.edtSenhaCliente);		
		this.btnop2 = (Button) findViewById(R.id.btnop2);
		this.btnEditar = (Button) findViewById(R.id.btneditar);
		this.btnop4 = (Button) findViewById(R.id.btnop4);
		this.btnMapa = (Button) findViewById(R.id.btnMapa);
		this.txtUsuarioLogado = (TextView) findViewById(R.id.txtUsuarioLogado);
	}
	
	public void adicionarListers() {
		this.btnTeste = (Button) findViewById(R.id.btnTeste);
		this.btnTeste.setOnClickListener(this);
		this.btnop2.setOnClickListener(this);
		this.btnEditar.setOnClickListener(this);
		this.btnMapa.setOnClickListener(this);
		this.btnop4.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent it = null;
		switch (v.getId()) {
		case R.id.btneditar:
			it = new Intent(this, CadastroClienteActivity.class);		
			it.putExtra("usuarioLogado", usuarioLogado);	
			it.putExtra("eEdicao", eEdicao);			
			startActivity(it);
			break;	
		case R.id.btnMapa:
			it = new Intent(this, MapaActivity.class);					
			startActivity(it);
			break;	
		case R.id.btnop4:
			it = new Intent(this, ListaServicosActivity.class);					
			startActivity(it);
			break;
		}
		
		
		
	}
}