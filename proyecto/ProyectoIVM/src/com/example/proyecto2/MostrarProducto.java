package com.example.proyecto2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MostrarProducto extends Activity {
	
	public static final String DATO_SUBACTIVIDAD="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_mostrar_producto);
	
		Button guardar = (Button)findViewById(R.id.guardarProducto);
		Button eliminar = (Button)findViewById(R.id.eliminarProducto);
		
		// Localizamos los controladores y les ponemos el tipo de letra
		Typeface fuente = Typeface.createFromAsset(getAssets(), "gloriahallelujah.ttf");
		final TextView descripcion = (TextView)findViewById(R.id.descripcion);
		descripcion.setTypeface(fuente);
		final TextView precio = (TextView)findViewById(R.id.precio);
		precio.setTypeface(fuente);
		final EditText cantidad = (EditText)findViewById(R.id.cantidad);
		cantidad.setTypeface(fuente);
		
			
		    // hacemos que la descripción y el precio no dean editables
		 	descripcion.setKeyListener(null);
		 	precio.setKeyListener(null);
		    	 
		// recuperamos información del intent
		   	 Bundle b = this.getIntent().getExtras();
		    	 
		   	 descripcion.setText(b.getString("Descripcion"));
		   	 precio.setText(b.getString("Precio"));
		   	 cantidad.setText(b.getString("Cantidad"));
		   	 
		   	 
		//actializamos la cantidad de producto que deseamos   			   	 
		guardar.setOnClickListener(new OnClickListener() {
		 			
		 	@Override
		 	public void onClick(View v) {
		 				 		
		 		String des = descripcion.getText().toString();
		 		String pre = precio.getText().toString();
		 		String can = cantidad.getText().toString();
		 		
		 		
		 		double prec = Double.parseDouble(pre);
		 		double cant = Double.parseDouble(can);
		 		
		 		double tota = prec * cant;
		 		
		 		String total = String.valueOf(tota);
		 		
		 		Intent resultData = new Intent();
		 				 		
		 		String sentencia = "UPDATE Milista SET  cantidad = '"+can+"', total = '"+total+"' WHERE descripcion = '"+des+"'";
		 				 		
		 		resultData.putExtra(DATO_SUBACTIVIDAD, sentencia);	
				setResult(android.app.Activity.RESULT_OK, resultData);
                finish();
						 				
			}
		});
		
		//eliminamos el producto seleccionado de la lista	
		eliminar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String des = descripcion.getText().toString();
				Intent resultData = new Intent();

				String sentencia = "DELETE FROM Milista WHERE descripcion = '"+des+"'";
				
				resultData.putExtra(DATO_SUBACTIVIDAD, sentencia);	
				setResult(android.app.Activity.RESULT_OK, resultData);
                finish();
				
			}
		});
				
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mostrar_producto, menu);
		return true;
	}

}
