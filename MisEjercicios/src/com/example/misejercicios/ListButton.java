package com.example.misejercicios;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListButton extends Activity {

    private Pelicula[] datos = 
    	new Pelicula[]{
    		new Pelicula("Dracula", "The scars of Dracula", "Año 1970", R.drawable.dracula),
    		new Pelicula("Indiana Jones", "Indiana Jones en busca del Arca perdida", "Año 1981", R.drawable.indiana),
    		new Pelicula("Madagascar", "Madagascar", "Año 2005", R.drawable.madagascar),
    		new Pelicula("Piratas del Caribe", "El cofre del hombre muerto", "Año 2006", R.drawable.piratas),
    		new Pelicula("Titanic", "Misterios del Titanic", "Año 2006", R.drawable.titanic)};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listbutton);
                
        ListView listView = (ListView)findViewById(R.id.LstOpciones);
     		
  		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
  			public void onItemClick(AdapterView<?> parent, android.view.View v, int position, long id){
  				
  				Intent intent = new Intent(ListButton.this, ListButtonPantallaDos.class);
  				      				
  				Bundle b = new Bundle();
  				String titulo=((Pelicula)parent.getAdapter().getItem(position)).getTitulo();
  				String subtitulo=((Pelicula)parent.getAdapter().getItem(position)).getSubtitulo();
  				String fecha=((Pelicula)parent.getAdapter().getItem(position)).getFecha();
  				b.putString("Titulo", titulo);
  				b.putString("Subtitulo", subtitulo);
  				b.putString("Fecha", fecha);
  				int foto=((Pelicula)parent.getAdapter().getItem(position)).getFoto();
  				b.putInt("Foto", foto);
  				
  				intent.putExtras(b);
				
				startActivity(intent);
  			}
			      			
  		});
                
        AdaptadorTitulares adaptador = new AdaptadorTitulares(this);
        
        ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);
        
        lstOpciones.setAdapter(adaptador);
    }
    
    class AdaptadorTitulares extends ArrayAdapter<Pelicula> {
    	
    	Activity context;
    	
    	AdaptadorTitulares(Activity context) {
    		super(context, R.layout.activity_pelicula, datos);
    		this.context = context;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {
			
    		View item = convertView;
    		ViewHolder holder;
    		
    		if (item == null){
    			LayoutInflater inflater = context.getLayoutInflater();
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