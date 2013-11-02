package com.example.ejemplocanvas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new EjemploView(this));
	}

	public class EjemploView extends View{
		public EjemploView (Context contexto){
			super (contexto);
		}
		@Override
		protected void onDraw (Canvas canvas){
			//Dentro de este metodo utilizamos los metodos para dibujar
			
			//Creamos un pincel con el que elegir color, trazo, estilo, etc.
			Paint pincel = new Paint();
			//Seleccionamos el color azul para el pincel
			pincel.setColor(Color.BLUE);
			//Establecemos el grosor del pincel
			pincel.setStrokeWidth(15);
			//Establecemos el estilo del trazo
			pincel.setStyle(Style.STROKE);
			canvas.drawCircle(150, 150, 80, pincel);
			
			pincel.setColor(Color.RED);
			pincel.setStrokeWidth(15);
			pincel.setStyle(Style.STROKE);
			canvas.drawPoint(150, 150, pincel);
			
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
