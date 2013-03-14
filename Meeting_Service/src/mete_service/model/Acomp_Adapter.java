package mete_service.model;

import java.util.List;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class Acomp_Adapter extends ArrayAdapter {
	
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
		return null;
		
	}

}
