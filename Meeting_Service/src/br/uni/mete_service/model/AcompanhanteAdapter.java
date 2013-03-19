package br.uni.mete_service.model;

import java.util.List;


import br.uni.mete_service.R;
import br.uni.mete_service.util.BitmapManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AcompanhanteAdapter extends ArrayAdapter<Acompanhante> {
	
	private List<Acompanhante> listaMenininhas;

	public List<Acompanhante> getListaMenininhas() {
		return listaMenininhas;
	}

	public void setListaMenininhas(List<Acompanhante> listaMenininhas) {
		this.listaMenininhas = listaMenininhas;
	}

	public AcompanhanteAdapter(Context context, List<Acompanhante> objects) {
		super(context, 0, objects);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Acompanhante acomp = getItem(position);

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.linha_acomp, null);
		}

		ImageView imgFoto = (ImageView) convertView.findViewById(R.id.imgFoto);
		TextView txtNome = (TextView) convertView.findViewById(R.id.textNome);
		TextView txtidade = (TextView) convertView.findViewById(R.id.textIdade);
		TextView txtEspecialidade = (TextView) convertView.findViewById(
				R.id.textEspecialidades);
		ImageView imgStatus = (ImageView) convertView
				.findViewById(R.id.imageStatus);

		txtNome.setText(acomp.getNome());
		txtidade.setText(acomp.getIdade());
		txtEspecialidade.setText(acomp.getEspecialidade());

		if (acomp.getStatus().equals("ocupada")) {
			imgStatus.setImageResource(R.drawable.ocupada);
		} else if (acomp.getStatus().equals("livre")) {
			imgStatus.setImageResource(R.drawable.livre);
		}

//		BitmapManager.getInstance().loadBitmap(menina.getFoto(), imgFoto);

		return convertView;
	}

}
