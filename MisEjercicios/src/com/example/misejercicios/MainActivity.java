package com.example.misejercicios;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView lblMensaje;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 lblMensaje = (TextView)findViewById(R.id.LblMensaje);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.MenuOpc1:
			Intent intent = new Intent(MainActivity.this, ControlesBasicos.class);
			startActivity(intent);
			return true;
		case R.id.MenuOpc2:
			Intent intent1 = new Intent(MainActivity.this, ControlesSeleccion.class);
			startActivity(intent1);
			return true;
		case R.id.MenuOpc3:
			lblMensaje.setText("Opcion 3 pulsada");
			return true;	
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
}
