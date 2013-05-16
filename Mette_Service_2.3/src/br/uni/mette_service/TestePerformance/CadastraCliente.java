package br.uni.mette_service.TestePerformance;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import br.uni.mette_service.Controller.Cliente.ClienteMenuActivity;
import br.uni.mette_service.Model.Cliente;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

public class CadastraCliente implements Runnable {

	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	Repositorio repositorio = new Repositorio();
	List<Object> listaUsuario = new ArrayList();
	Cliente usuario = new Cliente();
	

	public void run() {
		// TODO Auto-generated method stub

		long startTempo = System.currentTimeMillis(); 
		
		Random rand = new Random();

		usuario.setNome(rand.nextInt(99999) + "Nome Teste");
		usuario.setCpf(rand.nextInt(99999) + "11223300");
		usuario.setEmail(rand.nextInt(99999) + "testeperformance@teste.com");
		usuario.setSenha(rand.nextInt(99999) + "senhateste");

		listaUsuario.clear();

		listaUsuario.add(usuario);

		modelo.setDados(listaUsuario);
		modelo.setMensagem("");
		modelo.setStatus("");

		modeloRetorno = repositorio.acessarServidor("cadastrarCliente", modelo);
		Log.i("Teste Carga",
				"Tentativa de cadastro com email " + usuario.getEmail()
						+ " - Retorno: " + modeloRetorno.getMensagem());
		long endTempo = System.currentTimeMillis(); 
		long durou = startTempo - endTempo ;

		System.out.println("tempo q o acesso durou" + durou);
		Log.i("Teste Carga", "Acesso durou: " + durou);
	}
}