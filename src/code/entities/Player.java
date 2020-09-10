package code.entities;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import code.Frame;
import code.Keyboard;
import code.Mouse;
import code.animation.Direction;
import code.animation.PlayerAnimation;
import code.animation.PlayerState;

public class Player extends Rectangle implements ImageObserver{
	
	Frame frame;
	Keyboard keys;
	Mouse mouse;
	SpriteSheet spritesheet;
	PlayerAnimation anim;
	
	public boolean Alive = true;
	public PlayerState CurrentState;
	public Direction CurrentDirection;
	
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
	
	public long EnemiesKilled = 0;
	
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
		CurrentDirection = Direction.DOWN;
		anim = new PlayerAnimation((int) (Math.random() * 5));
		
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
//			CurrentState = State.ATTACKING;
//		} else {
//			CurrentState = State.IDLE;
		}
		
		if(keys.isKeyDown(KeyEvent.VK_SHIFT)) {
			MovSpeed *= 1.25;
		}
		
		if(keys.isKeyDown(KeyEvent.VK_W))Move(Direction.UP, MovSpeed);
		if(keys.isKeyDown(KeyEvent.VK_D))Move(Direction.RIGHT, MovSpeed);
		if(keys.isKeyDown(KeyEvent.VK_S))Move(Direction.DOWN, MovSpeed);
		if(keys.isKeyDown(KeyEvent.VK_A))Move(Direction.LEFT, MovSpeed);

		if(keys.isKeyDown(KeyEvent.VK_W) || keys.isKeyDown(KeyEvent.VK_D) || keys.isKeyDown(KeyEvent.VK_S) || keys.isKeyDown(KeyEvent.VK_A)) {
			if(keys.isKeyDown(KeyEvent.VK_SHIFT)) {
				CurrentState = PlayerState.RUNNING;
			}else {
				CurrentState = PlayerState.WALKING;
			}
		} else {
			CurrentState = PlayerState.IDLE;
		}
		
		for(int i = 0; i < SpawnedItems.size(); i++) {
			if(this.intersects(SpawnedItems.get(i))) {
				SpawnedItems.get(i).Retrieved();
				Items.add(SpawnedItems.get(i));
				frame.RetrieveItem(i);				
			}
		}
	}
	
	public void Move(Direction dir, long MovSpeed) {
		CurrentDirection = dir;
		int MoveX;
		int MoveY;
		
		switch(dir) {
		case UP:
			MoveX = 0;
			MoveY = -1;
			break;
		case RIGHT:
			MoveX = 1;
			MoveY = 0;
			break;
		case DOWN:
			MoveX = 0;
			MoveY = 1;
			break;
		case LEFT:
			MoveX = -1;
			MoveY = 0;
			break;
		default:
			MoveX = 0;
			MoveY = 0;
			break;
		}
		
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
	
	public Image getImage() {
		return anim.GetImage(CurrentState, CurrentDirection);
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
}
