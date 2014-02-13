package org.ejemplo.aguilar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EjemploComunicacionActividades extends Activity {
	private static final int TEXTO_ENVIADO = 0;
	private TextView t;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		t = (TextView) this.findViewById(R.id.textView);
        Button b = (Button) this.findViewById(R.id.botonCambiar);
        b.setOnClickListener(new OnClickListener( ){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(EjemploComunicacionActividades.this, SubActividad.class );
				i.putExtra("text", t.getText());
				EjemploComunicacionActividades.this.startActivityForResult(i, TEXTO_ENVIADO);
			}
        	
        });
    }

	@Override
	protected void onActivityResult(int codigoEnviado, int codigoResultado, Intent datos) {
		if ( codigoEnviado == TEXTO_ENVIADO ){
			if ( codigoResultado == Activity.RESULT_OK ){	
				t.setText(datos.getExtras().get("text").toString());
			}
		}
		
	}
}