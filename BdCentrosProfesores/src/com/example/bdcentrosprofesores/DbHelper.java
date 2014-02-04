package com.example.bdcentrosprofesores;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	private static int version = 1;
	private static String name = "DbCentrosProfesores" ;
	private static CursorFactory factory = null;
	
	public DbHelper(Context context) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		// CREAMOS LA TABLA CENTROS	
		db.execSQL( "CREATE TABLE CENTROS(" +
		          " cod_centro SMALLINT PRIMARY KEY," +
		          " tipo_centro CHAR(1), " +
		          " nombre VARCHAR(30), " +
		          " direccion VARCHAR(26)," +
		          " telefono VARCHAR(10)," +
		          " num_plazas SMALLINT UNSIGNED, " +
		          " PRIMARY KEY (cod_centro))" );
		
		// CREAMOS LA TABLA PERSONAL
		db.execSQL( "CREATE TABLE PERSONAL(" +
		          " cod_centro SMALLINT NOT NULL," +
		          " dni INT UNSIGNED NOT NULL , " +
		          " apellidos VARCHAR(30), " +
		          " funcion VARCHAR(15)," +
		          " salario FLOAT(7,2)" +
		          " PRIMARY KEY (dni)," +
		          " FOREING KEY (cod_centro) REFERENCES centros (cod_centro))" );
		
		// CREAMOS LA TABLA PROFESORES
		db.execSQL( "CREATE TABLE PROFESORES(" +
		          " cod_centro SMALLINT NOT NULL," +
		          " dni INT UNSIGNED NOT NULL , " +
		          " apellidos VARCHAR(30), " +
		          " especialidad VARCHAR(16)," +
		          " PRIMARY KEY (dni)," +
		          " FOREING KEY (dni) REFERENCES personal (dni)," +
		          " FOREING KEY (cod_centro) REFERENCES centros (cod_centro))");
		
		
		//INSERTAMOS DATOS EN LAS TABLAS
		
		
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
