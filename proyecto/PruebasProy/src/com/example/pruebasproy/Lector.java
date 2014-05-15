package com.example.pruebasproy;


import java.util.ArrayList;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Lector extends Activity {

	//Se le llama cuando se crea por primera vez la actividad
	  private static final String BS_PACKAGE = "com.google.zxing.client.android";
	  public static final int REQUEST_CODE = 0x0000c0de;
	  private Button boton;
	  private Activity activity;
	  private String contents;
	  private TextView textView;
	  
	  int cantidad;
	  double total;
	  double suma = 0;	  
	  List<Producto> lista = new ArrayList<Producto>();
	  
	    
	  
	//Los datos resultantes
		String strCodigo;
		String strDescripcion;
		double douPrecio;
		
		
	  
	  TableLayout tabla;  
	  TableLayout cabecera;  
	  TableRow.LayoutParams layoutFila;  
	  TableRow.LayoutParams layoutTexto;
	  TableRow.LayoutParams layoutPrecio;
	  TableRow.LayoutParams layoutCantidad;
	  TableRow.LayoutParams layoutTotal;
	  	  
	  Resources rs; 
	  
	  
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activity=this;
    setContentView(R.layout.activity_lector);
    	textView = (TextView)findViewById(R.id.txtSuma);  
	    boton=(Button)findViewById(R.id.button1);
	  
	    boton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intentScan = new Intent(BS_PACKAGE + ".SCAN");
			    intentScan.putExtra("PROMPT_MESSAGE", "Enfoque entre 9 y 11 cm.viendo solo el codigo de barras");
			    String targetAppPackage = findTargetAppPackage(intentScan);
			    if (targetAppPackage == null) {
			      showDownloadDialog();
			    } else startActivityForResult(intentScan, REQUEST_CODE);
			}
		});
	    
	    rs = this.getResources();  
        tabla = (TableLayout)findViewById(R.id.tabla);  
        cabecera = (TableLayout)findViewById(R.id.cabecera);  
        layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,  
                                               TableRow.LayoutParams.WRAP_CONTENT);  
        layoutTexto = new TableRow.LayoutParams(250,TableRow.LayoutParams.WRAP_CONTENT);
        layoutPrecio = new TableRow.LayoutParams(70,TableRow.LayoutParams.WRAP_CONTENT);
        layoutCantidad = new TableRow.LayoutParams(90,TableRow.LayoutParams.WRAP_CONTENT);
        layoutTotal = new TableRow.LayoutParams(70,TableRow.LayoutParams.WRAP_CONTENT);
        agregarCabecera();  
       
}

private String findTargetAppPackage(Intent intent) {
    PackageManager pm = activity.getPackageManager();
    List<ResolveInfo> availableApps = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
    if (availableApps != null) {
      for (ResolveInfo availableApp : availableApps) {
        String packageName = availableApp.activityInfo.packageName;
        if (BS_PACKAGE.contains(packageName)) {
          return packageName;
        }
      }
    }
    return null;
  }
