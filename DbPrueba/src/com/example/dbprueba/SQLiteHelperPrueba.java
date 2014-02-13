package com.example.dbprueba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelperPrueba extends SQLiteOpenHelper {

	//Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE Centros (codigo INTEGER, nombre TEXT)";
	
	
	public SQLiteHelperPrueba(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
        
        
        db.execSQL("INSERT INTO Centros VALUES (10,'IES El Quijote')");
        db.execSQL("INSERT INTO Centros VALUES (15,'CP Los Danzantes')");
		db.execSQL("INSERT INTO Centros VALUES (22,'IES Planeta Tierra')");
		db.execSQL("INSERT INTO Centros VALUES (45,'CP Manuel Hidalgo')");
		db.execSQL("INSERT INTO Centros VALUES (50,'IES Antoñete 1')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Centros");
 
        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
	}

}
