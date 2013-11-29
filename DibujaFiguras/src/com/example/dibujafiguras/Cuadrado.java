package com.example.dibujafiguras;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


public class Cuadrado extends Activity{

	Bundle b;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new EjemploView(this));
	
		//Recuperamos la informacion pasada en el intent
		b = this.getIntent().getExtras();
	}
			
	public class EjemploView extends View{
		public EjemploView (Context contexto){
			super (contexto);
		}
		@Override
		protected void onDraw (Canvas canvas){
			
			Float x1 = Float.parseFloat(b.getString("CoordenadaX"));
			Float y1 = Float.parseFloat(b.getString("CoordenadaY"));
			Float x2 = Float.parseFloat(b.getString("CoordenadaX2"));
			Float y2 = Float.parseFloat(b.getString("CoordenadaY2"));
			
			float area = ((x2 - x1) * (y2 - y1));
			String areaText = String.valueOf(area);
			String resultado = "El area del cuadrado es: " + areaText ;
			
			//Dentro de este metodo utilizamos los metodos para dibujar
			
			//Creamos un pincel con el que elegir color, trazo, estilo, etc.
			Paint pincel = new Paint();
			//Seleccionamos el color azul para el pincel
			pincel.setColor(Color.BLUE);
			//Establecemos el grosor del pincel
			pincel.setStrokeWidth(10);
			//Establecemos el estilo del trazo
			pincel.setStyle(Style.STROKE);
			canvas.drawRect(x1, y1, x2, y2, pincel);
												
			int b = canvas.getHeight();
			int a = canvas.getWidth();
			
			pincel.setStrokeWidth(1);
			pincel.setColor(Color.RED);
			pincel.setTextSize(20);
			pincel.setTextAlign(Align.CENTER);
			//canvas.drawText(resultado, a/2, b-50, pincel);
			canvas.drawText(resultado, 200, 500, pincel);
			
			
			
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}