private AlertDialog showDownloadDialog() {
	  final String DEFAULT_TITLE = "Instalar Barcode Scanner?";
	  final String DEFAULT_MESSAGE =
	      "Esta aplicacion necesita Barcode Scanner. Quiere instalarla?";
	  final String DEFAULT_YES = "Si";
	  final String DEFAULT_NO = "No";

    AlertDialog.Builder downloadDialog = new AlertDialog.Builder(activity);
    downloadDialog.setTitle(DEFAULT_TITLE);
    downloadDialog.setMessage(DEFAULT_MESSAGE);
    downloadDialog.setPositiveButton(DEFAULT_YES, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        Uri uri = Uri.parse("market://details?id=" + BS_PACKAGE);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        try {
          activity.startActivity(intent);
        } catch (ActivityNotFoundException anfe) {
            // Hmm, market is not installed
        	Toast.makeText(Lector.this, "Android market no esta instalado,no puedo instalar Barcode Scanner", Toast.LENGTH_LONG).show();
        }
      }
    });
    downloadDialog.setNegativeButton(DEFAULT_NO, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {}
    });
    return downloadDialog.show();
  }

	  public void onActivityResult(int requestCode, int resultCode, Intent intent ) {
		    if (requestCode == REQUEST_CODE) {
		        if (resultCode == Activity.RESULT_OK) {
		        	contents = intent.getStringExtra("SCAN_RESULT");
		        	Toast.makeText(this, contents, Toast.LENGTH_LONG).show();

		          	  		
					//Obtenemos los datos
					DoPOST mDoPOST = new DoPOST(Lector.this, contents);
					mDoPOST.execute("");		
		          
		        }
		      }
		      		      
		      return ;
	  }
	  
	  public class DoPOST extends AsyncTask<String, Void, Boolean>{

			Context mContext = null;
			String strCodeToSearch = "";
						
			Exception exception = null;
			
			DoPOST(Context context, String codeToSearch){
				mContext = context;
				strCodeToSearch = codeToSearch;
			}

			@Override
			protected Boolean doInBackground(String... arg0) {

				try{

					//Configuracion de los parametros
					ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					nameValuePairs.add(new BasicNameValuePair("CodeToSearch", strCodeToSearch));	
					
					//Creacion de la peticion HTTP
					HttpParams httpParameters = new BasicHttpParams();

					//Tiempos de espera para la peticion
					HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
					HttpConnectionParams.setSoTimeout(httpParameters, 15000);			

					HttpClient httpclient = new DefaultHttpClient(httpParameters);
					HttpPost httppost = new HttpPost("http://192.168.1.103/clienteservidor/login.php");//local
					//HttpPost httppost = new HttpPost("http://ivivesdam.zz.mu/clienteservidor/login.php");//185.28.20.38
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));        
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();

					String result = EntityUtils.toString(entity);

					// Creamos un objeto JSON con la respuesta de la peticion
					JSONObject jsonObject = new JSONObject(result);

					//Recuperamos los datos del objeto JSON
					strCodigo = jsonObject.getString("codigo");
					strDescripcion = jsonObject.getString("descripcion");
					douPrecio = jsonObject.getDouble("precio");
					
					cantidad = 1;
					total = douPrecio * cantidad;
					
															
					lista.add(new Producto('"'+strCodigo+'"', '"'+strDescripcion+'"', '"'+douPrecio+'"', '"'+cantidad+'"'));
									
										
					suma += total;
					

				}catch (Exception e){
					Log.e("ClienteServidor", "Error:", e);
					exception = e;
				}

				return true;
			}

			@Override
			protected void onPostExecute(Boolean valid){
				//Actualizamos la interfaz de usuario
				
				agregarFilasTabla();
				
				
//				for(int i=0; i<lista.size(); i++){
//					double num=Double.valueOf(lista.get(i).getTotal()).doubleValue();
//					suma += num;
//				}
				
				//String totalSuma = String.valueOf(suma);
				//String pre = new Double(suma).toString();
				textView.setText(Double.toString(suma));
				
				
				if(exception != null){
					Toast.makeText(mContext, exception.getMessage(), Toast.LENGTH_LONG).show();
				}
			}

		}
	  
	  
	  public void agregarCabecera(){  
		     TableRow fila;  
		     TextView txtDescripcion ;
		     TextView txtPrecio ;
		     TextView txtCantidad;
		     TextView txtTotal;
		  
		     fila = new TableRow(this);  
			 fila.setLayoutParams(layoutFila);  
			  
			 txtDescripcion = new TextView(this);
			 txtPrecio = new TextView(this);
			 txtCantidad = new TextView(this);
			 txtTotal = new TextView(this);
		  
			 txtDescripcion.setText(rs.getString(R.string.descrip));  
			 txtDescripcion.setGravity(Gravity.CENTER_HORIZONTAL);  
			 txtDescripcion.setTextAppearance(this,R.style.etiqueta);  
			 txtDescripcion.setBackgroundResource(R.drawable.tabla_celda_cabecera);  
			 txtDescripcion.setLayoutParams(layoutTexto);
			 
			 txtPrecio.setText(rs.getString(R.string.valor));  
			 txtPrecio.setGravity(Gravity.CENTER_HORIZONTAL);  
			 txtPrecio.setTextAppearance(this,R.style.etiqueta);  
			 txtPrecio.setBackgroundResource(R.drawable.tabla_celda_cabecera);  
			 txtPrecio.setLayoutParams(layoutPrecio);
			 
			 txtCantidad.setText(rs.getString(R.string.canti));
			 txtCantidad.setGravity(Gravity.CENTER_HORIZONTAL);
			 txtCantidad.setTextAppearance(this,R.style.etiqueta);
			 txtCantidad.setBackgroundResource(R.drawable.tabla_celda_cabecera);
			 txtCantidad.setLayoutParams(layoutCantidad);
			 
			 txtTotal.setText(rs.getString(R.string.total));
			 txtTotal.setGravity(Gravity.CENTER_HORIZONTAL);
			 txtTotal.setTextAppearance(this,R.style.etiqueta);
			 txtTotal.setBackgroundResource(R.drawable.tabla_celda_cabecera);
			 txtTotal.setLayoutParams(layoutTotal);
			 
		  
			 fila.addView(txtDescripcion);
			 fila.addView(txtPrecio);
			 fila.addView(txtCantidad);
			 fila.addView(txtTotal);
			 cabecera.addView(fila);  
	  }  
		  
		 public void agregarFilasTabla(){  
		  
		     TableRow fila;  
		     TextView txtDescripcion;
		     TextView txtPrecio;
		     TextView txtCantidad;
		     TextView txtTotal;
		     
		     
		     
		    // for(int i=0; i<lista.size(); i++){
		    	
		    	// String douPrecio2 = String.valueOf(lista.get(i).getPrecio());
		     	String douPrecio2 = String.valueOf(douPrecio);
		    	  
		         fila = new TableRow(this);  
		         fila.setLayoutParams(layoutFila);  
		  
		         txtDescripcion = new TextView(this);
		         txtPrecio = new TextView(this);
		         txtCantidad = new EditText(this);
		         txtTotal = new TextView(this);
		  
		        // txtDescripcion.setText(lista.get(i).getDescripcion());
		         txtDescripcion.setText(strDescripcion);
		         txtDescripcion.setGravity(Gravity.CENTER_HORIZONTAL);  
		         txtDescripcion.setTextAppearance(this,R.style.etiqueta);  
		         txtDescripcion.setBackgroundResource(R.drawable.tabla_celda);  
		         txtDescripcion.setLayoutParams(layoutTexto);
		         
		         txtPrecio.setText(douPrecio2);  
				 txtPrecio.setGravity(Gravity.CENTER_HORIZONTAL);  
				 txtPrecio.setTextAppearance(this,R.style.etiqueta);  
				 txtPrecio.setBackgroundResource(R.drawable.tabla_celda);  
				 txtPrecio.setLayoutParams(layoutPrecio);
				 
				 
				 String cant = String.valueOf(cantidad);
				// txtCantidad.setText(lista.get(i).getCantidad());
				 txtCantidad.setText(cant);
				 txtCantidad.setGravity(Gravity.CENTER_HORIZONTAL);  
				 txtCantidad.setTextAppearance(this,R.style.etiqueta2);  
				 txtCantidad.setBackgroundResource(R.drawable.tabla_celda);  
				 txtCantidad.setLayoutParams(layoutCantidad);
				 
//				 String c = txtCantidad.getText().toString();
//				 Double cant = Double.valueOf(c).doubleValue();
				 Double tot = douPrecio * cantidad;
				 String totalStr = String.valueOf(tot);
				 
				 txtTotal.setText(totalStr);
				 txtTotal.setGravity(Gravity.CENTER_HORIZONTAL);
				 txtTotal.setTextAppearance(this,R.style.etiqueta);
				 txtTotal.setBackgroundResource(R.drawable.tabla_celda);
				 txtTotal.setLayoutParams(layoutPrecio);
				 
		         fila.addView(txtDescripcion);
		         fila.addView(txtPrecio);
		         fila.addView(txtCantidad);
		         fila.addView(txtTotal);
		  
		         tabla.addView(fila);  
		    // } 
		     
		     
		    } 
	  
	  
}
