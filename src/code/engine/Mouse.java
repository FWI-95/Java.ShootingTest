package code.engine;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener{
	boolean[] buttons;
	public Mouse() {
		buttons = new boolean[64];
	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
		
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		
	}

	@Override
	public void mouseExited(MouseEvent me) {
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		buttons[me.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		buttons[me.getButton()] = false;
	}
	
	public Point getPointer() {
		return MouseInfo.getPointerInfo().getLocation();
	}
	
	public boolean isButtonDown(int code) {
		return buttons[code];
	}
}
