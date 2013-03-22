package br.uni.mete_service.model.repositorio.Cliente;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.uni.mete_service.model.Cliente;
import br.uni.mete_service.model.repositorio.RepositorioClass;

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

		//
		// cria o json com os paramterso que se quer..
		//
		JSONObject jsonObjectEntrada = new JSONObject();
		jsonObjectEntrada.put("senha", cliente.getSenha());
		jsonObjectEntrada.put("email", cliente.getEmail());

		//
		// criptogrfa o json gerando uma string na base64..
		//
		String textoCriptografado = this.toBase64StringEncode(jsonObjectEntrada
				.toString());

		//
		// cria a lista de parâmetros para o post seguindo este padrão
		// listaCamposPesquisa.add(new BasicNameValuePair("textoCriptografado",
		// String.valueOf(textoCriptografado)));
		//
		List<NameValuePair> listaCamposPesquisa = new ArrayList<NameValuePair>(
				1);
		listaCamposPesquisa.add(new BasicNameValuePair("textoCriptografado",
				String.valueOf(textoCriptografado)));

		//
		// passa o nome da açao do webservice
		//
		String nomeDaAcao = "logarAndroid";

		//
		// recebe um json descriptografado com as informações de retorno do
		// post
		//
		JSONObject jsonObjectSaida = this.getInformacao(nomeDaAcao,
				listaCamposPesquisa);

		//
		// cria um usuario pra receber os dados do post em status e msgm...
		//
		Cliente clienteRetorno = new Cliente();
		clienteRetorno.setStatus(jsonObjectSaida.getInt("status"));
		clienteRetorno.setMensagem(jsonObjectSaida.getString("mensagem"));

		return clienteRetorno;
	}

	public Cliente cadastrarCliente(Cliente cliente) throws JSONException {
		//
		// cria o json com os paramterso que se quer..
		//
		JSONObject jsonObjectEntrada = new JSONObject();
		jsonObjectEntrada.put("senha", cliente.getSenha());
		jsonObjectEntrada.put("email", cliente.getEmail());

		//
		// criptogrfa o json gerando uma string na base64..
		//
		String textoCriptografado = this.toBase64StringEncode(jsonObjectEntrada
				.toString());

		//
		// cria a lista de parâmetros para o post seguindo este padrão
		// listaCamposPesquisa.add(new BasicNameValuePair("textoCriptografado",
		// String.valueOf(textoCriptografado)));
		//
		List<NameValuePair> listaCamposPesquisa = new ArrayList<NameValuePair>(
				1);
		listaCamposPesquisa.add(new BasicNameValuePair("textoCriptografado",
				String.valueOf(textoCriptografado)));

		//
		// passa o nome da açao do webservice
		//
		// String nomeDaAcao = "cadastrarUsuario";
		String nomeDaAcao = "cadastrarUsuarioBUUU";

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
		clienteRetorno.setStatus(jsonObjectSaida.getInt("status"));
		clienteRetorno.setMensagem(jsonObjectSaida.getString("messagem"));

		return clienteRetorno;
	}

}
