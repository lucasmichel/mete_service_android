package br.uni.mete_service.model.repositorio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.UnsupportedEncodingException;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Base64;
import android.util.Log;

public class RepositorioClass {

	private String nomeConexao = null;

	protected RepositorioClass(String nomeConexao) {
		this.nomeConexao = nomeConexao;
	}

	/*
	 * Esse primeiro método é uma "receita de bolo". Ele será pelo método
	 * getRESTFileContent(String) (a seguir) e servirá para ler byte-a-byte (na
	 * verdade de 1024 em 1024 bytes) o conteúdo do arquivo JSON que é
	 * retornado pelo servidor, retornando-o em forma de String.
	 */

	private String toString(InputStream is) throws IOException {

		byte[] bytes = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int lidos;
		while ((lidos = is.read(bytes)) > 0) {
			baos.write(bytes, 0, lidos);
		}
		return new String(baos.toByteArray());
	}

	/*
	 * O método acima, utiliza o HttpClient (da Apache, que vem nativamente no
	 * Android) para estabelecer a conexão com o servidor, abrir o fluxo de
	 * leitura (InpuStream) e retornar o conteúdo do arquivo JSON em forma de
	 * String (feito pelo método toString(InputStream)).
	 */

	public JSONObject getInformacao(String nomeDaAcao,
			List<NameValuePair> listaCamposPesquisa) {

		JSONObject objetoJSONAQUI = null;
		try {

			HttpClient cliente = new DefaultHttpClient();
			String url = nomeConexao + nomeDaAcao;
			HttpPost get = new HttpPost(url);

			try {

				get.setEntity(new UrlEncodedFormEntity(listaCamposPesquisa));

				HttpResponse resposta = cliente.execute(get);
				String s = toString(resposta.getEntity().getContent());
				// objetoJSONAQUI = new JSONObject(s);

				String retornoDesciptografado = toBase64StringDecode(s);
				objetoJSONAQUI = new JSONObject(retornoDesciptografado);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return objetoJSONAQUI;
	}

	public JSONObject postInformacao(String nomeDaAcao,
			List<NameValuePair> listaCamposPesquisa) {

		JSONObject objetoJSONAQUI = null;

		String retornoDesciptografado = null;

		HttpResponse resposta = null;

		String s = null;

		HttpClient cliente = new DefaultHttpClient();

		String url = nomeConexao + nomeDaAcao;

		HttpPost get = new HttpPost(url);

		try {
			get.setEntity(new UrlEncodedFormEntity(listaCamposPesquisa));
		} catch (UnsupportedEncodingException e) {
			Log.e("LUCASMICHEL", "UnsupportedEncodingException", e);
			e.printStackTrace();
		}
		try {
			resposta = cliente.execute(get);
		} catch (ClientProtocolException e) {
			Log.e("LUCASMICHEL", "ClientProtocolException", e);
			e.printStackTrace();

		} catch (IOException e) {
			Log.e("LUCASMICHEL", "IOException", e);
			e.printStackTrace();
		}
		try {
			s = toString(resposta.getEntity().getContent());
		} catch (IllegalStateException e) {
			Log.e("LUCASMICHEL", "IllegalStateException", e);
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("LUCASMICHEL", "IOException", e);
			e.printStackTrace();
		}
		try {
			objetoJSONAQUI = new JSONObject(s);
		} catch (JSONException e) {
			Log.e("LUCASMICHEL", "JSONException", e);
			e.printStackTrace();
		}
		try {
			retornoDesciptografado = toBase64StringDecode(s);
		} catch (UnsupportedEncodingException e) {
			Log.e("LUCASMICHEL", "UnsupportedEncodingException", e);
			e.printStackTrace();
		}
		try {
			objetoJSONAQUI = new JSONObject(retornoDesciptografado);
		} catch (JSONException e) {
			Log.e("LUCASMICHEL", "JSONException", e);
			e.printStackTrace();
		}

		return objetoJSONAQUI;
	}

	protected String toBase64StringEncode(String text) {
		byte bytes[] = text.getBytes();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}

	private String toBase64StringDecode(String text)
			throws UnsupportedEncodingException {
		byte bytes[] = text.getBytes();
		Base64.decode(bytes, Base64.DEFAULT);
		String valor = new String(Base64.decode(bytes, Base64.DEFAULT),
				"ISO-8859-1");
		return valor;
	}

}
