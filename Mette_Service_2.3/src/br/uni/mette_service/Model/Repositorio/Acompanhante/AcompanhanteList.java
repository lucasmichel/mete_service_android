package br.uni.mette_service.Model.Repositorio.Acompanhante;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import br.uni.mette_service.Model.Acompanhante;



public class AcompanhanteList {

	private List<Acompanhante> results;

	public AcompanhanteList() {
		results = new ArrayList<Acompanhante>();
	}

	public void setResults(List<Acompanhante> results) {
		this.results = results;
	}

	public List<Acompanhante> getResults() {
		return results;
	}
	
//	public AcompanhanteList listarAcompanhante(AcompanhanteList acompanhanteList) throws JSONException {
//		RepositorioAcompanhante bd = RepositorioAcompanhante.getInstance();
//		return bd.listarAcompanhante(this);
//	}	
	
}
