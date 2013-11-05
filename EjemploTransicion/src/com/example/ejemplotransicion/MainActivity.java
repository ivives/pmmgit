package com.example.ejemplotransicion;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageView imagen = new ImageView(this);
		setContentView(imagen);
		
		TransitionDrawable miTransicion = (TransitionDrawable)
				getResources().getDrawable(R.drawable.transicion);
		imagen.setImageDrawable(miTransicion);
		miTransicion.startTransition(2000);
			
	}
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
