package br.uni.mette_service.Controller.Acompanhante;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.ls.LSInput;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Servico.ServicoAcompanhante;
import br.uni.mette_service.Mapa.MapaListarServicoSelecionado;
//import br.uni.mette_service.Mapa.MapaActivity;
//import br.uni.mette_service.Mapa.MapaListarServicoSelecionado;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Servico;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

import com.google.gson.Gson;

public class ListaServicosAcompActivity extends ListActivity implements OnClickListener {

	private Button btnVoltar;
	boolean acompanhanteSelecionada;
	boolean acompanhanteListarSeusServicos;
	boolean eEncontro;
	Acompanhante acompanhanteBuscar = new Acompanhante();
	List<Object> listaAcompanhante = new ArrayList();
	ServicoAcompanhante idServicoAcompanante;
	List<Object> listaobj = new ArrayList<Object>();
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	private AlertDialog alerta;
	
	Repositorio repositorio = new Repositorio();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        
        eEncontro = getIntent().getBooleanExtra("eEncontro",false);
        
        /*PEGAR O BOOLEAN ENVIANDO PELA ACOMPANHANTE PARA LISTAR
         * SEUS SERVIÇOS */
        acompanhanteListarSeusServicos = 
        		getIntent().getBooleanExtra("acompanhanteListarServicos",false);
        
