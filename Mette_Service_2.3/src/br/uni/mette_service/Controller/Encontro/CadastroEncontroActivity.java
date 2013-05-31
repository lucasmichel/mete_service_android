package br.uni.mette_service.Controller.Encontro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Acompanhante.ListaServicosAcompActivity;
import br.uni.mette_service.Controller.Acompanhante.ListarAcompanhanteActivity;
import br.uni.mette_service.Controller.Servico.ServicoAcompanhante;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Cliente;
import br.uni.mette_service.Model.Encontro;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;
import android.R.bool;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroEncontroActivity extends Activity implements OnClickListener,OnItemClickListener {

	private TextView txtLinkAdicionarAcompanhante;
	private TextView txtDataHoraEncontro;
	private Button btnMarcarEncontro;
	private Button btnVoltar;
	private ListView listViewAcompanhanteEncontro;
	private ListView listViewServicoEncontro;
	 private ArrayAdapter<String> listAdapterServico;
	
	private ServicoAcompanhante servicoAcompanhanteSelecionado = new ServicoAcompanhante();
	private Acompanhante acompanhanteClicada = new Acompanhante();
	Cliente cliente = new Cliente();
	Encontro encontro = new Encontro();	
	List<Object> listaApoio = new ArrayList();
	Usuario usuarioLogado = new Usuario();
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();	
	Repositorio repositorio = new Repositorio();		
	private List<Acompanhante> listaAcompanhanteEncontro = new ArrayList();
	private List<String> listContent = new ArrayList<String>();
	private List<ServicoAcompanhanteEncontro> listaServicoAcompanhanteEncontro = new ArrayList();	
	
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
		this.listViewAcompanhanteEncontro = (ListView) findViewById(R.id.listaAcompanhanteEncontro);	
		this.listViewServicoEncontro = (ListView) findViewById(R.id.listaServicoAcompanhanteEncontro);
		this.txtDataHoraEncontro = (TextView) findViewById(R.id.editEncontroDataHora);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}		

	public void adicionarListers() {
		this.txtLinkAdicionarAcompanhante.setOnClickListener(this);		
		this.btnVoltar.setOnClickListener(this);
		this.btnMarcarEncontro.setOnClickListener(this);	
		this.listViewAcompanhanteEncontro.setOnItemClickListener(this);		
	}			
	
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		acompanhanteClicada = (Acompanhante) arg0.getItemAtPosition(arg2);
		Intent it;
		it = new Intent(this, ListaServicosAcompActivity.class);
		it.putExtra("acompanhanteSelecionada", acompanhanteClicada);		
		it.putExtra("eEncontro", true);
		startActivityForResult(it, 2);				
	}	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {				
		try
		{			
			Acompanhante acompanhanteSelecionada = (Acompanhante) data.getSerializableExtra("ACOMPANHANTE_SELECIONADA");			
			if (requestCode == 1 && acompanhanteSelecionada.getId() > 0) {
				Toast toast = Toast.makeText(CadastroEncontroActivity.this, adicionarAcompanhanteAoListView(acompanhanteSelecionada), Toast.LENGTH_LONG);
				toast.show();
			}						
			servicoAcompanhanteSelecionado = (ServicoAcompanhante) data.getSerializableExtra("SERVICO_SELECIONADO");			
			if (requestCode == 2) {					
				ServicoAcompanhanteEncontro servicoAcompanhanteEncontro = new ServicoAcompanhanteEncontro();
				servicoAcompanhanteEncontro.setServicoAcompanhanteId(servicoAcompanhanteSelecionado.getId());					
				adicionarAcompanhanteServicoAoListView(acompanhanteClicada.getNome() + " - " + servicoAcompanhanteEncontro.getServicoAcompanhanteId());				
				atualizarlistaServicoAcompanhanteEncontro(servicoAcompanhanteEncontro);	
			}						
		} catch (Exception e) {//Cairá na Exception se não foi clicado em nenhuma das Acompanhantes; Com isso o retorno do StartActivityForResult será Nulo.	
			e.printStackTrace();
			Toast toast = Toast.makeText(CadastroEncontroActivity.this, "Nada Adicionado!", Toast.LENGTH_LONG);
			toast.show();   
		}		
	}
	private void adicionarAcompanhanteServicoAoListView(String novoValor){				
		listContent.add(novoValor);				
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listContent); 
		listViewServicoEncontro.setAdapter(adapter); 	
	}
	
	private void atualizarlistaServicoAcompanhanteEncontro(ServicoAcompanhanteEncontro servicoAcompanhanteEncontro){		
		listaServicoAcompanhanteEncontro.add(servicoAcompanhanteEncontro);					 		
	}

	private String adicionarAcompanhanteAoListView(Acompanhante acompanhante){
		String retorno = "A Acompanhante já presente ao Encontro!";		
		if (!itemJaAdicionado(acompanhante)){
			listaAcompanhanteEncontro.add(acompanhante);			
			listViewAcompanhanteEncontro.setAdapter(new ListaAcompanhanteAdapter(this, listaAcompanhanteEncontro));			
			return "Lista Atualizada com " + acompanhante.getNome();
		}
		return retorno;
	}
	
	private boolean itemJaAdicionado(Acompanhante acompanhante){	
		boolean retorno = false;
		if (listaAcompanhanteEncontro.size() > 0){
			for (int i = 0; i < listaAcompanhanteEncontro.size(); i++) {
				if (listaAcompanhanteEncontro.get(i).getId() == acompanhante.getId()){					
					retorno = true;			
				}
			}	
		}
		return retorno;
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
					int idClienteLogado = jsonObject.getInt("id");												
					listaApoio.clear();		
					encontro.setClienteId(idClienteLogado);		
					encontro.setDataHora(txtDataHoraEncontro.getText().toString());
					listaApoio.add(encontro);							
					modelo.setDados(listaApoio);
					modelo.setMensagem("");
					modelo.setStatus("");					
					modeloRetorno = repositorio.acessarServidor("cadastrarEncontro", modelo);	
					
					if (modeloRetorno.getStatus().equals("1"))
					{
						Toast toast = Toast.makeText(CadastroEncontroActivity.this, "ERRO: " + modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
						toast.show();
					}else{													
						
						Object dadosObjectEncontro = modeloRetorno.getDados().get(0);
						JSONObject jsonObjectEncontro = null;						
						jsonObjectEncontro = new JSONObject(gson.toJson(dadosObjectEncontro));						
						int idEncontro = jsonObjectEncontro.getInt("id");														
																								
						for (int i = 0; i < listaServicoAcompanhanteEncontro.size(); i++) {
							listaServicoAcompanhanteEncontro.get(i).setEncontroId(idEncontro);								
							listaServicoAcompanhanteEncontro.get(i).setClienteId(encontro.getClienteId());
						}						
						
						Log.i("NEW", "" + gson.toJson(listaServicoAcompanhanteEncontro));																					
						
						listaApoio.clear();
						listaApoio.add(listaServicoAcompanhanteEncontro);
						
						modelo.setDados(listaApoio);
						modelo.setMensagem("");
						modelo.setStatus("");					
						modeloRetorno = repositorio.acessarServidor("cadastrarServicosDoEncontro", modelo);
					}
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
			if (modeloRetorno.getStatus().equals("1")) //Se é 1, houve erro.
			{
				Toast toast = Toast.makeText(CadastroEncontroActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
			}else{								
				finish();
				Toast toast = Toast.makeText(CadastroEncontroActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();				
			}	
		}
	}	
	// Fim / cadastrarEncontro		
}