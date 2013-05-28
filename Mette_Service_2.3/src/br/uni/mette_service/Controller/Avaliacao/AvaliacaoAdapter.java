package br.uni.mette_service.Controller.Avaliacao;

import java.util.List;

import br.uni.mette_service.R;
import br.uni.mette_service.Model.AvaliacaoAcompanhante;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AvaliacaoAdapter extends BaseAdapter {

	private Context ctx;
	private List<AvaliacaoAcompanhante> avaliacaoAdapter;
	
	public AvaliacaoAdapter(Context ctx, List<AvaliacaoAcompanhante> avaliacaoAdapter) {
		this.ctx = ctx;
		this.avaliacaoAdapter = avaliacaoAdapter;
	}
	
	public int getCount() {
		return avaliacaoAdapter.size();
	}

	public Object getItem(int position) {
		return avaliacaoAdapter.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		AvaliacaoAcompanhante avaliacao = avaliacaoAdapter.get(position);
		
		if (convertView == null){
			convertView = LayoutInflater.from(ctx).inflate(
				R.layout.activity_linha_listaravaliacoes, null);
		}
				
		TextView txtIdAvaliacao = (TextView)convertView.
										findViewById(R.id.txtIdAvaliacao);
		TextView txtIdCliente = (TextView)convertView.
							findViewById(R.id.txtIdCliente);
		TextView txtNota = (TextView)convertView.
				findViewById(R.id.txtNota);
		
						
		txtIdAvaliacao.setText("Id Avaliacao: "  + avaliacao.getId());
		txtIdCliente.setText("Id Cliente: " + avaliacao.getIdCliente());
		txtNota.setText("Nota: "+ avaliacao.getNota());
		
					
		return convertView;
	}


}
