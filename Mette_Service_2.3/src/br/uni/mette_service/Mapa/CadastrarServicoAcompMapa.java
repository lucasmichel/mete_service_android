package br.uni.mette_service.Mapa;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;
import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Servico.ServicoAcompanhante;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Servico;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

public class CadastrarServicoAcompMapa extends FragmentActivity 
implements LocationListener{

	private Marker meuMarker ;
	private GoogleMap googleMap;
	Repositorio repositorio = new Repositorio();
	Localizacao localizacaoCadastro = new Localizacao();
	ServicoAcompanhante intentServicoAcomp = new ServicoAcompanhante();
	Modelo modeloRetorno = new Modelo();
	List<Object> listaLocalizacoes = new ArrayList();
	private AlertDialog alerta;
	
	protected void onCreate(Bundle savedInstanceState) {  
	super.onCreate(savedInstanceState);  

		if (isGooglePlay()){
			setContentView(R.layout.activity_mapa); 
			setUpMap();
		}
		
		intentServicoAcomp = (ServicoAcompanhante) 
				getIntent().getSerializableExtra("intentServicoAcompanhante");
	
		Toast.makeText(this, "Clique no mapa para cadastrar um localização. ", 
				Toast.LENGTH_LONG).show();
	}
	
	private boolean isGooglePlay(){
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		
		if ( status == ConnectionResult.SUCCESS ){
			return (true);
		}
		else {
			Toast.makeText(this,"Google play não encontrado", Toast.LENGTH_SHORT).show();
		}
		return (false);
		
	}
	
	
	public void onLocationChanged(final Location location) {

			cadastrarServico();

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
	
	public void onProviderDisabled(String arg0) {
	
	}
	
	public void onProviderEnabled(String arg0) {
	
	}
	
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
	
	}
	
	private void setUpMap () {
	if (googleMap == null){
	googleMap = ((SupportMapFragment)getSupportFragmentManager()
			.findFragmentById(R.id.map)).getMap();
	googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		if (googleMap != null){
			googleMap.setMyLocationEnabled(true);
			
			LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
			
			String provider = lm.getBestProvider(new Criteria(), true);
			
			if (provider == null) {
				onProviderDisabled(provider);
			}
			Location loc = lm.getLastKnownLocation(provider);
			
			if (loc != null) {
				onLocationChanged(loc);
			}
			
			googleMap.setOnMapLongClickListener(onLongClick());
			
		}
	}
	}
	
	private OnMapLongClickListener onLongClick() {
		return new OnMapLongClickListener() {
		
		public void onMapLongClick(LatLng point){
			
			localizacaoCadastro.setLatitude(String.valueOf(point.latitude));
			localizacaoCadastro.setLongitude(String.valueOf(point.longitude));
			
//			localizacaoCadastro.getLatitude();
//			localizacaoCadastro.getLongitude();
			
			AlertDialog.Builder builder = new AlertDialog.Builder(CadastrarServicoAcompMapa.this);
		    
			//define o titulo
		    builder.setTitle("Localizações");
		    
		    //define a mensagem
		    builder.setMessage("Deseja cadastrar um nova localização para esse serviço?");
		    
		    //define um botão como positivo
		    builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface arg0, int arg1) {
		        	
		        	Localizacao localizacaoLista = new Localizacao();
		        	boolean sim = true;
		        	localizacaoLista.setServicoAcompanhanteId(intentServicoAcomp.getId());
		        	

		        	localizacaoLista.setLatitude(localizacaoCadastro.getLatitude());
		        	localizacaoLista.setLongitude(localizacaoCadastro.getLongitude());
		        	
		        	listaLocalizacoes.add(localizacaoLista);
		        	sim(true);
		        	Toast.makeText(CadastrarServicoAcompMapa.this, "Selecione outro ponto.",
		        			Toast.LENGTH_LONG).show();
		        	
		        	Gson gson = new Gson();
		        String i =	gson.toJson(listaLocalizacoes);
		        	System.out.println(i);
		        	
		        }
		    });
		    
		    //define um botão como negativo.
		    builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface arg0, int arg1) {
		        	
		        	new	cadastrarServicoMapaAsyncTask().execute();
		       
		        }
		    });
		    //cria o AlertDialog
		    alerta = builder.create();
		    //Exibe
		    alerta.show();

		}
	};
	}
	
	private void sim(boolean b){
	
		onLongClick();
		
	}
	
	private void cadastrarServico() {
	
		onLongClick();

	}
	
		//----
		//CLASS ASYNCTASK PARA LISTAR TODOS O SERVIÇOS.
		//----
		class mapaAsyncTask extends AsyncTask<Void, Void, Modelo>{

		
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(CadastrarServicoAcompMapa.this, "LOADING:",
					"Carregando pontos do mapa!", true, false);
			super.onPreExecute();
		}
		@Override
		protected Modelo doInBackground(Void... params) {
			
			Modelo locRetorno = new Modelo();
			Modelo modelo = new Modelo();
			
			
			

			locRetorno = repositorio.acessarServidorMAPA("s/3fxqqgjx9q18kl0/log1.txt", modelo);

			return locRetorno;
		}
		
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);

			for ( int i = 0; i < result.getDados().size(); ++i){
				
			Localizacao local = new Localizacao();
			Servico serv = new Servico();
			Object dadosObject = result.getDados().get(i);
			Gson gson = new Gson();
		
			JSONObject jsonObject = null;
			try {
				jsonObject = new JSONObject(gson.toJson(dadosObject));				
				serv.setNome(jsonObject.getString("Servico"));
				local.setLatitude(jsonObject.getString("Latitude"));
				local.setLongitude(jsonObject.getString("Longitude"));
				
			System.out.println("    "+ jsonObject.getString("Latitude")+ "   "+
			jsonObject.getString("Longitude")+ "   " + serv.getNome());
			
			double lat = Double.parseDouble(local.getLatitude());
			double log = Double.parseDouble(local.getLongitude());
			
			Log.i("envio", " num"+ i +"....latitude" + lat + "...long" + log  );

			LatLng latLog	= new LatLng(lat, log);
			googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLog));
			googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
			
			meuMarker	= googleMap.addMarker(new MarkerOptions()  
			.position(latLog)  
			.icon(BitmapDescriptorFactory.fromResource(  
			  R.drawable.pin)).
			  title(serv.getNome())  
			  .snippet("R$: "+ jsonObject.getString("Valor")));
			
			}catch (Exception e) {
				e.printStackTrace();			
			}
			dialog.dismiss();
		}
	}
}
		
		//----
		// CLASS ASYNCTASK PARA LISTAR O SERVICO ESCOLHIDO PELO CLIENTE.
		//----
		class listarServicoMapaAsyncTask extends AsyncTask<Void, Void, Modelo>{

			
			ProgressDialog dialog;
			
			@Override
			protected void onPreExecute() {
				dialog = ProgressDialog.show(CadastrarServicoAcompMapa.this, "LOADING:",
						"Carregando pontos do mapa!", true, false);
				super.onPreExecute();
			}
			@Override
			protected Modelo doInBackground(Void... params) {
				
				Modelo locRetorno = new Modelo();
				Modelo modelo = new Modelo();
				
				
				

				locRetorno = repositorio.acessarServidorMAPA("s/3fxqqgjx9q18kl0/log1.txt", modelo);

				return locRetorno;
			}
			
			protected void onPostExecute(Modelo result) {
				super.onPostExecute(result);
				
				//--- PARA LISTAR POR SERVICO --- 
				
				Servico servicoIntent = new Servico();
				servicoIntent = (Servico) getIntent().getSerializableExtra("servico");
				Log.i("PEDRO1", servicoIntent.getNome());
				Log.i("SOSTENES", servicoIntent.getNome());
				System.out.println(" AQUIIIII .......   " + servicoIntent.getNome());
				
				
				for ( int i = 0; i < result.getDados().size(); ++i){
					
				Localizacao local = new Localizacao();
				Servico serv = new Servico();
				Object dadosObject = result.getDados().get(i);
				Gson gson = new Gson();
			
				JSONObject jsonObject = null;
				try {
					jsonObject = new JSONObject(gson.toJson(dadosObject));				
					serv.setNome(jsonObject.getString("Servico"));
					local.setLatitude(jsonObject.getString("Latitude"));
					local.setLongitude(jsonObject.getString("Longitude"));
					
				System.out.println("    "+ jsonObject.getString("Latitude")+ "   "+
				jsonObject.getString("Longitude")+ "   " + serv.getNome());
				
				double lat = Double.parseDouble(local.getLatitude());
				double log = Double.parseDouble(local.getLongitude());
				
				Log.i("envio", " num"+ i +"....latitude" + lat + "...long" + log  );

				if ( jsonObject.getString("Servico").equals(servicoIntent.getNome())){
				
				LatLng latLog	= new LatLng(lat, log);
				googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLog));
				googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
				
				meuMarker	= googleMap.addMarker(new MarkerOptions()  
				.position(latLog)  
				.icon(BitmapDescriptorFactory.fromResource(  
				  R.drawable.pin)).
				  title(serv.getNome())  
				  .snippet("R$: "+ jsonObject.getString("Valor")));
				}
				
				}catch (Exception e) {
					e.printStackTrace();			
				}
				dialog.dismiss();
			}
		}
	}
		
		//----
		// CLASS ASYNCTASK PARA CADASTRAR A LOCALIZAÇÃO DO SERVICO.
		//----
		class cadastrarServicoMapaAsyncTask extends AsyncTask<String, String, Modelo> {
			ProgressDialog dialog;

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = ProgressDialog.show(CadastrarServicoAcompMapa.this,
						"Cadastrando a localização", "Aguarde...",
						true, false);
			}

			@Override
			protected Modelo doInBackground(String... params) {
				Modelo modelo = new Modelo();
				
				modelo.setDados(listaLocalizacoes);
				modelo.setMensagem("");
				modelo.setStatus("");
				try
				{
					modeloRetorno = repositorio.acessarServidor("cadastrarLocalizacaoServicoAcompanhante", modelo);
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
					Toast toast = Toast.makeText(CadastrarServicoAcompMapa.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
					toast.show();
					finish();
				}	
			}
		}
	
	
}