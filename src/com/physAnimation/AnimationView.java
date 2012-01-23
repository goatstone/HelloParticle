package com.physAnimation;

import com.physAnimation.sprite.Ball;
import com.physAnimation.sprite.Particle;

import toxi.geom.Rect;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.VerletSpring2D;
import toxi.physics2d.behaviors.AttractionBehavior;
import toxi.physics2d.behaviors.GravityBehavior;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class AnimationView extends View {

	VerletPhysics2D physics;
	Particle head;

	public AnimationView(Context context) {
		super(context);

		physics = new VerletPhysics2D();
		physics.addBehavior(new GravityBehavior(new Vec2D(0, 0.1f)));
		Particle prev = null;
		for (int i = 0; i < 6; i++) {
			Particle p = new Ball(20, 30);
			physics.addBehavior(new AttractionBehavior(p, 20, -2.2f, 1.01f));
			physics.addParticle(p);
			if (prev != null) {
				VerletSpring2D s = new VerletSpring2D(p, prev, 35, 1);
				physics.addSpring(s);
			}else{
				head = p;
				head.lock();
			}
			prev = p;
		}

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		physics.setWorldBounds(new Rect(0, 0, getMeasuredWidth(),
				getMeasuredHeight()));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		head.x = event.getX();
		head.y = event.getY();
		 super.onTouchEvent(event);
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		physics.update();
		for (VerletSpring2D s : physics.springs) {
			Paint paint = new Paint();
			paint.setStyle(Paint.Style.FILL);
			paint.setAntiAlias(true);
			paint.setColor(Color.GRAY);
			paint.setStrokeWidth(3.0f);
			canvas.drawLine(s.a.x, s.a.y, s.b.x, s.b.y, paint);
			// spring.draw();
		}
		for (int i = physics.particles.size() - 1; i >= 0; i--) {
			((Particle) physics.particles.get(i)).draw(canvas);
		}

	}
}


//mousePos = new Vec2D(mouseX, mouseY);
//// create a new positive attraction force field around the mouse position (radius=250px)
//mouseAttractor = new AttractionBehavior(mousePos, 250, 0.9f);
//physics.addBehavior(mouseAttractor);
//}
//
//void mouseDragged() {
//// update mouse attraction focal point
//mousePos.set(mouseX, mouseY);
//}
//
//void mouseReleased() {
//// remove the mouse attraction when button has been released
//physics.removeBehavior(mouseAttractor);

// map the direction of each spring to a hue
// float currHue=map(s.b.sub(s.a).heading(),-PI,PI,0,1);
// define a color in HSV and convert into ARGB format (32bit packed
// integer)
// stroke(TColor.newHSV(currHue,1,1).toARGB());

// this.setBackgroundDrawable(this.getResources().getDrawable(
// R.drawable.ic_launcher));
