package com.example.misejercicios;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class ListButtonPantallaDos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listbuttonpantallados);
		
		// Localizamos los controladores
		 TextView titulo = (TextView)findViewById(R.id.LblTitu);
    	 TextView subtitulo = (TextView)findViewById(R.id.LblSubTitu);
    	 TextView fecha = (TextView)findViewById(R.id.LblFech);
    	 ImageView foto = (ImageView)findViewById(R.id.Imag);
    	 
    	 // recuperamos informacion del intent
    	 Bundle b = this.getIntent().getExtras();
    	 
    	 titulo.setText(b.getString("Titulo"));
    	 subtitulo.setText(b.getString("Subtitulo"));
    	 fecha.setText(b.getString("Fecha"));
    	 foto.setImageResource(b.getInt("Foto"));
    	 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pantalla_dos, menu);
		return true;
	}

}