        /*DEPENDENDO DE QUEM CHAMOU A ACTIVITY UMA ASYNCTASK 
         * ESPECIFICA É CHAMADA */
        if(acompanhanteListarSeusServicos){
        new listarMeusServicosAcompanhante().execute();
        }else{
        new listarServicoAcompanhante().execute();
        }
    
    }

    private void adicionarFindView() {
		this.btnVoltar = (Button) findViewById(R.id.btnVoltar);
	}
   
    public void adicionarListers() {
		this.btnVoltar.setOnClickListener(this);

	}
  
	public void onClick(View v) {
		Intent it = null;
		switch (v.getId()) {
		case R.id.btnVoltar:
				finish();
			break;	
		}
	}
    
	protected void onListItemClick(ListView l, View v, int position, long id) {
	super.onListItemClick(l, v, position, id);		
	
	idServicoAcompanante = (ServicoAcompanhante) l.getItemAtPosition(position);
	
	if (eEncontro){		
		
		AlertDialog.Builder builder = 
				new AlertDialog.Builder(ListaServicosAcompActivity.this);

	    builder.setTitle("ALERT!").setIcon(R.drawable.dialog_stop);
	    
	    builder.setMessage("Deseja realmente adicionar esse serviço?");

	    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface arg0, int arg1) {

	    		Intent i = new Intent();
	            i.putExtra("SERVICO_SELECIONADO", idServicoAcompanante);                     
	            setResult(RESULT_OK, i);
	            finish();
	        	
	        }
	    });
	    
	    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface arg0, int arg1) {
	        }
	    });
	    alerta = builder.create();
	    alerta.show();
	
	}
	
	/*CASO SEJA A ACOMPANHANTE A CLICAR EM UM DE SEUS SERVIÇOS*/
	if(acompanhanteListarSeusServicos){
		AlertDialog.Builder builder = new AlertDialog.Builder(ListaServicosAcompActivity.this);

	    builder.setTitle("Escolha uma ação...").setIcon(R.drawable.dialog_stop);
	    
	    builder.setMessage("O que deseja fazer ?");

	    builder.setPositiveButton("Excluir meu Servico.", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface arg0, int arg1) {
	        	
	        	AlertDialog.Builder builder = new AlertDialog.Builder(ListaServicosAcompActivity.this);

			    builder.setTitle(" CUIDADO !! ").setIcon(R.drawable.dialog_stop);
			    
			    builder.setMessage("Deseja realmente excluir seu servico?");
			    
			    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface arg0, int arg1) {
			        
			        	ServicoAcompanhante servico1 = new ServicoAcompanhante();
			    		servico1.setId(idServicoAcompanante.getId());

			    		listaobj.add(servico1);
			    		modelo.setDados(listaobj);
			    		modelo.setMensagem("");
			    		modelo.setStatus("");
			    		
			    		Gson g = new Gson();
			    		
			    		Log.i("PEDRO" , g.toJson(modelo));
			    		
			    		new excluirServico().execute();
			        	
			        }
			    });
			    
			    
			    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface arg0, int arg1) {
			        

			        }
			    });
			    
			    alerta = builder.create();
			    
			    alerta.show();

	        }
	    });
	    
	    builder.setNeutralButton("Listar Locais do meu Serviço.", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface arg0, int arg1) {
	        	Boolean chamadaAcompanhante = true ;
	        	Intent it = new Intent(ListaServicosAcompActivity.this,
	        							MapaListarServicoSelecionado.class);
	        	it.putExtra("idServicoAcompanante", idServicoAcompanante);
	        	it.putExtra("chamadaAcompanhante", chamadaAcompanhante);
//	        	it.putExtra("listarServicoAcomapanhteSelecionado", listarServicoAcomapanhteSelecionado);
	        	startActivity(it);
	        	
	        }
	    });
	    
	    
	    builder.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface arg0, int arg1) {

	        	Toast.makeText(ListaServicosAcompActivity.this,
	        			"Sair", Toast.LENGTH_LONG).show();
	        }
	    });
	    alerta = builder.create();
	    alerta.show();
		
		
	}/*FIM DO IF CASO TENHA SIDO A ACOMPANHANTE A CLICAR NO SERVIÇO*/
	else{
	
//	Intent it = new Intent(this, MapaListarServicoSelecionado.class);
//	it.putExtra("idServicoAcompanante", idServicoAcompanante);
//	startActivity(it);
	
	}
}

	private String toString1(InputStream is) throws IOException {

	byte[] bytes = new byte[1024];
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	int lidos;
	while ((lidos = is.read(bytes)) > 0) {
		baos.write(bytes, 0, lidos);
	}
	return new String(baos.toByteArray());
}

	/*ASYNCTASK CHAMADA PELO CLIENTE */
	class listarServicoAcompanhante extends AsyncTask<Void, Void, Modelo>{
		
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(ListaServicosAcompActivity.this, "LOADING:",
					"Carregando pontos do mapa!", true, false);
			super.onPreExecute();
		}
		@Override
		protected Modelo doInBackground(Void... params) {
			
			Modelo locRetorno = new Modelo();
			Modelo modelo = new Modelo();
			
			Acompanhante acomp = new Acompanhante();
			
			acompanhanteBuscar = (Acompanhante)getIntent().getSerializableExtra("acompanhanteSelecionada");
			acomp.setId(acompanhanteBuscar.getId());
			listaAcompanhante.add(acomp);
			
			modelo.setDados(listaAcompanhante);
			modelo.setMensagem("");
			modelo.setStatus("");
			
			locRetorno = repositorio.acessarServidor("listarServicoAcompanhante", modelo);

			return locRetorno;
		}
		
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);

//			for ( int i = 0; i < result.getDados().size(); ++i){
			
//				Object dadosObject = result.getDados().get(0);	
			Object dadosObject = result.getDados();	
			Gson gson = new Gson();
			String w = gson.toJson(dadosObject);
			System.out.println(w);
			JSONObject jsonObject = null;
			List<ServicoAcompanhante> addServico = new ArrayList<ServicoAcompanhante>();

			try {
				JSONArray jsonArray = new JSONArray(gson.toJson(dadosObject));
				for ( int x = 0; x < jsonArray.length(); ++x){
					jsonObject = jsonArray.getJSONObject(x);
					
					ServicoAcompanhante servicoAcompanhante = new ServicoAcompanhante();
										
					servicoAcompanhante.setId(
							jsonObject.getInt("id"));
					servicoAcompanhante.setServicoId(
							jsonObject.getInt("servicoId"));
					servicoAcompanhante.setValor(
							jsonObject.getString("valor"));										
					
    				Log.i("PEDRO", x +"..." + servicoAcompanhante.getId());
    				
    				addServico.add(servicoAcompanhante);
				}
				setListAdapter(
    					new ListarServicoAcompanhanteAdapter(ListaServicosAcompActivity.this, 
    							addServico));


			}catch (Exception e) {
				e.printStackTrace();			
			}
		
			
