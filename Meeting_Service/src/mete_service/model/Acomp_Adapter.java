package mete_service.model;

import java.util.List;

import br.edu.unibratec.Proxenetarecife.UTIL.BitmapManager;



import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Acomp_Adapter extends ArrayAdapter<Acompanhante>{

	private List<Acompanhante> listaAcompanhante;
	
	public List<Acompanhante> getListaAcompanhantes() {
		return listaAcompanhante;
	}

	public void setListaAcompanhantes(List<Acompanhante> listaAcompanhantes) {
		this.listaAcompanhante = listaAcompanhantes;
	}

	
	public Acomp_Adapter(Context context, List<Acompanhante> objects) {
		super(context,0, objects);
		
	}
//
//	@Override
//	public int getCount() {
//		return listaAcompanhante.size();
//	}
//
//	@Override
//	public Acompanhante getItem(int position) {
//		return listaAcompanhante.get(position);
//	}
//
//	@Override
//	public long getItemId(int po) {
//		return 0;
//	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Acompanhante acompanhante = getItem(position);
		
		ImageView imgFoto = (ImageView) convertView.findViewById(R.id.imgFoto);
		TextView txtNome = (TextView) convertView.findViewById(R.id.txtNOME);
		TextView txtidade = (TextView) convertView.findViewById(R.id.txtIDADE);
		ImageView imgStatus = (ImageView) convertView
				.findViewById(R.id.imgStatus);

		txtNome.setText(menina.getNome());
		txtidade.setText(menina.getIdade());

		if (menina.getOcupada().equals("Ocupada")) {
			imgStatus.setImageResource(R.drawable.ocupada);
		} else if (menina.getOcupada().equals("Livre")) {
			imgStatus.setImageResource(R.drawable.livre);
		}

		BitmapManager.getInstance().loadBitmap(menina.getFoto(), imgFoto);

		return convertView;
	}
		return null;
	}

}
