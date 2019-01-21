package code;

import java.awt.ItemSelectable;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Player extends Rectangle{
	
	Frame frame;
	Keyboard keys;
	Mouse mouse;
	
	boolean Alive = true;
	
	long BaseMovSpeed = 4;
	long BaseShtSpeed = 100;
	long BaseBulletCount = 15;
	long BaseBulletTTL = 300;
	long BaseBulletMovSpeed = 20;
	
	long TSLShoot = 0;
	
	int SpawnItemAfter = 5;
	int ItemSpawnChance = 100;
	
	long posX;
	long posY;
	
	long EnemiesKilled = 0;
	
	ArrayList<Item> Items;
	
	public Player(Keyboard k, Mouse m, Frame f) {
		keys = k;
		mouse = m;
		frame = f;
		
		posX = 150;
		posY = 150;
		
		width = 50;
		height = 50;
		
		Items = new ArrayList<Item>();
		
		TransferPos();
		
//		System.out.println("Center X: " + getCenterX() + " y: " + getCenterY());
	}
	
	public void TransferPos() {
		x = (int) posX;
		y = (int) posY;
	}
	
	public void update(ArrayList<Bullet> Bullets, ArrayList<Item> SpawnedItems) {
		long MovSpeed = BaseMovSpeed;
		long ShtSpeed = BaseShtSpeed;
		long BulletCount = BaseBulletCount;
		long BulletTTL = BaseBulletTTL;
		long BulletMovSpeed = BaseBulletMovSpeed;
		boolean BulletPassthrough = false;
		
		for(int x = Items.size() - 1; x > 0; x--) {
			if(Items.get(x).MTTL < (System.currentTimeMillis() - Items.get(x).MAliveSince)){
				Items.remove(x);
			} else {
				switch(Items.get(x).MType) {
				case Item.MTMovement:
					MovSpeed *= Items.get(x).MValue;
					break;
				case Item.MTShootSpeed:
					ShtSpeed *= Items.get(x).MValue;
					break;
				case Item.MTBulletCount:
					BulletCount *= Items.get(x).MValue;
					break;
				case Item.MTBulletLife:
					BulletTTL *= Items.get(x).MValue;
					break;
				case Item.MTBulletPassthrough:
					BulletPassthrough = true;
					break;
				}
			}
		}
		
		if(mouse.isButtonDown(MouseEvent.BUTTON1)) {
			if(((System.currentTimeMillis() - TSLShoot) > ShtSpeed) && (Bullets.size() < BulletCount)){
				Bullets.add(new Bullet(this, mouse.getPointer(), BulletTTL, BulletMovSpeed, BulletPassthrough));
				TSLShoot = System.currentTimeMillis();
			}
		}
		
		if(keys.isKeyDown(KeyEvent.VK_W))Move(0,-1, MovSpeed);
		if(keys.isKeyDown(KeyEvent.VK_D))Move(1,0, MovSpeed);
		if(keys.isKeyDown(KeyEvent.VK_S))Move(0,1, MovSpeed);
		if(keys.isKeyDown(KeyEvent.VK_A))Move(-1,0, MovSpeed);
		
		for(int i = 0; i < SpawnedItems.size(); i++) {
			if(this.intersects(SpawnedItems.get(i))) {
				SpawnedItems.get(i).Retrieved();
				Items.add(SpawnedItems.get(i));
				frame.RetrieveItem(i);				
			}
		}
	}
	
	public void Move(int MoveX , int MoveY, long MovSpeed) {
		if((x + (MoveX * MovSpeed)) < 0) MoveX = 0;
		if((x + (MoveX * MovSpeed) + width) > frame.getBounds().width) MoveX = 0;
		
		if((y + (MoveY * MovSpeed)) < 0) MoveY = 0;
		if((y + (MoveY * MovSpeed) + height) > frame.getBounds().height) MoveY = 0;

		posX += MoveX * MovSpeed;
		posY += MoveY * MovSpeed;
		
		TransferPos();
	}
	
	public void EnemyKilled() {
		if((EnemiesKilled % SpawnItemAfter) == 0) {
			if((Math.random() * 100) < ItemSpawnChance) {
				frame.SpawnItem();
			}
		}
		EnemiesKilled++;
	}
	
	public ArrayList<Item> getItems(){
		return Items;
	}
}
