package br.uni.mete_service.Controller;

import br.uni.mete_service.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity implements OnClickListener {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cliente);
		instantiateComponents();
	}

	public void instantiateComponents() {

	}

	public void onClick(View v) {
		Intent it = null;

		switch (v.getId()) {

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
		
		ClienteController.getInstance().setUsuarioLogged(null);
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
