package com.example.bdcentrosprofesores;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ConsultaCentros extends Activity {

	public static final int CODIGO_RESPUESTA = 123;
	
	CreaBase base;
private Centros[] datos;
	
	class AdaptadorCentro extends ArrayAdapter<Centros> 
	{
		Activity a;
		AdaptadorCentro(Activity b) 
		{
			super(b, R.layout.listin, datos);
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
			View item = inflater.inflate(R.layout.listin, null);
			 	 	 
			final TextView lblCodigo=(TextView)item.findViewById(R.id.codigo);
			final TextView lblNombre = (TextView)item.findViewById(R.id.nombre);
			final TextView lblDireccion = (TextView)item.findViewById(R.id.direccion);
			
			lblNombre.setText(datos[position].getNomCentro());
			lblCodigo.setText(datos[position].getCodCentro());
			lblDireccion.setText(datos[position].getDireccion());
			
			return(item);
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consulta_centros);
		
		
		//ArrayList<CentrosIes> centros=new ArrayList<CentrosIes>(); 
		
		try 
		{
			
			String[] campos = new String[] {"cod_centro","nombre","direccion"};
			
			base=new CreaBase(this,"dbase",null,1);
			SQLiteDatabase db=base.getReadableDatabase();
			
			
			Cursor rs=db.query("centros", campos, null,null,null,null,null);
			
			datos=new Centros[rs.getCount()+1];//Devuelve el numero de filas + 1 
			datos[0]= new Centros("Codigos","Nombres","Direccion");
			int i=1;
	        if (rs.moveToFirst()) 
	        {
	                do 
	                {
	                		String cod=rs.getString(0);
	                		String nom=rs.getString(1);
	                        String dir=rs.getString(2);
	                        
	                        datos[i]=new Centros(cod,nom,dir);
	                        i++;       
	                }
	                while (rs.moveToNext());
	        }
			
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		
		final Spinner spinner=(Spinner)findViewById(R.id.centros);
		AdaptadorCentro adaptador =new AdaptadorCentro(this); 
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adaptador);
		

		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				Intent intent = new Intent(ConsultaCentros.this, MostrarCentro.class);
				
				Bundle b = new Bundle();
				String codigocentro =((Centros)parent.getAdapter().getItem(position)).getCodCentro();
				String nombrecentro=((Centros)parent.getAdapter().getItem(position)).getNomCentro();
				String direccion=((Centros)parent.getAdapter().getItem(position)).getDireccion();
				
				b.putString("Codigo", codigocentro);
				b.putString("Nombre", nombrecentro);
				b.putString("Direccion", direccion);
				  				
				intent.putExtras(b);
				
				
				if(position > 0)
				startActivityForResult(intent, CODIGO_RESPUESTA);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

			
			
		});
	
		
	}
	
	protected void onActivityResult(int requestCode,int resultCode, Intent pData)            
    {
        if ( requestCode == CODIGO_RESPUESTA )//Si el código de respuesta es igual al requestCode
            {
            if (resultCode == Activity.RESULT_OK )//Si resultCode es igual a ok
                {
                    final String dato = pData.getExtras().getString(MostrarCentro.DATO_SUBACTIVIDAD );//Obtengo el string de la subactividad
                    //Aquí se hara lo que se desee con el valor recuperado

                    SQLiteDatabase db=base.getWritableDatabase();
                    db.execSQL(dato);
                    
                    Intent intent = new Intent(ConsultaCentros.this, ConsultaCentros.class);
                    finish();
                    startActivity(intent);
                }
            }
    }
	
}
