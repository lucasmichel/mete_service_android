package br.uni.mette_service.Mapa;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;
import br.uni.mette_service.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity 
implements LocationListener {  

	private GoogleMap googleMap;
	
	protected void onCreate(Bundle savedInstanceState) {  
	super.onCreate(savedInstanceState);  
	setContentView(R.layout.mapa); 
	setUpMap();
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onLocationChanged(Location location) {
//	LatLng latLog	= new LatLng(-8.05668, -34.896011);
		
		//------
		// Onde latLog recebe os valores do serviço da acompanhante
		//------
	LatLng latLog	= new LatLng(location.getLatitude(), location.getLongitude());
	
	googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLog));
	googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
	
	googleMap.addMarker(new MarkerOptions()  
    .position(latLog)  
    .icon(BitmapDescriptorFactory.fromResource(  
      R.drawable.pin)).
      title("QUALQUER LUGAR")  
      .snippet("PERNAMBUCO"));
	
	
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
			Toast.makeText(MapActivity.this, point.toString(), Toast.LENGTH_LONG).show();
		}
	};
	}


}
