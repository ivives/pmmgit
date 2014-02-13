package com.example.dbprueba;


import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	 private TextView txtResultado; 
	 private SQLiteDatabase db;
	 private ListView lista;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtResultado = (TextView)findViewById(R.id.txtResultado);
		lista = (ListView)findViewById(R.id.lista);
		 
		//Abrimos la base de datos 'DBUsuarios' en modo escritura
	     SQLiteHelperPrueba usdbh =
	         new SQLiteHelperPrueba(this, "DBCentrosProfesores", null, 1);

	     db = usdbh.getWritableDatabase();
	     
	     
	     //Alternativa 1: método rawQuery()
         Cursor c = db.rawQuery("SELECT codigo, nombre FROM Centros", null);
                                 
           
         //Recorremos los resultados para mostrarlos en pantalla
         txtResultado.setText("");
         if (c.moveToFirst()) {
              //Recorremos el cursor hasta que no haya más registros
              do {
                   String cod = c.getString(0);
                   String nom = c.getString(1);
                   
                   txtResultado.append(" " + cod + " - " + nom + "\n");
              } while(c.moveToNext());
         }
	     
           
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
