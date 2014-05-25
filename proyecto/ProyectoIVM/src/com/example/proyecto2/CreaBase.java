package com.example.proyecto2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CreaBase extends SQLiteOpenHelper{

	public CreaBase(Context context, String name, CursorFactory factory, int version) {
		super(context, "dbase", factory, version);
		
	}

	//Sentencia SQL para crear la tabla de productos
    String sqlCreate = "create table Milista ("
    				+ "codigo text primary key not null, "
    				+ "descripcion text, "
    				+ "precio double, "
    				+ "cantidad int, "
    				+ "total double)";
		
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		
		db.execSQL(sqlCreate);
				       
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}