package code.entities;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Enemy extends Rectangle{
	
	public boolean Alive = false;
	
	long MovSpeed = 4;
	
	long posX;
	long posY;
	
	public long lastDestinationX;
	public long lastDestinationY;
	
	public double lastPercX;
	public double lastPercY;
	
	public long lastAfterX;
	public long lastAfterY;
	
	long MaxSpawnTime = 250;
	
	long DistanceToPlayer = 100;

	public Enemy(Player p, int fwidth, int fheight) {
		width = 50;
		height = 50;
		
		long BeginSpawn = System.currentTimeMillis();
		while(!Alive) {
			posX = (long) (Math.random() * (fwidth - width));
			posY = (long) (Math.random() * (fheight - height));
			
			if((Math.abs(posX - p.getBounds().x) > DistanceToPlayer) && (Math.abs(posY - p.getBounds().y) > DistanceToPlayer))Alive = true;
		}
		
		TransferPos();
	}
	
	public void update(Player p, ArrayList<Bullet> Bullets, int fwidth, int fheight) {
		for(int b = 0; b < Bullets.size(); b++) {
			if(Bullets.get(b).Alive)if(Bullets.get(b).intersects(this)) {
				Alive = false;
				Bullets.get(b).HasHit();
				p.EnemyKilled();
			}
		}
		
		if(p.intersects(this))p.Alive = false;

		
		double distX = (p.getBounds().x - (p.getBounds().width / 2)) - (getBounds().x - (getBounds().width / 2));
		double distY = (p.getBounds().y - (p.getBounds().height / 2)) - (getBounds().y - (getBounds().height / 2));
		
		double distG = Math.abs(distX) + Math.abs(distY);
		
		double percX = distX / distG;
		double percY = distY / distG;
		
		lastDestinationX = p.getBounds().x - (p.getBounds().width / 2);
		lastDestinationY = p.getBounds().y - (p.getBounds().height / 2);
		lastPercX = percX;
		lastPercY = percY;
		lastAfterX = (long) (percX * MovSpeed);
		lastAfterY = (long) (percY * MovSpeed);
		
//		if((posX + (percX * MovSpeed)) < 0) percX = 0;
//		if((posX + (percX * MovSpeed) + getBounds().x) > fwidth) percY = 0;
//		
//		if((posY + (percY * MovSpeed)) < 0) percY = 0;
//		if((posY + (percY * MovSpeed) + getBounds().y) > fwidth) percY = 0;
		
		posX += percX * MovSpeed;
		posY += percY * MovSpeed;
	
		TransferPos();
	}
	
	public void TransferPos() {
		x = (int) posX;
		y = (int) posY;
	}
}
