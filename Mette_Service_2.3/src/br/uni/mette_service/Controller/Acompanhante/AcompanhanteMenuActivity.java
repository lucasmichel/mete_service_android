package br.uni.mette_service.Controller.Acompanhante;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.LogarAndroidActivity;
import br.uni.mette_service.Controller.Servico.CadastroServicoActivity;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import android.util.Log;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AcompanhanteMenuActivity extends Activity implements OnClickListener{
	
	boolean eEdicao = true;
	Usuario usuarioLogado = new Usuario();
	

	
	private Button btnEditarPerfil;
	private Button btnExcluirPerfil;
	private Button btnAlterarStatus;
	private Button btnSair;
	private Button btnCadastrarServico;
	private Button btnCadastrarFoto;
	private Button btnEncontrosAprovados, btnEncontrosPendentes;
	private int idAcompanhanteExcluir;
	
	private TextView txtUsuarioLogado;	
	List<Object> listaobj = new ArrayList<Object>();
	Acompanhante acompanhanteRetorno, acompanhanteLogada;
	Repositorio repositorio = new Repositorio();
	Modelo modelo = new Modelo();
	Modelo modeloRetorno = new Modelo();
	Acompanhante acompExcluir = new Acompanhante();

	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_acompanhante);
		
		usuarioLogado =  (Usuario) getIntent().getSerializableExtra("usuarioLogado");
		adicionarFindView();			
		adicionarListers();
		this.txtUsuarioLogado.setText(usuarioLogado.getIdUsuario() + " - Olá, " + usuarioLogado.getEmail() + "!");
		
		//executarBuscarAcompanhante(usuarioLogado);
		
		Log.i("SOSTENES", "ID ACOMPANHANTE EXCLUIR: " + idAcompanhanteExcluir);
		
		
		
		// IMAGEM DE PERFIL POR URL - VAI SER ALTERADO 
		ImageView imgView =(ImageView)findViewById(R.id.imageView1);
		Drawable drawable = LoadImageFromWebOperations("http://www.imagensdahora.com.br/clipart/cliparts_imagens/01Animais//tubarao_06.gif");
		imgView.setImageDrawable(drawable);
		//
		
		
	}
	
		private void executarExcluirAcompanhantePorIdUsuario(Usuario usuarioLogado) {
			 
			 Usuario usuarioExcluir = new Usuario();
			 usuarioExcluir.setIdUsuario(usuarioLogado.getIdUsuario());
			 
			 listaobj.clear();
			 listaobj.add(usuarioExcluir);
			 
			 modelo.setDados(listaobj);
			 modelo.setMensagem("");
			 modelo.setStatus("");
			 
			 new excluirAcompanhanteAsyncTask().execute();
			 
		}
		
		private void executarBuscarAcompanhante(Usuario usuarioLogado){
			  
				listaobj.clear();
				acompExcluir.setId(usuarioLogado.getIdUsuario());
				listaobj.add(acompExcluir);
				 
				modelo.setDados(listaobj);
				modelo.setMensagem("");
				modelo.setStatus("");
	
				new buscarAcompanhantePorIdAsyncTask().execute();
			 
		}
	
		private void executarExcluirAcompanhante(int idAcompanhanteExcluir) {
			
			acompExcluir = new Acompanhante();
			acompExcluir.setId(idAcompanhanteExcluir);
			 
			listaobj.clear();
			listaobj.add(acompExcluir);
			modelo.setDados(listaobj);
			modelo.setMensagem("");
			modelo.setStatus("");
			
			new excluirAcompanhanteAsyncTask().execute();
			
		}

		private void adicionarListers() {
			this.btnCadastrarFoto.setOnClickListener(this);
			this.btnAlterarStatus.setOnClickListener(this);
			this.btnExcluirPerfil.setOnClickListener(this); 
			this.btnEditarPerfil.setOnClickListener(this); 
			this.btnEncontrosAprovados.setOnClickListener(this); 
			this.btnEncontrosPendentes.setOnClickListener(this); 
			this.btnSair.setOnClickListener(this); 
			this.btnCadastrarServico.setOnClickListener(this); 
			
	}

		private void adicionarFindView() {
			this.btnCadastrarFoto = (Button) findViewById(R.id.btnCadastrarFoto);
			this.btnEncontrosAprovados = (Button) findViewById(R.id.btnEncontrosApr);
			this.btnEncontrosPendentes = (Button) findViewById(R.id.btnEncontrosPnd);
			this.btnAlterarStatus = (Button) findViewById(R.id.btnAlterarStatus);
			this.btnEditarPerfil = (Button) findViewById(R.id.btnEditarPerfil);
			this.btnExcluirPerfil = (Button) findViewById(R.id.btnExcluirPerfil);
			this.btnSair = (Button) findViewById(R.id.btnSair);
			this.btnCadastrarServico = (Button) findViewById(R.id.btnCadastrarServico);
			this.txtUsuarioLogado = (TextView) findViewById(R.id.txtUsuarioLogadoAcomp);
	}

