package br.uni.mete_service.Controller.Acompanhante;

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

import br.uni.mete_service.R;
import br.uni.mete_service.model.Acompanhante;
import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class DadosAcompanhanteActivity extends Activity implements OnClickListener {

	private TextView nome, idade, peso, altura, busto,
	atendo, status;
	private Acompanhante acomp;
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dados_acomp);
	
	this.nome = (TextView) this.findViewById(R.id.txtNomeAcomp);
	this.idade = (TextView) this.findViewById(R.id.txtIdadeAcomp);
	this.altura = (TextView) this.findViewById(R.id.txtAlturaAcomp);
	this.busto = (TextView) this.findViewById(R.id.txtBustoAcomp);
	
	
	acomp = (Acompanhante) getIntent().getSerializableExtra("acompan");
	new informacaoMeninasAsyncTask().execute();
	
	nome.setText(acomp.getNome());
	idade.setText(acomp.getIdade());
	altura.setText(acomp.getAltura());
	busto.setText(acomp.getBusto());
	//para a foto da acom
//	BitmapManager.getInstance().loadBitmap(acomp.getFoto(), imagem);

}
class informacaoMeninasAsyncTask extends AsyncTask<Void, Void, Acompanhante> {

	ProgressDialog dialog;

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = ProgressDialog.show(DadosAcompanhanteActivity.this, "Aguarde",
				acomp.getNome() + " toda sua!", true, false);
	}

	@Override
	protected Acompanhante doInBackground(Void... params) {
		HttpClient cliente = new DefaultHttpClient();

		HttpPost get = new HttpPost(
				"https://dl.dropbox.com/s/itwq2o3knlomodo/js.json");

		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
					2);
			nameValuePairs.add(new BasicNameValuePair("id", String
					.valueOf(acomp.getId())));
			get.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resposta = cliente.execute(get);
			String s = toString(resposta.getEntity().getContent());
			JSONObject objeto = new JSONObject(s);

			acomp.setNome(objeto.getString("nome"));
			acomp.setFoto(objeto.getString("foto"));
			acomp.setIdade(objeto.getString("idade"));
			acomp.setPeso(objeto.getString("peso"));
			acomp.setAtendo(objeto.getString("atendo"));
			acomp.setBusto(objeto.getString("busto"));
			
			Log.i("pedro", "oiii: " + objeto.getString("busto"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return acomp;
	}

	@Override
	protected void onPostExecute(Acompanhante acompanhante) {
		super.onPostExecute(acompanhante);
		if (acompanhante != null) {
//			peso.setText(acompanhante.getPeso());
			altura.setText(acompanhante.getAltura());
//			atendo.setText(acompanhante.getAtendo());
			busto.setText(acompanhante.getBusto());
			
		}
		dialog.dismiss();
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

}

	@Override
	public void onClick(View v) {
		
	}

}
