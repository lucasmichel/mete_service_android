package br.uni.mete_service.model.repositorio.Acompanhante;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.uni.mete_service.model.Acompanhante;

public class AcompanhanteParser {

	public List<Acompanhante> lerTodosAcompanhantes(
			String todosAcompanhantesJson) throws ParseException {

		List<Acompanhante> listacompanhantes = new ArrayList<Acompanhante>();
		try {

			JSONObject json = new JSONObject(todosAcompanhantesJson);
			if (json.has("objacompanhante")
					&& json.optJSONArray("objacompanhante") != null) {
				JSONArray acompanhanteArray = json
						.getJSONArray("objacompanhante");
				listacompanhantes = parserAcompanhanteArray(acompanhanteArray);
			} else {
				listacompanhantes = new ArrayList<Acompanhante>();
				listacompanhantes.add(parseAcompanhanteJsonObject(json
						.getJSONObject("objacompanhante")));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return listacompanhantes;

	}

	private List<Acompanhante> parserAcompanhanteArray(
			JSONArray acompanhanteArray) throws ParseException {
		List<Acompanhante> listacompanhante = new ArrayList<Acompanhante>();

		for (int i = 0; i < acompanhanteArray.length(); i++) {
			try {

				listacompanhante
						.add(parseAcompanhanteJsonObject(acompanhanteArray
								.getJSONObject(i)));

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return listacompanhante;
	}

	public static Acompanhante parseAcompanhanteJsonObject(
			JSONObject usuarioJson) throws JSONException {

		Acompanhante acomp = new Acompanhante();

		acomp.setId(usuarioJson.getInt("id"));
		acomp.setEmail(usuarioJson.optString("email"));
		acomp.setSenha(usuarioJson.optString("senha"));
		acomp.setNome(usuarioJson.optString("name"));
		acomp.setIdade((usuarioJson.optString("idade")));
		acomp.setAltura(usuarioJson.optString("altura"));
		acomp.setPeso(usuarioJson.optString("peso"));
		acomp.setBusto(usuarioJson.optString("busto"));
		acomp.setCintura(usuarioJson.optString("cintura"));
		acomp.setQuadril(usuarioJson.optString("quadril"));
		acomp.setOlhos(usuarioJson.optString("olhos"));
		acomp.setPernoite(usuarioJson.optString("pernoite"));
		acomp.setAtendo(usuarioJson.optString("atendo"));
		acomp.setEspecialidade(usuarioJson.optString("especialidade"));
		acomp.setHorario_atentimento(usuarioJson
				.optString("horario_atendimento"));

		return acomp;

	}

}