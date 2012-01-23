package com.physAnimation.sprite;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball extends Particle {
	 private String name = "Ball";
	 private float radius = 5.0f;
	  public Ball (float x, float y) {
	    super(x,y);
	  }
	 
	  public void draw(Canvas canvas) {
			Paint paint = new Paint();
			paint.setStyle(Paint.Style.FILL);
			paint.setAntiAlias(true);
			paint.setColor(color);
			canvas.drawCircle(x, y, 2, paint);
			
			paint.setStyle(Paint.Style.STROKE);
			paint.setColor( Color.GREEN );
			canvas.drawCircle(x, y, radius, paint);			
	  }
}
