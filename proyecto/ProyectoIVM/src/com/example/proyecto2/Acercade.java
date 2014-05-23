package com.example.proyecto2;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Acercade extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acercade);
		
		// Localizamos los controladores y les ponemos el tipo de letra
		Typeface fuente = Typeface.createFromAsset(getAssets(), "gloriahallelujah.ttf");
		final TextView acercade = (TextView)findViewById(R.id.acercade);
		acercade.setTypeface(fuente);
		
		Button volver = (Button)findViewById(R.id.VolverA);
		
		volver.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.exit(0);
				
			}
		});
		
		
		
	}

	

}
