package br.uni.mette_service.Mapa;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;
import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Servico.ListaServicosActivity;
import br.uni.mette_service.Controller.Servico.ServicoAcompanhante;
import br.uni.mette_service.Controller.Servico.ServicoAdapter;
import br.uni.mette_service.Mapa.CadastrarServicoAcompMapa.cadastrarServicoMapaAsyncTask;
import br.uni.mette_service.Mapa.MapaActivity.mapaAsyncTask;
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

public class MapaListarServicoSelecionado extends FragmentActivity 
implements LocationListener{  
	private Marker meuMarker ;
	private GoogleMap googleMap;
	Repositorio repositorio = new Repositorio();
	Localizacao localizacaoCadastro = new Localizacao();
	ServicoAcompanhante intentServicoAcomp = new ServicoAcompanhante();
	Modelo modeloRetorno = new Modelo();
	List<Object> listaServicoAcompanhante = new ArrayList();
//	private Location location;
	
	protected void onCreate(Bundle savedInstanceState) {  
	super.onCreate(savedInstanceState);  
	
		if (isGooglePlay()){
			setContentView(R.layout.activity_mapa); 
			setUpMap();
		}
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
	new mapaListarServicoAcompanhanteAsyncTask().execute();
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
	// TODO Auto-generated method stub
	
	}
	
	public void onProviderEnabled(String arg0) {
	// TODO Auto-generated method stub
	
	}
	
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
	// TODO Auto-generated method stub
	
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
	// TODO Auto-generated method stub
	return new OnMapLongClickListener() {
		
		public void onMapLongClick(LatLng point) {
		}
	};
	}
	
		class mapaListarServicoAcompanhanteAsyncTask extends AsyncTask<Void, Void, Modelo>{

		
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(MapaListarServicoSelecionado.this, "LOADING:",
					"Carregando pontos do mapa!", true, false);
			super.onPreExecute();
		}
		@Override
		protected Modelo doInBackground(Void... params) {
			
			intentServicoAcomp = (ServicoAcompanhante) 
					getIntent().getSerializableExtra("idServicoAcompanante");
			
			ServicoAcompanhante idServicoAcompanhante = new ServicoAcompanhante();
			
			idServicoAcompanhante.setId(intentServicoAcomp.getId());

			listaServicoAcompanhante.add(idServicoAcompanhante);
			
			Modelo locRetorno = new Modelo();
			Modelo modelo = new Modelo();
			
			modelo.setDados(listaServicoAcompanhante);
			modelo.setMensagem("");
			modelo.setStatus("");
			
			
			

			locRetorno = repositorio.acessarServidor("listarLocalizacaoServicoAcompanhante", modelo);

			return locRetorno;
		}
		
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);
	
			Object dadosObject = result.getDados();
			Gson gson = new Gson();
			JSONObject jsonObject = null;
			List<Servico> addServico = new ArrayList<Servico>();

			try {
				JSONArray jsonArray = new JSONArray(gson.toJson(dadosObject));
				for ( int x = 0; x < jsonArray.length(); ++x){
					jsonObject = jsonArray.getJSONObject(x);
					
					Localizacao local = new Localizacao();
					local.setLatitude(jsonObject.getString("\u0000Localizacao\u0000latitude"));
					local.setLongitude(jsonObject.getString("\u0000Localizacao\u0000longitude"));
					
					double lat = Double.parseDouble(local.getLatitude());
					double log = Double.parseDouble(local.getLongitude());
					

					LatLng latLog	= new LatLng(lat, log);
					googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLog));
					googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
					
					meuMarker	= googleMap.addMarker(new MarkerOptions()  
					.position(latLog)  
					.icon(BitmapDescriptorFactory.fromResource(  
					  R.drawable.pin))
					  .snippet("R$: "+ intentServicoAcomp.getId()));
					
				}


			}catch (Exception e) {
				e.printStackTrace();			
			}
			
			
			
			
////			for ( int i = 0; i < result.getDados().size(); ++i){
//				
//			Localizacao local = new Localizacao();
//			Servico serv = new Servico();
//			Object dadosObject = result.getDados().get(i);
//			Gson gson = new Gson();
//		
//			JSONObject jsonObject = null;
//			try {
//				jsonObject = new JSONObject(gson.toJson(dadosObject));				
//				serv.setNome(jsonObject.getString("Servico"));
//				local.setLatitude(jsonObject.getString("Latitude"));
//				local.setLongitude(jsonObject.getString("Longitude"));
//				
//			System.out.println("    "+ jsonObject.getString("Latitude")+ "   "+
//			jsonObject.getString("Longitude")+ "   " + serv.getNome());
//			
//			double lat = Double.parseDouble(local.getLatitude());
//			double log = Double.parseDouble(local.getLongitude());
//			
//			Log.i("envio", " num"+ i +"....latitude" + lat + "...long" + log  );
//
//			
//			LatLng latLog	= new LatLng(lat, log);
//			googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLog));
//			googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
//			
//			meuMarker	= googleMap.addMarker(new MarkerOptions()  
//			.position(latLog)  
//			.icon(BitmapDescriptorFactory.fromResource(  
//			  R.drawable.pin)).
//			  title(serv.getNome())  
//			  .snippet("R$: "+ jsonObject.getString("Valor")));
//			
//			}catch (Exception e) {
//				e.printStackTrace();			
//			}
			dialog.dismiss();
	}
}



}
	