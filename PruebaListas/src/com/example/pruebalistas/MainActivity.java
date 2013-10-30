package com.example.pruebalistas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final String[] datos = new String[]{"Elem1", "Elem2", "Elem3", "Elem4"};
		
		//Añado una lista desplegable spinner
		Spinner spinner = (Spinner)findViewById(R.id.spinner);
				
		ArrayAdapter<String> adaptador = 
				new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, datos);
		
				
		spinner.setAdapter(adaptador);
		
		//Hacemos que muestre con un mensaje el elemento seleccionado
		final TextView lblMensaje = (TextView)findViewById(R.id.lblMensaje);
		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id){
				lblMensaje.setText("Seleccionado: " + datos[position]);
			}
			public void onNothingSelected(AdapterView<?> parent){
				
				lblMensaje.setText("");
			}
			
		});
			
		//Añado una lista fija
		ArrayAdapter<String> adapta = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
		
		final ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);
				
		lstOpciones.setAdapter(adapta);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
