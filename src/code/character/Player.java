package code.character;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import code.engine.Frame;
import code.engine.Keyboard;
import code.engine.Mouse;
import code.level.Difficulty;
// CYBERPUNK CHARACTERS

public class Player extends Rectangle{
	
	Frame frame;
	Keyboard keys;
	Mouse mouse;

	Player_Stats Stats;
	
	long posX;
	long posY;
	
	public long EnemiesKilled = 0;
	
	final int Mode_Dead = 3;
	
	ArrayList<Item> Items;
	
	public Player(Keyboard k, Mouse m, Frame f, Difficulty d) {
		keys = k;
		mouse = m;
		frame = f;
				
		posX = 150;
		posY = 150;
		
		width = 50;
		height = 50;
		
		Stats = new Player_Stats(d);
		
		Items = new ArrayList<Item>();
		
		TransferPos();
		
//		System.out.println("Center X: " + getCenterX() + " y: " + getCenterY());
	}
	
	public void TransferPos() {
		x = (int) posX;
		y = (int) posY;
	}
	
	public void update(ArrayList<Bullet> Bullets, ArrayList<Item> SpawnedItems) {
		
		for(int x = Items.size() - 1; x > 0; x--) {
			if(Items.get(x).MTTL < (System.currentTimeMillis() - Items.get(x).MAliveSince)){
				switch(Items.get(x).MType) {
				case Item.MTMovement:
					Stats.UpdateMovSpeedMultiplier(Items.get(x).MValue, false);
					break;
				case Item.MTShootSpeed:
					Stats.UpdateShtSpeedMultiplier(Items.get(x).MValue, false);
					break;
				case Item.MTBulletCount:
					Stats.UpdateBulletCountMultiplier(Items.get(x).MValue, false);
					break;
				case Item.MTBulletLife:
					Stats.UpdateBulletTTLMultiplier(Items.get(x).MValue, false);
					break;
				}
				Items.remove(x);
			} else {
				switch(Items.get(x).MType) {
				case Item.MTMovement:
					Stats.UpdateMovSpeedMultiplier(Items.get(x).MValue, true);
					break;
				case Item.MTShootSpeed:
					Stats.UpdateShtSpeedMultiplier(Items.get(x).MValue, true);
					break;
				case Item.MTBulletCount:
					Stats.UpdateBulletCountMultiplier(Items.get(x).MValue, true);
					break;
				case Item.MTBulletLife:
					Stats.UpdateBulletTTLMultiplier(Items.get(x).MValue, true);
					break;
				case Item.MTBulletPassthrough:
					Stats.UpdateBulletPassthrough((Items.get(x).MTTL - Items.get(x).MAliveSince));
					break;
				}
			}
		}
		
		if(mouse.isButtonDown(MouseEvent.BUTTON1)) {
			if(((System.currentTimeMillis() - Stats.TSLShoot) > Stats.CurrentShtSpeed) && (Bullets.size() < Stats.CurrentBulletCount)){
				Bullets.add(new Bullet(this, mouse.getPointer(), Stats.CurrentBulletTTL, Stats.CurrentBulletMovSpeed, Stats.BulletPassthrough));
				Stats.TSLShoot = System.currentTimeMillis();
			}
		}
		
		if(keys.isKeyDown(KeyEvent.VK_W))Move(0,-1, Stats.CurrentMovSpeed);
		if(keys.isKeyDown(KeyEvent.VK_D))Move(1,0, Stats.CurrentMovSpeed);
		if(keys.isKeyDown(KeyEvent.VK_S))Move(0,1, Stats.CurrentMovSpeed);
		if(keys.isKeyDown(KeyEvent.VK_A))Move(-1,0, Stats.CurrentMovSpeed);
		
		for(int i = 0; i < SpawnedItems.size(); i++) {
			if(this.intersects(SpawnedItems.get(i))) {
				SpawnedItems.get(i).Retrieved();
				Items.add(SpawnedItems.get(i));
				frame.RetrieveItem(i);				
			}
		}
		
		Stats.Update();
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
		if((EnemiesKilled % Stats.CurrentItemSpawnCooldown) == 0) {
			if((Math.random() * 100) < Stats.CurrentItemSpawnChance) {
				frame.SpawnItem();
			}
		}
		EnemiesKilled++;
	}
	
	public ArrayList<Item> getItems(){
		return Items;
	}

	public void SetAlive(boolean b) {
		Stats.setAlive(b);
		if(!b) {
			frame.changeState(Mode_Dead);
		}
	}
	
	public boolean IsAlive() {
		return Stats.IsAlive();
	}
}
