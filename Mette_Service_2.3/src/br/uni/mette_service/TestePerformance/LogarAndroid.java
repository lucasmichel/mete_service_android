package br.uni.mette_service.TestePerformance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import android.util.Log;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

public class LogarAndroid implements Runnable {

	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	Repositorio repositorio = new Repositorio();
	List<Object> lista = new ArrayList();
	Usuario usuario = new Usuario();
	
	public void run() {

		java.text.DateFormat tempo = new java.text.SimpleDateFormat("HH:mm:ss.SSS");
		Calendar dataInicio = Calendar.getInstance(); 

		usuario.setEmail("admin@admin.com.br");
		usuario.setSenha("admin");
		
		lista.clear();

		lista.add(usuario);
		modelo.setDados(lista);
		modelo.setMensagem("");
		modelo.setStatus("");

		modeloRetorno = repositorio.acessarServidor("cadastrarAcompanhante", modelo);
		Log.i("Teste Carga",
				"Tentativa de logar com email " + usuario.getEmail()
						+ " - Retorno: " + modeloRetorno.getMensagem());
		
		Calendar dataFim = Calendar.getInstance();
		long diferenca = dataFim.getTimeInMillis()- dataInicio.getTimeInMillis();
		tempo.setTimeZone(TimeZone.getTimeZone("UTC"));
		Log.i("Teste Carga", "Acesso durou: " +  tempo.format(diferenca));
	}
}