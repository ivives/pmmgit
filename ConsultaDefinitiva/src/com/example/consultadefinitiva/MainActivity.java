package com.example.consultadefinitiva;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button boton;
	private TextView texto;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        boton = (Button)findViewById(R.id.boton);
        texto = (TextView)findViewById(R.id.texto);
        
        //Escuchador para el boton
        boton.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		try {
        			//Recuperamos los valores introducidos en los campos
        			//de latitud y longitud
       
        			String resultado = consultaPHP();
        			texto.setText(resultado);
        		} catch (Exception e) {
        			texto.setText("Error al conectar\n");
        			e.printStackTrace();
        		}
        	}
        });
    }
    
    //Metodo que conectara con el WebService de Google que nos permite obtener
    //los datos de una localizacion dadas sus coordenadas
    String consultaPHP() {
    	String devuelve = "";
    	
    	//Creamos un nuevo objeto HttpClient que sera el encargado de realizar la
    	//comunicacion HTTP con el servidor a partir de los datos que le damos.
    	HttpClient comunicacion = new DefaultHttpClient();
    	//Creamos una peticion GET indicando la URL de llamada al servicio.
    	String url = "http://192.168.1.104:8080/holamundo.php";
    			//"latlng=38.15,-0.89&sensor=false");
    	HttpGet peticion = new HttpGet(url);
    	//Modificamos mediante setHeader el atributo http content-type para indicar
    	//que el formato de los datos que utilizaremos en la comunicacion sera JSON.
    	//peticion.setHeader("content-type", "application/json");
    	String respuestaCad="";
    	try {
    		//Ejecutamos la peticion y obtenemos la respuesta en forma de cadena
    		HttpResponse respuesta = comunicacion.execute(peticion);
		    respuestaCad = EntityUtils.toString(respuesta.getEntity());
    		//Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
    		//JSONObject respuestaJSON = new JSONObject(respuestaCad);
    		//Accedemos al vector de resultados
    		//JSONArray resultJSON = respuestaJSON.getJSONArray("results");
    		//Vamos obteniendo todos los campos que nos interesen.
    		//En este caso obtenemos la primera direccion de los resultados.
    		String direccion="SIN DATOS PARA ESA LONGITUD Y LATITUD";
    		//if (resultJSON.length()>0){
    			//direccion = resultJSON.getJSONObject(0).getString("formatted_address");
    		//}
    		devuelve = "Direccion: " + direccion;
    	} catch(Exception e) {
    		Log.e("Error ies REST", "ERROR!!", e);
    	}
    	
    	return respuestaCad;
    }
}
