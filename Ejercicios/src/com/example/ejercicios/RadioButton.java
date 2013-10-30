package com.example.ejercicios;

import android.os.Bundle;
import android.app.Activity;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioButton extends Activity {
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_radiobutton);
	        
	        final TextView lblMensaje = (TextView)findViewById(R.id.LblSeleccion);
	        final RadioGroup rg = (RadioGroup)findViewById(R.id.gruporb);

	        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
		        public void onCheckedChanged(RadioGroup group, int checkedId) {
		        	lblMensaje.setText("ID opción seleccionada: " + checkedId);
		        }
	        });

	    }

}
