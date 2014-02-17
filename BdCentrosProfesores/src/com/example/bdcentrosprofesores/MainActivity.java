package com.example.bdcentrosprofesores;



import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button buttonProfesores = (Button)findViewById(R.id.Profesores);
		Button buttonCentros = (Button)findViewById(R.id.Centros);
		Button buttonPersonal = (Button)findViewById(R.id.Personal);
		Button buttonConsultas = (Button)findViewById(R.id.Consultas);
		
		
//		CreaBase base = new CreaBase(this,"dbase",null,1);
//		SQLiteDatabase db=base.getReadableDatabase();
		
		
		buttonProfesores.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Profesores.class);
					
				startActivity(intent);
				
			}
		});
		
		buttonPersonal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Personal.class);
					
				startActivity(intent);
				
			}
		});
		
		buttonCentros.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ConsultaCentros.class);
					
				startActivity(intent);
				
			}
		});
		
		buttonConsultas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Consultas.class);
					
				startActivity(intent);
				
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
