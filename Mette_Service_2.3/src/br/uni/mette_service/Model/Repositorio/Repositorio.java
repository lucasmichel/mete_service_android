package br.uni.mette_service.Model.Repositorio;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.util.Base64;
import android.util.Log;
import com.google.gson.Gson;

public class Repositorio {
	
	Gson gson = new Gson();
	String urlAcesso = "http://leonardogalvao.com.br/mete_service/src/";
	String retornoDesciptografado = "";
	
	public Repositorio(){}
	
	private String toBase64StringDecode(String text)
			       throws UnsupportedEncodingException {
		byte bytes[] = text.getBytes();
		Base64.decode(bytes, Base64.DEFAULT);
		String valor = new String(Base64.decode(bytes, Base64.DEFAULT),
				"ISO-8859-1");
		return valor;
	}
	
	private String toBase64StringEncode(String text) {
		byte bytes[] = text.getBytes();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
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
	
	public Modelo acessarServidor (String acao, Modelo modelo) {
		Modelo modeloRetorno = new Modelo();
		List<NameValuePair> listaCamposPesquisa = new ArrayList<NameValuePair>(1);  
	    listaCamposPesquisa.add(new BasicNameValuePair("textoCriptografado", toBase64StringEncode(gson.toJson(modelo))));							
	    Log.i("SOSTENES", "Json Enviado. Em Repositório: " + gson.toJson(modelo));
	    try {			
		    HttpClient cliente = new DefaultHttpClient();		    
		    String url = urlAcesso+acao;		    
			HttpPost get = new HttpPost(url);
			try {				
				get.setEntity(new UrlEncodedFormEntity(listaCamposPesquisa));
				HttpResponse resposta = cliente.execute(get);
				String s = toString(resposta.getEntity().getContent());			
				Log.i("SOSTENES", "Retorno Criptografado em Repositório: " + s);	
				String retornoDesciptografado = toBase64StringDecode(s);
				Log.i("SOSTENES", "Retorno Desciptografado em Repositório: " + retornoDesciptografado);								
				modeloRetorno = gson.fromJson(retornoDesciptografado, Modelo.class);						
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return modeloRetorno;		
	}	
	
	//-------------------------------------------------------------
	/*COPIA TEMPORARIA DO METODO acessarServidor PARA TESTE DO MAPA
	DEVIDO A TROCA DA urlAcesso PARA NÃO MEXER NO QUE JA ESTA FUNCIONANDO
	 							PEDRO SANTA ROSA						*/
	
	String urlAcessoMAPA = "https://dl.dropbox.com/";
	
	public Modelo acessarServidorMAPA (String acao, Modelo modelo) {
		Modelo modeloRetorno = new Modelo();
		List<NameValuePair> listaCamposPesquisa = new ArrayList<NameValuePair>(1);  
	    listaCamposPesquisa.add(new BasicNameValuePair("textoCriptografado", toBase64StringEncode(gson.toJson(modelo))));							
	    Log.i("SOSTENES", "Json Enviado. Em Repositório: " + gson.toJson(modelo));
	    try {			
		    HttpClient cliente = new DefaultHttpClient();		    
		    String url = urlAcessoMAPA+acao;		    
			HttpPost get = new HttpPost(url);
			try {				
				get.setEntity(new UrlEncodedFormEntity(listaCamposPesquisa));
				HttpResponse resposta = cliente.execute(get);
				String s = toString(resposta.getEntity().getContent());				
				String retornoDesciptografado = toBase64StringDecode(s);
				Log.i("PEDRORETORNO", s);
				Log.i("SOSTENES", "Retorno Desciptografado em Repositório: " + retornoDesciptografado);								
				modeloRetorno = gson.fromJson(retornoDesciptografado, Modelo.class);						
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return modeloRetorno;		
	}
	//-----------------FIM DO METODO-------------------------------
	
}