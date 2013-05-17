package br.uni.mette_service.TestePerformance;

import java.util.Calendar;
import java.util.TimeZone;
import android.util.Log;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

public class ListarServicos implements Runnable {

	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	Repositorio repositorio = new Repositorio();
	
	public void run() {

		java.text.DateFormat tempo = new java.text.SimpleDateFormat("HH:mm:ss.SSS");
		Calendar dataInicio = Calendar.getInstance(); 

		modelo.setDados(null);
		modelo.setMensagem("");
		modelo.setStatus("");

		modeloRetorno = repositorio.acessarServidor("listarServicos", modelo);
		Log.i("Teste Carga", "Tentativa de listarServicos - Retorno: " + modeloRetorno.getMensagem());
		
		Calendar dataFim = Calendar.getInstance();
		long diferenca = dataFim.getTimeInMillis()- dataInicio.getTimeInMillis();
		tempo.setTimeZone(TimeZone.getTimeZone("UTC"));
		Log.i("Teste Carga", "Acesso durou: " +  tempo.format(diferenca));
	}
}