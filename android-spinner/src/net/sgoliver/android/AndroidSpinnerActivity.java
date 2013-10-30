package net.sgoliver.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class AndroidSpinnerActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final TextView lblMensaje = (TextView)findViewById(R.id.LblMensaje);
        final Spinner cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);
        
        
        
        final String[] datos =
            new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};
     
        ArrayAdapter<String> adaptador =
            new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        
        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
         
        cmbOpciones.setAdapter(adaptador);

        cmbOpciones.setOnItemSelectedListener(
        	new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent,
                    android.view.View v, int position, long id) {
                        lblMensaje.setText("Seleccionado: " + datos[position]);
                }
         
                public void onNothingSelected(AdapterView<?> parent) {
                    lblMensaje.setText("");
                }
        });
    }
}