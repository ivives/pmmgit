package com.example.tarifasavion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Tarifas extends Activity{

	private RadioButton r1,r2,r3,r4,r5,r6;
	static String trayecto = "";
    static String descuento = "";
    static double precio = 0;
    static String prec = String.valueOf(precio);
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tarifas);
		
		final Button btnBoton1 = (Button)findViewById(R.id.BtnBoton1);
		final Button btnBoton2 = (Button)findViewById(R.id.BtnBoton2);
		r1=(RadioButton)findViewById(R.id.radio1);
        r2=(RadioButton)findViewById(R.id.radio2);
        r3=(RadioButton)findViewById(R.id.radio3);
        r4=(RadioButton)findViewById(R.id.radio4);
        r5=(RadioButton)findViewById(R.id.radio5);
        r6=(RadioButton)findViewById(R.id.radio6);
		final TextView lblTarifa = (TextView)findViewById(R.id.LblTarifa);
	    final RadioGroup rg = (RadioGroup)findViewById(R.id.grupotra);
	    final RadioGroup rg2 = (RadioGroup)findViewById(R.id.grupodes);
	    
	    final int t1 = 100;
	    final int t2 = 100;
	    final int t3 = 150;
	    final double d1 = 0.1;
	    final double d2 = 0.15;
	    final double d3 = 0.2;
	    
	        
	      
	  //Evento boton 1
	  		btnBoton1.setOnClickListener(new OnClickListener() {
	  			
	  			@Override
	  			public void onClick(View v) {
	  				if (r1.isChecked() == true){
	  					precio = t1;
	  				}else{
	  					if (r2.isChecked() == true){
	  						precio = t2;
	  					}else{
	  						if (r3.isChecked() == true){
	  							precio = t3;
	  						}
	  					}
	  				}
	  				
	  				if (r4.isChecked() == true){
	  					precio = precio - (precio * d1);
	  				}else{
	  					if (r5.isChecked() == true){
	  						precio = precio - (precio * d2);
	  					}else{
	  						if (r6.isChecked() == true){
	  							precio = precio - (precio * d3);
	  						}else{
	  							precio = precio;
	  						}
	  					}
	  				}
	  				
	  				lblTarifa.setText("Trayecto de: Madrid-Valencia\n" + trayecto + descuento + "Total precio: " + precio );
	  				  				
	  			}
	  		});
	  		
	  		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
		        public void onCheckedChanged(RadioGroup group, int checkedId) {
		        			        	
		        	if (checkedId == R.id.radio1){
		                trayecto = "Trayecto de solo Ida.\n";
		            }else if (checkedId == R.id.radio2){
		            	trayecto = "Trayecto de solo Vuelta.\n";
		            }else if (checkedId == R.id.radio3){
		            	trayecto = "Trayecto de Ida y Vuelta.\n";
		            }
		        }
	        });
	  		
	  		rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
		        public void onCheckedChanged(RadioGroup group, int checkedId) {
		        			        	
		        	if (checkedId == R.id.radio4){
		                descuento = "Descuento del 10%.\n";
		            }else if (checkedId == R.id.radio5){
		            	descuento = "Descuento del 15%.\n";
		            }else if (checkedId == R.id.radio6){
		            	descuento = "Descuento del 20%.\n";
		            }
		        }
	        });
	  		
	       
	  	//Evento boton 2
			btnBoton2.setOnClickListener(new OnClickListener() {
//				
//			 
//				
				@Override
				public void onClick(View v) {
					// creamos el intent
					Intent intent2 = new Intent(Tarifas.this, MainActivity.class);
							
					//Creamos la informacion a pasar entre actividades
					Bundle b = new Bundle();
					b.putString("PRECIO", prec);
					b.putString("TIPO", trayecto);
					b.putString("DESCUENTO", descuento);
							
					//Añadimos la informacion al intent
					intent2.putExtras(b);
					
					
					//Iniciamos la nueva actividad
					
					startActivity(intent2);
					this.finish();
//					
//					
////					Intent dato = new Intent();
////			        dato.putExtra("PRECIO","prec");//Dato que pasaremos a la actividad principal
////			        dato.putExtra("TIPO","trayecto");//Dato que pasaremos a la actividad principal
////			        dato.putExtra("DESCUENTO","descuento");//Dato que pasaremos a la actividad principal
////			        setResult(RESULT_OK, dato);
////			        //Nos devuelve a la actividad principal "ActividadPrincipal"
////			        finish();
////					
//							
				}

					private void finish() {
						// TODO Auto-generated method stub
						
					}
			});
	}		
			
			
	
	
	
	
}
