package code.entities;

import java.awt.Point;
import java.awt.Rectangle;

import code.math.Vektor;

public class Bullet extends Rectangle{
	double posX;
	double posY;
	
	double MovSpeed;
	double TTL;
	
	boolean Passthrough;
	
	public boolean Alive = true;
	
	long AliveSince;
	
	Vektor v;
	
	public Bullet(Player p, Point c, long pTTL, long pMovSpeed, boolean pPassthrough) {
		MovSpeed = pMovSpeed;
		TTL = pTTL;
		Passthrough = pPassthrough;
		AliveSince = System.currentTimeMillis();
		
		width = 20;
		height = 20;
//		System.out.println("Player x: " + p.getCenterX() + " y: " + p.getCenterY());
		posX = p.getCenterX() - (width / 2);
		posY = p.getCenterY() - (height / 2);
		
		TransferPos();
		
		v = new Vektor(p.getCenterX(), 
				p.getCenterY(), 
				c.getX(), 
				c.getY());
	}
	
	public void update(int FrameWidth, int FrameHeight) {
		if(TTL < (System.currentTimeMillis() - AliveSince))Alive = false;
		
		posX += (v.percentX * MovSpeed);
		posY += (v.percentY * MovSpeed);
		
		if(posX < 0 || posY < 0)Alive = false;
		if(posX > FrameWidth || posY > FrameHeight)Alive = false;
		
//		System.out.println("FromX: " + x + " FromY: " + y + " ToX: " + posX + " ToY: " + posY);
		
		TransferPos();
	}
	
	public void TransferPos() {
		x = (int) posX;
		y = (int) posY;
		
	}
	
	public void HasHit() {
		if(!Passthrough)Alive = false;
	}

}
