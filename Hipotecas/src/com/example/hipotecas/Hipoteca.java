package com.example.hipotecas;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
 
public class Hipoteca extends ListActivity {
 
   private HipotecaDbAdapter dbAdapter;
    private Cursor cursor;
    private HipotecaCursorAdapter hipotecaAdapter ;
    private ListView lista;
 
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_hipoteca);
 
      lista = (ListView) findViewById(android.R.id.list);
 
      dbAdapter = new HipotecaDbAdapter(this);
      dbAdapter.abrir();
 
      consultar();
   }
 
   private void consultar()
   {
      cursor = dbAdapter.getCursor();
      startManagingCursor(cursor);
      hipotecaAdapter = new HipotecaCursorAdapter(this, cursor);
      lista.setAdapter(hipotecaAdapter);
   }
 
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.hipoteca, menu);
      return true;
   }
}
