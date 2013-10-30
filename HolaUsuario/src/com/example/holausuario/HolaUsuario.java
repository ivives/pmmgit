package com.example.holausuario;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;

public class HolaUsuario extends Activity {
	
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText txtNombre = (EditText)findViewById(R.id.TxtNombre);
		final Button botonHola = (Button)findViewById(R.id.BotonHola);
	}
}
