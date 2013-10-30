package com.example.misejercicios;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


public class SpinnerButton  extends Activity{

	private Pelicula[] datos = new Pelicula[]{
			new Pelicula("Dracula", "The scars of Dracula", "Año 1970", R.drawable.dracula),
    		new Pelicula("Indiana Jones", "Indiana Jones en busca del Arca perdida", "Año 1981", R.drawable.indiana),
    		new Pelicula("Madagascar", "Madagascar", "Año 2005", R.drawable.madagascar),
    		new Pelicula("Piratas del Caribe", "El cofre del hombre muerto", "Año 2006", R.drawable.piratas),
    		new Pelicula("Titanic", "Misterios del Titanic", "Año 2006", R.drawable.titanic)};
	
		 
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_spinner);
	        
	        final TextView lblMensaje = (TextView)findViewById(R.id.LblMensaje);
	        final Spinner cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);
	        
	        AdaptadorPelicula adaptador = new AdaptadorPelicula(this);
	        
	        adaptador.setDropDownViewResource(
	                android.R.layout.simple_spinner_dropdown_item);
	         
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
		 
	 class AdaptadorPelicula extends ArrayAdapter<Pelicula> {
	    	
		 Activity a;
			AdaptadorPelicula(Activity b) {
			super(b, R.layout.activity_spinnerbutton, datos);
			this.a = b;
			}
			
			public View getDropDownView ( int position, View convertView, ViewGroup parent){
				return getView (position, convertView, parent);
			}
	    	
	    	public View getView(int position, View convertView, ViewGroup parent) {
				
	    		View item = convertView;
	    		ViewHolder holder;
	    		
	    		if (item == null){
	    			LayoutInflater inflater = a.getLayoutInflater();
	    			item = inflater.inflate(R.layout.activity_pelicula, null);
				
	    			holder = new ViewHolder();
	    			holder.titulo = (TextView)item.findViewById(R.id.LblTitulo);
	    			holder.subtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
	    			holder.fecha = (TextView)item.findViewById(R.id.LblFecha);
	    			holder.imagen = (ImageView)item.findViewById(R.id.LblImagen);
	    			
	    			item.setTag(holder);
	    		}
	    		else{
	    			holder = (ViewHolder)item.getTag();
	    		}
				
				holder.titulo.setText(datos[position].getTitulo());
				holder.subtitulo.setText(datos[position].getSubtitulo());
				holder.fecha.setText(datos[position].getFecha());
				holder.imagen.setImageResource(datos[position].getFoto());
				
				return(item);
			}
	    }
	    
	    static class ViewHolder{
	    	TextView titulo;
	    	TextView subtitulo;
	    	TextView fecha;
	    	ImageView imagen;
	    }
	
}


