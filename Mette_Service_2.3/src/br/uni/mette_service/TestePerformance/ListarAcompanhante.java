package br.uni.mette_service.TestePerformance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import android.util.Log;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

public class ListarAcompanhante implements Runnable {

	int tentativa = 0;
	int qtd_tentativas = 0;
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	Repositorio repositorio = new Repositorio();
	
	public int getTentativa() {
		return tentativa;}
	public void setTentativa(int tentativa) {
		this.tentativa = tentativa;}
	public int getQtd_tentativas() {
		return qtd_tentativas;}
	public void setQtd_tentativas(int qtd_tentativas) {
		this.qtd_tentativas = qtd_tentativas;}
	
	public void run() {

		java.text.DateFormat tempo = new java.text.SimpleDateFormat("HH:mm:ss.SSS");
		Calendar dataInicio = Calendar.getInstance(); 

		modelo.setDados(null);
		modelo.setMensagem("");
		modelo.setStatus("");
		
		modeloRetorno = repositorio.acessarServidor("listarAcompanhante", modelo);
		
		Calendar dataFim = Calendar.getInstance();
		long diferenca = dataFim.getTimeInMillis()- dataInicio.getTimeInMillis();
		tempo.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		Log.i("Teste Carga",
				"Tentativa de listarAcompanhante "
			+ " - Retorno: " + modeloRetorno.getMensagem()	
			+ " - Acesso durou: " +  tempo.format(diferenca));		
	}
}