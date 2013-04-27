package br.uni.mette_service.Controller.Servico;

import java.util.List;


import br.uni.mette_service.R;
import br.uni.mette_service.Model.Servico;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ServicoAdapter extends ArrayAdapter<Servico> {
	
	public ServicoAdapter(Context context, List<Object> list) {
		super(context, 0);
	}
	

	public View getView(int position, View convertView, ViewGroup parent) {
		Servico servico = getItem(position);
		
		if (convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(
				R.layout.linha_servico, null);
		}
				
		TextView txtNome = (TextView)convertView.findViewById(R.id.txtNome);
		TextView txtId = (TextView)convertView.findViewById(R.id.txtId);
						
		txtNome.setText(servico.getNome());
		txtId.setText(servico.getId());
					
		return convertView;
	}

}