package com.example.ejemplocanvas2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	private BitmapDrawable miImagen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new EjemploView(this));
	}
	
	public class EjemploView extends View{
		public EjemploView (Context contexto){
			super(contexto);
			Resources res = contexto.getResources();
			miImagen = (BitmapDrawable) res.getDrawable(R.drawable.logo_cefire);
			miImagen.setBounds(new Rect(30,30,200,200));
			setBackgroundResource(R.drawable.degradado);
		}
		
		@Override
		protected void onDraw(Canvas canvas){
			//Dentro de este metodo utilizamos los metodos para dibujar
			//BitmapDrawable
			miImagen.draw(canvas);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
