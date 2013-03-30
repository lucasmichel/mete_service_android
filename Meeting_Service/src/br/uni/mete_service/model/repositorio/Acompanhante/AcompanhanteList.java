package br.uni.mete_service.model.repositorio.Acompanhante;

import java.util.ArrayList;
import java.util.List;

import br.uni.mete_service.model.Acompanhante;


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
}
