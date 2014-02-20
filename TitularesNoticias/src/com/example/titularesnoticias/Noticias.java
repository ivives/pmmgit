package com.example.titularesnoticias;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Noticias extends Activity{

	private Conexion conexion;
	private SQLiteDatabase db;
	private String[] datos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noticias);
		conexion=new Conexion(this,"DBNoticias",null,1);
		db=conexion.getReadableDatabase();
		leer();
		ListView lista=(ListView)findViewById(R.id.lista);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, datos);
		lista.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void leer(){
		String[] campos = new String[] {"noticia"};

        Cursor c = db.query("Noticias", campos, null, null, null, null, null);
        datos=new String[c.getCount()];
        System.out.println(c.getCount());
        int i=0;
        System.out.println("hola");
        if (c.moveToFirst()) {
        	System.out.println("hol2");
                do {
                		
                		String noticia=c.getString(0);                      
                        System.out.println(noticia);
                        datos[i]=noticia;
                        i++;
                        
                } while (c.moveToNext());
        }
        db.close();
	}

}
