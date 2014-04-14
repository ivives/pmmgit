package prueba.src;


import java.util.List;


import bdMySQL.BDFamilia;
import clases.Familia;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PruebaMySQLActivity extends Activity {
    /** Called when the activity is first created. */

	public List<Familia> familias;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		final Button btnConectar = (Button)findViewById(R.id.btnConectar);
		final TextView txtMensaje = (TextView)findViewById(R.id.txtMensaje);
		
	    btnConectar.setOnClickListener(new View.OnClickListener() {
		    @Override
			public void onClick(View arg0)
		    {
		    	familias = BDFamilia.getDatosFamilia();
		    	txtMensaje.setText( familias.get(1).getNombre() );
		    }
	    }); 
		

    }
}