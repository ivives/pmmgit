package com.example.titularesnoticias;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button boton;
	private TextView text;
	private Conexion conexion;
	private SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		boton=(Button)findViewById(R.id.boton);
		Button ver=(Button)findViewById(R.id.ver);
		text=(TextView)findViewById(R.id.text);
		conexion=new Conexion(this,"DBNoticias",null,1);
		db=conexion.getWritableDatabase();
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    	
	    StrictMode.setThreadPolicy(policy);
		
		boton.setOnClickListener(new OnClickListener(
				) {
			
			@Override
			public void onClick(View v) {
				try{
				String noticias=buscarNoticias();
				text.append(noticias);
				}catch (Exception e){
					text.append("Error al conectar");
					e.printStackTrace();
				}
				
			}
		});
		
		ver.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i= new Intent(MainActivity.this,Noticias.class);
				startActivity(i);
				
			}
		});
		
		
	}

	private String buscarNoticias() throws Exception{
		String salida="";
		int i=0,j=0;
		
		URL url = new URL("http://www.elpais.com/rss/feed.html?feedId=1022");
		
		HttpURLConnection conexion=(HttpURLConnection) url.openConnection();
		
		//conexion.setRequestProperty("User-Agent","Mozilla/5.0 Linux; Android 1.5; es-ES) Ejemplo HTTP");
		
		conexion.setRequestProperty("User-Agent","Chrome; Android 1.5; es-ES) Ejemplo HTTP");
		
		if(conexion.getResponseCode() == HttpURLConnection.HTTP_OK){
			BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			
			String linea=lector.readLine();
			while(linea!=null){
				if(linea.indexOf("<title>")>=0){
					i=linea.indexOf("<title>")+16;
					j=linea.indexOf("</title>")-3;
					salida+=linea.substring(i,j);
					System.out.println(linea.substring(i,j));
					db.execSQL("INSERT INTO Noticias (noticia) values ('"+linea.substring(i,j)+"')");
					salida+="\n---------------------------\n";
				}
				linea=lector.readLine();
			}
			lector.close();
		}
		else{
			salida="No encontrado";
		}
		db.close();
		conexion.disconnect();
		
		return salida;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
