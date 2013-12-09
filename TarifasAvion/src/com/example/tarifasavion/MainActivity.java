package com.example.tarifasavion;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	final String[] destinos = new String[]{"Elem1", "Elem2", "Elem3", "Elem4"};
	TextView txtTarifa;
	public static final int CODIGO_RESPUESTA = 123;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtTarifa = (TextView)findViewById(R.id.TxtTarifa);
		
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, destinos);
		
		ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);
		
		lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
  			public void onItemClick(AdapterView<?> parent, android.view.View v, int position, long id){
  				
  				if (position == 0){
  					
  					Intent intent = new Intent(MainActivity.this, Tarifas.class);
  						
  					//Creamos la informacion a pasar entre actividades
  					Bundle b = new Bundle();
  					b.putString("TRAYECTO", "Madrid_Valencia");
  					//Añadimos la informacion al intent
  					intent.putExtras(b);
  					  					
  					startActivityForResult(intent, CODIGO_RESPUESTA);
  				}
  				if (position == 1){
  	  				
  	  				Intent intent = new Intent(MainActivity.this, Tarifas.class);
  	  				
  	  				Bundle b = new Bundle();
					b.putString("TRAYECTO", "Madrid_Barcelona");
					intent.putExtras(b);
  	  				
  	  				startActivityForResult(intent, CODIGO_RESPUESTA);
  	  			}
  				
  				if (position == 2){
  	  				
  	  				Intent intent = new Intent(MainActivity.this, Tarifas.class);
  	  				
  	  				startActivityForResult(intent, CODIGO_RESPUESTA);
  	  			}
  				
  				if (position == 3){
  	  				
  	  				Intent intent = new Intent(MainActivity.this, Tarifas.class);
  	  				 								
  	  				startActivityForResult(intent, CODIGO_RESPUESTA);
  	  			}
  			}	

			      			
  		});
		
		lstOpciones.setAdapter(adaptador);

	}	
  
	protected void onActivityResult(int requestCode,int resultCode, Intent pData)            
    {
        if ( requestCode == CODIGO_RESPUESTA )//Si el código de respuesta es igual al requestCode
            {
            if (resultCode == Activity.RESULT_OK )//Si resultCode es igual a ok
                {
                    final String dato = pData.getExtras().getString(Tarifas.DATO_SUBACTIVIDAD );//Obtengo el string de la subactividad
                    //Aquí se hara lo que se desee con el valor recuperado
                    txtTarifa.setText(dato);//El valor recogido en dato es un string que será mostrado en el TextView de la actividad principal
                }
            }
    }
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
