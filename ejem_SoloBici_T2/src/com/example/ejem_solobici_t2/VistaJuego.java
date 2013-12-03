package com.example.ejem_solobici_t2;

import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
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
	
	// RUEDA //
	private Grafico rueda;
	private static int VELOCIDAD_RUEDA = 12;
	private boolean ruedaActiva;
	private int distanciaRueda;
		
	// THREAD Y TIEMPO  //
	//Hilo encargado de procesar el tiempoe
	private HiloJuego hiloJuego;
	//Tiempo que debe transcurrir para procesar cambios (ms)
	private static int PERIODO_PROCESO = 50;
	//Momento en el que realizo el ultomo proceso
	private long ultimoProceso = 0;
	
	//controlar si la aplicacion esta en segundo plano
	private boolean corriendo = false;
	//controlar si la aplicacion esta en pausa
	private boolean pausa;
	
	
	public VistaJuego(Context contexto, AttributeSet atributos) {
		super (contexto, atributos);
		Drawable graficoBici, graficoCoche, graficoRueda;
		///Obtenemos la imagen/recurso del coche
		graficoCoche = contexto.getResources().getDrawable(R.drawable.coche);
		graficoRueda = contexto.getResources().getDrawable(R.drawable.rueda);
	
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
		
			// RUEDA 
			//DIBUJO RUEDA
//			  ShapeDrawable dRueda = new ShapeDrawable(new RectShape());
//			  dRueda.getPaint().setColor(Color.WHITE);
//			  dRueda.getPaint().setStyle(Style.STROKE);
//			  dRueda.setIntrinsicWidth(15);
//			  dRueda.setIntrinsicHeight(3);
//			  graficoRueda = dRueda;
			  
			  rueda = new Grafico(this, graficoRueda);
			  ruedaActiva = false;
				
			//CONTROL DEL HILO DEL JUEGO
			  corriendo = true;
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
		
		//dibujamos la rueda si lo indica la variable ruedaActiva
		if (ruedaActiva)
			rueda.dibujaGrafico(canvas);
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
		
		//Movemos la rueda
		if(ruedaActiva){
			rueda.incrementaPos();
			distanciaRueda--;
			if (distanciaRueda < 0){
				ruedaActiva = false;
			}else {
				for (int i=0; i < Coches.size(); i++){
					if (rueda.verificacionColision(Coches.elementAt(i))){
						destruyeCoche(i);
					}
				}
			}
		}
	}
	
	private void destruyeCoche(int i) {
		Coches.remove(i);
		ruedaActiva = false;
		
	}
	
	private void lanzarRueda() {
		rueda.setPosX(bici.getPosX() + bici.getAncho()/2 - rueda.getAncho()/2);
		rueda.setPosY(bici.getPosY() + bici.getAlto()/2 - rueda.getAlto()/2);
		rueda.setAngulo(bici.getAngulo());
		rueda.setIncX(Math.cos(Math.toRadians(bici.getAngulo())) + VELOCIDAD_RUEDA);
		rueda.setIncY(Math.sin(Math.toRadians(bici.getAngulo())) + VELOCIDAD_RUEDA);
		distanciaRueda = (int)Math.min(
				this.getWidth() / Math.abs(rueda.getIncX()),
				this.getHeight() / Math.abs(rueda.getIncY())) -2;
		ruedaActiva = true;
	}

	HiloJuego getHilo(){
		return hiloJuego;
	}
	
	public void setCorriendo(boolean corriendo){
		this.corriendo = corriendo;
	}
	
	public void setPausa(boolean pausa){
		this.pausa = pausa;
		
	}
	
	
	public class HiloJuego extends Thread {
		
		private boolean pausa,corriendo;
		public synchronized void pausar() {
			pausa = true;
		}
		public synchronized void reanudar() {
			pausa = false;
			notify();
		}
		public void detener() {
			corriendo = false;
			if (pausa) reanudar();
		}
		
		@Override
		public void run() {
			corriendo = true;
			while (corriendo) {
				actualizaMovimiento();
				synchronized (this) {
					while (pausa) {
						try {
							wait();
						} catch (Exception e) {
						}
					}
				}
			} // del while
		} //del metodo run
	} //de la clase HiloJuego
	
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
				lanzarRueda();
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
		//obtenemos la posicion de la pulsacion
		float x = evento.getX();
		float y = evento.getY();
		switch (evento.getAction()){
		//si comienza una pulsacion (ACTION_DOWN) activamos la variable disparo
		case MotionEvent.ACTION_DOWN:
			disparo = true;
			break;
		//comprobamos si la pulsacion es continuada con un desplazamiento horizontal o vertical
		//en caso de ser asi, desactivamos disparo porque se tratara de un movimiento
		//en lugar de un disparo
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
				lanzarRueda();
			}
			break;
		}
		mX = x;
		mY = y;
		return true;
	}
	
	// REGISTRO DE SENSORES
	SensorManager miSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
	@SuppressWarnings("deprecation")
	List<Sensor> listaSensores = miSensorManager.getSensorList(Sensor.TYPE_ORIENTATION);{
	if (!listaSensores.isEmpty()){
		Sensor sensorOrientacion = listaSensores.get(0);
		miSensorManager.registerListener(this, sensorOrientacion, SensorManager.SENSOR_DELAY_UI);
	}
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
		
	private boolean hayValorInicial = false;
	private float valorInicial;

	@Override
	public void onSensorChanged(SensorEvent evento) {
		float valor = evento.values[1];
		if (!hayValorInicial){
			valorInicial = valor;
			hayValorInicial = true;
		}
		giroBici = (int) (valor - valorInicial)/3;
	}


	
}	
	

