package com.physAnimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends Activity {

	protected static final int TICK = 1;
	private AnimationView animationView = null;
	public Handler messageHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MainActivity.TICK:
				animationView.invalidate();
				break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		// Set fullscreen
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.animationView = new AnimationView(this);
		this.setContentView(this.animationView);

		new Thread(new AnimationEngine(this)).start();
	}

}
