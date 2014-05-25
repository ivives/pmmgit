package com.example.proyecto2;



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
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class Lector extends Activity {

	//Se le llama cuando se crea por primera vez la actividad
	  private static final String BS_PACKAGE = "com.google.zxing.client.android";
	  public static final int REQUEST_CODE = 0x0000c0de;
	  public static final String DATO_SUBACTIVIDAD="";
	  private Activity activity;
	  private String contents;
	  //Los datos resultantes
	  String strCodigo;
	  String strDescripcion;
	  double douPrecio;
	    
	  CreaBase base; 
	  
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activity=this;
    setContentView(R.layout.activity_lector);
    	 
    base = new CreaBase(this,"dbase",null,1);   		
    
    	//Creamos el intent para llamar al lector de c�digos
		Intent intentScan = new Intent(BS_PACKAGE + ".SCAN");
		intentScan.putExtra("PROMPT_MESSAGE", "Enfoque entre 9 y 11 cm.viendo solo el codigo de barras");
		String targetAppPackage = findTargetAppPackage(intentScan);
		if (targetAppPackage == null) {
		showDownloadDialog();
		} else startActivityForResult(intentScan, REQUEST_CODE);
		
			    
	                  
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
//Si Barcode Scanner no esta instalado
private AlertDialog showDownloadDialog() {
	  final String DEFAULT_TITLE = "Instalar Barcode Scanner?";
	  final String DEFAULT_MESSAGE ="Esta aplicacion necesita Barcode Scanner. Quiere instalarla?";
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
            // Si la android market no esta instalada mostramos
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
		        	//Toast.makeText(this, contents, Toast.LENGTH_LONG).show();
		        			        	
		        	//Obtenemos los datos
		    		DoPOST mDoPOST = new DoPOST(Lector.this, contents);
		    		mDoPOST.execute("");

		        }
		      }
		      	
		    	    
		      return ;
	  }
	  
	  //Creamos una tarea asincr�nica (asynctask) para hacer en un segundo plano la consulta a la base de datos
	  public class DoPOST extends AsyncTask<String, Void, Boolean>{

			Context mContext = null;
			String strCodeToSearch = "";
						
			Exception exception = null;
			
			DoPOST(Context context, String codeToSearch){
				mContext = context;
				strCodeToSearch = codeToSearch;
			}

			//Contendr� el c�digo principal de nuestra tarea
			@Override
			protected Boolean doInBackground(String... arg0) {

				try{

					//Configuraci�n de los par�metros
					ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					nameValuePairs.add(new BasicNameValuePair("CodeToSearch", strCodeToSearch));	
					
					//Creaci�n de la petici�n HTTP
					HttpParams httpParameters = new BasicHttpParams();

					//Tiempos de espera para la petici�n
					HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
					HttpConnectionParams.setSoTimeout(httpParameters, 15000);			

					//Hacemos la petici�n a la base de datos
					HttpClient httpclient = new DefaultHttpClient(httpParameters);
					HttpPost httppost = new HttpPost("http://192.168.1.103/clienteservidor/login.php");//local
					//HttpPost httppost = new HttpPost("http://ivivesdam.zz.mu/clienteservidor/login.php");//185.28.20.38
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));        
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();

					String result = EntityUtils.toString(entity);

					// Creamos un objeto JSON con la respuesta de la petici�n
					JSONObject jsonObject = new JSONObject(result);

					//Recuperamos los datos del objeto JSON
					strCodigo = jsonObject.getString("codigo");
					strDescripcion = jsonObject.getString("descripcion");
					douPrecio = jsonObject.getDouble("precio");
					
					
					// Insertamos en la db auxiliar del dispositivo el producto escaneado
					int cantidad = 1;
					double total = douPrecio * cantidad;
      
					String valor = "INSERT INTO Milista VALUES  ("
															+ "'"+strCodigo+"', "
															+ "'"+strDescripcion+"',"
															+ " '"+douPrecio+"',"
															+ " '"+cantidad+"',"
															+ "'"+total+"')";
					
					 SQLiteDatabase db=base.getWritableDatabase();
	                    db.execSQL(valor);

					finish();
					
					Intent intent = new Intent(Lector.this, Carrito.class);
  						  					
  					startActivity(intent);
									

				}catch (Exception e){
					Log.e("ClienteServidor", "Error:", e);
					exception = e;
				}

				return true;
			}

			@Override
			protected void onPostExecute(Boolean valid){
										
				
				if(exception != null){
					Toast.makeText(mContext, exception.getMessage(), Toast.LENGTH_LONG).show();
				}
			}

		}

	  
}
