package com.example.ejemploanimacion;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends Activity {

	AnimationDrawable animacion;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Obtenemos el recurso creado en animacion.xml
        animacion = (AnimationDrawable) getResources().getDrawable(R.drawable.animacion);
        //Creamos una vista que contendrá una imagen
        ImageView imagen = new ImageView(this);
        //Le ponemos color de fondo
        imagen.setBackgroundColor(Color.WHITE);
        //Cargamos en lugar de una imagen, una animación.
        imagen.setImageDrawable(animacion);
        setContentView(imagen);
    }
    
    public boolean onTouchEvent(MotionEvent evento) {
    	//Al realizar una pulsación en pantalla
    	if (evento.getAction() == MotionEvent.ACTION_DOWN) {
    		//Ponemos en marcha la animación
    		animacion.start();
    		return true;
    	}
    	return super.onTouchEvent(evento);
    }
}
