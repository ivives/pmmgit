package com.example.ejem_solobici_t2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button bJuego;
	private Button bConfigurar;
	private Button bAcercaDe;
	private Button bSalir;
	
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
		
		//Boton y escuchador para la pantalla de preferencias
		//Al hacer click en este boton llamamos al metodo lanzarConfigurar
		bConfigurar = (Button) findViewById(R.id.Boton02);
		bConfigurar.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				lanzarConfigurar();
			}
		});
		
		//Boton y escuchador para la pantalla de acerca de
		//Al hacer click en este boton llamamos al metodo lanzarAcercaDe
		bAcercaDe = (Button) findViewById(R.id.Boton03);
		bAcercaDe.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				lanzarAcercaDe();
			}
		});
		
		
		//Boton y escuchador para la pantalla de preferencias
		//Al hacer click en este boton llamamos al metodo lanzarConfigurar
		bSalir= (Button) findViewById(R.id.Boton04);
		bSalir.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				mostrarPreferencias();
			}
			
		});
		
	}
		//Metodo que activa la pantalla de juego
		public void lanzarJuego() {
			Intent i = new Intent (this, Juego.class);
			startActivity(i);
		}
		
		//Metodo que activa la pantalla de preferencias
		public void lanzarConfigurar(){
			Intent i = new Intent (this, Preferencias.class);
			startActivity(i);
		}
		
		
		//Metodo que activa la pantalla de acerca de
		public void lanzarAcercaDe() {
			Intent i = new Intent (this, AcercaDe.class);
			startActivity(i);
		}

		
		public void mostrarPreferencias(){
			SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
			String s = "Musica: " + pref.getBoolean("musica", true) + 
					"\nGraficos: " + pref.getString("preguntas", "");
			Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
			
			finish();
		}
		
		
		@Override
		protected void onStart() {
			super.onStart();
			Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
		}
		@Override
		protected void onResume() {
			super.onResume();
			Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
		}
		@Override
		protected void onPause() {
			Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
			super.onPause();
		}
		@Override
		protected void onStop() {
			super.onStop();
			Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
		}
		@Override
		protected void onRestart() {
			super.onRestart();
			Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
		}
		@Override
		protected void onDestroy() {
			Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
			super.onDestroy();
			}
		
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
