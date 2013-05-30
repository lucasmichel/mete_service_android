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
import android.view.View;
import android.widget.TextView;
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
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
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
	private AlertDialog alerta;
	boolean chamadaAcompanhante;
	Handler handler = new Handler();
	Localizacao localizacaoExcluir;
	List<Object> listaLocalizacaoExcluir = new ArrayList<Object>();
	Modelo modeloExcluirLocalizacao = new Modelo();

	List<Object> listaServicoMarker = new ArrayList();
	
//	private Location location;
	
	protected void onCreate(Bundle savedInstanceState) {  
	super.onCreate(savedInstanceState);  
	
		if (isGooglePlay()){
			setContentView(R.layout.activity_mapa); 
			setUpMap();
		}
		new mapaListarServicoAcompanhanteAsyncTask().execute();
	
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

//		new mapaListarServicoAcompanhanteAsyncTask().execute();

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
			
			googleMap.setOnInfoWindowClickListener(windowClickListener());

			
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
	
	private OnInfoWindowClickListener windowClickListener(){
		return new OnInfoWindowClickListener() {
			
			public void onInfoWindowClick(Marker marker) {
	
				chamadaAcompanhante = 
						getIntent().getBooleanExtra("chamadaAcompanhante",false);
				
				String acompanhante = marker.getSnippet();
				localizacaoExcluir = new Localizacao();
				
				localizacaoExcluir.setId(Integer.valueOf(acompanhante));
				
				Toast.makeText(MapaListarServicoSelecionado.this,
						acompanhante, Toast.LENGTH_LONG).show();
				 
				 if(chamadaAcompanhante){
					 
					 Toast.makeText(MapaListarServicoSelecionado.this,
							 "acomp", Toast.LENGTH_LONG).show();
				
					 AlertDialog.Builder builder = new AlertDialog.Builder(MapaListarServicoSelecionado.this);

					    builder.setTitle("ALERT!");
					    
					    builder.setMessage("O que deseja fazer ?");

					    builder.setPositiveButton("Excluir esse ponto de localização.", new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface arg0, int arg1) {
					        	
					        	Toast.makeText(MapaListarServicoSelecionado.this,
					        			"exlui: " + localizacaoExcluir.getId(), Toast.LENGTH_LONG).show();
					        	
					        	//EXECULTAR UM ALERT PARA PERGUNTAR SE DESEJA REALMENTE EXCLUIR.
					        	execultarExcluir();
					        }
					    });
					    
					    builder.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface arg0, int arg1) {
					        }
					    });
					    //CRIAR O ALERT.
					    alerta = builder.create();
					    //EXIBI O ALERT.
					    alerta.show();

				 }else{
	
				AlertDialog.Builder builder = new AlertDialog.Builder(MapaListarServicoSelecionado.this);

			    builder.setTitle("ALERT!");
			    
			    builder.setMessage("O que deseja fazer ?");

			    builder.setPositiveButton("Criar Uma Rota", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface arg0, int arg1) {
			        	
			        	Toast.makeText(
			        			MapaListarServicoSelecionado.this, 
			        			"ROTAAA ", Toast.LENGTH_LONG).show();
			          
			        }
			    });
			    
			    builder.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface arg0, int arg1) {
			        }
			    });
			    //CRIAR ALERT.
			    alerta = builder.create();
			    //EXIBE O ALERT.
			    alerta.show();

			}
			}
			
		};
	}
	
	//METODO PARA CONFIRMAR A EXCLUSÃO.
	public void execultarExcluir(){
		 AlertDialog.Builder builder = new AlertDialog.Builder(MapaListarServicoSelecionado.this);

		    builder.setTitle("Calma !!! ");
		    
		    builder.setMessage("Deseja realmente excluir esse ponto de seu serviço ?");

		    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface arg0, int arg1) {
	
		        	listaLocalizacaoExcluir.add(localizacaoExcluir);		
		        	modeloExcluirLocalizacao.setDados(listaLocalizacaoExcluir);
		        	modeloExcluirLocalizacao.setMensagem("");
		        	modeloExcluirLocalizacao.setStatus("");
		        	
		        	//EXECULTAR ASYNCTASK PARA REALIZAR A EXCLUSÃO
		          new excluirLocalizacaoAsyncTask().execute();
		        }
		    });
		    
		    //define um botão como negativon.
		    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface arg0, int arg1) {
		        }
		    });
		    //cria o AlertDialog
		    alerta = builder.create();
		    //Exibe
		    alerta.show();
		
		
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
//			
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
			
			Gson g = new Gson();
			Log.i("GSON", "PEDRO   " + g.toJson(modelo));
			

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
					local.setId(jsonObject.getInt("\u0000Localizacao\u0000id"));
					local.setLatitude(jsonObject.getString("\u0000Localizacao\u0000latitude"));
					local.setLongitude(jsonObject.getString("\u0000Localizacao\u0000longitude"));
					local.setEnderecoFormatado(
							jsonObject.getString("\u0000Localizacao\u0000enderecoFormatado"));
					
					listaServicoMarker.add(local);
	
				}

			}catch (Exception e) {
				e.printStackTrace();			
			}
			AddMarkers();
			dialog.dismiss();
	}
}
	
	//ADICIONAR OS MARKER AO MAPA.
		private void AddMarkers (){
		
		
			for(int i = 0; i < listaServicoMarker.size(); i++){
				Localizacao localizacao = (Localizacao) listaServicoMarker.get(i);
					
				double lat = Double.parseDouble(localizacao.getLatitude());
				double log = Double.parseDouble(localizacao.getLongitude());
				
				LatLng latLog	= new LatLng(lat, log);
				
				meuMarker	= googleMap.addMarker(new MarkerOptions()  
				.position(latLog)  
				.icon(BitmapDescriptorFactory.fromResource(  
						 R.drawable.pin)).
						 title(localizacao.getEnderecoFormatado())
						  .snippet(String.valueOf(localizacao.getId())));

				
				googleMap.setInfoWindowAdapter(new InfoWindowAdapter() {
				
				public View getInfoWindow(Marker marker) {
					// TODO Auto-generated method stub
					
					
					   		
					return null;
				}

				public View getInfoContents(Marker markerAdapter) {
					
		            View v = getLayoutInflater().inflate(R.layout.info_windows, null);
	
		          

		            TextView txtEndereco = (TextView) v.findViewById(R.id.textViewTitulo);

		            TextView txtValor = (TextView) v.findViewById(R.id.textViewValor);
		            
		            LatLng latLngo = markerAdapter.getPosition();
		            
		            Localizacao l = new Localizacao();
		            Geocoder geocoder = new Geocoder(
							MapaListarServicoSelecionado.this, Locale.getDefault());
					List<Address> addresses = null;
		            try {
						addresses = geocoder.getFromLocation(latLngo.latitude,latLngo.longitude,1);
						
						final Address endereco = addresses.get(0);
						StringBuilder strReturnedAddress = new StringBuilder();
						for (int i = 0; i < endereco.getMaxAddressLineIndex(); i++) {
							strReturnedAddress.append(endereco.getAddressLine(i));
								} 
								
								l.setEnderecoFormatado(
									strReturnedAddress.toString());
									Log.i("gson", "ende   " + localizacaoCadastro.getEnderecoFormatado());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		            txtEndereco.setText("Endereço: " + l.getEnderecoFormatado());

		            txtValor.setText("Valor: R$ "+ intentServicoAcomp.getValor());

		            return v;

				}
				});
			}
	}
	
		class excluirLocalizacaoAsyncTask extends AsyncTask<Void, Void, Modelo>{

			
			ProgressDialog dialog;
			
			@Override
			protected void onPreExecute() {
				dialog = ProgressDialog.show(MapaListarServicoSelecionado.this, "LOADING:",
						"Carregando pontos do mapa!", true, false);
				super.onPreExecute();
			}
			@Override
			protected Modelo doInBackground(Void... params) {

				
				Modelo locRetorno = new Modelo();

				
				Gson g = new Gson();
				Log.i("GSON", "PEDRO   " + g.toJson(modeloExcluirLocalizacao));
				

				locRetorno = repositorio.acessarServidor("excluirLocalizacaoServicoAcompanhante",
															modeloExcluirLocalizacao);

				return locRetorno;
			}
			
			protected void onPostExecute(Modelo result) {
				super.onPostExecute(result);
		
//				Object dadosObject = result.getDados();
//				Gson gson = new Gson();
//				JSONObject jsonObject = null;
//				List<Servico> addServico = new ArrayList<Servico>();
//
//				try {
//					JSONArray jsonArray = new JSONArray(gson.toJson(dadosObject));
//					for ( int x = 0; x < jsonArray.length(); ++x){
//						jsonObject = jsonArray.getJSONObject(x);
//						
//						Localizacao local = new Localizacao();
//						local.setId(jsonObject.getInt("\u0000Localizacao\u0000id"));
//						local.setLatitude(jsonObject.getString("\u0000Localizacao\u0000latitude"));
//						local.setLongitude(jsonObject.getString("\u0000Localizacao\u0000longitude"));
//						local.setEnderecoFormatado(
//								jsonObject.getString("\u0000Localizacao\u0000enderecoFormatado"));
//						
//						listaServicoMarker.add(local);
//		
//					}
//
//				}catch (Exception e) {
//					e.printStackTrace();			
//				}
//				AddMarkers();
				Toast.makeText(MapaListarServicoSelecionado.this,
						"RESULT: " + result.getMensagem(), Toast.LENGTH_LONG).show();
				dialog.dismiss();
		}
	}	

}

	