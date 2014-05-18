package com.example.proyecto2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



public class Carrito extends Activity {

	public static final int CODIGO_RESPUESTA = 123;
	private Button boton;
	String dato = "";
	CreaBase base = new CreaBase(this,"dbase",null,1);
	private Producto[] datos;	
		
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carrito);
		
		CreaBase base = new CreaBase(this,"dbase",null,1);
		
		
		
			boton=(Button)findViewById(R.id.button1);
		  
		    boton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Carrito.this, Lector.class);
					  					
  					  					
  					startActivity(intent);
				}
			});
				
		    ListView listView = (ListView)findViewById(R.id.LstOpciones);
		    
		    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      			public void onItemClick(AdapterView<?> parent, android.view.View v, int position, long id){
      				
      				Intent intent = new Intent(Carrito.this, MostrarProducto.class);
    				
    				Bundle b = new Bundle();
    				String descripcion = ((Producto)parent.getAdapter().getItem(position)).getDescripcion();
    				String precio = ((Producto)parent.getAdapter().getItem(position)).getPrecio();
    				String cantidad = ((Producto)parent.getAdapter().getItem(position)).getCantidad();
    				String total = ((Producto)parent.getAdapter().getItem(position)).getTotal();
    				    				
    				b.putString("Descripcion", descripcion);
    				b.putString("Precio", precio);
    				b.putString("Cantidad", cantidad);
    				b.putString("Total", total);
    				
    				  				
    				intent.putExtras(b);
    				
    				
    				if(position > 0)
    				startActivityForResult(intent, CODIGO_RESPUESTA);
      			}

				      			
      		});
	
	
        
        
        try 
		{
			
			String[] campos = new String[] {"codigo","descripcion","precio","cantidad","total"};
			
			SQLiteDatabase db=base.getReadableDatabase();
			
			
			Cursor rs=db.query("milista", campos, null,null,null,null,null);
			
			datos=new Producto[rs.getCount()+1];//Devuelve el numero de filas + 1 
			datos[0]= new Producto("Codigo","Descripción","Precio","Uds","Total");
			int i=1;
	        if (rs.moveToFirst()) 
	        {
	                do 
	                {
	                		
	                		String cod=rs.getString(0);
	                		String des=rs.getString(1);
	                		String pre=rs.getString(2);
	                        String can=rs.getString(3);
	                        String tot=rs.getString(4);
	                        
	                        
	                        datos[i]=new Producto(cod,des,pre,can,tot);
	                        i++;       
	                }
	                while (rs.moveToNext());
	        }
			
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		
        
        AdaptadorProducto adaptador = new AdaptadorProducto(this);
        
        ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);
        
        lstOpciones.setAdapter(adaptador);
        
    }
	
	
	class AdaptadorProducto extends ArrayAdapter<Producto> {
    	
    	Activity context;
    	
    	AdaptadorProducto(Activity context) {
    		super(context, R.layout.list_item, datos);
    		this.context = context;
    	}
    	
	    public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.list_item, null);
				
			TextView lblDescripcion = (TextView)item.findViewById(R.id.txtdescripcion);
			lblDescripcion.setText(datos[position].getDescripcion());
				
			TextView lblPrecio = (TextView)item.findViewById(R.id.txtprecio);
			lblPrecio.setText(datos[position].getPrecio());
				
			TextView lblCantidad = (TextView)item.findViewById(R.id.txtcantidad);
			lblCantidad.setText(datos[position].getCantidad());
				
			TextView lblTotal = (TextView)item.findViewById(R.id.txttotal);
			lblTotal.setText(datos[position].getTotal());
				
			return(item);
		}
    }
	
	
	protected void onActivityResult(int requestCode,int resultCode, Intent pData)            
    {
        if ( requestCode == CODIGO_RESPUESTA )//Si el código de respuesta es igual al requestCode
            {
            if (resultCode == Activity.RESULT_OK )//Si resultCode es igual a ok
                {
                    final String dato = pData.getExtras().getString(MostrarProducto.DATO_SUBACTIVIDAD );//Obtengo el string de la subactividad
                    //Aquí se hara lo que se desee con el valor recuperado

                    SQLiteDatabase db=base.getWritableDatabase();
                    db.execSQL(dato);
                                                           
                    Intent intent = new Intent(Carrito.this, Carrito.class);
                    finish();
                    startActivity(intent);
                }
            }
    }
	

}
