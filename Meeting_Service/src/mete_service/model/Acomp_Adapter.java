package mete_service.model;

import java.util.List;

import com.example.meeting_service.R;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
		Acompanhante acompanhante = getItem(position);
		
		convertView = LayoutInflater.from(getContext()).inflate(
				R.layout.linha_acomp,null);
		
		
		
		
		return convertView;
		
	}

}
