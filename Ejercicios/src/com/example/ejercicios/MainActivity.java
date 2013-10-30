package com.example.ejercicios;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button btnBoton1 = (Button)findViewById(R.id.BtnBoton1);
		final Button btnBoton2 = (Button)findViewById(R.id.BtnBoton2);
		
		
		//Evento boton 1
		btnBoton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// creamos el intent
				Intent intent = new Intent(MainActivity.this, RadioButton.class);
				
				startActivity(intent);
				
			}
		});
		
		//Evento boton 2
				btnBoton2.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// creamos el intent
						Intent intent = new Intent(MainActivity.this, CheckButton.class);
						
						startActivity(intent);
						
					}
				});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
