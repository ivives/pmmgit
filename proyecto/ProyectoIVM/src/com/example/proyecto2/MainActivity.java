package com.example.proyecto2;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;



public class MainActivity extends Activity {
	
	 // Establecemos la duración de la splash screen
    private static final long SPLASH_SCREEN_DELAY = 4000;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		//Hacemos que la actividad ocupe toda la pantalla
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
				
		//Ponemos la orientación a portrait (vertical)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Ocultamos la barra del título
        requestWindowFeature(Window.FEATURE_NO_TITLE);
 
        setContentView(R.layout.activity_main);
 
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
 
                // Iniciamos la siguiente actividad
                Intent mainIntent = new Intent().setClass(
                        MainActivity.this, Carrito.class);
                startActivity(mainIntent);
 
                // Cerramos la actividad haciendo que no sea posible volver a ella aunque se apriete el botón de volver atrás
                finish();
            }
        };
 
        // Simulamos un proceso de carga al iniciar la aplicación.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
		
	

}