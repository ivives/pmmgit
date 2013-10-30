package com.example.misejercicios;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FrmSaludo extends Activity {

	
	@Override
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saludo);
		
		//Localizar los controles
		TextView txtSaludo = (TextView)findViewById(R.id.TxtSaludo);
		
		//Recuperamos la informacion pasada en el intent
		Bundle b = this.getIntent().getExtras();
		
		//Construimos el mensaje para mostrar
		txtSaludo.setText("Hola " + b.getString("NOMBRE"));
	}
	
}
