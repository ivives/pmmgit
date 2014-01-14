package com.example.examenandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path.FillType;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class Dibujo extends Activity {

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
			
//			//Creamos un pincel con el que elegir color, trazo, estilo, etc.
			Paint pincel = new Paint();
						
			//dibujo los ojos
			pincel.setColor(Color.CYAN);
			pincel.setStrokeWidth(2);
			pincel.setStyle(Style.STROKE);
			canvas.drawCircle(375, 340, 10, pincel);
			canvas.drawCircle(425, 340, 10, pincel);
			
			//dibujo la boca
			pincel.setColor(Color.RED);
			pincel.setStrokeWidth(2);
			pincel.setStyle(Style.STROKE);
			canvas.drawArc(new RectF(360,380,440,420), 0, 180, false, pincel);
			
		
			
			//Seleccionamos el color azul para el pincel
			pincel.setColor(Color.GRAY);
			//Establecemos el grosor del pincel
			pincel.setStrokeWidth(10);
			//Establecemos el estilo del trazo
			pincel.setStyle(Style.STROKE);
			canvas.drawOval(new RectF(320, 260, 480, 460), pincel);
			
			
			//dibujo el sombrero
			Path path2 = new Path();
			path2.setFillType(FillType.EVEN_ODD);
			pincel.setColor(Color.BLUE);
			pincel.setStrokeWidth(10);
			pincel.setStyle(Style.FILL_AND_STROKE);
			path2.moveTo(300, 300);
			path2.lineTo(500, 300);
			path2.lineTo(400, 140);
			path2.lineTo(300, 300);
			path2.close();
			canvas.drawPath(path2, pincel);
			
			//Dibujo brazos y piernas
			pincel.setColor(Color.GRAY);
			pincel.setStrokeWidth(20);
			pincel.setStyle(Style.FILL);
			canvas.drawLine(370, 730, 370, 930, pincel);
			
			pincel.setColor(Color.GRAY);
			pincel.setStrokeWidth(20);
			pincel.setStyle(Style.FILL);
			canvas.drawLine(430, 730, 430, 930, pincel);
				
			pincel.setColor(Color.GRAY);
			pincel.setStrokeWidth(15);
			pincel.setStyle(Style.FILL);
			canvas.drawLine(355, 500, 260, 600, pincel);
			
			pincel.setColor(Color.GRAY);
			pincel.setStrokeWidth(15);
			pincel.setStyle(Style.FILL);
			canvas.drawLine(445, 500, 550, 600, pincel);
			
			//dibujo el cuerpo
			Path path = new Path();
			path.setFillType(FillType.EVEN_ODD);
			pincel.setColor(Color.YELLOW);
			pincel.setStrokeWidth(10);
			path.moveTo(360, 460);
			path.lineTo(440, 460);
			path.lineTo(490, 730);
			path.lineTo(310, 730);
			path.lineTo(360, 460);
			path.close();
			canvas.drawPath(path, pincel);

			//escribo en el canvas
			pincel.setStrokeWidth(1);
			pincel.setColor(Color.RED);
			pincel.setTextSize(20);
			pincel.setTextAlign(Align.CENTER);
			canvas.drawText("Nuestro uniforme personal", 400, 960, pincel);
			
		}
	}
	
	
}