//			}
			
			dialog.dismiss();
		}
	}
	
	/*ASYNCTASK CHAMADA PELA A ACOMPANHANTE */
	class listarMeusServicosAcompanhante extends AsyncTask<Void, Void, Modelo>{
		
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(ListaServicosAcompActivity.this, "LOADING:",
					"Carregando pontos do mapa!", true, false);
			super.onPreExecute();
		}
		@Override
		protected Modelo doInBackground(Void... params) {
			
			Modelo locRetorno = new Modelo();
			Modelo modelo = new Modelo();
			
			Acompanhante acomp = new Acompanhante();
			
			acompanhanteBuscar = (Acompanhante)getIntent().getSerializableExtra("idAcompanhante");
			acomp.setId(acompanhanteBuscar.getId());
			listaAcompanhante.add(acomp);
			
			modelo.setDados(listaAcompanhante);
			modelo.setMensagem("");
			modelo.setStatus("");
			
			locRetorno = repositorio.acessarServidor("listarServicoAcompanhante", modelo);

			return locRetorno;
		}
		
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);

//			for ( int i = 0; i < result.getDados().size(); ++i){
			
//				Object dadosObject = result.getDados().get(0);	
			Object dadosObject = result.getDados();	
			Gson gson = new Gson();
			String w = gson.toJson(dadosObject);
			System.out.println(w);
			JSONObject jsonObject = null;
			List<ServicoAcompanhante> addServico = new ArrayList<ServicoAcompanhante>();

			try {
				JSONArray jsonArray = new JSONArray(gson.toJson(dadosObject));
				for ( int x = 0; x < jsonArray.length(); ++x){
					jsonObject = jsonArray.getJSONObject(x);
					
					ServicoAcompanhante servicoAcompanhante = new ServicoAcompanhante();
					
					servicoAcompanhante.setId(
							jsonObject.getInt("id"));
					servicoAcompanhante.setServicoId(
							jsonObject.getInt("servicoId"));
					servicoAcompanhante.setValor(
							jsonObject.getString("valor"));
    				Log.i("PEDRO", x +"..." + servicoAcompanhante.getId());
    				
    				addServico.add(servicoAcompanhante);
				}
				setListAdapter(
    					new ListarServicoAcompanhanteAdapter(ListaServicosAcompActivity.this, 
    							addServico));


			}catch (Exception e) {
				e.printStackTrace();			
			}
		
			
//			}
			
			dialog.dismiss();
		}
	}
	
	/*ASYNCTASK CHAMADA PELA A ACOMPANHANTE PARA EXCLUIR UM SERVIÇO */
	class excluirServico extends AsyncTask<Void, Void, Modelo>{
		
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(ListaServicosAcompActivity.this, "LOADING:",
					"Excluindo seu Servico.", true, false);
			super.onPreExecute();
		}
		@Override
		protected Modelo doInBackground(Void... params) {
			
			modeloRetorno = repositorio.acessarServidor(
					"excluirServicoAcompanhante", modelo);

			return modeloRetorno;
		}
		
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);

			if (result.getStatus().equals("1"))
			{
				Toast toast = Toast.makeText(ListaServicosAcompActivity.this, result.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
			}else{									
				Toast toast = Toast.makeText(ListaServicosAcompActivity.this, result.getMensagem(), Toast.LENGTH_LONG);
				toast.show();	
				finish();
			}	
			
			dialog.dismiss();
		}
	}

}