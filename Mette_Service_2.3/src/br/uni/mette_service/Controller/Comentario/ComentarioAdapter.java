package br.uni.mette_service.Controller.Comentario;

import java.util.List;

import br.uni.mette_service.R;
import br.uni.mette_service.Model.Acompanhante;
import br.uni.mette_service.Model.Comentario;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ComentarioAdapter extends BaseAdapter {

	private Context ctx;
	private List<Comentario> comentarioAdapter;
	
	public ComentarioAdapter(Context ctx, List<Comentario> comentarioAdapter) {
		this.ctx = ctx;
		this.comentarioAdapter = comentarioAdapter;
	}
	
	public int getCount() {
		return comentarioAdapter.size();
	}

	public Object getItem(int position) {
		return comentarioAdapter.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Comentario comentario = comentarioAdapter.get(position);
		
		if (convertView == null){
			convertView = LayoutInflater.from(ctx).inflate(
				R.layout.activity_linha_listarcomentarios, null);
		}
				
		TextView txtIdComentario = (TextView)convertView.
										findViewById(R.id.txtIdComentario);
		TextView txtIdCliente = (TextView)convertView.
							findViewById(R.id.txtIdCliente);
		TextView txtComentario = (TextView)convertView.
				findViewById(R.id.txtComentario);
		
						
		txtIdComentario.setText("Id Comentairo: "  + comentario.getId());
		txtIdCliente.setText("Id Cliente: " + comentario.getIdCliente());
		txtComentario.setText("Comentario: "+ comentario.getComentario());
		
					
		return convertView;
	}


}
