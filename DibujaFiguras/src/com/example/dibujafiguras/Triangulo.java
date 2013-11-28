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
import android.view.Menu;
import android.view.View;

public class Triangulo extends Activity{

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
			Float x3 = Float.parseFloat(b.getString("CoordenadaX3"));
			Float y3 = Float.parseFloat(b.getString("CoordenadaY3"));
			
			float abx = x2 - x1;
			float aby = y2 - y1;
			float acx = x3 - x1;
			float acy = y3 - y1;
			float nabx = y2 - y1;
			float naby = -(x2 - x1);
			
			
			float area = ((nabx * acx) + (naby * acy))/2;
			String areaText = String.valueOf(area);
			String resultado = "El area del triangulo es: " + areaText;
			
			//Dentro de este metodo utilizamos los metodos para dibujar
			
			//Creamos un pincel con el que elegir color, trazo, estilo, etc.
			Paint pincel = new Paint();
			Path path = new Path();
			path.setFillType(FillType.EVEN_ODD);
			//Seleccionamos el color azul para el pincel
			pincel.setColor(Color.BLUE);
			//Establecemos el grosor del pincel
			pincel.setStrokeWidth(10);
			//Establecemos el estilo del trazo
			pincel.setStyle(Style.STROKE);
			path.moveTo(x1, y1);
			path.lineTo(x2, y2);
			path.lineTo(x3, y3);
			path.lineTo(x1, y1);
			path.close();
			canvas.drawPath(path, pincel);
			
			int b = canvas.getHeight();
			int a = canvas.getWidth();
			
			pincel.setStrokeWidth(1);
			pincel.setColor(Color.RED);
			pincel.setTextSize(20);
			pincel.setTextAlign(Align.CENTER);
			canvas.drawText(resultado, a/2, b-50, pincel);
			
			
			
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}