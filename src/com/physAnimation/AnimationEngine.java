package com.physAnimation;

import android.os.Message;

class AnimationEngine implements Runnable {
	private MainActivity activity;
	private int delay = 20;

	public AnimationEngine(MainActivity activity) {
		this.activity = activity;
	}

	private void tick() {
		Message message = new Message();
		message.what = MainActivity.TICK;
		activity.messageHandler.sendMessage(message);
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			tick();
			try {
				Thread.sleep(delay); 
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
