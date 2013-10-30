package com.example.spinner;


import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Pelicula[] datos = new Pelicula[]{
			new Pelicula("Dracula", "The scars of Dracula", "Año 1970", R.drawable.dracula),
    		new Pelicula("Indiana Jones", "Indiana Jones en busca del Arca perdida", "Año 1981", R.drawable.indiana),
    		new Pelicula("Madagascar", "Madagascar", "Año 2005", R.drawable.madagascar),
    		new Pelicula("Piratas del Caribe", "El cofre del hombre muerto", "Año 2006", R.drawable.piratas),
    		new Pelicula("Titanic", "Misterios del Titanic", "Año 2006", R.drawable.titanic)};
	
	class AdaptadorPelicula extends ArrayAdapter<Pelicula> {
    	
    	Activity a;
    	AdaptadorPelicula(Activity b) {
    	super(b, R.layout.activity_spinner, datos);
    	this.a = b;
    	}
    	
    	public View getDropDownView ( int position, View convertView, ViewGroup parent){
    		return getView (position, convertView, parent);
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = a.getLayoutInflater();
			View item = inflater.inflate(R.layout.activity_spinner, null);
			
			final TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
			lblTitulo.setText(datos[position].getTitulo());
			
			final TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
			lblSubtitulo.setText(datos[position].getSubtitulo());
			
			final TextView lblFecha = (TextView)item.findViewById(R.id.LblFecha);
			lblFecha.setText(datos[position].getFecha());
			
			ImageView imagen = (ImageView)item.findViewById(R.id.Imagen);
			imagen.setImageResource(datos[position].getFoto());
			
			return(item);
		}
    }
	
		
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        final TextView lblMensaje = (TextView)findViewById(R.id.LblMensaje);
	        final Spinner cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);
	        
//	        final String[] datos =
//	            new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};
	     
//	        ArrayAdapter<Titular> adaptador =
//	            new ArrayAdapter<Titular>(this,
//	                android.R.layout.simple_spinner_item, datos);
	        
	        AdaptadorPelicula adaptador = new AdaptadorPelicula(this);
	        
	        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	         
	        cmbOpciones.setAdapter(adaptador);
	        	        
	        cmbOpciones.setOnItemSelectedListener(
	        	new AdapterView.OnItemSelectedListener() {
	                public void onItemSelected(AdapterView<?> parent,
	                    android.view.View v, int position, long id) {
	                        lblMensaje.setText("Seleccionado: " + datos[position].getTitulo());
	                }
	         
	                public void onNothingSelected(AdapterView<?> parent) {
	                    lblMensaje.setText("");
	                }
	        });
	    }
	 
}


