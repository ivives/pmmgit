package org.ejemplo.aguilar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ClientesSQLiteHelper extends SQLiteOpenHelper {
	//Cadena con la sentencia SQL que permite crear la tabla Clientes
	String cadSQL = "CREATE TABLE Clientes (codigo INTEGER, nombre TEXT, telefono TEXT)";
	
	public ClientesSQLiteHelper(Context contexto, String nombre, CursorFactory almacen, int version){
		super(contexto, nombre, almacen, version);
	}
	
	//Este método se ejecuta automáticamente cuando sea necesaria la creación de la base de datos.
	//Es decir, se ejecutará cuando la base de datos todavía no exista.
	//Aquí deben crearse todas las tablas necesarias e insertar los datos iniciales si es necesario.
	@Override
	public void onCreate(SQLiteDatabase bd) {
		//Ejecutamos la sentencia SQL para crear la tabla Clientes
		//El método execSQL se limita a ejecutar directamente el código SQL que le pasemos.
		bd.execSQL(cadSQL);
	}
	
	//Este método se lanza automáticamente cuando es necesaria una actualización de la estructura 
	//de la base de datos o una conversión de los datos.
	@Override
	public void onUpgrade(SQLiteDatabase bd, int versionAnterior, int versionNueva) {
		//NOTA: Para simplificar este ejemplo eliminamos la tabla anterior y la creamos de nuevo
		//		con el nuevo formato.
		//		Lo normal sería realizar una migración o traspaso de los datos de la tabla antigua
		//		a la nueva, con la consiguiente complicación que ello conlleva.
		
		//Eliminamos la versión anterior de la tabla
		bd.execSQL("DROP TABLE IF EXISTS Clientes");
		
		//Creamos la nueva versión de la tabla
		bd.execSQL(cadSQL);
	}
}
