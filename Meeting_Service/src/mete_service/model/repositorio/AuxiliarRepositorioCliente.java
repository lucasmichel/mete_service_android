package mete_service.model.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AuxiliarRepositorioCliente extends SQLiteOpenHelper{

	private static final String NOME_BANCO = "cliente";
	private static final int VERSAO_DO_BANCO = 2;
	
	public AuxiliarRepositorioCliente(Context context) {
		super(context,NOME_BANCO,null,VERSAO_DO_BANCO);
	}
	
	
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table cliente (_id long primary key " +
				"autoincrement, nome text not null, cpf text, " +
				"telefone text, email text, senha text)");
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
