package com.example.pintapantalla;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {

	DrawingView dv ;   
	private Paint pincel;
	
	private static final String MNU_OPC1 = "BLACK";
	private static final String MNU_OPC2 = "RED";
	private static final String MNU_OPC3 = "BLUE";
	private static final String MNU_OPC4 = "GREEN";
	private static final String MNU_OPC5 = "WHITE";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String color = "BLACK";
		
		dv = new DrawingView(this);
	    setContentView(dv);
	    pincel = new Paint();
	    pincel.setAntiAlias(true);
	    pincel.setDither(true);
	    pincel.setColor(Color.parseColor(color));
	    pincel.setStyle(Paint.Style.STROKE);
	    pincel.setStrokeJoin(Paint.Join.ROUND);
	    pincel.setStrokeCap(Paint.Cap.ROUND);
	    pincel.setStrokeWidth(12);
	}

	class DrawingView extends View {

        public int width;
        public  int height;
        private Bitmap  mBitmap;
        private Canvas  mCanvas;
        private Path    mPath;
        private Paint   mBitmapPaint;
        Context context;

       public DrawingView(Context c) {
        super(c);
        context=c;
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);  

        }

        @Override
         protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        }
        @Override
        protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap( mBitmap, 0, 0, mBitmapPaint);

        canvas.drawPath( mPath,  pincel);

 
        }

        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 4;

        private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
        }
        private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
             mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;


        }
        }
        private void touch_up() {
        mPath.lineTo(mX, mY);
 
        // commit the path to our offscreen
        mCanvas.drawPath(mPath,  pincel);
        // kill this so we don't double draw
        mPath.reset();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
        }  
        }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
		return true;
	}
	
	@Override
    public String onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
	        case R.id.MnuOpc1:
	            
	            return true;
	        case R.id.MnuOpc2:
	        	
	            return true;
	        case R.id.MnuOpc3:
	        	
	            return true;
	        case R.id.MnuOpc4:
	        	
	            return true;
	        case R.id.MnuOpc5:
	        	
	            return MNU_OPC5;
	        default:
	            return super.onOptionsItemSelected(item);
        }
    }

}
