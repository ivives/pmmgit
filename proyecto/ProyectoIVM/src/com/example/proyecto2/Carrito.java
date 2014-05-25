package com.example.proyecto2;

import java.text.DecimalFormat;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



public class Carrito extends Activity {

	public static final int CODIGO_RESPUESTA = 123;
	private Button escan, vacia;
	String dato = "";
	CreaBase base = new CreaBase(this,"dbase",null,1);
	private Producto[] datos;	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Hacemos que la actividad ocupe toda la pantalla
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_carrito);
		Typeface fuente = Typeface.createFromAsset(getAssets(), "gloriahallelujah.ttf");
		final CreaBase base = new CreaBase(this,"dbase",null,1);
		TextView resultado = (TextView)findViewById(R.id.result);
		resultado.setTypeface(fuente);
		TextView titulo = (TextView)findViewById(R.id.titulo);
		titulo.setTypeface(fuente);
		
		vacia=(Button)findViewById(R.id.vaciar);
		escan=(Button)findViewById(R.id.escanear);
				  
		//Llamamos a la actividad que lee los codigos de barras y los guarda en la base de datos del dispositivo
		escan.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Carrito.this, Lector.class);
				startActivity(intent);
			}
		});
		    
		//Vaciamos la lista completamente
		vacia.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View arg0) {
					
				String borra = "DELETE FROM Milista";
					
				SQLiteDatabase db=base.getWritableDatabase();
                db.execSQL(borra);
                Intent intent = new Intent(Carrito.this, Carrito.class);
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
       
               
        
        //Obtengo de la db la suma de los totales individuales y la muestro por pantalla
     	 double columntotal = 0;
       	 SQLiteDatabase db=base.getReadableDatabase();
       	 Cursor cursor1 = db.rawQuery("SELECT SUM(total) FROM Milista", null);
       	 if(cursor1.moveToFirst()) {
            columntotal = cursor1.getDouble(0);
        }
              	 
        //Formateo la suma para que la muestre con dos decimales como maximo          
        DecimalFormat formateador = new DecimalFormat("#.##"); 
        
        String  sumtotal= (formateador.format(columntotal))+ "  €";
        resultado.setText("Total compra:  " + sumtotal);
       	 
       
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
			
			Typeface fuente = Typeface.createFromAsset(getAssets(), "gloriahallelujah.ttf");
				
			TextView lblDescripcion = (TextView)item.findViewById(R.id.txtdescripcion);
			lblDescripcion.setTypeface(fuente);
			lblDescripcion.setText(datos[position].getDescripcion());
				
			TextView lblPrecio = (TextView)item.findViewById(R.id.txtprecio);
			lblPrecio.setTypeface(fuente);
			lblPrecio.setText(datos[position].getPrecio());
				
			TextView lblCantidad = (TextView)item.findViewById(R.id.txtcantidad);
			lblCantidad.setTypeface(fuente);
			lblCantidad.setText(datos[position].getCantidad());
				
			TextView lblTotal = (TextView)item.findViewById(R.id.txttotal);
			lblTotal.setTypeface(fuente);
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
            		//Obtengo el string de la subactividad MostrarProducto (update o delete)
                    final String dato = pData.getExtras().getString(MostrarProducto.DATO_SUBACTIVIDAD );
                    
                    //Aquí se hara lo que se desee con el valor recuperado en este caso ejecutamos la sentencia SQL 
                    SQLiteDatabase db=base.getWritableDatabase();
                    db.execSQL(dato);
                                                           
                    Intent intent = new Intent(Carrito.this, Carrito.class);
                    finish();
                    startActivity(intent);
                }
            }
    }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.ayuda:
			Intent intent = new Intent(Carrito.this, Ayuda.class);
			startActivity(intent);
			return true;
		case R.id.acercade:
			Intent intent1 = new Intent(Carrito.this, Acercade.class);
			startActivity(intent1);
			return true;
		case R.id.salir:
			System.exit(0);
			return true;	
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	

}
