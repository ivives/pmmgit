package com.example.ejem_solobici_t2;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class Grafico {
	private Drawable drawable; //Imagen que dibujaremos
	private double posX, posY; //Posicion en la pantalla
	private double incX, incY; //Velocidad de desplazamiento
	private int angulo, rotacion; //Angulo y velocidad de rotacion
	private int ancho, alto; //Dimensiones de la imagen
	private int radioColision; //Para determinar si chocamos con algun objeto
	//Vista donde dibujamos el grafico
	private View view;
	//Para determinar el espacio a borrar
	public static final int MAX_VELOCIDAD = 20;
	
	//Iniciamos los atributos de esta clase
	public Grafico(View view, Drawable drawable){
		this.view = view;
		this.drawable = drawable;
		ancho = drawable.getIntrinsicWidth();
		alto = drawable.getIntrinsicHeight();
		radioColision = (alto + ancho) / 4;
	}
	
	//Dibujamos el grafico en su posicion actual
	public void dibujaGrafico(Canvas canvas){
		canvas.save();
		int x = (int) (posX + ancho / 2);
		int y = (int) (posY + alto / 2);
		canvas.rotate((float) angulo, (float) x, (float) y);
		drawable.setBounds((int) posX, (int) posY, (int) posX * ancho, (int) posY * alto);
		drawable.draw(canvas);
		canvas.restore();
		int rInval = (int) distanciaE(0,0, ancho, alto) / 2 + MAX_VELOCIDAD;
		view.invalidate(x - rInval, y - rInval, x + rInval, y + rInval);
	};
	
	//Correcciones de posicion en caso de que el grafico se salga de la pantalla
	//En estos casos aparecemos por el otro lado de la pantalla
	public void incrementaPos(){
		posX += incX;
		//Si salimos de la pantalla, corregira la posicion
		if (posX < -ancho / 2){
			posX = view.getWidth() - ancho /2;
		}
		if (posX > view.getWidth() - ancho /2){
			posX = -ancho / 2;
		}
		posY += incY;
		//Si salimos de la pantalla, corregira la posicion
		if (posY < -alto / 2){
			posY = view.getHeight() - alto /2;
		}
		if (posY > view.getHeight() - alto /2){
			posY = -alto / 2;
		}
		angulo += rotacion; //Actualizamos el angulo
	}
	
	//Nos devuelve la distancia entre los objetos Grafico
	public double distancia(Grafico g){
		return distanciaE(posX, posY, g.posX, g.posY);
	}
	
	//Nos devuelve si se produce o no colision entre objetos
	public boolean verificacionColision(Grafico g){
		return (distancia(g) < (radioColision + g.radioColision));
	}

}
