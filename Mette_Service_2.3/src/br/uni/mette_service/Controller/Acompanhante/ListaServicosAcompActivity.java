package br.uni.mette_service.Controller.Acompanhante;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
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
import br.uni.mette_service.Mapa.MapaActivity;
import br.uni.mette_service.Mapa.MapaListarServicoSelecionado;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Servico;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

import com.google.gson.Gson;

public class ListaServicosAcompActivity extends ListActivity implements OnClickListener {

	private Button btnVoltar;
	boolean acompanhanteSelecionada;
	Acompanhante acompanhanteBuscar = new Acompanhante();
	List<Object> listaAcompanhante = new ArrayList();
	
	Repositorio repositorio = new Repositorio();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        
//        acompanhanteSelecionada = getIntent().getBooleanExtra("mapaAcompSelecionada",false);
        new listarLocalizacaoServicoAcompanhante().execute();
        
        //AVISO PARA OS INTEGRANTES QUE FOREM TESTAR
        
        Toast.makeText(this, "SO O SERVICO ANAL SE ENCONTRA CADASTRADO EM MAPA",
        		Toast.LENGTH_LONG).show();
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
    
	class listarLocalizacaoServicoAcompanhante extends AsyncTask<Void, Void, Modelo>{
		
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
							jsonObject.getInt("\u0000ServicosAcompanhante\u0000id"));
					servicoAcompanhante.setServicoId(
							jsonObject.getInt("\u0000ServicosAcompanhante\u0000servicoId"));
					servicoAcompanhante.setValor(
							jsonObject.getString("\u0000ServicosAcompanhante\u0000valor"));
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
	
	
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
	// TODO Auto-generated method stub
	super.onListItemClick(l, v, position, id);
	
	ServicoAcompanhante idServicoAcompanante = (ServicoAcompanhante) l.getItemAtPosition(position);
//	boolean listarServicoAcomapanhteSelecionado = true;
	
	Intent it = new Intent(this, MapaListarServicoSelecionado.class);
	it.putExtra("idServicoAcompanante", idServicoAcompanante);
//	it.putExtra("listarServicoAcomapanhteSelecionado", listarServicoAcomapanhteSelecionado);
	startActivity(it);
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

}
