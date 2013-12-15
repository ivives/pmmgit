package com.example.examenandroid;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class PantallaDos extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantallados);
		
		// Localizamos los controladores
		 TextView tarifa = (TextView)findViewById(R.id.LblTarifa);
    	 ImageView mapa = (ImageView)findViewById(R.id.Imagen);
    	 
    	 // recuperamos informacion del intent
    	 Bundle b = this.getIntent().getExtras();
    	 
    	 tarifa.setText(b.getString("Tarifa"));
    	 mapa.setImageResource(b.getInt("Mapa"));
    	 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
		        case R.id.MnuOpc1:
		        	Intent intent = new Intent(PantallaDos.this, Dibujo.class);
					startActivity(intent);
		            return true;
		        case R.id.MnuOpc2:
		        	//creamos un alerta emergente
		        	AlertDialog alertDialog;
		    		alertDialog = new AlertDialog.Builder(this).create();
		    		alertDialog.setTitle("Desarrollado por:");
		    		alertDialog.setMessage("Ignacio Vives Menor");
		    		alertDialog.show();
		            return true;
		        default:
		            return super.onOptionsItemSelected(item);
	        }
	    }

}