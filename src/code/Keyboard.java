package code;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	boolean[] keys;
	boolean GameEscaped = false;
	
	public Keyboard() {
		keys = new boolean[1024];
		
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) GameEscaped = true;
		keys[ke.getKeyCode()] = true;
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
	
	public boolean hasEscaped() {
		return GameEscaped;
	}
}
