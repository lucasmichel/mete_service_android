package br.uni.mette_service.Mapa;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
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
import android.view.Menu;
import android.widget.Toast;
import br.uni.mette_service.R;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.l;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends FragmentActivity 
implements LocationListener{  
	private Marker meuMarker ;
	private GoogleMap googleMap;
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
		HttpClient cliente = new DefaultHttpClient();
		
				HttpGet get = new HttpGet(
						"https://dl.dropbox.com/s/az80zijmacavbk5/jslonglat.json");
				try {
					HttpResponse resposta = cliente.execute(get);

					JSONArray jsonAray = new JSONArray(toString(resposta
							.getEntity().getContent()));
					for (int i = 0; i < jsonAray.length(); i++) {
						JSONObject objeto = jsonAray.getJSONObject(i);
//						m.setNome(objeto.getString("nome"));
//						
					
						double lat = objeto.getDouble("Latitude");
						double log = objeto.getDouble("Longitude");
						Log.i("envio", "latitude" + lat + "...long" + log  );
		
		LatLng latLog	= new LatLng(lat, log);
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLog));
		googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
		
	
	meuMarker	= googleMap.addMarker(new MarkerOptions()  
		.position(latLog)  
		.icon(BitmapDescriptorFactory.fromResource(  
		  R.drawable.pin)).
		  title(objeto.getString("Servico"))  
		  .snippet(objeto.getString("Valor")));
				
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
	}
			
//		public boolean onMarkerClick(Marker marker) {
//
//			if (marker.equals(meuMarker)) 
//			{
//				Toast.makeText(MapaActivity.this, meuMarker.getTitle(), Toast.LENGTH_SHORT).show();
//        }
//			return false;
//		}


	
	
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
			// TODO Auto-generated method stub
			System.out.println(point.toString());
			Toast.makeText(MapaActivity.this, point.toString(), Toast.LENGTH_LONG).show();
		}
	};
	}



}
