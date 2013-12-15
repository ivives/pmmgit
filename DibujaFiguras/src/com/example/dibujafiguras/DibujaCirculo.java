package com.example.dibujafiguras;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DibujaCirculo extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dibujacirculo);
		
		final Button btnDibuja = (Button)findViewById(R.id.BotonDibuja);
		final EditText txtRadio = (EditText)findViewById(R.id.TxtRadio);
		
		
		//Implementamos el evento "click" del boton
		btnDibuja.setOnClickListener(new OnClickListener() {
							
			@Override
			public void onClick(View v) {
				//Creamos el intent
				Intent intent = new Intent(DibujaCirculo.this, Circulo.class);
							
				//Creamos la informacion a pasar entre actividades
				Bundle b = new Bundle();
				b.putString("Radio", txtRadio.getText().toString());
							
				//Añadimos la informacion al intent
				intent.putExtras(b);
									
				//Iniciamos la nueva actividad
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