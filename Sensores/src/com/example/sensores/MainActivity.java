package com.example.sensores;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	    private TextView salida;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        //Campo de texto para mostrar la salida
	        salida = (TextView)findViewById(R.id.salida);
	        //Objeto SensorManager que nos permitir� ver la lista de sensores del dispositivo
	        SensorManager miSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
	        List<Sensor> listaSensores = miSensorManager.getSensorList(Sensor.TYPE_ALL);
	        //Vamos mostrando los sensores uno a uno
	        for(Sensor sensor:listaSensores) {
	        	mostrar(sensor.getName());
	        }
	        
	        
	        
	    }
	    
	    private void mostrar(String cadena) {
	    	salida.append(cadena + "\n");
	    }

		
				
			
			
		
	}