////// MÉTODO PARA CARREGAR URL - FUTURAMENTE SERÁ RETIRADO
		private Drawable LoadImageFromWebOperations(String url)
		  {
		      try
		      {
		          InputStream is = (InputStream) new URL(url).getContent();
		          Drawable d = Drawable.createFromStream(is, "src name");
		          return d;
		      }catch (Exception e) {
		          System.out.println("Exc="+e);
		          return null;
		      }
		  }
	

	
	// PASSAR O CLICK DOS BOTOES PARA ONCLIKLISTENER
	
	public void onClick(DialogInterface arg0, int arg1) {}
	public void onClick(View v) {
		Intent it = null;
		switch (v.getId()) {
		
		case R.id.btnCadastrarFoto:	
			it = new Intent(this, FotoAcompanhanteActivity.class);
			it.putExtra("usuarioLogado", usuarioLogado);
			startActivity(it);
			Toast.makeText(this, "Chamou Cadastrar Foto", Toast.LENGTH_LONG).show();
			break;
			
		case R.id.btnAlterarStatus:	
			it = new Intent(this, AlterarStatusActivity.class);
			startActivity(it);
			Toast.makeText(this, "Entrou no Alterar", Toast.LENGTH_LONG).show();
			break;
			
		case R.id.btnExcluirPerfil:
			Toast.makeText(this, "Entrou no Excluir", Toast.LENGTH_LONG).show();
			android.content.DialogInterface.OnClickListener trataDialog = new android.content.DialogInterface.OnClickListener() 
			{
				
				public void onClick(DialogInterface dialog, int which) 
				{
					 
					executarExcluirAcompanhantePorIdUsuario(usuarioLogado);
					
					
					//executarExcluirAcompanhante(idAcompanhanteExcluir);
					
				}

			};
			
			AlertDialog alert = new AlertDialog.Builder(this)
			.setTitle("Confirmação")
			.setMessage("Deseja realmente excluir?")
			.setPositiveButton("Sim", trataDialog)
			.setNegativeButton("Não", null).create();
		alert.show();
			
			
			break;
			
		case R.id.btnEditarPerfil:
			
			it = new Intent(this, CadastroAcompanhanteActivity.class);
			it.putExtra("usuarioLogado", usuarioLogado);
			it.putExtra("eEdicao", eEdicao);	
			startActivity(it);
			
			break;
		
		case R.id.btnEncontrosApr:
			
			it = new Intent(this, ListarEncontrosAprovados.class);
			it.putExtra("usuarioLogado", usuarioLogado);
			startActivity(it);
			
			break;
			
		case R.id.btnEncontrosPnd:
			
			it = new Intent(this, ListarEncontrosPendentes.class);
			it.putExtra("usuarioLogado", usuarioLogado);
			startActivity(it);
			
			break;
			
		case R.id.btnSair:
			Toast.makeText(this, "Saindo", Toast.LENGTH_LONG).show();
			finish();
			
			break;
			
		case R.id.btnCadastrarServico:
			it = new Intent(this, CadastroServicoActivity.class);
			it.putExtra("usuarioLogado", usuarioLogado);
			startActivity(it);
			
			break;
		}
}
	
	class excluirAcompanhanteAsyncTask extends AsyncTask<String, String, Modelo>  {
		ProgressDialog dialog;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(AcompanhanteMenuActivity.this,
					"Excluindo Perfil...", "Aguarde !",
					true, false);
		}

		@Override
		protected Modelo doInBackground(String... params) {	
			try
			{
				modeloRetorno = repositorio.acessarServidor("excluirAcompanhantePorIdUsuario", modelo);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			return modeloRetorno;
		}

		@Override
		protected void onPostExecute(Modelo result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if (modeloRetorno.getStatus().equals("1"))
			{
				Toast toast = Toast.makeText(AcompanhanteMenuActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();	
				finish();
				
			}else{									
				Intent it = new Intent(AcompanhanteMenuActivity.this, LogarAndroidActivity.class);
				startActivity(it);
				Toast toast = Toast.makeText(AcompanhanteMenuActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
				toast.show();
				finish();
			}	
		}
	}	

	class buscarAcompanhantePorIdAsyncTask extends AsyncTask<String, String, Modelo>  
	{
			ProgressDialog dialog;
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = ProgressDialog.show(AcompanhanteMenuActivity.this,
						"Carregando Dados...", "Aguarde...",
						true, false);
			}


			@Override
			protected Modelo doInBackground(String... params) 
			{
				try
				{
					modeloRetorno = repositorio.acessarServidor("buscarAcompanhantePorIdUsuario", modelo);
				
				} catch (Exception e) {				
					e.printStackTrace();
				}
				return modeloRetorno;
			}
		
			
			@Override
			protected void onPostExecute(Modelo result) {
				super.onPostExecute(result);
				dialog.dismiss();
				if (modeloRetorno.getStatus().equals("1"))
				{
					Toast toast = Toast.makeText(AcompanhanteMenuActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
					toast.show();
				}else{				
					Object dadosObject = modeloRetorno.getDados().get(0);
					JSONObject jsonObject = null;
					Gson gson = new Gson();
					
					try {
						jsonObject = new JSONObject(gson.toJson(dadosObject));
						
						Log.i("SOSTENES", "RETORNO PARA MONTAR NA TELA" + gson.toJson(dadosObject));
						
						
						idAcompanhanteExcluir = jsonObject.getInt("\u0000Acompanhante\u0000id");
						
//						acompanhanteLogada = new Acompanhante();
//						
//						acompanhanteLogada.setId(jsonObject.getInt("\u0000Acompanhante\u0000id"));
//						acompanhanteLogada.setIdUsuario(jsonObject.getInt("\u0000Acompanhante\u0000idUsuario"));
//						acompanhanteLogada.setNome(jsonObject.getString("\u0000Acompanhante\u0000nome"));
//						acompanhanteLogada.setIdade(jsonObject.getString("\u0000Acompanhante\u0000idade"));
//						acompanhanteLogada.setPeso(jsonObject.getString("\u0000Acompanhante\u0000peso"));
//						acompanhanteLogada.setBusto(jsonObject.getString("\u0000Acompanhante\u0000busto"));
//						acompanhanteLogada.setAltura(jsonObject.getString("\u0000Acompanhante\u0000altura"));
//						acompanhanteLogada.setCintura(jsonObject.getString("\u0000Acompanhante\u0000cintura"));
//						acompanhanteLogada.setQuadril(jsonObject.getString("\u0000Acompanhante\u0000quadril"));
//						acompanhanteLogada.setOlhos(jsonObject.getString("\u0000Acompanhante\u0000olhos"));
//						acompanhanteLogada.setPernoite(jsonObject.getInt("\u0000Acompanhante\u0000pernoite"));
//						acompanhanteLogada.setAtendo(jsonObject.getString("\u0000Acompanhante\u0000atendo"));									
//						acompanhanteLogada.setEspecialidade(jsonObject.getString("\u0000Acompanhante\u0000especialidade"));
//						acompanhanteLogada.setHorarioAtendimento(jsonObject.getString("\u0000Acompanhante\u0000horarioAtendimento"));
//						acompanhanteLogada.setEmail(jsonObject.getString("\u0000Acompanhante\u0000email"));
//						acompanhanteLogada.setSenha(jsonObject.getString("\u0000Acompanhante\u0000senha"));	
//						
					} catch (JSONException e) {
					}				
					
					Toast toast = Toast.makeText(AcompanhanteMenuActivity.this, modeloRetorno.getMensagem(), Toast.LENGTH_LONG);
					toast.show();
				
				}	
			}
			
		
	}

}
