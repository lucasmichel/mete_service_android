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
import org.apache.http.params.HttpConnectionParams;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;

public class Repositorio {
	
	Gson gson = new Gson();
	String urlAcesso = "http://leonardogalvao.com.br/mete_service/src/";
	
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
	
	public Modelo acessarServidor (Modelo modelo, String acao) {
        
		Modelo modeloRetorno = new Modelo();
		modeloRetorno = null;
		HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);
        HttpResponse response;
        String URL = urlAcesso + acao;

        try{
            HttpPost post = new HttpPost(URL);
            List<NameValuePair> nVP = new ArrayList<NameValuePair>(2);  
            nVP.add(new BasicNameValuePair("textoCriptografado", toBase64StringEncode(gson.toJson(modelo))));  
            post.setEntity(new UrlEncodedFormEntity(nVP));
            response = client.execute(post);  
            if(response!=null){
                InputStream in = response.getEntity().getContent();             
                Log.i("OBS" , "InputStream: " + toString(in));                
                modeloRetorno = gson.fromJson(toString(in), Modelo.class);                
                return modeloRetorno;
            }else {return modeloRetorno;}                        
        }
        catch(Exception e){
            e.printStackTrace();            
        }
        return modeloRetorno;
    }	
}