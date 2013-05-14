package br.uni.mette_service.Controller.Acompanhante;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import br.uni.mette_service.R;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Foto;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Util.AdaptadorImagem;
import br.uni.mette_service.Util.BitmapManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GaleriaFotosActivity extends Activity implements OnClickListener {

	private Gallery g;
	private Uri[] imagens;
	private ImageView imagem;
	private Foto foto;
	private Usuario usuarioLogado = new Usuario();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_foto);

		usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuarioLogado");
		new informacaoMeninasAsyncTask().execute();
		BitmapManager.getInstance().loadBitmap(foto.getNome(), imagem);
		g = (Gallery) findViewById(R.id.galeriafts);

		g.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int posicao,
					long id) {
				ImageView imgView = new ImageView(GaleriaFotosActivity.this);
				imgView.setImageURI(imagens[posicao]);
				Toast t = new Toast(GaleriaFotosActivity.this);
				t.setView(imgView);
				t.show();
			}

		});

	}

	class informacaoMeninasAsyncTask extends AsyncTask<Void, Void, Usuario> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(GaleriaFotosActivity.this, "Aguarde","" + " toda sua!", true, false);
		}

		@Override
		protected Acompanhante doInBackground(Void... params) {
			HttpClient cliente = new DefaultHttpClient();

			HttpPost get = new HttpPost("http://neparq.com.br/webservice/webServiceBuscar");

			try {
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						2);
				nameValuePairs.add(new BasicNameValuePair("id", String
						.valueOf(usuarioLogado.getIdUsuario())));
				get.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				HttpResponse resposta = cliente.execute(get);
				String s = toString(resposta.getEntity().getContent());
				JSONObject objeto = new JSONObject(s);

			} catch (Exception e) {
				e.printStackTrace();
			}
			//return menina;
			return null;
		}

	/*	@Override
		protected void onPostExecute(Meninas menina) {
			super.onPostExecute(menina);
			if (menina != null) {

				Uri urFoto1 = Uri.parse(menina.getFoto1());
				Uri urFoto2 = Uri.parse(menina.getFoto2());
				Uri urFoto3 = Uri.parse(menina.getFoto3());
				Uri urFoto4 = Uri.parse(menina.getFoto4());

				imagens[0] = urFoto1;
				imagens[1] = urFoto2;
				imagens[2] = urFoto3;
				imagens[3] = urFoto4;

				g.setAdapter(new AdaptadorImagem(GaleriaFotosActivity.this, imagens));

			}
			dialog.dismiss();
		}*/

		private String toString(InputStream is) throws IOException {

			byte[] bytes = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int lidos;
			while ((lidos = is.read(bytes)) > 0) {
				baos.write(bytes, 0, lidos);
			}
			return new String(baos.toByteArray());
		}

	}

	public void onClick(View v) {

	}

}
