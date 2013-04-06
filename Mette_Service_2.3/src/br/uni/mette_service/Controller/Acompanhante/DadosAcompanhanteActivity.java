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

	private TextView nome, idade, peso, altura, busto, cintura,
	atendo, quadril, olhos, pernoite, horario_atent;
	ImageView status;
	private Acompanhante acomp;
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dados_acomp);
	
	this.nome = (TextView) this.findViewById(R.id.txtNomeAcomp);
	this.idade = (TextView) this.findViewById(R.id.txtIdadeAcomp);
	this.altura = (TextView) this.findViewById(R.id.txtAlturaAcomp);
	this.busto = (TextView) this.findViewById(R.id.txtBustoAcomp);
	this.atendo = (TextView) this.findViewById(R.id.txtAtentoAcomp);
	this.cintura = (TextView) this.findViewById(R.id.txtCinturaAcomp);
	this.quadril = (TextView) this.findViewById(R.id.txtQuadrilAcomp);
	this.olhos = (TextView) this.findViewById(R.id.txtOlhosAcomp);
	this.pernoite = (TextView) this.findViewById(R.id.txtPernoiteAcomp);
	this.horario_atent = (TextView) this.findViewById(R.id.txtHorarioAcomp);
	this.peso = (TextView) this.findViewById(R.id.txtPesoAcomp);
	this.status = (ImageView) this.findViewById(R.id.imageStatus);
	
	
	
	acomp = (Acompanhante) getIntent().getSerializableExtra("acompan");
//	new informacaoMeninasAsyncTask().execute();
	
	nome.setText(acomp.getNome());
	idade.setText(acomp.getIdade());
	altura.setText(acomp.getAltura());
	busto.setText(acomp.getBusto());
	atendo.setText(acomp.getAtendo());
	cintura.setText(acomp.getCintura());
	quadril.setText(acomp.getQuadril());
	olhos.setText(acomp.getOlhos());
//	pernoite.setText(acomp.getPernoite());
	horario_atent.setText(acomp.getHorario_atendimento());
	peso.setText(acomp.getPeso());
	
//	if (acomp.getStatusAt().equals("ocupada")) {
//		status.setImageResource(R.drawable.ocupada);
//	} else if (acomp.getStatusAt().equals("livre")) {
//		status.setImageResource(R.drawable.livre);
//	}
	
	
	
	//para a foto da acom
//	BitmapManager.getInstance().loadBitmap(acomp.getFoto(), imagem);

}
//class informacaoMeninasAsyncTask extends AsyncTask<Void, Void, Acompanhante> {
//
//	ProgressDialog dialog;
//
//	@Override
//	protected void onPreExecute() {
//		super.onPreExecute();
//		dialog = ProgressDialog.show(DadosAcompanhanteActivity.this, "Aguarde",
//				acomp.getAtendo() + " toda sua companheiro!", true, false);
//	}
//
//	@Override
//	protected Acompanhante doInBackground(Void... params) {
//		HttpClient cliente = new DefaultHttpClient();
//
//		HttpPost get = new HttpPost(
//				"https://dl.dropbox.com/s/itwq2o3knlomodo/js.json"  );
//		Acompanhante acompanh = new Acompanhante();
//
//		try {
//			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
//			nameValuePairs.add(new BasicNameValuePair("id", String
//					.valueOf(acomp.getId())));
//			get.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//			HttpResponse resposta = cliente.execute(get);
//			String s = toString(resposta.getEntity().getContent());
//			JSONObject objeto = new JSONObject(s);
//
//			
////			acomp.setBusto(objeto.getString("busto"));
////			acomp.setAltura(objeto.getString("altura"));
//			
//			acomp.setNome(objeto.getString("nome"));
//			acomp.setEspecialidade(objeto.getString("especialidade"));
//			acomp.setIdade(objeto.getString("idade"));
//			acomp.setStatus(objeto.getString("status"));
//			acomp.setBusto(objeto.getString("busto"));
////			acomp.setAltura(objeto.getString("altura"));
////			acomp.setCintura(objeto.getString("cintura"));
////			acomp.setQuadril(objeto.getString("quadril"));
////			acomp.setOlhos(objeto.getString("olhos"));
////			acomp.setPernoite(objeto.getString("pernoite"));
////			acomp.setAtendo(objeto.getString("atendo"));
////			acomp.setHorario_atentimento(objeto.getString("horario_aten"));
////			acomp.setPeso(objeto.getString("peso"));
////			acomp.setStatus(objeto.getString("status"));
//			
//			
//			
//			Log.i("pedro", "oiii: " + objeto.getString("busto"));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return acompanh;
//	}
//
//	@Override
//	protected void onPostExecute(Acompanhante acompanhante) {
//		super.onPostExecute(acompanhante);
//		if (acompanhante != null) {
////			peso.setText(acompanhante.getPeso());
//			altura.setText(acompanhante.getAltura());
////			atendo.setText(acompanhante.getAtendo());
//			busto.setText(acompanhante.getBusto());
//			
//		}
//		dialog.dismiss();
//	}
//
//	private String toString(InputStream is) throws IOException {
//
//		byte[] bytes = new byte[1024];
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		int lidos;
//		while ((lidos = is.read(bytes)) > 0) {
//			baos.write(bytes, 0, lidos);
//		}
//		return new String(baos.toByteArray());
//	}
//
//}

	@Override
	public void onClick(View v) {
		
	}

}
