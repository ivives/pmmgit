package org.ejemplo.aguilar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SubActividad extends Activity {
	private EditText textoEditable;
	
	public void onCreate( Bundle savedInstance ){
		super.onCreate(savedInstance);
		this.setContentView(R.layout.text);
		textoEditable = (EditText) this.findViewById(R.id.editar);
		textoEditable.setText(this.getIntent().getExtras().getString("text"));
		
		Button ok = (Button) this.findViewById(R.id.BotonOK);
		ok.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent( SubActividad.this, SubActividad.class );
				i.putExtra("text", textoEditable.getText());
				setResult( Activity.RESULT_OK, i );
				SubActividad.this.finish();
				
			}
			
		});
		
		Button cancel = (Button) this.findViewById(R.id.BotonCancelar);
		cancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent( SubActividad.this, SubActividad.class );
				i.putExtra("text", textoEditable.getText());
				setResult( Activity.RESULT_CANCELED, i );
				SubActividad.this.finish();
			}
			
		});
	}

}