package br.uni.mette_service.Controller.Encontro;

import java.util.List;

import br.uni.mette_service.R;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Comentario;
import br.uni.mette_service.Model.Encontro;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EncontroAdapter extends BaseAdapter {

	private Context ctx;
	private List<Encontro> encontroAdapter;
	
	public EncontroAdapter(Context ctx, List<Encontro> encontroAdapter) {
		this.ctx = ctx;
		this.encontroAdapter = encontroAdapter;
	}
	
	public int getCount() {
		return encontroAdapter.size();
	}

	public Object getItem(int position) {
		return encontroAdapter.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Encontro encontro = encontroAdapter.get(position);
		
		if (convertView == null){
			convertView = LayoutInflater.from(ctx).inflate(
				R.layout.activity_linha_listarencontros, null);
		}
				
//		TextView txtIdComentario = (TextView)convertView.
//										findViewById(R.id.txtIdComentario);
//		TextView txtIdCliente = (TextView)convertView.
//							findViewById(R.id.txtIdCliente);
//		TextView txtComentario = (TextView)convertView.
//				findViewById(R.id.txtComentario);
//		
//						
//		txtIdComentario.setText("Id Comentairo: "  + comentario.getId());
//		txtIdCliente.setText("Id Cliente: " + comentario.getIdCliente());
//		txtComentario.setText("Comentario: "+ comentario.getComentario());
		
					
		return convertView;
	}


}
