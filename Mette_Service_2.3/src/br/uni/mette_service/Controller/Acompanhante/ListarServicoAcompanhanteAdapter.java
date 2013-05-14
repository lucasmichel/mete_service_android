package br.uni.mette_service.Controller.Acompanhante;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Servico.ServicoAcompanhante;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ListarServicoAcompanhanteAdapter extends BaseAdapter {

	private Context ctx;
	private List<ServicoAcompanhante> ServicoAcompanhanteAdapter;
	
	public ListarServicoAcompanhanteAdapter(
					Context ctx, List<ServicoAcompanhante> ServicoAcompanhanteAdapter) {
		
		this.ctx = ctx;
		this.ServicoAcompanhanteAdapter = ServicoAcompanhanteAdapter;
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return ServicoAcompanhanteAdapter.size();
	}

	public Object getItem(int position) {
		return ServicoAcompanhanteAdapter.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private String toString1(InputStream is) throws IOException {

		byte[] bytes = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int lidos;
		while ((lidos = is.read(bytes)) > 0) {
			baos.write(bytes, 0, lidos);
		}
		return new String(baos.toByteArray());
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ServicoAcompanhante servicoAcompanhanteAdapter = ServicoAcompanhanteAdapter.get(position);
		
		if (convertView == null){
			convertView = LayoutInflater.from(ctx).inflate(
				R.layout.lista_servico_acomp_adapter, null);
		}
		TextView txtNomeAcompanhante = (TextView)convertView.
				findViewById(R.id.txtListaServicoAdapater);
		
		System.out.println("PEDRO    "+servicoAcompanhanteAdapter.getId());
		
		txtNomeAcompanhante.setText(String.valueOf(servicoAcompanhanteAdapter.getId()));
		
		
		
		return convertView;
	}
	
}
