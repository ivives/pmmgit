package com.example.misejercicios;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View.OnClickListener;

public class ControlesBasicos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_controlesbasicos);
		
		final Button btnBoton1 = (Button)findViewById(R.id.BtnBoton1);
		final Button btnBoton2 = (Button)findViewById(R.id.BtnBoton2);
		final EditText txtNombre = (EditText)findViewById(R.id.TxtNombre);
		final Button btnHola = (Button)findViewById(R.id.BtnHola);
		
		
		//Evento boton 1
		btnBoton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// creamos el intent
				Intent intent = new Intent(ControlesBasicos.this, RadioButton.class);
				
				startActivity(intent);
				
			}
		});
		
		//Evento boton 2
		btnBoton2.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// creamos el intent
				Intent intent = new Intent(ControlesBasicos.this, CheckButton.class);
						
				startActivity(intent);
						
			}
		});
				
		//Implementamos el evento "click" del boton
		btnHola.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				//Creamos el intent
				Intent intent = new Intent(ControlesBasicos.this, FrmSaludo.class);
						
				//Creamos la informacion a pasar entre actividades
				Bundle b = new Bundle();
				b.putString("NOMBRE", txtNombre.getText().toString());
						
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