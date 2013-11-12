package com.example.ejem_solobici_t2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button bJuego;
//	private Button bAcercaDe;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
				
		//Boton y escuchador para la pantalla de juego
		//Al hacer click en este boton llamamos al metodo lanzar juego
		bJuego = (Button) findViewById(R.id.Boton01);
		bJuego.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				lanzarJuego();
			}
		});
		
		
//		//Boton y escuchador para la pantalla de acreca de
//		//Al hacer click en este boton llamamos al metodo lanzar juego
//		bAcercaDe = (Button) findViewById(R.id.Boton03);
//		bAcercaDe.setOnClickListener(new OnClickListener() {
//			public void onClick(View view) {
//				lanzarAcercaDe();
//			}
//		});
		
	}
		//Metodo que activa la pantalla de juego
		public void lanzarJuego() {
			Intent i = new Intent (this, Juego.class);
			startActivity(i);
		}
		
//		//Metodo que activa la pantalla de acerca de
//		public void lanzarAcercaDe() {
//			Intent i = new Intent (this, AcercaDe.class);
//			startActivity(i);
//		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
