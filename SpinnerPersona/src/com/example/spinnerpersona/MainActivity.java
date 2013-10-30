package com.example.spinnerpersona;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Persona[] datos =new Persona[]{
			new Persona("Alicia", "Pons",30, R.drawable.ganicus),
			new Persona("David", "Garcia", 25, R.drawable.gollum),
			new Persona("Pedro", "Garcia", 25, R.drawable.megan)};

class AdaptadorPersona extends ArrayAdapter<Persona> {
	
	Activity a;
	AdaptadorPersona(Activity b) {
	super(b, R.layout.listin, datos);
	this.a = b;
	}
	
	public View getDropDownView (int position,View convertView,ViewGroup parent) {
	//if(item==null)
		return getView (position, convertView, parent);
	}
	
	public View getView(int position,View convertView,ViewGroup parent) {
	LayoutInflater inflater = a.getLayoutInflater();
		
	View item = inflater.inflate(R.layout.listin, null);
	 	 	 
	final TextView lblApellido=(TextView)item.findViewById(R.id.apellido);
	final TextView lblNombre = (TextView)item.findViewById(R.id.nombre);
	final TextView lblEdad = (TextView)item.findViewById(R.id.edad);
	ImageView lafoto=(ImageView)item.findViewById(R.id.foto);
	
	lblNombre.setText(datos[position].getNombre());
	lblApellido.setText(datos[position].getApellido());
	lblEdad.setText(Integer.toString(datos[position].getEdad()));
	lafoto.setImageResource(datos[position].getFoto());
	return(item);
	}
}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdaptadorPersona adaptador =new AdaptadorPersona(this);
		final Spinner cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cmbOpciones.setAdapter(adaptador);

		cmbOpciones.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> a,
				android.view.View v, int position, long id) {
				Intent intent =new Intent(MainActivity.this, Ejecucion.class);//Creamos el Intent
				
				String nom=((Persona)a.getAdapter().getItem(position)).getNombre();
				String ape =((Persona)a.getAdapter().getItem(position)).getApellido();
				int edad= ((Persona)a.getAdapter().getItem(position)).getEdad();
				int lafoto= ((Persona)a.getAdapter().getItem(position)).getFoto();
				Bundle b = new Bundle();
				b.putString("persona",nom);
				b.putString("apellido", ape);
				b.putInt("edad", edad);
				b.putInt("foto", lafoto);
				//b.putInt("edad", edad);
				
				intent.putExtras(b);
				
		        //Iniciamos la nueva actividad
		        startActivity(intent);
				
				}
				
				public void onNothingSelected(AdapterView<?> parent) {
				
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
