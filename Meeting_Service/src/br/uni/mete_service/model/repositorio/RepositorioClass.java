package br.uni.mete_service.model.repositorio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.util.Base64;

public class RepositorioClass {

	private String nomeConexao = null;

	protected RepositorioClass(String nomeConexao) {
		this.nomeConexao = nomeConexao;
	}

	public static String toString(InputStream is) throws IOException {

		byte[] bytes = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int lidos;
		while ((lidos = is.read(bytes)) > 0) {
			baos.write(bytes, 0, lidos);
		}
		return new String(baos.toByteArray());
	}

	/**
	 * @param url
	 *            Caminho do método desejado no web-service.
	 * @return O json em formato de String.
	 */
	public static String getRESTFileContent(String url) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);

		try {
			HttpResponse response = httpclient.execute(httpget);

			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				String result = toString(instream);
				instream.close();
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String executaPost(JSONObject objetoJson, String nomeDoObjeto) {
		try {
			URL url = new URL(nomeConexao + nomeDoObjeto);
			HttpURLConnection conexao = (HttpURLConnection) url
					.openConnection();

			conexao.setRequestMethod("POST");
			conexao.addRequestProperty("Content-type", "application/json");

			conexao.setDoOutput(true);

			conexao.connect();

			// faz o encode64 do json
			byte[] bytes = Base64.encode(objetoJson.toString().getBytes(),
					Base64.DEFAULT);
			String stringInserir = new String(bytes, "UTF-8");

			OutputStream os = conexao.getOutputStream();
			// os.write(objetoJson.toString().getBytes());
			os.write(stringInserir.getBytes());

			os.flush();

			InputStream is = conexao.getInputStream();
			return toString(is);

		} catch (Exception e) {
			e.printStackTrace();
			return "ERRO! " + e.getMessage();
		}
	}

	public JSONObject getPorPost(String nomeDaAcao,
			List<NameValuePair> listaCamposPesquisa) {

		JSONObject objetoJSONAQUI = null;
		try {

			HttpClient cliente = new DefaultHttpClient();

			String a = nomeConexao + nomeDaAcao;

			HttpPost get = new HttpPost(a);

			try {

				get.setEntity(new UrlEncodedFormEntity(listaCamposPesquisa));

				HttpResponse resposta = cliente.execute(get);
				String s = toString(resposta.getEntity().getContent());
				objetoJSONAQUI = new JSONObject(s);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return objetoJSONAQUI;
	}

}
