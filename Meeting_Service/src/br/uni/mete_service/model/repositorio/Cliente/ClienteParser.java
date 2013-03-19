package br.uni.mete_service.model.repositorio.Cliente;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.uni.mete_service.model.Cliente;

public class ClienteParser {
	
	public List<Cliente> lerTodosClientes(String todosClientesJson) throws ParseException {

		List<Cliente> listclientes = new ArrayList<Cliente>();
		try {

			JSONObject json = new JSONObject(todosClientesJson);
			if (json.has("cliente") && json.optJSONArray("cliente") != null) {
				JSONArray clienteArray = json.getJSONArray("cliente");
				listclientes = parserClienteArray(clienteArray);
			} else {
				listclientes = new ArrayList<Cliente>();
				listclientes.add(parseClienteJsonObject(json.getJSONObject("cliene")));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return listclientes;

	}

	private List<Cliente> parserClienteArray(JSONArray clienteArray) throws ParseException {
		List<Cliente> listcliente = new ArrayList<Cliente>();

		for (int i = 0; i < clienteArray.length(); i++) {
			try {

				listcliente.add(parseClienteJsonObject(clienteArray.getJSONObject(i)));

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return listcliente;
	}

	public static Cliente parseClienteJsonObject(JSONObject usuarioJson) throws JSONException {

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
