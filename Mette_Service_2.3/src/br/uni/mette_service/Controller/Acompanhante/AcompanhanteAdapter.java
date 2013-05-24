package br.uni.mette_service.Controller.Acompanhante;

import java.util.List;

import br.uni.mette_service.R;
import br.uni.mette_service.Model.Acompanhante;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AcompanhanteAdapter extends BaseAdapter {

	private Context ctx;
	private List<Acompanhante> acompanhanteAdapter;
	
	public AcompanhanteAdapter(Context ctx, List<Acompanhante> AcompAdapter) {
		this.ctx = ctx;
		this.acompanhanteAdapter = AcompAdapter;
	}
	
	public int getCount() {
		return acompanhanteAdapter.size();
	}

	public Object getItem(int position) {
		return acompanhanteAdapter.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Acompanhante acompanhante = acompanhanteAdapter.get(position);
		
		if (convertView == null){
			convertView = LayoutInflater.from(ctx).inflate(
				R.layout.activity_linha_acompanhante, null);
		}
				
		TextView txtNomeAcompanhante = (TextView)convertView.
										findViewById(R.id.txtNomeAcompanhante);
		TextView txtIdadeAcompanhante = (TextView)convertView.
							findViewById(R.id.txtIdadeAcompanhante);
		TextView txtEspecialidadesAcompanhante = (TextView)convertView.
				findViewById(R.id.txtEspecialidadesAcompanhante);
		
						
		txtNomeAcompanhante.setText("Nome: " + acompanhante.getNome());
		txtIdadeAcompanhante.setText("Idade: "+ acompanhante.getIdade());
		txtEspecialidadesAcompanhante.setText("Especialidade: " + acompanhante.getEspecialidade());
					
		return convertView;
	}


}
