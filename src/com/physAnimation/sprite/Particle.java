package com.physAnimation.sprite;

import toxi.physics2d.VerletParticle2D;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Particle extends VerletParticle2D {
	 private String name = "Sprite";
	 protected int color = Color.CYAN;
	 
	  public Particle(float x, float y) {
	    super(x,y);
	  }
	 
	  public void draw(Canvas canvas) {
			Paint paint = new Paint();
			paint.setStyle(Paint.Style.FILL);
			paint.setAntiAlias(true);
			paint.setColor(color);
			canvas.drawText(name, x, y, paint);
	  }
}
