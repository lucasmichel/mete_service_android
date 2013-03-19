package br.uni.mete_service.model.repositorio.acompanhante;

import com.google.gson.Gson;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.List;


import br.uni.mete_service.model.Acompanhante;

import br.uni.mete_service.util.ConversorGson;

public class RepositorioAcompanhante {

	private static RepositorioAcompanhante repositorioAcompanhante = null;
	private AcompanhanteParser acompanhanteParser = null;
	private final String urlGetAllAcompanhante = "";
	private Acompanhante acompLogged;
	private final String urlInserirAcompanhante = "?";
	private final String urlLogarAcompanhante = "?";
	private final String urlAlterarAcompanhante = "?";
	private Gson gson;

	public RepositorioAcompanhante() {
		acompanhanteParser = new AcompanhanteParser();
	}

	public static RepositorioAcompanhante getInstance() {
		if (repositorioAcompanhante == null) {
			repositorioAcompanhante = new RepositorioAcompanhante();
		}
		return repositorioAcompanhante;
	}

	public void setUsuarioLogged(Acompanhante acompanhanteLogged) {
		this.acompLogged = acompanhanteLogged;
	}

	public Acompanhante getAcompanhanteLogged() {
		return acompLogged;
	}

	public synchronized String inserirAcompanhante(Acompanhante acomp) {

		try {
			URL url = new URL(urlInserirAcompanhante);
			HttpURLConnection conexao = (HttpURLConnection) url
					.openConnection();

			conexao.setRequestMethod("POST");
			conexao.addRequestProperty("Content-type", "text/plain");

			conexao.setDoOutput(true);

			conexao.connect();

			OutputStream os = conexao.getOutputStream();
			os.write(gson.toJson(acomp).getBytes());
			os.flush();
			InputStream is = conexao.getInputStream();

			return ConversorGson.toString(is);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public synchronized String alterarAcompanhante(Acompanhante acomp) {

		try {
			URL url = new URL(urlAlterarAcompanhante);
			HttpURLConnection conexao = (HttpURLConnection) url
					.openConnection();

			conexao.setRequestMethod("POST");
			conexao.addRequestProperty("Content-type", "text/plain");

			conexao.setDoOutput(true);

			conexao.connect();

			OutputStream os = conexao.getOutputStream();
			os.write(gson.toJson(acomp).getBytes());
			os.flush();
			InputStream is = conexao.getInputStream();
			return ConversorGson.toString(is);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public synchronized String logarAcompanhante(Acompanhante acomp) {

		try {
			URL url = new URL(urlLogarAcompanhante);
			HttpURLConnection conexao = (HttpURLConnection) url
					.openConnection();

			conexao.setRequestMethod("POST");
			conexao.addRequestProperty("Content-type", "text/plain");

			conexao.setDoOutput(true);

			conexao.connect();

			OutputStream os = conexao.getOutputStream();
			os.write(gson.toJson(acomp).getBytes());
			os.flush();
			InputStream is = conexao.getInputStream();
			return ConversorGson.toString(is);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public synchronized List<Acompanhante> getAllAcompanhantes() throws ParseException {
		List<Acompanhante> listAllUsuarios = null;

		String result = ConversorGson.getRESTFileContent(urlGetAllAcompanhante);

		if (result != null) {
			listAllUsuarios = acompanhanteParser.lerTodosAcompanhantes(result);
		}
		return listAllUsuarios;
	}

}
