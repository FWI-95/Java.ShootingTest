package code.engine;

import java.awt.event.KeyEvent;

public class Main {

	static long UpdateSpeed = 25;
	
	public static void main(String[] args) {
		
		Frame f = new Frame();
		f.makeStrat();
		
		long LastUpdate = System.currentTimeMillis();
		while(true){
			if((System.currentTimeMillis() - LastUpdate) > UpdateSpeed){
				f.calc();
				f.draw();
				LastUpdate = System.currentTimeMillis();
			}
		}
	}

}
