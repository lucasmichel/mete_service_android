package br.uni.mette_service.Model.Repositorio;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.uni.mette_service.Model.Usuario;

import com.google.gson.Gson;

public class RepositorioUsuario extends RepositorioClass {
	Gson gson = new Gson();
	
	protected RepositorioUsuario(String nomeConexao) {
		super(nomeConexao);
		// TODO Auto-generated constructor stub
	}
	
	/*responssavel por obter uma unica instancia do objeto*/
	private static RepositorioUsuario instancia = null;	
	/*responssavel por obter uma unica instancia do objeto*/
	
	
	private static String nomeConexao = "http://leonardogalvao.com.br/mete_service/src/";
	//private static String nomeConexao = "http://192.168.0.101/mete_service/src/";
	
	/*responssavel por obter uma unica instancia do objeto*/
	public static RepositorioUsuario getInstance(){
		
		if(instancia == null){
            instancia = new RepositorioUsuario(nomeConexao);            
            return instancia;
		}
		else{
			return instancia;
		}
	}
	/*responssavel por obter uma unica instancia do objeto*/
	
	
	public ModelClass logarAndroid(Usuario usuario) throws JSONException{
	
				
		List<Object> lista = new ArrayList<Object>();
		lista.add(usuario);
		
		
		ModelClass modelo = new ModelClass();
		modelo.setDados(lista);
		modelo.setMensagem("OI");
		modelo.setStatus(0);
		
		gson = new Gson();
		List<NameValuePair> listaCamposPesquisa = new ArrayList<NameValuePair>(1);  
		listaCamposPesquisa.add(new BasicNameValuePair("textoCriptografado", toBase64StringEncode(gson.toJson(modelo))));		
		
		String nomeDaAcao = "logarAndroid";
		//
		//recebe um json descriptografado com as informações de retorno do post
		//
		JSONObject jsonObjectSaida = this.getInformacao(nomeDaAcao, listaCamposPesquisa);
		//
		//cria um usuario pra receber os dados do post em status e msgm...
		//
		
		
		List<Object> listaRetorno = new ArrayList<Object>();
		JSONArray array = jsonObjectSaida.getJSONArray("dados");
		
		/*AQUI PREENCHE O OBJETO e ADD a LISTA*/
		for (int i = 0; i < array.length(); ++i) {
		    JSONObject rec = array.getJSONObject(i);
		    
		    Usuario useAdd = new Usuario();
		    
		    useAdd.setId(rec.getInt("id"));
		    useAdd.setIdPerfil(rec.getInt("id_perfil"));
		    useAdd.setEmail(rec.getString("email"));
		    
		    listaRetorno.add(useAdd);
		}
		/*AQUI PREENCHE O OBJETO e adiciona a lista*/
		
		
		//jsonObjectSaida.g
		
		ModelClass usuarioRetorno = new ModelClass();
		usuarioRetorno.setDados(listaRetorno);
		usuarioRetorno.setStatus(jsonObjectSaida.getInt("status"));
		usuarioRetorno.setMensagem(jsonObjectSaida.getString("mensagem"));
		
		return usuarioRetorno;
	}
	
	
	
	
	
}
