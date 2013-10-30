package net.sgoliver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class DemoListView extends Activity {

    private Titular[] datos = 
    	new Titular[]{
    		new Titular("Titulo 1", "Subtitulo largo 1", "10/12/2013", R.drawable.dracula),
    		new Titular("Titulo 2", "Subtitulo largo 2", "03/01/2005", R.drawable.indiana),
    		new Titular("Titulo 3", "Subtitulo largo 3", "12/11/2010", R.drawable.madagascar),
    		new Titular("Titulo 4", "Subtitulo largo 4", "04/06/2009", R.drawable.piratas),
    		new Titular("Titulo 5", "Subtitulo largo 5", "15/09/2001", R.drawable.titanic)};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                     
      
       		ListView listView = (ListView)findViewById(R.id.LstOpciones);
       		      		
      		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      			public void onItemClick(AdapterView<?> parent, android.view.View v, int position, long id){
      				
      				Intent intent = new Intent(DemoListView.this, PantallaDos.class);
      				      				
      				Bundle b = new Bundle();
      				String titulo=((Titular)parent.getAdapter().getItem(position)).getTitulo();
      				String subtitulo=((Titular)parent.getAdapter().getItem(position)).getSubtitulo();
      				String fecha=((Titular)parent.getAdapter().getItem(position)).getFecha();
      				b.putString("Titulo", titulo);
      				b.putString("Subtitulo", subtitulo);
      				b.putString("Fecha", fecha);
      				int foto=((Titular)parent.getAdapter().getItem(position)).getFoto();
      				b.putInt("Foto", foto);
      				
      				intent.putExtras(b);
    				
    				startActivity(intent);
      			}

				      			
      		});
        
        
        AdaptadorTitulares adaptador = new AdaptadorTitulares(this);
        
        ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);
        
        lstOpciones.setAdapter(adaptador);
    }
    
    
    class AdaptadorTitulares extends ArrayAdapter<Titular> {
    	
    	Activity context;
    	
    	AdaptadorTitulares(Activity context) {
    		super(context, R.layout.listitem_titular, datos);
    		this.context = context;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.listitem_titular, null);
			
			TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
			lblTitulo.setText(datos[position].getTitulo());
			
			TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
			lblSubtitulo.setText(datos[position].getSubtitulo());
			
			TextView lblFecha = (TextView)item.findViewById(R.id.LblFecha);
			lblFecha.setText(datos[position].getFecha());
			
			ImageView imagen = (ImageView)item.findViewById(R.id.Imagen);
			imagen.setImageResource(datos[position].getFoto());
			
			return(item);
		}
    }
}
