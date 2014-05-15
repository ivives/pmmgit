package com.example.pruebasproy2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
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
		
	//TextView txtView = (TextView)findViewById(R.id.textview);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carrito);
		
			boton=(Button)findViewById(R.id.button1);
		  
		    boton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Carrito.this, Lector.class);
					  					
  					  					
  					startActivityForResult(intent, CODIGO_RESPUESTA);
				}
			});
				
	}
	
	protected void onActivityResult(int requestCode,int resultCode, Intent pData)            
    {
        if ( requestCode == CODIGO_RESPUESTA )//Si el código de respuesta es igual al requestCode
            {
            if (resultCode == Activity.RESULT_OK )//Si resultCode es igual a ok
                {
                    dato = pData.getExtras().getString(Lector.DATO_SUBACTIVIDAD );//Obtengo el string de la subactividad
                    //Aquí se hara lo que se desee con el valor recuperado
                    SQLiteDatabase db=base.getWritableDatabase();
                    db.execSQL(dato);
                    
                    Intent intent = new Intent(Carrito.this, Carrito.class);
                    finish();
                    startActivity(intent);
                                      
                }
            }
        
        
        
        
        try 
		{
			
			String[] campos = new String[] {"id","codigo","descripcion","precio","cantidad","total"};
			
			SQLiteDatabase db=base.getReadableDatabase();
			
			
			Cursor rs=db.query("milista", campos, null,null,null,null,null);
			
			datos=new Producto[rs.getCount()+1];//Devuelve el numero de filas + 1 
			datos[0]= new Producto("Id", "Codigo","Descripcion","Precio","Cantidad","Total");
			int i=1;
	        if (rs.moveToFirst()) 
	        {
	                do 
	                {
	                		String id=rs.getString(0);
	                		String cod=rs.getString(1);
	                		String des=rs.getString(2);
	                		String pre=rs.getString(3);
	                        String can=rs.getString(4);
	                        String tot=rs.getString(5);
	                        
	                        
	                        
	                        datos[i]=new Producto(id,cod,des,pre,can,tot);
	                        i++;       
	                }
	                while (rs.moveToNext());
	        }
			
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		
		final ListView list=(ListView)findViewById(R.id.LstOpciones);
		AdaptadorProducto adaptador =new AdaptadorProducto(this); 
		adaptador.setDropDownViewResource(android.R.layout.simple_list_item_1);
		list.setAdapter(adaptador);
        
        
        
        
        
    }
	
	

	
	 class AdaptadorProducto extends ArrayAdapter<Producto> 
		{
			Activity a;
			AdaptadorProducto(Activity b) 
			{
				super(b, R.layout.list_item, datos);
				this.a = b;
			}
			public View getDropDownView (int position,View convertView,ViewGroup parent) {
				
				//if(item==null)
				return getView (position, convertView, parent);
				}
			public View getView(int position,View convertView,ViewGroup parent) 
			{
			//if(item==null)	
				LayoutInflater inflater = a.getLayoutInflater();	
				View item = inflater.inflate(R.layout.list_item, null);
				 	 	 
				final TextView lblId=(TextView)item.findViewById(R.id.txtid);
				final TextView lblDescripcion = (TextView)item.findViewById(R.id.txtdescripcion);
				final TextView lblPrecio = (TextView)item.findViewById(R.id.txtprecio);
				final TextView lblCantidad = (TextView)item.findViewById(R.id.txtcantidad);
				final TextView lblTotal = (TextView)item.findViewById(R.id.txttotal);
								
				lblId.setText(datos[position].getId());
				lblDescripcion.setText(datos[position].getDescripcion());
				lblPrecio.setText(String.valueOf(datos[position].getPrecio()));
				lblCantidad.setText(datos[position].getCantidad());
				lblTotal.setText(String.valueOf(datos[position].getTotal()));
				
				
				return(item);
			}
		}

	

}
