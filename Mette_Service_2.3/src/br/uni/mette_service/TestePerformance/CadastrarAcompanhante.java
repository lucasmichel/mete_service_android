package br.uni.mette_service.TestePerformance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import android.util.Log;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Cliente;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

public class CadastrarAcompanhante implements Runnable {

	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	Repositorio repositorio = new Repositorio();
	List<Object> lista = new ArrayList();
	Acompanhante acompanhante = new Acompanhante();
	
	public void run() {

		java.text.DateFormat tempo = new java.text.SimpleDateFormat("HH:mm:ss.SSS");
		Calendar dataInicio = Calendar.getInstance(); 

		Random rand = new Random();

		acompanhante.setNome(rand.nextInt(999999) + "Nome Acompanhante");
		acompanhante.setIdade("20");
		acompanhante.setPeso("60");
		acompanhante.setBusto("30");
		acompanhante.setAltura("20");
		acompanhante.setCintura("40");
		acompanhante.setQuadril("50");
		acompanhante.setOlhos("Azul");
		acompanhante.setPernoite(0);
		acompanhante.setPernoite(1);
		acompanhante.setAtendo("Homens");
		acompanhante.setAtendo("Mulheres");					
		acompanhante.setAtendo("Ambos");
		acompanhante.setEspecialidade("Teste");
		acompanhante.setHorarioAtendimento("");
		acompanhante.setEmail(rand.nextInt(999999999) + "@acompanhante.com");
		acompanhante.setSenha(rand.nextInt(999999) + "senha123");
		
		lista.clear();

		lista.add(acompanhante);
		modelo.setDados(lista);
		modelo.setMensagem("");
		modelo.setStatus("");

		modeloRetorno = repositorio.acessarServidor("cadastrarAcompanhante", modelo);
		Log.i("Teste Carga",
				"Tentativa de cadastro de Acompanhante com email " + acompanhante.getEmail()
						+ " - Retorno: " + modeloRetorno.getMensagem());
		
		Calendar dataFim = Calendar.getInstance();
		long diferenca = dataFim.getTimeInMillis()- dataInicio.getTimeInMillis();
		tempo.setTimeZone(TimeZone.getTimeZone("UTC"));
		Log.i("Teste Carga", "Acesso durou: " +  tempo.format(diferenca));
	}
}