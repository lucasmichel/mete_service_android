package br.uni.mete_service.model.repositorio;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.uni.mete_service.model.Cliente;

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

		List<NameValuePair> listaCamposPesquisa = new ArrayList<NameValuePair>(
				2);
		listaCamposPesquisa.add(new BasicNameValuePair("email", String
				.valueOf(cliente.getEmail())));
		listaCamposPesquisa.add(new BasicNameValuePair("senha", String
				.valueOf(cliente.getSenha())));

		Cliente clienteRetorno = new Cliente();
		String nomeDaAcao = "logarAndroid";
		JSONObject objetoJSON = this
				.getPorPost(nomeDaAcao, listaCamposPesquisa);
		/*
		 * usuarioRetorno.setEmail(objeto.getString("email"));
		 * usuarioRetorno.setSenha(objeto.getString("senha"));
		 */
		clienteRetorno.setStatus(objetoJSON.getInt("status"));
		clienteRetorno.setMensagem(objetoJSON.getString("mesagem"));

		return clienteRetorno;
	}

	public List<Cliente> lerTodosClientes(String todosClientesJson)
			throws ParseException {

		List<Cliente> listclientes = new ArrayList<Cliente>();
		try {

			JSONObject json = new JSONObject(todosClientesJson);
			if (json.has("cliente") && json.optJSONArray("cliente") != null) {
				JSONArray clienteArray = json.getJSONArray("cliente");
				listclientes = parserClienteArray(clienteArray);
			} else {
				listclientes = new ArrayList<Cliente>();
				listclientes.add(parseClienteJsonObject(json
						.getJSONObject("cliene")));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return listclientes;

	}

	private List<Cliente> parserClienteArray(JSONArray clienteArray)
			throws ParseException {
		List<Cliente> listcliente = new ArrayList<Cliente>();

		for (int i = 0; i < clienteArray.length(); i++) {
			try {

				listcliente.add(parseClienteJsonObject(clienteArray
						.getJSONObject(i)));

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return listcliente;
	}

	public static Cliente parseClienteJsonObject(JSONObject usuarioJson)
			throws JSONException {

		Cliente cli = new Cliente();

		cli.setId(usuarioJson.getInt("id"));
		cli.setNome(usuarioJson.optString("name"));
		cli.setEmail(usuarioJson.optString("email"));
		cli.setTelefone((usuarioJson.optString("telefone")));
		cli.setSenha(usuarioJson.optString("senha"));
		cli.setTipo((usuarioJson.optInt("tipo")));
		cli.setCpf(usuarioJson.optString("cpf"));

		return cli;

	}

}
