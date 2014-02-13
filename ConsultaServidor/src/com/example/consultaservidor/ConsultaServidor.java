package com.example.consultaservidor;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ConsultaServidor extends Activity {

	private Button boton;
	private TextView texto;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_servidor);
        
        boton = (Button)findViewById(R.id.boton);
        texto = (TextView)findViewById(R.id.texto);
        
        //Escuchador para el botón
        boton.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		try {
        			//Recuperamos los valores introducidos en los campos
        			//de latitud y longitud
        			
        			String resultado = consultaVariablePHP();
        			texto.setText(resultado);
        		} catch (Exception e) {
        			texto.setText("Error al conectar\n");
        			e.printStackTrace();
        		}
        	}
        });
    }
    
    //Método que conectará con el WebService de Google que nos permite obtener
    //los datos de una localización dadas sus coordenadas
    String consultaVariablePHP() {
    	String devuelve = "";
    	
    	//Creamos un nuevo objeto HttpClient que será el encargado de realizar la
    	//comunicación HTTP con el servidor a partir de los datos que le damos.
    	HttpClient comunicacion = new DefaultHttpClient();
    	//Creamos una peticion POST indicando la URL de llamada al servicio.
    	String url = "http://192.168.24.193:8080/holamundo.php";
    			
    	HttpGet peticion = new HttpGet(url);
    	

    	try {
    		//Ejecutamos la petición y obtenemos la respuesta en forma de cadena
    		HttpResponse respuesta = comunicacion.execute(peticion);
    		String respuestaCad = EntityUtils.toString(respuesta.getEntity());
    		
    		devuelve = "ConsultaPHP: " + respuestaCad;
    	} catch(Exception e) {
    		Log.e("Error ies REST", "ERROR!!", e);
    	}
    	
    	return devuelve;
    }
}