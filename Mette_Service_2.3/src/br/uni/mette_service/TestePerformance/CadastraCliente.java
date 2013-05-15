package br.uni.mette_service.TestePerformance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.util.Log;
import br.uni.mette_service.Model.Cliente;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

public class CadastraCliente implements Runnable{	
	
	Modelo modelo = new Modelo();	
	Modelo modeloRetorno = new Modelo();	
	Repositorio repositorio = new Repositorio();
	List<Object> listaUsuario = new ArrayList();
	Cliente usuario = new Cliente();

	public void run() {
		// TODO Auto-generated method stub

		
		Date data = new Date();		
		long inicio =  data.getTime();
		
		usuario.setEmail("admin@admin.com.br");
		usuario.setSenha("admin");
		
		listaUsuario.clear();
		
		listaUsuario.add(usuario);

		modelo.setDados(listaUsuario);
		modelo.setMensagem("");
		modelo.setStatus("");

		modeloRetorno = repositorio.acessarServidor("logarAndroid", modelo);
		
		Log.i("Teste Carga" , "Tentativa de Logar com email " + usuario.getEmail() + " - Retorno: " + modeloRetorno.getMensagem());
		//Thread.currentThread().stop(); // termina a Thread quando terminar o processo 
		
		long durou = data.getTime() - inicio;
		Log.i("Teste Carga", "Acesso durou: " + durou);
	}
}