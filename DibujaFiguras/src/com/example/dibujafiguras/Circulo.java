package com.example.dibujafiguras;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path.FillType;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;


public class Circulo extends Activity{
	
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
			
//			Float x = Float.parseFloat(b.getString("CoordenadaX"));
//			Float y = Float.parseFloat(b.getString("CoordenadaY"));
			Float r = Float.parseFloat(b.getString("Radio"));
			
			float area = (float) ((r * r) * Math.PI);
			String areaText = String.valueOf(area);
			String resultado = "El area del circulo es: " + areaText;
			
			DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			int screenWidth = dm.widthPixels;
			int screenHeight = dm.heightPixels;
			
			float x = screenWidth/2;
			float y = screenHeight/3;
			
			
			//Dentro de este metodo utilizamos los metodos para dibujar
			
			//Creamos un pincel con el que elegir color, trazo, estilo, etc.
			Paint pincel = new Paint();
			//Seleccionamos el color azul para el pincel
			pincel.setColor(Color.BLUE);
			//Establecemos el grosor del pincel
			pincel.setStrokeWidth(10);
			//Establecemos el estilo del trazo
			pincel.setStyle(Style.STROKE);
			canvas.drawCircle(x, y, r, pincel);
						
			float ay = screenHeight - 200;
			
			pincel.setStrokeWidth(1);
			pincel.setColor(Color.RED);
			pincel.setTextSize(20);
			pincel.setTextAlign(Align.CENTER);
			canvas.drawText(resultado, x, ay, pincel);
			//canvas.drawText(resultado, 200, 500, pincel);	

		}
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}



