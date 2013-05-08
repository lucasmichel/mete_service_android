package br.uni.mette_service.Controller.Acompanhante;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import br.uni.mette_service.R;
import br.uni.mette_service.Model.Foto;
import br.uni.mette_service.Model.Usuario;
import br.uni.mette_service.Model.Repositorio.Modelo;
import br.uni.mette_service.Model.Repositorio.Repositorio;

import com.google.gson.Gson;

public class FotoAcompanhanteActivity extends Activity implements OnClickListener {
	
	private final Context context = this;
	private static final int SELECT_PICTURE = 1;
	private Button btnEnviar;
	private ImageButton imageView;
	private TextView txtArquivo;
	private Usuario usuarioLogado = new Usuario();
	Modelo modelo = new Modelo();
	List<Object> listaFoto = new ArrayList();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foto);

		usuarioLogado =  (Usuario) getIntent().getSerializableExtra("usuarioLogado");
		
		adicionarFindView();
		adicionarListers();
		
		Toast.makeText(this, usuarioLogado.getIdUsuario() + "", Toast.LENGTH_LONG).show();
	}
	
	private void adicionarFindView() {
		this.txtArquivo = (TextView) findViewById(R.id.txt);
		this.imageView = (ImageButton) findViewById(R.id.IMAGEM);
		this.btnEnviar = (Button) findViewById(R.id.enviarArquivo);
	}	
	private void adicionarListers() {
		this.imageView.setOnClickListener(this);		
		this.btnEnviar.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.IMAGEM:
			Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(i, SELECT_PICTURE);
			break;
		case R.id.enviarArquivo:
			try {
				
				String nomeDoCaminho = txtArquivo.getText().toString();
				
				Foto foto = new Foto();
				//foto.setId(usuarioLogado.getIdUsuario());
				foto.setId(11);
				foto.setNome(nomeDoCaminho);
				
				listaFoto.add(foto);
				
				modelo.setStatus("");
				modelo.setMensagem("");
				modelo.setDados(listaFoto);
								
				executeMultipartPost("cadastrarFoto", modelo);
				
				Toast.makeText(getBaseContext(), "clicou : 2",Toast.LENGTH_SHORT).show();
				//DIALOG
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setTitle("Enviar Fotos");
				alertDialogBuilder.setMessage("Deseja enviar mais fotos!").setCancelable(false)
				.setPositiveButton("N�o",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										FotoAcompanhanteActivity.this.finish();}})
				.setNegativeButton("Sim",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										dialog.cancel();
										imageView.setImageDrawable(null);
										txtArquivo.setText("");
										}});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
				//FIM DO DIALOG
			} catch (Exception e) {
				Toast.makeText(getBaseContext(),"error enviarArquivo : " + e.getMessage(),Toast.LENGTH_LONG).show();
			}
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {

		case SELECT_PICTURE:
			if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && null != data) {
				Uri selectedImageUri = data.getData();
				txtArquivo.setText(getPath(selectedImageUri));
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(selectedImageUri,filePathColumn, null, null, null);
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);
				cursor.close();
				Bitmap yourSelectedImage = BitmapFactory.decodeFile(picturePath);
				imageView.setImageBitmap(yourSelectedImage);

			}
		}
	}
	
	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = getContentResolver().query(uri, projection, null, null,	null);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}
	
	public void executeMultipartPost(String acao, Modelo modelo) throws Exception {
		Repositorio repositorio = new Repositorio();
		String caminhoDoArquivoNoDispositivo = txtArquivo.getText().toString();
		String urlDoServidor = "http://leonardogalvao.com.br/TesteAndroid/index.php";
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****"; // Delimitador
		byte[] buffer;
		int bytesRead, bytesAvailable, bufferSize;
		int maxBufferSize = 1 * 1024 * 1024; // 1MB

		try {
			repositorio.acessarServidor(acao, modelo);
			// Criando conexão com o servidor
			URL url = new URL(urlDoServidor);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// Conexão vai ler e escrever dados
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			// Setando método POST
			connection.setRequestMethod("POST");
			// Adicionando cabeçalhos
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);
			// Escrevendo payload da requisição
			DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
			outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			outputStream.writeBytes("Content-Disposition: form-data; "+ "name=\"uploadedfile\";filename=\""+ caminhoDoArquivoNoDispositivo + "\"" + lineEnd);
			outputStream.writeBytes(lineEnd);
			// Stream para ler o arquivo
			FileInputStream fileInputStream = new FileInputStream(new File(caminhoDoArquivoNoDispositivo));
			// Preparando para escrever arquivo
			bytesAvailable = fileInputStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			buffer = new byte[bufferSize];
			// Lendo arquivo e escrevendo na conexão
			bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			while (bytesRead > 0) {
				outputStream.write(buffer, 0, bufferSize);
				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			}
			outputStream.writeBytes(lineEnd);
			outputStream.writeBytes(twoHyphens + boundary + twoHyphens+ lineEnd);
			// Obtendo o codigo e a mensagem de resposta do servidor
			int serverResponseCode = connection.getResponseCode();
			String serverResponseMessage = connection.getResponseMessage();
			Toast.makeText(getBaseContext(),"serverResponse : " + serverResponseCode + " = " + serverResponseMessage, Toast.LENGTH_LONG).show();
			fileInputStream.close();
			outputStream.flush();
			outputStream.close();
		} catch (Exception ex) {
			// Exception handling
		}
	}
}