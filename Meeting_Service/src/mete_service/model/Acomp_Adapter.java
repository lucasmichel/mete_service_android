package mete_service.model;

import java.util.List;

import mete_service.util.BitmapManager;

import com.example.meeting_service.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Acomp_Adapter extends ArrayAdapter<Acompanhante> {
	
	private List<Acompanhante> listaAcompanhante;
	
	public List<Acompanhante> getlistaAcompanhantes() {
		return listaAcompanhante;
	}
	
	public void setListaMenininhas(List<Acompanhante> listaAcompanhante) {
		this.listaAcompanhante = listaAcompanhante;
	}
	
	
	
	public Acomp_Adapter(Context context, List<Acompanhante> objects) {
		super(context,0 , objects);
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		Acompanhante acomp = getItem(position);
		
		convertView = LayoutInflater.from(getContext()).inflate(
				R.layout.linha_acomp,null);
		
		ImageView imgFoto = (ImageView) convertView.findViewById(R.id.imgFoto);
		TextView txtNome = (TextView) convertView.findViewById(R.id.textNome);
		TextView txtidade = (TextView) convertView.findViewById(R.id.textIdade);
		ImageView imgStatus = (ImageView) convertView
				.findViewById(R.id.imageStatus);

		txtNome.setText(acomp.getNome());
		txtidade.setText(acomp.getIdade());

		if (acomp.getStatus().equals("Ocupada")) {
			imgStatus.setImageResource(R.drawable.ocupada);
		} else if (acomp.getStatus().equals("Livre")) {
			imgStatus.setImageResource(R.drawable.livre);
		}

		BitmapManager.getInstance().loadBitmap(acomp.getFoto(), imgFoto);
		
		
		
		
		return convertView;
		
	}

}
