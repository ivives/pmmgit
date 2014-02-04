package com.example.basecentrosprofesores;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ConsultaCentros extends Activity {

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
		final Button boton=(Button)findViewById(R.id.boton);
		//ArrayList<CentrosIes> centros=new ArrayList<CentrosIes>(); 
		
		try 
		{
			
			String[] campos = new String[] {"cod_centro","nombre","direccion"};
			
			CreaBase base=new CreaBase(this,"dbase",null,1);
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
		
		boton.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				
			}
		});
	}
}
