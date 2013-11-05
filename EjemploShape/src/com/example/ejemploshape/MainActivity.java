package com.example.ejemploshape;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new VistaAMedida(this));
	}
	ShapeDrawable[] formasDrawable = new ShapeDrawable[10];
	public class VistaAMedida extends View{
		private ShapeDrawable miDrawable;
		public VistaAMedida(Context contexto){
			super(contexto);
			int x = 5, y = 5;
			int ancho = 150, alto = 30;
			miDrawable = new ShapeDrawable(new OvalShape());
			miDrawable.getPaint().setColor(0xff0000ff);
			miDrawable.setBounds(x, y, x + ancho, y + alto);
			
			
			
			for (int i = 0; i < formasDrawable.length; i++){
				formasDrawable[i] = new ShapeDrawable(new OvalShape());
				formasDrawable[i].getPaint().setColor(0xff0000ff);
				formasDrawable[i].setBounds(5 * (x * (i+1)),10 * (y * (i+1)), 5 * (x * (i+1)) + ancho, 10 * (y * (i+1)) + alto);
			}
		}
		
		protected void onDraw(Canvas canvas){
//			miDrawable.draw(canvas);
			for (int a = 1; a < formasDrawable.length; a++){
				formasDrawable[a].draw(canvas);
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
