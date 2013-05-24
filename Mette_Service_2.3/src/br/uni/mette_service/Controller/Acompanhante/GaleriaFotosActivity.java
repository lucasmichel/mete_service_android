package br.uni.mette_service.Controller.Acompanhante;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import br.uni.mette_service.R;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Foto;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class GaleriaFotosActivity extends Activity {

	private final Context context = this;
	Foto fto = new Foto();
	private Gallery gallery;
	private ImageView imgView;
	ArrayList<String> gallery_list = new ArrayList<String>();
	ArrayList<String> itensId = new ArrayList<String>();
	private String idAtual = "0";
	private boolean cliente;

	private Usuario usuarioLogado = new Usuario();
	List<Object> listaAcompanhante = new ArrayList();
	Acompanhante acompanhante = new Acompanhante();
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	Repositorio repositorio = new Repositorio();
	Acompanhante buscarAcompanhante = new Acompanhante();
	Acompanhante Acomp = new Acompanhante();
	Acompanhante AcompRes = new Acompanhante();
	List<Object> listaFotos = new ArrayList();
	List<Object> listaExclusao = new ArrayList();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_foto);
		cliente = getIntent().getBooleanExtra("exibirFotosTipo", false);
		usuarioLogado = (Usuario) getIntent().getSerializableExtra(
				"usuarioLogado");
		adicionarFindView();
		listarFotos();
	}

	private void adicionarFindView() {
		gallery = (Gallery) findViewById(R.id.galeriafts);
		imgView = (ImageView) findViewById(R.id.imageFotoAcom);
		imgView.setOnLongClickListener(vLong);

	}

	private View.OnLongClickListener vLong = new View.OnLongClickListener() {
		public boolean onLongClick(View view) {
			if (cliente) {
				Toast toast = Toast.makeText(GaleriaFotosActivity.this,"Calma ao ver as fotos", Toast.LENGTH_LONG);
				toast.show();
			} else {
				fto.setId(Integer.parseInt(idAtual));
				listaExclusao.clear();
				listaExclusao.add(fto);

				modelo.setDados(listaExclusao);
				modelo.setMensagem("");
				modelo.setStatus("");

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
				alertDialogBuilder.setTitle("EXCLUIR Fotos");
				alertDialogBuilder
						.setMessage("Deseja excluir a fotos!")
						.setCancelable(false)
						.setPositiveButton("Não",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
									}
								})
						.setNegativeButton("Sim",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										new excluirFotoIdUsuarioAsyncTask()
												.execute();
									}
								});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}
			return true;
		}
	};

	class excluirFotoIdUsuarioAsyncTask extends
			AsyncTask<String, String, Modelo> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(GaleriaFotosActivity.this,
					"Excluindo...", "Aguarde...", true, false);
		}

		@Override
		protected Modelo doInBackground(String... params) {
			try {
				modeloRetorno = repositorio.acessarServidor("excluirFoto",
						modelo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return modeloRetorno;
		}

		@Override
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);

			Toast toast = Toast.makeText(GaleriaFotosActivity.this,
					"Foto Excluida com SUCESSO!", Toast.LENGTH_LONG);
			toast.show();
			listarFotos();
			dialog.dismiss();
		}
	}

	private void listarFotos() {

		if (cliente) {
			Acomp = (Acompanhante) getIntent().getSerializableExtra(
					"exibirfotos");
			AcompRes.setId(Acomp.getId());
			listaAcompanhante.add(AcompRes);

			modelo.setDados(listaAcompanhante);
			modelo.setMensagem("");
			modelo.setStatus("");
			new listarFtoClienteAsyncTask().execute();
		} else {
			gallery_list.clear();
			listaAcompanhante.clear();
			acompanhante.setId(usuarioLogado.getIdUsuario());
			listaAcompanhante.add(acompanhante);

			modelo.setDados(listaAcompanhante);
			modelo.setMensagem("");
			modelo.setStatus("");
			new listarFotosAsyncTask().execute();
		}
	}

	class listarFotosAsyncTask extends AsyncTask<String, String, Modelo> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(GaleriaFotosActivity.this,
					"Listando...", "Aguarde...", true, false);
		}

		@Override
		protected Modelo doInBackground(String... params) {
			try {
				modeloRetorno = repositorio.acessarServidor(
						"buscarAcompanhantePorIdUsuario", modelo);
				if (modeloRetorno.getStatus().equals("1")) {
					Toast toast = Toast.makeText(GaleriaFotosActivity.this,
							"ERRO: " + modeloRetorno.getMensagem(),
							Toast.LENGTH_LONG);
					toast.show();
				} else {
					Object dadosObject = modeloRetorno.getDados().get(0);
					JSONObject jsonObject = null;
					Gson gson = new Gson();
					jsonObject = new JSONObject(gson.toJson(dadosObject));
					int idAcompanhanteListarFotos = jsonObject
							.getInt("\u0000Acompanhante\u0000id");
					listaAcompanhante.clear();
					acompanhante.setId(idAcompanhanteListarFotos);
					listaAcompanhante.add(acompanhante);
					modelo.setDados(listaAcompanhante);
					modelo.setMensagem("");
					modelo.setStatus("");
					modeloRetorno = repositorio.acessarServidor(
							"listarFotosPorIdAcompanhnate", modelo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return modeloRetorno;
		}

		@Override
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);

			if (modeloRetorno.getStatus().equals("1")) {
				Toast toast = Toast.makeText(GaleriaFotosActivity.this,
						"ERRO: " + modeloRetorno.getMensagem(),
						Toast.LENGTH_LONG);
				toast.show();
			} else {
				Toast toast = Toast.makeText(GaleriaFotosActivity.this,
						"100%: " + modeloRetorno.getMensagem(),
						Toast.LENGTH_LONG);
				toast.show();
				Object dadosObject = result.getDados();
				Gson gson = new Gson();
				JSONObject jsonObject = null;
				try {
					JSONArray jsonArray = new JSONArray(
							gson.toJson(dadosObject));
					for (int x = 0; x < jsonArray.length(); ++x) {
						jsonObject = jsonArray.getJSONObject(x);
						gallery_list
								.add("http://leonardogalvao.com.br/mete_service/src/img/foto/"
										+ jsonObject
												.getString("\u0000Fotos\u0000nome"));
						itensId.add(jsonObject.getString("\u0000Fotos\u0000id"));
					}
					String URL = gallery_list.get(0);
					GetXMLTask task = new GetXMLTask();
					task.execute(new String[] { URL });
					Context contexto = getApplicationContext();
					gallery.setAdapter(new GalleryAdapter(contexto,
							gallery_list));
					dialog.dismiss();
					Log.i("SOSTENES",
							"Test in GaleriaFotosActivity: "
									+ gson.toJson(gallery_list));
					Log.i("SOSTENES",
							"Test in ID FOTOS: " + gson.toJson(itensId));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void onResume() {
		super.onResume();
		gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				if (!itensId.equals(gallery_list.get(position)))
					try {
						String URL = gallery_list.get(position);
						GetXMLTask task = new GetXMLTask();
						task.execute(new String[] { URL });
						idAtual = itensId.get(position);
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), "Erro! " + e,
								Toast.LENGTH_SHORT).show();
						e.printStackTrace();
					}
			}
		});
	}

	private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {
		@Override
		protected Bitmap doInBackground(String... urls) {
			Bitmap map = null;
			for (String url : urls) {
				map = downloadImage(url);
			}
			return map;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			imgView.setImageBitmap(result);
		}

		private Bitmap downloadImage(String url) {
			Bitmap bitmap = null;
			InputStream stream = null;
			BitmapFactory.Options bmOptions = new BitmapFactory.Options();
			bmOptions.inSampleSize = 1;

			try {
				stream = getHttpConnection(url);
				bitmap = BitmapFactory.decodeStream(stream, null, bmOptions);
				stream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return bitmap;
		}

		private InputStream getHttpConnection(String urlString)
				throws IOException {
			InputStream stream = null;
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			try {
				HttpURLConnection httpConnection = (HttpURLConnection) connection;
				httpConnection.setRequestMethod("GET");
				httpConnection.connect();
				if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					stream = httpConnection.getInputStream();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return stream;
		}
	}

	class listarFtoClienteAsyncTask extends AsyncTask<String, String, Modelo> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(GaleriaFotosActivity.this,
					"Listando...", "Aguarde...", true, false);
		}

		@Override
		protected Modelo doInBackground(String... params) {
			try {
				modeloRetorno = repositorio.acessarServidor(
						"listarFotosPorIdAcompanhnate", modelo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return modeloRetorno;
		}

		@Override
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);

			if (modeloRetorno.getStatus().equals("1")) {
				Toast toast = Toast.makeText(GaleriaFotosActivity.this,
						"ERRO: " + modeloRetorno.getMensagem(),
						Toast.LENGTH_LONG);
				toast.show();
			} else {

				Object dadosObject = result.getDados();
				Gson gson = new Gson();
				JSONObject jsonObject = null;
				try {
					JSONArray jsonArray = new JSONArray(
							gson.toJson(dadosObject));
					for (int x = 0; x < jsonArray.length(); ++x) {
						jsonObject = jsonArray.getJSONObject(x);
						gallery_list
								.add("http://leonardogalvao.com.br/mete_service/src/img/foto/"
										+ jsonObject
												.getString("\u0000Fotos\u0000nome"));
						itensId.add(jsonObject.getString("\u0000Fotos\u0000id"));
					}
					String URL = gallery_list.get(0);
					GetXMLTask task = new GetXMLTask();
					task.execute(new String[] { URL });
					Context contexto = getApplicationContext();
					gallery.setAdapter(new GalleryAdapter(contexto,
							gallery_list));
					dialog.dismiss();
					Log.i("SOSTENES",
							"Test in GaleriaFotosActivity: "
									+ gson.toJson(gallery_list));
					Log.i("SOSTENES",
							"Test in ID FOTOS: " + gson.toJson(itensId));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
