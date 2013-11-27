package com.example.dibujafiguras;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	final String[] figuras = new String[]{"Circulo", "Cuadrado", "Ovalo"};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, figuras);
		
		ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);
		
		lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
  			public void onItemClick(AdapterView<?> parent, android.view.View v, int position, long id){
  				
  				if (position == 0){
  				
  				Intent intent = new Intent(MainActivity.this, DibujaCirculo.class);
  				 								
				startActivity(intent);
  				}
  				
  				if (position == 1){
  	  				
  	  				Intent intent = new Intent(MainActivity.this, DibujaCuadrado.class);
  	  				 								
  					startActivity(intent);
  	  				}
  				
  				if (position == 2){
  	  				
  	  				Intent intent = new Intent(MainActivity.this, DibujaOvalo.class);
  	  				 								
  					startActivity(intent);
  	  				}
  			}	

			      			
  		});
		
		
		
		
		lstOpciones.setAdapter(adaptador);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
