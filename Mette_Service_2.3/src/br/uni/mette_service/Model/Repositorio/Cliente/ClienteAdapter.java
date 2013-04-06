package br.uni.mette_service.Model.Repositorio.Cliente;
import java.util.List;

import br.uni.mette_service.Model.Cliente;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ClienteAdapter extends BaseAdapter {

	private Context context;
	private List<Cliente> listCliente;

	public ClienteAdapter(Context context) {
		this.context = context;
	}

	public void setListClientes(List<Cliente> listClientes) {
		this.listCliente = listClientes;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return listCliente.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listCliente.get(position);
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		Cliente cliente = (Cliente) getItem(position);
		return convertView;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
