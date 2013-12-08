package com.example.tarifasavion;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	final String[] destinos = new String[]{"Elem1", "Elem2", "Elem3", "Elem4"};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, destinos);
		
		ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);
		
		lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
  			public void onItemClick(AdapterView<?> parent, android.view.View v, int position, long id){
  				
  				if (position == 0){
  					
  					Intent intent = new Intent(MainActivity.this, Tarifas.class);
  												
  					startActivityForResult(intent, 0);
  				}
  				if (position == 1){
  	  				
  	  				Intent intent = new Intent(MainActivity.this, Tarifas.class);
  	  				 								
  	  				startActivityForResult(intent, 0);
  	  			}
  				
  				if (position == 2){
  	  				
  	  				Intent intent = new Intent(MainActivity.this, Tarifas.class);
  	  				
  	  				startActivityForResult(intent, 0);
  	  			}
  				
  				if (position == 3){
  	  				
  	  				Intent intent = new Intent(MainActivity.this, Tarifas.class);
  	  				 								
  	  				startActivityForResult(intent, 0);
  	  			}
  			}	

			      			
  		});
		
		lstOpciones.setAdapter(adaptador);
		
	
//		//Localizar los controles
//		TextView txtTarifa = (TextView)findViewById(R.id.TxtTarifa);
				
//		//Recuperamos la informacion pasada en el intent
//		Bundle b = getIntent().getExtras();
		
//		String tipo = b.getString("TIPO");
//		String descuento = b.getString("DESCUENTO");
//		String precio = b.getString("PRECIO");
		
//		//Construimos el mensaje para mostrar
//		txtTarifa.setText("El vuelo contratado es: el que sea\n " + b.getString("TIPO") + b.getString("DESCUENTO") + "Precio total: " + b.getString("PRECIO"));
//		txtTarifa.setText("El vuelo contratado es: el que sea\n " + tipo + descuento + "Precio total: " + precio);
	}	
  
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
	    if (data != null) {
	    	
	    	
	        AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        builder.setMessage(data.getStringExtra("TIPO") + "Precio inicial: " + data.getStringExtra("PRECIOINI") + data.getStringExtra("DESCUENTO") + "Precio final: " + data.getStringExtra("PRECIOFIN"))
	        
	                .setTitle("El vuelo contratado es:")
	                .setCancelable(false)
	                .setNeutralButton("Aceptar",
	                        new DialogInterface.OnClickListener() {
	                            public void onClick(DialogInterface dialog, int id) {
	                                dialog.cancel();
	                            }
	                        });
	        AlertDialog alert = builder.create();
	        alert.show();
	    }
	}
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
