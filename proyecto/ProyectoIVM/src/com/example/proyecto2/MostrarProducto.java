package com.example.proyecto2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
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
		
		// Localizamos los controladores
		final TextView descripcion = (TextView)findViewById(R.id.descripcion);
		final TextView precio = (TextView)findViewById(R.id.precio);
		final TextView cantidad = (TextView)findViewById(R.id.cantidad);
		
		
			
		    
		    descripcion.setKeyListener(null);
		    precio.setKeyListener(null);
		    	 
		// recuperamos informacion del intent
		   	 Bundle b = this.getIntent().getExtras();
		    	 
		   	 descripcion.setText(b.getString("Descripcion"));
		   	 precio.setText(b.getString("Precio"));
		   	 cantidad.setText(b.getString("Cantidad"));
		   	 
		   	 
		    			   	 
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
