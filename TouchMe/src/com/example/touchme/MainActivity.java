package com.example.touchme;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	StringBuilder stringBuilder = new StringBuilder();
	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		  this.textView = (TextView) findViewById( R.id.strXY );
		  this.textView.setText("X: ,Y: ");//texto inicial
		  //evento Touch
		  this.textView.setOnTouchListener( new OnTouchListener()
		  {
		   @Override
		   public boolean onTouch( View arg0, MotionEvent arg1 ) {

		    stringBuilder.setLength(0);
		    //si la acción que se recibe es de movimiento
		    if( arg1.getAction() == MotionEvent.ACTION_MOVE )
		    {
		     stringBuilder.append("Moviendo, X:" + arg1.getX() + ", Y:" + arg1.getY() );
		    }
		    else
		    {
		     stringBuilder.append( "Detenido, X:" + arg1.getX() + ", Y:" + arg1.getY() );
		    }        
		    //Se muestra en pantalla
		    textView.setText( stringBuilder.toString() );
		    return true;
		   }   
		  });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
