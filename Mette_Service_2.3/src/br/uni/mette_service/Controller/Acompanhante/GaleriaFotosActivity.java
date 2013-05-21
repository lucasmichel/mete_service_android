package br.uni.mette_service.Controller.Acompanhante;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Cliente.CadastroClienteActivity;
import br.uni.mette_service.Controller.Servico.ListaServicosActivity;
import br.uni.mette_service.Controller.Servico.ServicoAdapter;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Foto;
import br.uni.mette_service.Model.Servico;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;


public class GaleriaFotosActivity extends Activity{

	private Gallery gallery;
	private Usuario usuarioLogado = new Usuario();
	List<Object> listaAcompanhante = new ArrayList();
	Acompanhante acompanhante = new Acompanhante();
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	Repositorio repositorio = new Repositorio();
	Acompanhante buscarAcompanhante = new Acompanhante();
	List<Object> listaFotos = new ArrayList();
	private ImageView imgView;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_foto);
		usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuarioLogado");
		adicionarFindView();
		listarFotos();
	}
	
	private void adicionarFindView() {
		gallery = (Gallery) findViewById(R.id.galeriafts);
		imgView = (ImageView) findViewById(R.id.imageView1);
	}
	
	private void listarFotos(){
		Toast toast = Toast.makeText(GaleriaFotosActivity.this, "Activity chamada listar Fotos...", Toast.LENGTH_LONG);
		toast.show();	

		listaAcompanhante.clear();		
		acompanhante.setId(usuarioLogado.getIdUsuario());	
		listaAcompanhante.add(acompanhante);		
		
		modelo.setDados(listaAcompanhante);
		modelo.setMensagem("");
		modelo.setStatus("");								
		new listarFotosAsyncTask().execute();
	}	
	
	//listarFotosAsyncTask
	class listarFotosAsyncTask extends
			AsyncTask<String, String, Modelo> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(GaleriaFotosActivity.this,
					"Listando...", "Aguarde...",
					true, false);
		}

		@Override
		protected Modelo doInBackground(String... params) {
			try
			{
				modeloRetorno = repositorio.acessarServidor("buscarAcompanhantePorIdUsuario", modelo);				
				if (modeloRetorno.getStatus().equals("1"))
				{
					Toast toast = Toast.makeText(GaleriaFotosActivity.this, "ERRO: " + modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
					toast.show();
				}else{					
					Object dadosObject = modeloRetorno.getDados().get(0);
					JSONObject jsonObject = null;
					Gson gson = new Gson();					
					jsonObject = new JSONObject(gson.toJson(dadosObject));
					int idAcompanhanteListarFotos = jsonObject.getInt("\u0000Acompanhante\u0000id");					
					listaAcompanhante.clear();		
					acompanhante.setId(idAcompanhanteListarFotos);						
					listaAcompanhante.add(acompanhante);							
					modelo.setDados(listaAcompanhante);
					modelo.setMensagem("");
					modelo.setStatus("");					
					modeloRetorno = repositorio.acessarServidor("listarFotosPorIdAcompanhnate", modelo);
				}						
			} catch (Exception e) {				
				e.printStackTrace();
			}
			return modeloRetorno;
		}

		@Override
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);
			
			if (modeloRetorno.getStatus().equals("1"))
			{
				Toast toast = Toast.makeText(GaleriaFotosActivity.this, "ERRO: " + modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
			}else{									
				Toast toast = Toast.makeText(GaleriaFotosActivity.this, "100%: " + modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();	
	    		Object dadosObject = result.getDados();
    			Gson gson = new Gson();
    			JSONObject jsonObject = null;
    			List<Foto> listaFotos = new ArrayList<Foto>();
    			try {
    				JSONArray jsonArray = new JSONArray(gson.toJson(dadosObject));  
    				for ( int x = 0; x < jsonArray.length(); ++x){
    					jsonObject = jsonArray.getJSONObject(x);
    					Foto foto = new Foto();
    					foto.setId(jsonObject.getInt("\u0000Fotos\u0000id"));
    					foto.setNome("http://leonardogalvao.com.br/mete_service/src/img/foto/" +  jsonObject.getString("\u0000Fotos\u0000nome"));
    					listaFotos.add(foto);
    				}        				
    				Log.i("SOSTENES", "Test in GaleriaFotosActivity: " + gson.toJson(listaFotos));  
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    	    }
		}
	}
	//listarFotosAsyncTask	
}