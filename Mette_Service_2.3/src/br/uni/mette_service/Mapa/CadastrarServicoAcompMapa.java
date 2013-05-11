package br.uni.mette_service.Mapa;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;

import android.app.ProgressDialog;
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
	Acompanhante intentAcompLogada = new Acompanhante();
//	private Location location;
	
	protected void onCreate(Bundle savedInstanceState) {  
	super.onCreate(savedInstanceState);  

	
	intentServicoAcomp = (ServicoAcompanhante) 
				getIntent().getSerializableExtra("intentServicoAcompanhante");
	
	
	System.out.println("id do servicoooo....." + "   " + intentServicoAcomp.getId());
	
		if (isGooglePlay()){
			setContentView(R.layout.activity_mapa); 
			setUpMap();
		}
	
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
			
			localizacaoCadastro.getLatitude();
			localizacaoCadastro.getLongitude();
			
			System.out.println("MAPAAAA   .." + localizacaoCadastro.getLatitude() + "   " + localizacaoCadastro.getLongitude());
			Toast.makeText(CadastrarServicoAcompMapa.this, "latitude  " + point.latitude, Toast.LENGTH_LONG).show();
		}
	};
	}
	
	private void cadastrarServico() {
		
		intentServicoAcomp = (ServicoAcompanhante) getIntent().getSerializableExtra("servicoAcomp");
		intentAcompLogada = (Acompanhante) getIntent().getSerializableExtra("acompanhanteLogada");		
		
		//-----
		//VALORES QUE PRECISA PASSAR PARA O WEB SERVICE..
		
		intentServicoAcomp.getServicoId(); // ID DO SERVICO
		intentServicoAcomp.getValor(); // VALOR DO SERVICO
		
		intentAcompLogada.getId(); // ID DA NEGA
		
//		localizacaoCadastro.getLatitude(); // LATITUDE
//		localizacaoCadastro.getLongitude(); // LONGITUDE
		
		System.out.println("id do servico:  " + intentServicoAcomp.getServicoId() +
				"    valor: " +intentServicoAcomp.getValor() + 
				"   id da nega " + intentAcompLogada.getId());


//		android.content.DialogInterface.OnClickListener trataDialog = new android.content.DialogInterface.OnClickListener() 
//		{
//			
//			public void onClick(DialogInterface dialog, int which) 
//			{
//				
//				// Vai execultar a AssyncTask
//			//	acompanhanteRetorno = 
//			//			objacompanhante.excluirAcompanhante(objacompanhante);
//			}
//		};
//		
//		AlertDialog alert = new AlertDialog.Builder(this)
//		.setTitle("Confirmação")
//		.setMessage("Deseja realmente excluir?")
//		.setPositiveButton("Não", trataDialog)
//		.setNegativeButton("Sim", null).create();
//	alert.show();
	
		
	}
	
	
	//CLASS ASYNCTASK PARA LISTAR TODOS O SERVIÇOS.
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
		
	
	
}