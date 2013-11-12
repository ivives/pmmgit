package com.example.dibujocasita;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path.FillType;
import android.graphics.Path;
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
			pincel.setStrokeWidth(10);
			//Establecemos el estilo del trazo
			pincel.setStyle(Style.STROKE);
			canvas.drawRect(100, 350, 400, 550, pincel);
			
			pincel.setColor(Color.DKGRAY);
			pincel.setStrokeWidth(15);
			pincel.setStyle(Style.STROKE);
			canvas.drawLine(100, 350, 253, 150, pincel);
			canvas.drawLine(400, 350, 247, 150, pincel);
			
			
			pincel.setColor(Color.RED);
			pincel.setStrokeWidth(10);
			pincel.setStyle(Style.FILL);
			canvas.drawRect(225, 470, 275, 550, pincel);
			
			
			pincel.setColor(Color.CYAN);
			pincel.setStrokeWidth(10);
			pincel.setStyle(Style.FILL);
			canvas.drawRect(150, 400, 200, 450, pincel);
			
			pincel.setColor(Color.CYAN);
			pincel.setStrokeWidth(10);
			pincel.setStyle(Style.FILL);
			canvas.drawRect(300, 400, 350, 450, pincel);
			
			
			pincel.setColor(Color.YELLOW);
			pincel.setStrokeWidth(10);
			pincel.setStyle(Style.FILL);
			canvas.drawCircle(250, 260, 25, pincel);
			
			pincel.setColor(Color.DKGRAY);
			pincel.setStrokeWidth(10);
			pincel.setStyle(Style.STROKE);
			canvas.drawLine(360, 300, 360, 200, pincel);
			canvas.drawLine(340, 270, 340, 200, pincel);
			canvas.drawLine(335, 200, 365, 200, pincel);
			
			
			Path path = new Path();
			path.setFillType(FillType.EVEN_ODD);
			
			path.moveTo(120, 280);
			path.lineTo(380, 280);
			path.lineTo(250, 140);
			path.lineTo(120, 280);
			path.close();
			canvas.drawPath(path, pincel);
			
					
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
