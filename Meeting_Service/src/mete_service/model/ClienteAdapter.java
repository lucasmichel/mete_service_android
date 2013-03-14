package mete_service.model;
import java.util.List;
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

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return listCliente.get(0).getId();
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		Cliente carro = (Cliente) getItem(position);
		return convertView;
	}

}
