package br.uni.mete_service.Controller;

import org.json.JSONObject;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import br.uni.mete_service.model.Cliente;
import br.uni.mete_service.model.Usuario;

public class ClienteController {

	private static ClienteController clienteController = null;
	private ClienteParser clienteParser = null;
	private final String urlGetAllCliente = "";
	private Cliente cliLogged;
	private final String urlInserirCliente = "?";
	private final String urlLogarCliente = "?";
	private final String urlAlterarCliente = "?";
	private Gson gson;

	public ClienteController() {
		clienteParser = new ClienteParser();
	}

	public static ClienteController getInstance() {
		if (clienteController == null) {
			clienteController = new ClienteController();
		}
		return clienteController;
	}

	public void setUsuarioLogged(Cliente clienteLogged) {
		this.cliLogged = clienteLogged;
	}

	public Cliente getCienteLogged() {
		return cliLogged;
	}

	

	public synchronized String inserirCliente(Cliente cli) {

		try {
			URL url = new URL(urlInserirCliente);
			HttpURLConnection conexao = (HttpURLConnection) url
					.openConnection();

			conexao.setRequestMethod("POST");
			conexao.addRequestProperty("Content-type", "text/plain");

			conexao.setDoOutput(true);

			conexao.connect();

			OutputStream os = conexao.getOutputStream();
			os.write(gson.toJson(cli).getBytes());
			os.flush();
			InputStream is = conexao.getInputStream();
			return HttpCliente.toString(is);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public synchronized String alterarCliente(Cliente cli) {

		try {
			URL url = new URL(urlAlterarCliente);
			HttpURLConnection conexao = (HttpURLConnection) url
					.openConnection();

			conexao.setRequestMethod("POST");
			conexao.addRequestProperty("Content-type", "text/plain");

			conexao.setDoOutput(true);

			conexao.connect();

			OutputStream os = conexao.getOutputStream();
			os.write(gson.toJson(cli).getBytes());
			os.flush();
			InputStream is = conexao.getInputStream();
			return HttpCliente.toString(is);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public synchronized String logarCliente(Cliente cli) {

		try {
			URL url = new URL(urlLogarCliente);
			HttpURLConnection conexao = (HttpURLConnection) url
					.openConnection();

			conexao.setRequestMethod("POST");
			conexao.addRequestProperty("Content-type", "text/plain");

			conexao.setDoOutput(true);

			conexao.connect();

			OutputStream os = conexao.getOutputStream();
			os.write(gson.toJson(cli).getBytes());
			os.flush();
			InputStream is = conexao.getInputStream();
			return HttpCliente.toString(is);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public synchronized List<Cliente> getAllClientes() throws ParseException {
		List<Cliente> listAllUsuarios = null;

		String result = HttpCliente.getRESTFileContent(urlGetAllCliente);

		if (result != null) {
			listAllUsuarios = clienteParser.lerTodosClientes(result);
		}
		return listAllUsuarios;
	}

}
