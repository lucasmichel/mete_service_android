package mete_service.model.repositorio;

import java.util.ArrayList;
import java.util.List;

import mete_service.model.Cliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RepositorioCliente {
	private AuxiliarCliente helper;

	public RepositorioCliente(Context ctx) {
		helper = new AuxiliarCliente(ctx);
	}

	public void inserir(Cliente cliente) {
		SQLiteDatabase db = helper.getWritableDatabase();

		db.insert("car", null, obterParametros(cliente));

		db.close();
	}

	public void alterar(Car c) {
		SQLiteDatabase db = helper.getWritableDatabase();

		db.update("car", obterParametros(c), "_id=" + c.getId(), null);

		db.close();
	}

	public void excluir(Car c) {
		SQLiteDatabase db = helper.getWritableDatabase();

		db.delete("car", "_id=" + c.getId(), null);

		db.close();
	}

	private ContentValues obterParametros(Car c) {
		ContentValues parametros = new ContentValues();
		parametros.put("nome", c.getNome());
		parametros.put("ano", c.getAno());
		parametros.put("placa", c.getPlaca());
		return parametros;
	}

	public List<Car> listar() {
		List<Car> lista = new ArrayList<Car>();

		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from car order by nome", null);

		while (cursor.moveToNext()) {
			long id = cursor.getLong(0);
			String nome = cursor.getString(1);
			String placa = cursor.getString(cursor.getColumnIndex("placa"));
			String ano = cursor.getString(cursor.getColumnIndex("ano"));

			Car c = new Car(id, nome, placa, ano);
			lista.add(c);
		}
		cursor.close();
		db.close();
		return lista;
	}
}
