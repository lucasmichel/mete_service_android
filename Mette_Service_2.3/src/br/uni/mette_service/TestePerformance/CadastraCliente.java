package br.uni.mette_service.TestePerformance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import br.uni.mette_service.Controller.Cliente.ClienteMenuActivity;
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
	Date data = new Date();	
	long inicio = 0;

	public void run() {
		// TODO Auto-generated method stub

		
			
		inicio =  data.getTime();
		
		usuario.setNome("Nome Teste");
		usuario.setCpf("11223300");
		usuario.setEmail("testeperformance@teste.com");
		usuario.setSenha("senhateste");
		
		listaUsuario.clear();
		
		listaUsuario.add(usuario);

		modelo.setDados(listaUsuario);
		modelo.setMensagem("");
		modelo.setStatus("");

		new cadastrarClienteAsyncTask().execute();
		
		
	}
	
	// cadastrarCliente
			class cadastrarClienteAsyncTask extends AsyncTask<String, String, Modelo>  {
				ProgressDialog dialog;
				@Override
				protected void onPreExecute() {
					super.onPreExecute();
					
				}

				@Override
				protected Modelo doInBackground(String... params) {	
					modeloRetorno = repositorio.acessarServidor("cadastrarCliente", modelo);
					return modeloRetorno;
				}

				@Override
				protected void onPostExecute(Modelo result) {
					super.onPostExecute(result);
					dialog.dismiss();
					Log.i("Teste Carga" , "Tentativa de cadastro com email " + usuario.getEmail() + " - Retorno: " + modeloRetorno.getMensagem());		
					
					long durou = data.getTime() - inicio;
					Log.i("Teste Carga", "Acesso durou: " + durou);					
				}
			}	
			// Fim			
}