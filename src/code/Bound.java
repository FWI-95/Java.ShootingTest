package code;

import java.awt.Rectangle;

public class Bound extends Rectangle{
	long CenterPoint;
	public Bound() {
		
	}
	
	public Rectangle getCenter() {
		return new Rectangle(x + (width / 2), y + (height / 2));
	}
}
