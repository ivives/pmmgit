package com.example.examenandroid;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class PantallaDos extends Activity{

	AnimationDrawable animaci;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantallados);
		
		// Localizamos los controladores
		 TextView tarifa = (TextView)findViewById(R.id.LblTarifa);
    	 ImageView mapa = (ImageView)findViewById(R.id.Imagen);
    	 TextView cambio = (TextView)findViewById(R.id.LblCambio);
//    	 ImageView imagen = (ImageView)findViewById(R.id.Trans);
    	 
    	 // recuperamos informacion del intent
    	 Bundle b = this.getIntent().getExtras();
    	 
    	 tarifa.setText(b.getString("Tarifa"));
    	 mapa.setImageResource(b.getInt("Mapa"));
    	 
    	 double precio = b.getDouble("Precio");
    	 
    	 int ent=(int)precio;
    	 double decima = precio - ent;
    	 
    	 int b500, b200, b100, b50, b20, b10, b5, m2, m1, m050, m020, m010, m005, m002, m001;
    	 
    	 //Calculo los billetes y monedas de la parte entera
    	 
    	 b500 = ent / 500; //billetes de 500
    	 ent = ent % 500; //el resto
    	 b200 = ent / 200; //billetes de 200
    	 ent = ent % 200; //el resto
    	 b100 = ent / 100; //billetes de 100
    	 ent = ent % 100; //el resto
    	 b50 = ent / 50; //billetes de 50
    	 ent = ent % 50; //el resto
    	 b20 = ent / 20; //billetes de 20
    	 ent = ent % 20; //el resto
    	 b10 = ent / 10; //billetes de 10
    	 ent = ent % 10; //el resto
    	 b5 = ent / 5; //billetes de 5
    	 ent = ent % 5; //el resto
    	 m2 = ent / 2; //monedas de 2
    	 ent = ent % 2; //el resto
    	 m1 = ent / 1; //monedas de 1
    	 ent = ent % 1; //el resto
    	 
    	//Calculo las monedas de la parte decimal
    	//Le sumo 0.5 por el redondeo 
    	 int dec = (int)(decima * 1000 + 0.5);
    	 m050 = dec / 500; //monedas de 0,50
    	 dec = dec % 500; //el resto
    	 m020 = dec / 200; //monedas de 0,20
    	 dec = dec % 200; //el resto
    	 m010 = dec / 100; //monedas de 0,10
    	 dec = dec % 100; //el resto
    	 m005 = dec / 50; //monedas de 0,05
    	 dec = dec % 50; //el resto
    	 m002 = dec / 20; //monedas de 0,02
    	 dec = dec % 20; //el resto
    	 m001 = dec / 10; //monedas de 0,01
    	   
    	 
    	 cambio.setText("Billetes de 500 euros: " + b500 + 
    			 "\nBilletes de 200 euros: " + b200 + 
    			 "\nBilletes de 100 euros: " + b100 +
    			 "\nBilletes de 50 euros:  " + b50 +
    			 "\nBilletes de 20 euros:  " + b20 +
    			 "\nBilletes de 10 euros:  " + b10 +
    			 "\nBilletes de 5 euros:   " + b5 +
    			 "\nMonedas de 2 euros:   " + m2 +
    			 "\nMonedas de 1 euro:    " + m1 +
    			 "\nMonedas de 50 centimos: " + m050 +
    			 "\nMonedas de 20 centimos: " + m020 +
    			 "\nMonedas de 10 centimos: " + m010 +
    			 "\nMonedas de 5 centimos: " + m005 +
    			 "\nMonedas de 2 centimos: " + m002 +
    			 "\nMonedas de 1 centimo: " + m001);
    	 
    	 
    	 ImageView imagen = (ImageView)findViewById(R.id.Trans);
    	     	 
//    	 //Obtenemos el recurso creado en animacion.xml
//         animaci = (AnimationDrawable) getResources().getDrawable(R.drawable.animacion);
//         //Creamos una vista que contendra una imagen
//         ImageView imagen =  new ImageView(this);
//         //Le ponemos color de fondo
//         imagen.setBackgroundColor(Color.TRANSPARENT);
//         //Cargamos en lugar de una imagen, una animation.
//         imagen.setImageDrawable(animaci);
//         setContentView(imagen);
//         
//         animaci.start();
    	 
    	 
    	
		 		
  		TransitionDrawable miTransicion = (TransitionDrawable)
  				getResources().getDrawable(R.drawable.transicion);
  		imagen.setImageDrawable(miTransicion);
  		miTransicion.startTransition(2000);

    	 
    	 
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