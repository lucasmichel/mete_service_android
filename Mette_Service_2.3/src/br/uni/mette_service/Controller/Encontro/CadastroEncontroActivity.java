package br.uni.mette_service.Controller.Encontro;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import com.google.gson.Gson;
import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Acompanhante.ListarAcompanhanteActivity;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Cliente;
import br.uni.mette_service.Model.Encontro;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroEncontroActivity extends Activity implements OnClickListener {

	private TextView txtLinkAdicionarAcompanhante;
	private Button btnMarcarEncontro;
	private Button btnVoltar;
	Cliente cliente = new Cliente();
	Encontro encontro = new Encontro();	
	List<Object> listaApoio = new ArrayList();
	Usuario usuarioLogado = new Usuario();
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();	
	Repositorio repositorio = new Repositorio();			

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_encontro);
		usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuarioLogado");

		adicionarFindView();
		adicionarListers();
	}

	private void adicionarFindView() {
		this.txtLinkAdicionarAcompanhante = (TextView) findViewById(R.id.textView2);
		this.btnVoltar = (Button) findViewById(R.id.btnEncontroVoltar);
		this.btnMarcarEncontro = (Button) findViewById(R.id.btnEncontroAvancar);		
	}

	public void adicionarListers() {
		this.txtLinkAdicionarAcompanhante.setOnClickListener(this);		
		this.btnVoltar.setOnClickListener(this);
		this.btnMarcarEncontro.setOnClickListener(this);
	}


	protected void onActivityResult(int requestCode, int resultCode, Intent data) {				
		try
		{			
			Acompanhante acompanhanteSelecionada = (Acompanhante) data.getSerializableExtra("ACOMPANHANTE_SELECIONADA");
			adicionarAcompanhanteAoListView(acompanhanteSelecionada);
			if (requestCode == 1 && acompanhanteSelecionada.getId() > 0) {
				Toast toast = Toast.makeText(CadastroEncontroActivity.this, "Foi adicionada ao Encontro a Acompanhante " 
						+ acompanhanteSelecionada.getNome() + " de Id " 
						+ acompanhanteSelecionada.getId(), Toast.LENGTH_LONG);
				toast.show();         
			}			
		} catch (Exception e) {//Cairá na Exception se não foi clicado em nenhuma das Acompanhantes; Com isso o retorno do StartActivityForResult será Nulo.	
			e.printStackTrace();
			Toast toast = Toast.makeText(CadastroEncontroActivity.this, "Nenhuma Acompanhante Adicionada.", Toast.LENGTH_LONG);
			toast.show();   
		}		
	}

	private String adicionarAcompanhanteAoListView(Acompanhante acompanhante){

		return "";
	}

	public void onClick(View v) {
		Intent it = null;		
		switch (v.getId()) 		
		{
		case R.id.textView2:	
			it = new Intent(this, ListarAcompanhanteActivity.class);
			it.putExtra("eSolicitacaoDeEncontro", true);			
			startActivityForResult(it, 1);			
			break;
		case R.id.btnEncontroAvancar:
			listaApoio.clear();
			cliente.setId(usuarioLogado.getIdUsuario());
			listaApoio.add(cliente);
			
			modelo.setDados(listaApoio);
			modelo.setMensagem("");
			modelo.setStatus("");
			new cadastrarEncontroAsyncTask().execute();
			break;
		case R.id.btnEncontroVoltar:

			break;
		}
	}		

	// cadastrarEncontro
	class cadastrarEncontroAsyncTask extends AsyncTask<String, String, Modelo>  {
		ProgressDialog dialog;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(CadastroEncontroActivity.this,
					"Cadastrando...", "Aguarde...",
					true, false);
		}

		@Override
		protected Modelo doInBackground(String... params) {	
			try
			{
				modeloRetorno = repositorio.acessarServidor("buscarClientePorIdUsuario", modelo);				
				if (modeloRetorno.getStatus().equals("1"))
				{
					Toast toast = Toast.makeText(CadastroEncontroActivity.this, "ERRO: " + modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
					toast.show();
				}else{					
					Object dadosObject = modeloRetorno.getDados().get(0);
					JSONObject jsonObject = null;
					Gson gson = new Gson();					
					jsonObject = new JSONObject(gson.toJson(dadosObject));
					int idClienteLogado = jsonObject.getInt("\u0000Cliente\u0000id");					

					listaApoio.clear();		
					encontro.setClienteId(idClienteLogado);		
					encontro.setDataHora("TesteViaAndroid - 30/04/2013 21:11:21"); //hora para testes

					listaApoio.add(encontro);							

					modelo.setDados(listaApoio);
					modelo.setMensagem("");
					modelo.setStatus("");					
					modeloRetorno = repositorio.acessarServidor("cadastrarEncontro", modelo);					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return modeloRetorno;
		}

		@Override
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if (modeloRetorno.getStatus().equals("1"))
			{
				Toast toast = Toast.makeText(CadastroEncontroActivity.this, "Erro no Servidor " + modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
			}else{								
				Toast toast = Toast.makeText(CadastroEncontroActivity.this, "Tudo Ok " + modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();												
			}	
		}
	}	
	// Fim / cadastrarEncontro		
}