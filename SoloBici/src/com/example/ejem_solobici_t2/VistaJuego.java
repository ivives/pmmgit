package com.example.ejem_solobici_t2;

import java.util.Vector;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class VistaJuego extends View{
	// COCHES  //
	private Vector<Grafico> Coches; //Vector con los coches
	private int numCoches = 5;		//Numero inicial de coches
	private int numMoteos = 3;		//Fragmentos/Motos en que se dividira un coche
	
	// BICI  //
	private Grafico bici;
	private int giroBici;			//Incremento en la direccion de la bici
	private float aceleracionBici;	//Aumento de la velocidad dela bici
	private static final int PASO_GIRO_BICI = 5;
	private static final float PASO_ACELERACION_BICI = 0.5f;
	
	// THREAD Y TIEMPO  //
	//Hilo encargado de procesar el tiempo
	private HiloJuego hiloJuego;
	//Tiempo que debe transcurrir para procesar cambios (ms)
	private static int PERIODO_PROCESO = 50;
	//Momento en el que realizo el ultomo proceso
	private long ultimoProceso = 0;
	
	public VistaJuego(Context contexto, AttributeSet atributos) {
		super (contexto, atributos);
		Drawable graficoBici, graficoCoche, graficoRueda;
		///Obtenemos la imagen/recurso del coche
		graficoCoche = contexto.getResources().getDrawable(R.drawable.coche);
		
		//Creamos un vector para contener todos los coches que iran por la pantalla
		//y lo rellenamos con graficos de coches
		// con valores aleatoriso para su velocidad, direccion y rotacion
		Coches = new Vector<Grafico>();
		for (int i = 0; i < numCoches; i ++) {
			Grafico coche = new Grafico(this, graficoCoche);
			coche.setIncX(Math.random() * 4 - 2);
			coche.setIncY(Math.random() * 4 - 2);
			coche.setAngulo((int) (Math.random() * 360));
			coche.setRotacion((int) (Math.random() * 8 - 4));
			Coches.add(coche);
		}
	}

	//Al comenzar y dibujar por primera vez la pantalla de juego
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		super.onSizeChanged(w, h, oldw, oldh);
		
		//Dibujamos los coches en posiciones aleatorias
		for (Grafico coche: Coches){
			do {
				coche.setPosX(Math.random() * (w - coche.getAncho()));
				coche.setPosY(Math.random() * (h - coche.getAlto()));
			}while (coche.distancia(bici) < (w + h)/5);
		}
		//Hilo que controla el juego
		hiloJuego = new HiloJuego();
		hiloJuego.start();
		
	}
	
	@Override
	protected void onDraw (Canvas canvas){
		super.onDraw(canvas);
		//Dibujamos cada uno de los coches
		for (Grafico coche: Coches) {
			coche.dibujaGrafico(canvas);
		}
	}
	
	protected synchronized void actualizaMovimiento() {
		long ahora = System.currentTimeMillis();
		//No hacemos nada si el periodo de proceso no se ha cumplido
		if (ultimoProceso + PERIODO_PROCESO > ahora) {
			return;
		}
		//Para la ejecucion en tiempo real calculamos retardo
		double retardo = (ahora - ultimoProceso) / PERIODO_PROCESO;
		//Actualizamos la posicion de la bici
		bici.setAngulo((int) (bici.getAngulo() + giroBici * retardo));
		double nIncX = bici.getIncX() + aceleracionBici *
				Math.cos(Math.toRadians(bici.getAngulo())) * retardo;
		double nIncY = bici.getIncY() + aceleracionBici *
				Math.sin(Math.toRadians(bici.getAngulo())) * retardo;
		if (Grafico.distanciaE(0, 0, nIncX, nIncY) <= Grafico.getMaxVelocidad()){
			bici.setIncX(nIncX);
			bici.setIncY(nIncY);
		}
		bici.incrementaPos();
		
		//Movemos los coches
		for (Grafico coche: Coches){
			coche.incrementaPos();
		}
		ultimoProceso = ahora;
	}
	
	private class HiloJuego extends Thread {
		@Override
		public void run() {
			while (true) {
				actualizaMovimiento();
			}
		}
	}
	
	
}
