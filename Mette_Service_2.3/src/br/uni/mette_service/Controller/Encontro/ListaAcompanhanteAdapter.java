package br.uni.mette_service.Controller.Encontro;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Servico.ServicoAcompanhante;
import br.uni.mette_service.Model.Acompanhante;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListaAcompanhanteAdapter extends BaseAdapter {

	private Context ctx;
	private List<Acompanhante> acompanhante;
	
	public ListaAcompanhanteAdapter(
					Context ctx, List<Acompanhante> ListaAcompanhanteAdapter) {
		
		this.ctx = ctx;
		this.acompanhante = ListaAcompanhanteAdapter;
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return acompanhante.size();
	}

	public Object getItem(int position) {
		return acompanhante.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private String toString1(InputStream is) throws IOException {

		byte[] bytes = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int lidos;
		while ((lidos = is.read(bytes)) > 0) {
			baos.write(bytes, 0, lidos);
		}
		return new String(baos.toByteArray());
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Acompanhante acompanhanteAtual = acompanhante.get(position);
		
		if (convertView == null){
			convertView = LayoutInflater.from(ctx).inflate(
				R.layout.activity_linha_acompanhante_encontro, null);
		}
		TextView txtNomeNomeAdpater = (TextView)convertView.
				findViewById(R.id.txt_linha_acompanhante_encontro);
				
		txtNomeNomeAdpater.setText(acompanhanteAtual.getNome());		
		
		return convertView;
	}
	
}