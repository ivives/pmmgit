package com.example.numerosarabigosromanos;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText txtArabigo = (EditText)findViewById(R.id.TxtArabigo); 
		final Button btnBoton1 = (Button)findViewById(R.id.BtnBoton1);
		final TextView txtRomano = (TextView)findViewById(R.id.TxtRomano);
		
		final String Unidad[]={"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};  
	    final String Decena[]={"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};  
	    final String Centena[]={"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
	    final String Millar[]={"", "M", "MM", "MMM"};
        
	    //Evento boton 1
   		btnBoton1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int N = Integer.parseInt(txtArabigo.getText().toString());
			    
			     int u = N % 10;  
			     int d =(N / 10) % 10;  
			     int c = (N / 100) % 10; 
			     int m = N / 1000;
			     if (N >= 1000){
			    	 txtRomano.setText(Millar[m] + Centena[c] + Decena[d] + Unidad[u]);
			     }else{
			    	if(N >= 100){ 
			    		txtRomano.setText(Centena[c] + Decena[d] + Unidad[u]);
			       
				     }else{  
				         if(N >= 10){
				        	 txtRomano.setText(Decena[d] + Unidad[u]);
				                                  
				         }else{
				        	 txtRomano.setText(Unidad[u]);
				         }
				     }
			     }
			}
   			
   			
   		});
     
     
}
     
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
