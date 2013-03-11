package com.example.meeting_service;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

public class ListarAcompanhanteActivity extends Activity{

List<Acompanhante> acomp;
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.detalhes_acomp);
	// criando acompanhantes para exemplo
//	
//	this.id = id;
//	this.nome = nome;
//	this.idade = idade;
//	this.altura = altura;
//	this.busto = busto;
//	this.cintura = cintura;
//	this.quadril = quadril;
//	this.olhos = olhos;
//	this.pernoite = pernoite;
//	this.atendo = atendo;
//	this.especialidade = especialidade;
//	this.horario_atentimento = horario_atentimento;
//	this.peso = peso;
//	this.foto = foto;
	
	acomp = new ArrayList<Acompanhante>();
    
    acomp.add(new Acompanhante(1,"JOANA",20,"1.80","80","60","50",
    		"azuis","50","tudo","sxo", "15:00", "60 kg",));
   
}
}

