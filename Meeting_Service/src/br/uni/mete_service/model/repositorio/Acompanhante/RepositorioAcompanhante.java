package br.uni.mete_service.model.repositorio.Acompanhante;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import br.uni.mete_service.model.Acompanhante;
import br.uni.mete_service.model.repositorio.RepositorioClass;


///////////////////////////////////////////////////////////////////////

public class RepositorioAcompanhante extends RepositorioClass {

	protected RepositorioAcompanhante(String nomeConexao) {
		super(nomeConexao);
		// TODO Auto-generated constructor stub
	}

	private static RepositorioAcompanhante instancia = null;
	private static String nomeConexao = "http://leonardogalvao.com.br/mete_service/src/";

	public static RepositorioAcompanhante getInstance() {

		if (instancia == null) {
			instancia = new RepositorioAcompanhante(nomeConexao);
			return instancia;
		} else {
			return instancia;
		}
	}

	public Acompanhante logarAndroid(Acompanhante objacompanhante) throws JSONException {

		//
		// CRIA O JSON COM OS PARÂMETROS QUE SE QUER 
		//
		JSONObject jsonObjectEntrada = new JSONObject();
		jsonObjectEntrada.put("email", objacompanhante.getEmail());
		jsonObjectEntrada.put("senha", objacompanhante.getSenha());

		//
		// criptogrfa o json gerando uma string na base64..
		//
		String textoCriptografado = this.toBase64StringEncode(jsonObjectEntrada
				.toString());

		//
		// CRIA A LISTA DE PARÂMETROS DO POST SEGUINDO ESTE PADRÃO
		// listaCamposPesquisa.add(new BasicNameValuePair("textoCriptografado",
		// String.valueOf(textoCriptografado)));
		//
		List<NameValuePair> listaCamposPesquisa = new ArrayList<NameValuePair>(
				1);
		listaCamposPesquisa.add(new BasicNameValuePair("textoCriptografado",
				String.valueOf(textoCriptografado)));

		//
		// PASSA O NOME DA AÇAO DO WEBSERVICE
		//
		String nomeDaAcao = "logarAndroid";

		//
		// RECEBE UM JSON DESCRIPTOGRAFADO COM AS INFOS DE RETORNO DO
		// post
		//
		JSONObject jsonObjectSaida = this.getInformacao(nomeDaAcao,
				listaCamposPesquisa);
		//
		// CRIA UM USUARIO PARA RECEBER OS DADOS DO POST EM STATUS E MENSAGEM..
		//
		Acompanhante acompanhanteRetorno = new Acompanhante();
		acompanhanteRetorno.setStatus(jsonObjectSaida.getInt("status"));
		acompanhanteRetorno.setMensagem(jsonObjectSaida.getString("mensagem"));

		return acompanhanteRetorno;
	}

	public Acompanhante cadastrarAcompanhante(Acompanhante objacompanhante) throws JSONException {
		//
		// CRIA O JSON COM OS PARÂMETROS QUE SE QUER
		//
		JSONObject jsonObjectEntrada = new JSONObject();
		///// 	DADOS EXISTENTES DA CLASSE USUARIO
		jsonObjectEntrada.put("email", objacompanhante.getEmail());
		jsonObjectEntrada.put("senha", objacompanhante.getSenha());
		jsonObjectEntrada.put("tipo", objacompanhante.getTipo());
		
		/////   DADOS ESPECÍFICOS DA CLASSE ACOMPANHANTE
		jsonObjectEntrada.put("nome", objacompanhante.getNome());
		jsonObjectEntrada.put("idade", objacompanhante.getIdade());
		jsonObjectEntrada.put("altura", objacompanhante.getAltura());
		jsonObjectEntrada.put("peso", objacompanhante.getPeso());
		jsonObjectEntrada.put("busto", objacompanhante.getBusto());
		jsonObjectEntrada.put("cintura", objacompanhante.getCintura());
		jsonObjectEntrada.put("quadril", objacompanhante.getQuadril());
		jsonObjectEntrada.put("olhos", objacompanhante.getOlhos());
		jsonObjectEntrada.put("especialidade", objacompanhante.getEspecialidade());
		jsonObjectEntrada.put("horario_atendimento", objacompanhante.getHorario_atendimento());
		jsonObjectEntrada.put("status_atendimento", objacompanhante.getStatusAt());
		jsonObjectEntrada.put("pernoite", objacompanhante.getPernoite());
		jsonObjectEntrada.put("atendo", objacompanhante.getAtendo());
		jsonObjectEntrada.put("fotoperfil", objacompanhante.getFoto());
		

		
		
		//
		// CRIPTOGRAFANDO O JSON PARA GERAR UM BASE64
		//

		String textoCriptografado = this.toBase64StringEncode(jsonObjectEntrada
				.toString());

		//
		// CRIA UMA LISTA DE PARÂMETROS PARA O POST SEGUINDO ESSE PADRÃO
		// listaCamposPesquisa.add(new BasicNameValuePair("textoCriptografado",
		// String.valueOf(textoCriptografado)));
		//
		List<NameValuePair> listaCamposPesquisa = new ArrayList<NameValuePair>(
				1);
		listaCamposPesquisa.add(new BasicNameValuePair("textoCriptografado",
				String.valueOf(textoCriptografado)));

		//
		// PARA O NOME DA AÇÃO DO WEBSERVICE
		//
		// String nomeDaAcao = "cadastrarUsuario";
		String nomeDaAcao = "cadastrarUsuario";

		//
		// RECEBE UM JSON DESCRIPTOGRAFADO COM AS INFORMAÇÕES DE RETORNO do
		// post
		//
		// JSONObject jsonObjectSaida = this.postInformacao(nomeDaAcao,
		// listaCamposPesquisa);
		JSONObject jsonObjectSaida = this.getInformacao(nomeDaAcao,
				listaCamposPesquisa);

		//
		// CRIA UM USUARIO PARA RECEBER OS DADOS DO POST EM STATUS E MENSAGEM...
		//
		Acompanhante acompanhanteRetorno = new Acompanhante();
		acompanhanteRetorno.setId(jsonObjectSaida.getString("id"));
		acompanhanteRetorno.setStatus(jsonObjectSaida.getInt("status"));
		acompanhanteRetorno.setMensagem(jsonObjectSaida.getString("messagem"));

		return acompanhanteRetorno;
	}

}