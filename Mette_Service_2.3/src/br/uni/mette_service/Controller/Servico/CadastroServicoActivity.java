package br.uni.mette_service.Controller.Servico;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.uni.mette_service.R;
import br.uni.mette_service.Mapa.CadastrarServicoAcompMapa;
import br.uni.mette_service.Mapa.Localizacao;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Servico;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

import com.google.gson.Gson;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class CadastroServicoActivity extends Activity implements OnClickListener{

	Usuario usuarioLogado = new Usuario();
	Repositorio repositorio = new Repositorio();
	private Button btnVoltar, btnCadastrarServico;
	private Spinner spinnerServicos;
	private EditText editValor;
	ServicoAcompanhante servicoAcompanhante = new ServicoAcompanhante();
	boolean cadastrarServico = true;
	List<Object> listaAcompanhante = new ArrayList<Object>();
	Acompanhante acompanhante = new Acompanhante();
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	Servico servico = new Servico();
	Acompanhante buscarAcompanhante = new Acompanhante();
	List<Object> listaServicoAcompanhante = new ArrayList();
	private AlertDialog alerta;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuarioLogado");
		
		setContentView(R.layout.activity_cadastro_servico);
		
		preencherSpinner();
		buscarAcompanhate(usuarioLogado);
		adicionarFindView();
		adicionarListers();
//		Double.valueOf(editValor.getText().toString()).doubleValue();
		
		
		
		spinnerServicos.setOnItemSelectedListener(new OnItemSelectedListener() {

	        public void onItemSelected(AdapterView<?> arg0,View arg1, int arg2, long arg3) {
	                                                
	        	servico = (Servico) arg0.getItemAtPosition(arg2);
	        	
	        	Toast.makeText(CadastroServicoActivity.this, "Serviço Selecionado: " +
	        									servico.getNome(), Toast.LENGTH_LONG).show();
	        	
	        	servicoAcompanhante.setServicoId(servico.getId());
	        	
	        	Log.i("teste", "oii  " + servicoAcompanhante.getServicoId());
	        }

	        public void onNothingSelected(
	        AdapterView<?> arg0) {                                              
	        }
	                                            
	    });

		super.onCreate(savedInstanceState);
	}
	
	private void buscarAcompanhate(Usuario usuarioLogado) {
		Toast toast = Toast.makeText(CadastroServicoActivity.this, "Activity FOI chamada para Edição.", Toast.LENGTH_LONG);
		toast.show();						
		listaAcompanhante.clear();
		acompanhante.setId(usuarioLogado.getIdUsuario());						
		listaAcompanhante.add(acompanhante);		
		
		modelo.setDados(listaAcompanhante);
		modelo.setMensagem("");
		modelo.setStatus("");									
		new buscarAcompanhantePorIdAsyncTask().execute();
	}

	private void adicionarFindView() {
		this.btnVoltar = (Button) findViewById(R.id.btnVoltar);
		this.btnCadastrarServico = (Button) findViewById(R.id.btnCadastrarServico);
		this.spinnerServicos = (Spinner) findViewById(R.id.spinnerServico);
		this.editValor = (EditText) findViewById(R.id.edtCServicoValor);
		
	}
   
	public void adicionarListers() {
		this.btnVoltar.setOnClickListener(this);
		this.btnCadastrarServico.setOnClickListener(this);

	}
	
	public void preencherSpinner(){
		
		new PreencherSpinnerAsyncTask().execute();
		
		
	}

	public void onClick(View v) {
		Intent it = null;
		switch (v.getId()) {
		case R.id.btnCadastrarServico:
//			it = new Intent(this, CadastroServicoMapa.class);
			//id da acompanhante é pego pela asynctask...
			
			servicoAcompanhante.setValor(editValor.getText().toString());	

			AlertDialog.Builder builder = new AlertDialog.Builder(CadastroServicoActivity.this);
			  
			//define o titulo
		    builder.setTitle("Casdatrar Serviço");
		    
		    //define a mensagem
	    builder.setMessage("Deseja cadastrar o serviço " + servico.getNome()
	    					+ " " + "no valor de: R$ " + servicoAcompanhante.getValor()
	    					+ " reais" );
		    
		    //define um botão como positivo
		    builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface arg0, int arg1) {
		        	
		        	new cadastrarServicoAsyncTask().execute();
		        	
		        }
		    });
		    
		    //define um botão como negativo.
		    builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface arg0, int arg1) {
		        		 alerta.dismiss();
		        }
		    });
		    //cria o AlertDialog
		    alerta = builder.create();
		    //Exibe
		    alerta.show();


			 
			
			break;		
		case R.id.btnVoltar:			
			finish();			
			break;	
		}
	}
	
	private String toString(InputStream is) throws IOException {

		byte[] bytes = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int lidos;
		while ((lidos = is.read(bytes)) > 0) {
		baos.write(bytes, 0, lidos);
		}
		return new String(baos.toByteArray());
	}
	
	
		//	------
		//	CLASS ASYNC TASK PARA PREENCHER A SPINNER COM OS SERVIÇOS
		//	--
	
		class PreencherSpinnerAsyncTask extends AsyncTask<Void, Void, Modelo> {
    	
    	ProgressDialog dialog;
    	
    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
    		dialog = ProgressDialog.show(
    				CadastroServicoActivity.this, 
    				"Aguarde", "Baixando Serviços", true, false);
    	}

    	@Override
    	protected Modelo doInBackground(Void... params) {
    		
    		Modelo servicoRetorno = new Modelo();
			Modelo modelo = new Modelo();

			servicoRetorno = repositorio.acessarServidor("listarServicos", modelo);

			return servicoRetorno;
    	}
    	
    	@Override
    	protected void onPostExecute(Modelo result) {
    		super.onPostExecute(result);

    		
    		
//    		for ( int i = 0; i < result.getDados().size(); ++i){
				
    			
//    			Object dadosObject = result.getDados().get(i);	
    		Object dadosObject = result.getDados();
    			Gson gson = new Gson();
    			JSONObject jsonObject = null;
    			
    			List<Servico> addServico = new ArrayList<Servico>();

    			try {
    				JSONArray jsonArray = new JSONArray(gson.toJson(dadosObject));
    				for ( int x = 0; x < jsonArray.length(); ++x){
    					jsonObject = jsonArray.getJSONObject(x);
    					
    					Servico serv = new Servico();
    					
    					serv.setNome(jsonObject.getString("\u0000Servico\u0000nome"));
        				serv.setId(jsonObject.getInt("\u0000Servico\u0000id"));

        				Log.i("PEDRO", x +"..." + serv.getId() + "..."+ serv.getNome());
        				
        				addServico.add(serv);
    				}

    				ServicoSpinnerAdapter spinnerAdapter = new ServicoSpinnerAdapter(getApplicationContext(),
    						addServico);
    				
//    				ArrayAdapter<Servico> arrayAdapter = new ArrayAdapter<Servico>(CadastroServicoActivity.this, 
//    						android.R.layout.simple_spinner_dropdown_item, addServico);
    				
//    				ArrayAdapter<Servico> spinnerArrayAdapter = arrayAdapter;
//    				spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
    				spinnerServicos.setAdapter(spinnerAdapter);
    				
    			}catch (Exception e) {
    				e.printStackTrace();			
    			}
    		
//    			}
    		dialog.dismiss();
    	}
    }
		
			//		------
			//	CLASS ASYNC TASK PARA BUSCAR ACOMPANHANTE
			//	--
		
		class buscarAcompanhantePorIdAsyncTask extends AsyncTask<String, String, Modelo>  {
			ProgressDialog dialog;
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = ProgressDialog.show(CadastroServicoActivity.this,
						"Cadastrando...", "Aguarde...",
						true, false);
			}

			@Override
			protected Modelo doInBackground(String... params) {	
				try
				{
					modeloRetorno = repositorio.acessarServidor("buscarAcompanhantePorIdUsuario", modelo);
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
					Toast toast = Toast.makeText(CadastroServicoActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
					toast.show();
				}else{				
					Object dadosObject = modeloRetorno.getDados().get(0);
					JSONObject jsonObject = null;
					Gson gson = new Gson();
					
					try {
						jsonObject = new JSONObject(gson.toJson(dadosObject));
						
						Log.i("SOSTENES", "RETORNO PARA MONTAR NA TELA" + gson.toJson(dadosObject));
						
						buscarAcompanhante.setId(jsonObject.getInt("\u0000Acompanhante\u0000id"));
					
						servicoAcompanhante.setAcompanhanteId(buscarAcompanhante.getId());
						
						String i = String.valueOf(buscarAcompanhante.getId());
						Log.i("teste", i);
						
					} catch (JSONException e) {
					}				

					
				}	
			}
		}	
	
		//		------
		//	CLASS ASYNC TASK PARA CADASTRAR O SERVICO.
		//	--
		
		class cadastrarServicoAsyncTask extends AsyncTask<String, String, Modelo>  {
			ProgressDialog dialog;
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = ProgressDialog.show(CadastroServicoActivity.this,
						"Cadastrando...", "Aguarde...",
						true, false);
			}

			@Override
			protected Modelo doInBackground(String... params) {

				ServicoAcompanhante servAcomp = new ServicoAcompanhante();
				
				servAcomp.setServicoId(servicoAcompanhante.getServicoId());
				servAcomp.setValor(servicoAcompanhante.getValor());
				servAcomp.setAcompanhanteId(servicoAcompanhante.getAcompanhanteId());
				
				listaServicoAcompanhante.add(servAcomp);
				modelo.setDados(listaServicoAcompanhante);
				modelo.setMensagem("");
				modelo.setStatus("");
				
				Gson gson = new Gson();
				
				
				System.out.println(gson.toJson(modelo));
				
				try
				{
					modeloRetorno = repositorio.acessarServidor("cadastrarServicosAcompanhante", modelo);
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
					Toast toast = Toast.makeText(CadastroServicoActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
					toast.show();
				}else{	
					
					Object dadosObject = modeloRetorno.getDados().get(0);
					JSONObject jsonObject = null;
					Gson gson = new Gson();
					
				try {
					jsonObject = new JSONObject(gson.toJson(dadosObject));
						
					ServicoAcompanhante servicoAcompanhanteRetorno = new ServicoAcompanhante();
			
					servicoAcompanhanteRetorno.setId(
						jsonObject.getInt("\u0000ServicosAcompanhante\u0000id"));
				
						//INTENT PARA PASSAR AO MAPA PARA CADASTRO DA LOCALIZAÇÃO
						Intent it = new Intent(CadastroServicoActivity.this, CadastrarServicoAcompMapa.class);
						it.putExtra("intentServicoAcompanhante", servicoAcompanhanteRetorno);
							startActivity(it);
					
					} catch (JSONException e) {
					}				
					finish();
				}	
				}	
			}
		

}