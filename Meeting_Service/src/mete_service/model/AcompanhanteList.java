package mete_service.model;

import java.util.ArrayList;
import java.util.List;


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
