package com.example.ejem_solobici_t2;

import java.util.List;
import java.util.Vector;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class VistaJuego extends View implements SensorEventListener{
	// COCHES  //
	private Vector<Grafico> Coches; //Vector con los coches
	private int numCoches = 5;		//Numero inicial de coches
	private int numMotos = 3;		//Fragmentos/Motos en que se dividira un coche
	
	// BICI  //
	private Grafico bici;
	private int giroBici;			//Incremento en la direccion de la bici
	private float aceleracionBici;	//Aumento de la velocidad de la bici
	private static final int PASO_GIRO_BICI = 5;
	private static final float PASO_ACELERACION_BICI = 0.5f;
		
	// THREAD Y TIEMPO  //
	//Hilo encargado de procesar el tiempoe
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
		// con valores aleatorios para su velocidad, direccion y rotacion
		Coches = new Vector<Grafico>();
		for (int i = 0; i < numCoches; i ++) {
			Grafico coche = new Grafico(this, graficoCoche);
			coche.setIncX(Math.random() * 4 - 2);
			coche.setIncY(Math.random() * 4 - 2);
			coche.setAngulo((int) (Math.random() * 360));
			coche.setRotacion((int) (Math.random() * 8 - 4));
			Coches.add(coche);
		}
		
		///Obtenemos la imagen/recurso de la bici
			graficoBici = contexto.getResources().getDrawable(R.drawable.bici);
			bici = new Grafico(this, graficoBici);
//			bici.setPosX(50);
//			bici.setPosY(100);
		
	}

	//Al comenzar y dibujar por primera vez la pantalla de juego
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		super.onSizeChanged(w, h, oldw, oldh);
		
		//Dibujamos los coches en posiciones aleatorias
		for (Grafico coche: Coches){
			do {
				coche.setPosX(Math.random() * (w-coche.getAncho()));
				coche.setPosY(Math.random() * (h-coche.getAlto()));
			}while (coche.distancia(bici) < (w + h)/5);
		}
		bici.setPosX((w-bici.getAncho())/2);
		bici.setPosY((h-bici.getAlto())/2);
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
		
		bici.dibujaGrafico(canvas);
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
		double nIncX = bici.getIncX() + aceleracionBici * Math.cos(Math.toRadians(bici.getAngulo())) * retardo;
		double nIncY = bici.getIncY() + aceleracionBici * Math.sin(Math.toRadians(bici.getAngulo())) * retardo;
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
	
	public boolean onKeyDown (int codigoTecla, KeyEvent evento) {
		super.onKeyDown(codigoTecla, evento);
		//procesamos la pulsacion
		boolean pulsacion = true;
		switch (codigoTecla){
			case KeyEvent.KEYCODE_DPAD_UP:
				aceleracionBici =+ PASO_ACELERACION_BICI;
				break;
			case KeyEvent.KEYCODE_DPAD_LEFT:
				giroBici =- PASO_GIRO_BICI;
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				giroBici =+ PASO_GIRO_BICI;
				break;
			case KeyEvent.KEYCODE_DPAD_CENTER:
			case KeyEvent.KEYCODE_ENTER:
				//lanzarRueda();
				break;
			default:
				//si estamos aqui no hemos pulsado nada que nos interese
				pulsacion = false;
				break;
		}
		
		return pulsacion;
		
	}
	
	
	//PANTALLA TACTIL //
	//las variables mX y mY se utilizaran para recortar
	//las coordenadas del ultimo evento
	private float mX = 0, mY = 0;
	private boolean disparo = false;
	
	@Override
	public boolean onTouchEvent(MotionEvent evento) {
		super.onTouchEvent(evento);
		//obtenemos la posicio de la pulsacion
		float x = evento.getX();
		float y = evento.getY();
		switch (evento.getAction()){
		//si comienza una pulsacion (ACTION_DOWN) activamos la variable disparo
		case MotionEvent.ACTION_DOWN:
			disparo = true;
			break;
		//comprobamos si la pulsacion es continuada con un desplazamiento horizontal o vertical
		//en caso de ser asi, desactivamos disparo porque se tratara de un movimiento
		//en llugar de un disparo
		case MotionEvent.ACTION_MOVE:
			float dx = Math.abs(x-mX);
			float dy = Math.abs(y-mY);
			if (dy < 6 && dx > 6) //un desplazamiento del dedo horizontal hace girar la bici
			{
				giroBici = Math.round((x-mX)/2);
				disparo = false;
			} else if (dx < 6 && dy > 6) //un desplazamiento vertical produce aceleracion
				{
					aceleracionBici = Math.round((mY-y)/25);
					disparo = false;
				
				}
			break;
		//si se levanta el dedo (ACTION_UP) sin haberse producido desplazamiento horizontal o vertical
		//disparo estara activado y lo que hacemos es disparar
		case MotionEvent.ACTION_UP:
			giroBici = 0;
			aceleracionBici = 0;
			if (disparo){
				//ActivarRueda();
			}
			break;
		}
		mX = x;
		mY = y;
		return true;
	}
	
	// REGISTRO DE SENSORES
	SensorManager miSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
	List<Sensor> listaSensores = miSensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
	if (!listaSensores.isEmpty()){
		Sensor sensorOrientacion = listaSensores.get(0);
		miSensorManager.registerListener(this, sensorOrientacion, SensorManager.SENSOR_DELAY_UI);
	}
	
	
}
