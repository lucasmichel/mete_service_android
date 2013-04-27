package br.uni.mette_service.Model.Repositorio.Cliente;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.uni.mette_service.Model.Cliente;
import br.uni.mette_service.Model.Repositorio.ModelClass;
import br.uni.mette_service.Model.Repositorio.RepositorioClass;

import android.util.Log;

public class RepositorioCliente extends RepositorioClass {

	protected RepositorioCliente(String nomeConexao) {
		super(nomeConexao);
		// TODO Auto-generated constructor stub
	}

	private static RepositorioCliente instancia = null;
	private static String nomeConexao = "http://leonardogalvao.com.br/mete_service/src/";
	
	
	public static RepositorioCliente getInstance() {

		if (instancia == null) {
			instancia = new RepositorioCliente(nomeConexao);
			return instancia;
		} else {
			return instancia;
		}
	}

	public Cliente logarAndroid(Cliente cliente) throws JSONException {

		Log.i("SOSTENES", "Retorno Json (cliente in repo: " + cliente.getEmail());
		Log.i("SOSTENES", "Retorno Json (cliente in repo: " + cliente.getSenha());	
		
		//
		// cria o json com os paramterso que se quer..
		//
		JSONObject jsonObjectEntrada = new JSONObject();
		jsonObjectEntrada.put("email", cliente.getEmail());
		jsonObjectEntrada.put("senha", cliente.getSenha());

		//
		// criptogrfa o json gerando uma string na base64..
		//
		String textoCriptografado = this.toBase64StringEncode(jsonObjectEntrada.toString());
		Log.i("SOSTENES", "textoCriptografado in repo: " + textoCriptografado);
		
		//
		// cria a lista de parâmetros para o post seguindo este padrão
		// listaCamposPesquisa.add(new BasicNameValuePair("textoCriptografado",
		// String.valueOf(textoCriptografado)));
		//
		List<NameValuePair> listaCamposPesquisa = new ArrayList<NameValuePair>(1);
		listaCamposPesquisa.add(new BasicNameValuePair("textoCriptografado",String.valueOf(textoCriptografado)));

		//
		// passa o nome da açao do webservice
		//
		String nomeDaAcao = "logarAndroid";

		//
		// recebe um json descriptografado com as informações de retorno do
		// post
		//
		JSONObject jsonObjectSaida = this.getInformacao(nomeDaAcao,listaCamposPesquisa);
		Log.i("SOSTENES", "jsonObjectSaida messagem: " + jsonObjectSaida.getString("messagem") + " " + jsonObjectSaida.getInt("status"));
		
		//
		// cria um usuario pra receber os dados do post em status e msgm...
		//
		
		Cliente clienteRetorno = new Cliente();
//		clienteRetorno.setStatus(jsonObjectSaida.getInt("status"));
//		clienteRetorno.setMensagem(jsonObjectSaida.getString("messagem"));
		
		Log.i("SOSTENES", "jsonObjectSaida status: " + jsonObjectSaida.getInt("status"));

		return clienteRetorno;
	}
 
	public Cliente cadastrarCliente(Cliente cliente) throws JSONException {
		List<Object> lista = new ArrayList();
		lista.add(cliente);
		
		
		Gson gson = new Gson();
		
		ModelClass modelo = new ModelClass();
		modelo.setDados(lista);
		modelo.setMensagem("OI");
		modelo.setStatus(0);
		
		Log.i("envio",gson.toJson(modelo));
		
		
		
		List<NameValuePair> listaCamposPesquisa = new ArrayList<NameValuePair>(1);  
		listaCamposPesquisa.add(new BasicNameValuePair("textoCriptografado",
				toBase64StringEncode(gson.toJson(modelo))));

		//
		// passa o nome da açao do webservice
		//
		// String nomeDaAcao = "cadastrarUsuario";
		String nomeDaAcao = "cadastrarCliente"; //S�stenes: Alterado para Realiza��o de testes

		//
		// recebe um json descriptografado com as informações de retorno do
		// post
		//
		// JSONObject jsonObjectSaida = this.postInformacao(nomeDaAcao,
		// listaCamposPesquisa);
		JSONObject jsonObjectSaida = this.getInformacao(nomeDaAcao,
				listaCamposPesquisa);

		//
		// cria um usuario pra receber os dados do post em status e msgm...
		//
		Cliente clienteRetorno = new Cliente();	
		
		return clienteRetorno;
	}

}
