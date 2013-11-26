package com.example.ejem_solobici_t2;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Juego extends Activity {
	
	private VistaJuego vistaJuego;
	MediaPlayer miMediaPlayer;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.juego);
		vistaJuego = (VistaJuego) findViewById(R.id.VistaJuego);
		//musica de fondo
		miMediaPlayer = MediaPlayer.create(this, R.raw.audio);
		miMediaPlayer.start();
	}
	
	@Override
	protected void onDestroy(){
		//al poner la variable corriendo a false permitimos que el thread pueda acabar
		vistaJuego.setCorriendo(false);
		super.onDestroy();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		//ponemos el thread e suspension
		vistaJuego.setPausa(true);
		miMediaPlayer.pause();
		
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		//continuamos ejecutando el thread
		vistaJuego.setPausa(false);
		miMediaPlayer.release();
	}

}
