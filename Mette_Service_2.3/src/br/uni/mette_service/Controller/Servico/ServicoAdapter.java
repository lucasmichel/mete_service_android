package br.uni.mette_service.Controller.Servico;

import java.util.List;


import br.uni.mette_service.R;
import br.uni.mette_service.Model.Servico;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ServicoAdapter extends BaseAdapter {
	
	private Context ctx;
	private List<Servico> servicoAdapter;
	
	public ServicoAdapter(Context ctx, List<Servico> servicoAdapter) {
		this.ctx = ctx;
		this.servicoAdapter = servicoAdapter;
	}
	
	public int getCount() {
		return servicoAdapter.size();
	}

	public Object getItem(int position) {
		return servicoAdapter.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Servico servico = servicoAdapter.get(position);
		
		if (convertView == null){
			convertView = LayoutInflater.from(ctx).inflate(
				R.layout.activity_linha_servico, null);
		}
				
		TextView txtNome = (TextView)convertView.findViewById(R.id.txtNomeListarServicos);
						
		txtNome.setText(servico.getNome());
					
		return convertView;
	}

}