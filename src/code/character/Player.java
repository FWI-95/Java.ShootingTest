package code.character;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import code.animation.Direction;
import code.animation.PlayerAnimation;
import code.animation.PlayerState;
import code.engine.Frame;
import code.engine.Keyboard;
import code.engine.Modes;
import code.engine.Mouse;
import code.entities.Bullet;
import code.entities.Item;
import code.entities.SpriteSheet;
import code.level.Difficulty;

public class Player extends Rectangle implements ImageObserver{
	
	Frame frame;
	Keyboard keys;
	Mouse mouse;
	SpriteSheet spritesheet;
	PlayerAnimation anim;
	Player_Stats Stats;
	Difficulty CurrentDifficulty;
	
	public PlayerState CurrentState;
	public Direction CurrentDirection;
	
	long posX;
	long posY;
	
	public long EnemiesKilled = 0;
	
	ArrayList<Item> Items;
	
	public Player(Keyboard k, Mouse m, Frame f, Difficulty d) {
		keys = k;
		mouse = m;
		frame = f;
		CurrentDifficulty = d;
		
		posX = 150;
		posY = 150;
		
		width = 50;
		height = 50;
		
		Stats = new Player_Stats(d);

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
//			CurrentState = State.ATTACKING;
//		} else {
//			CurrentState = State.IDLE;
		}
		CurrentState = PlayerState.IDLE;
		if(keys.isKeyDown(KeyEvent.VK_SHIFT)) {
			if(keys.isKeyDown(KeyEvent.VK_W))Run(Direction.UP);
			if(keys.isKeyDown(KeyEvent.VK_D))Run(Direction.RIGHT);
			if(keys.isKeyDown(KeyEvent.VK_S))Run(Direction.DOWN);
			if(keys.isKeyDown(KeyEvent.VK_A))Run(Direction.LEFT);	
		} else {
			if(keys.isKeyDown(KeyEvent.VK_W))Walk(Direction.UP);
			if(keys.isKeyDown(KeyEvent.VK_D))Walk(Direction.RIGHT);
			if(keys.isKeyDown(KeyEvent.VK_S))Walk(Direction.DOWN);
			if(keys.isKeyDown(KeyEvent.VK_A))Walk(Direction.LEFT);
		}
		
		for(int i = 0; i < SpawnedItems.size(); i++) {
			if(this.intersects(SpawnedItems.get(i))) {
				SpawnedItems.get(i).Retrieved();
				Items.add(SpawnedItems.get(i));
				frame.RetrieveItem(i);				
			}
		}
	}
	//TODO MovSpeed + Multiplier + Run
	public void Walk(Direction dir){
		CurrentState = PlayerState.WALKING;
		Move(dir, CurrentDifficulty.BaseMovSpeed);
	}

	public void Run(Direction dir){
		CurrentState = PlayerState.RUNNING;
		Move(dir, CurrentDifficulty.BaseMovSpeed * Stats.MovSpeedMP);
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
	
	public Image getImage() {
		return anim.GetImage(CurrentState, CurrentDirection);
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

	public void kill(){
		Stats.setAlive(false);
		frame.changeState(Modes.DEAD);
	}

	public boolean IsAlive(){
		return Stats.Alive;
	}

}





