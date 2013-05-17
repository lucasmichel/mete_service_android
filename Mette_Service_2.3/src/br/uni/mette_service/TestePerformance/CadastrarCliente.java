package br.uni.mette_service.TestePerformance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import android.util.Log;
import br.uni.mette_service.Model.Cliente;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

public class CadastrarCliente implements Runnable {

	int tentativa = 0;
	int qtd_tentativas = 0;
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	Repositorio repositorio = new Repositorio();
	List<Object> lista = new ArrayList();
	Cliente cliente = new Cliente();
	
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

		Random rand = new Random();

		cliente.setNome(rand.nextInt(999999) + "Nome Teste");
		cliente.setCpf(rand.nextInt(999999) + "111223300");			
		cliente.setEmail(rand.nextInt(999999) + "teste@cliente.com.br");
		cliente.setSenha(rand.nextInt(999999) + "admin");
		
		lista.clear();

		lista.add(cliente);
		modelo.setDados(lista);
		modelo.setMensagem("");
		modelo.setStatus("");

		modeloRetorno = repositorio.acessarServidor("cadastrarCliente", modelo);
		Calendar dataFim = Calendar.getInstance();
		long diferenca = dataFim.getTimeInMillis()- dataInicio.getTimeInMillis();
		tempo.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		Log.i("Teste Carga",
		      "Tentativa de cadastrarCliente com email " + cliente.getEmail()
			+ " - Retorno: " + modeloRetorno.getMensagem()	
			+ " - Acesso durou: " +  tempo.format(diferenca));		
	}
}