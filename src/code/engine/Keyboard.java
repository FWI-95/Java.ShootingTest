package code.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	boolean[] keys;
	Frame frame;
	
	public Keyboard(Frame f) {
		frame = f;
		keys = new boolean[2048];
		System.out.println("Keyboard initialized");
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) frame.changeState(Modes.PAUSED);
		keys[ke.getKeyCode()] = true;
		System.out.println(ke.toString());
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		keys[ke.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isKeyDown(int code) {
		return(keys[code]);
	}
	
}
