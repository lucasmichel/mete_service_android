package br.uni.mette_service.Controller.Encontro;

import java.util.ArrayList;

import br.uni.mette_service.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class AdapterLinhaEncontro extends BaseAdapter {


	private OnClickListener onClick1 = null;		
	private LayoutInflater mInflater;
    private ArrayList<LinhaEncontro> linhas;

    
    public AdapterLinhaEncontro(Context context, ArrayList<LinhaEncontro> linhas) {
        //Itens que preencheram o listview
        this.linhas = linhas;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }
    
    
    public AdapterLinhaEncontro(LayoutInflater mInflater, OnClickListener onClick1) {
    		super();
    		this.onClick1 = onClick1;
    }
    
    
    
	public int getCount() {
        return linhas.size();
    }

	public LinhaEncontro getItem(int position) {
        return linhas.get(position);
    }
	
	public long getItemId(int position) {
        return position;
    }

	public View getView(int position, View view, ViewGroup parent) {
	
		 //Pega o item de acordo com a posção.
		LinhaEncontro item = linhas.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.linha_encontro, null);
 
        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.encontro_txtAcompanhante)).setText(item.getTexto());              
        Button buttonExcluir =  (Button) view.findViewById(R.id.encontro_btnExcluir);
        buttonExcluir.setOnClickListener(onClick1);
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.encontro_ratingBar1);
        
		return view;
	}

}
