package com.example.dibujafiguras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DibujaOvalo extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dibujaovalo);
		
		final Button btnDibuja = (Button)findViewById(R.id.BotonDibuja);
		final EditText txtDiam = (EditText)findViewById(R.id.TxtDiam);
		final EditText txtDiaM = (EditText)findViewById(R.id.TxtDiaM);
		
		
				
		//Implementamos el evento "click" del boton
		btnDibuja.setOnClickListener(new OnClickListener() {
							
			@Override
			public void onClick(View v) {
				//Creamos el intent
				Intent intent = new Intent(DibujaOvalo.this, Ovalo.class);
							
				//Creamos la informacion a pasar entre actividades
				Bundle b = new Bundle();
				b.putString("DIAm", txtDiam.getText().toString());
				b.putString("DIAM", txtDiaM.getText().toString());
				
							
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
