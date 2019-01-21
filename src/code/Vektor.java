package code;

public class Vektor {
	double fromX;
	double fromY;
	
	double destinationX;
	double destinationY;
	
	double percentX;
	double percentY;
	
	double distanceX;
	double distanceY;
	double distance;
	
	public Vektor(double startX, double startY, double endX, double endY) {
		fromX = startX;
		fromY = startY;
		
		destinationX = endX;
		destinationY = endY;
		
		distanceX = destinationX - fromX;
		distanceY = destinationY - fromY;
		
		distance = Math.abs(distanceX) + Math.abs(distanceY);
		
		percentX = distanceX / distance;
		percentY = distanceY / distance;
		
//		Debug("fx: " + fromX + " fy: " + fromY);
//		Debug("destX: " + destinationX + " destY:" + destinationY);
//		Debug("distX: " + distanceX + " distY: " + distanceY);
//		Debug("percX: " + percentX + " percY: " + percentY);
	}
	
	public void Debug(String text) {
		System.out.println(text);
	}
}
