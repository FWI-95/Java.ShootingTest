package code.animation;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import code.entities.SpriteSheet;

public class Animation {

	SpriteSheet spritesheet;
	
	long Speed = 50;
	
	ArrayList<Image> Walk_Up;
	ArrayList<Image> Walk_Down;
	ArrayList<Image> Walk_Left;
	ArrayList<Image> Walk_Right;
	
	ArrayList<Image> Run_Up;
	ArrayList<Image> Run_Down;
	ArrayList<Image> Run_Left;
	ArrayList<Image> Run_Right;
	
	ArrayList<Image> Attack_Up;
	ArrayList<Image> Attack_Down;
	ArrayList<Image> Attack_Left;
	ArrayList<Image> Attack_Right;
	
	ArrayList<Image> Idle_Up;
	ArrayList<Image> Idle_Down;
	ArrayList<Image> Idle_Left;
	ArrayList<Image> Idle_Right;
	
	long LastUpdateTime;
	PlayerState lastState;
	Direction lastDirection;
	int Counter;
	ArrayList<Image> CurrentAnimation;
	
	public Animation(int dummy) {
		Walk_Up = new ArrayList<Image>();
		Walk_Down = new ArrayList<Image>();
		Walk_Left = new ArrayList<Image>();
		Walk_Right = new ArrayList<Image>();
		
		Run_Up = new ArrayList<Image>();
		Run_Down = new ArrayList<Image>();
		Run_Left = new ArrayList<Image>();
		Run_Right = new ArrayList<Image>();
		
		Attack_Up = new ArrayList<Image>();
		Attack_Down = new ArrayList<Image>();
		Attack_Left = new ArrayList<Image>();
		Attack_Right = new ArrayList<Image>();
		
		Idle_Up = new ArrayList<Image>();
		Idle_Down = new ArrayList<Image>();
		Idle_Left = new ArrayList<Image>();
		Idle_Right = new ArrayList<Image>();
	}
	public void LoadSpritesheet(String spritepath, int TileWidth, int TileHeight, int SepX, int SepY)
	{
		URL iconUrl = this.getClass().getResource(spritepath);
		try {
			spritesheet = new SpriteSheet(ImageIO.read(iconUrl), TileWidth, TileHeight, SepX, SepY);
		} catch (IOException e) {
			System.out.println("Sprite " + spritepath + " could not be loaded");
		}
		
		LastUpdateTime = System.currentTimeMillis();
	}
	
	public Image GetImage(PlayerState state, Direction dir) {
		
		if ((lastState != state) || (lastDirection != dir)) {
			Counter = 1;
			lastState = state;
			lastDirection = dir;
			switch(state) {
			case WALKING:
				switch(dir) {
				case UP:
					CurrentAnimation = Walk_Up;
					System.out.println("Up");
					break;
				case DOWN:
					CurrentAnimation = Walk_Down;
					System.out.println("Down");
					break;
				case LEFT:
					CurrentAnimation = Walk_Left;
					System.out.println("Left");
					break;
				case RIGHT:
					CurrentAnimation = Walk_Right;
					System.out.println("Right");
					break;
				default:
					break;
				}
				break;
			case RUNNING:
				switch(dir) {
				case UP:
					CurrentAnimation = Run_Up;
					break;
				case DOWN:
					CurrentAnimation = Run_Down;
					break;
				case LEFT:
					CurrentAnimation = Run_Left;
					break;
				case RIGHT:
					CurrentAnimation = Run_Right;
					break;
				default:
					break;
				}
				break;
			case IDLE:
				switch(dir) {
				case UP:
					CurrentAnimation = Idle_Up;
					break;
				case DOWN:
					CurrentAnimation = Idle_Down;
					break;
				case LEFT:
					CurrentAnimation = Idle_Left;
					break;
				case RIGHT:
					CurrentAnimation = Idle_Right;
					break;
				default:
					break;
				}
				break;
			case ATTACKING:
				switch(dir) {
				case UP:
					CurrentAnimation = Attack_Up;
					break;
				case DOWN:
					CurrentAnimation = Attack_Down;
					break;
				case LEFT:
					CurrentAnimation = Attack_Left;
					break;
				case RIGHT:
					CurrentAnimation = Attack_Right;
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
		}
		
		if (Counter >= CurrentAnimation.size()) {
			Counter = 1;
		} else {
			if ((System.currentTimeMillis() - LastUpdateTime) > Speed) {
				Counter++;
				LastUpdateTime = System.currentTimeMillis();
			}
		}
		
//		System.out.println("counter " + Counter + " size " + CurrentAnimation.size());
		return CurrentAnimation.get(Counter - 1);
	}
}
