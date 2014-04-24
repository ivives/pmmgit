package com.example.clienteservidor;

import java.util.ArrayList;

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

import com.example.clienteservidor.R;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button botonBuscar = null;
	EditText codigoBusqueda = null;
	TextView codigo = null;
	TextView descripcion = null;
	TextView precio = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		botonBuscar = (Button) findViewById(R.id.botonBuscar);
		codigoBusqueda = (EditText) findViewById(R.id.codigoBuscar);
		codigo = (TextView) findViewById(R.id.textoCodigo);
		descripcion = (TextView) findViewById(R.id.textoDescripcion);
		precio = (TextView) findViewById(R.id.textoPrecio);
		

		//Setup the Button's OnClickListener
		botonBuscar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Get the data
				DoPOST mDoPOST = new DoPOST(MainActivity.this, codigoBusqueda.getText().toString());
				mDoPOST.execute("");
				botonBuscar.setEnabled(false);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class DoPOST extends AsyncTask<String, Void, Boolean>{

		Context mContext = null;
		String strCodeToSearch = "";
		
		//Result data
		String strCodigo;
		String strDescripcion;
		double douPrecio;
		
		
		Exception exception = null;
		
		DoPOST(Context context, String codeToSearch){
			mContext = context;
			strCodeToSearch = codeToSearch;
		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			try{

				//Setup the parameters
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("CodeToSearch", strCodeToSearch));	
				//Add more parameters as necessary

				//Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();

				//Setup timeouts
				HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);			

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://192.168.1.4/clienteservidor/login.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));        
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();

				String result = EntityUtils.toString(entity);

				// Create a JSON object from the request response
				JSONObject jsonObject = new JSONObject(result);

				//Retrieve the data from the JSON object
				strCodigo = jsonObject.getString("codigo");
				strDescripcion = jsonObject.getString("descripcion");
				douPrecio = jsonObject.getDouble("precio");
				

			}catch (Exception e){
				Log.e("ClienteServidor", "Error:", e);
				exception = e;
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean valid){
			//Update the UI
			codigo.setText("Codigo: " + strCodigo);
			descripcion.setText("Descripcion: " + strDescripcion);
			precio.setText("Precio: " + douPrecio);
				
			
			botonBuscar.setEnabled(true);
			
			if(exception != null){
				Toast.makeText(mContext, exception.getMessage(), Toast.LENGTH_LONG).show();
			}
		}

	}
}
