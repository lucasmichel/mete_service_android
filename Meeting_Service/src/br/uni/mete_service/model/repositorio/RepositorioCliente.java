package br.uni.mete_service.model.repositorio;

import java.util.ArrayList;
import java.util.List;

import br.uni.mete_service.model.Cliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RepositorioCliente {

	private AuxiliarRepositorioCliente helper;

	public RepositorioCliente(Context ctx) {
		helper = new AuxiliarRepositorioCliente(ctx);
	}

	public void inserir(Cliente cliente) {
		SQLiteDatabase db = helper.getWritableDatabase();

		db.insert("meteservice", null, obterParametros(cliente));

		db.close();
	}

	public void alterar(Cliente cliente) {
		SQLiteDatabase db = helper.getWritableDatabase();

		db.update("meteservice", obterParametros(cliente),
				"_id=" + cliente.getId(), null);

		db.close();
	}

	public void excluir(Cliente cliente) {
		SQLiteDatabase db = helper.getWritableDatabase();

		db.delete("meteservice", "_id=" + cliente.getId(), null);

		db.close();
	}

	private ContentValues obterParametros(Cliente cliente) {
		ContentValues parametros = new ContentValues();
		parametros.put("nome", cliente.getNome());
		parametros.put("cpf", cliente.getCpf());
		parametros.put("email", cliente.getEmail());
		parametros.put("telefone", cliente.getTelefone());
		parametros.put("senha", cliente.getSenha());

		return parametros;
	}

}