package com.example.escanercodigobarras;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button scanner = (Button) this.findViewById(R.id.scan_barcode);
        scanner.setOnClickListener((OnClickListener) this);
    }

    
    public void onClick(View view){
	Intent intent = new Intent("com.google.zxing.client.android.SCAN");
	intent.putExtra("SCAN_MODE","PRODUCT_MODE");
	this.startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if(resultCode == RESULT_OK){
		TextView tv = (TextView) this.findViewById(R.id.tvResult);
		tv.setText(data.getStringExtra("SCAN_RESULT"));
	}
    }
		
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
