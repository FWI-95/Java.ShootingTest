package code.animation;

public class EnemyAnimation extends Animation{
	String spritepath = "/gfx/enemy/";
	int TileWidth = 30;
	int TileHeight = 30;
	int SepX = 2;
	int SepY = 2;
	
	public EnemyAnimation(int type) {
		super(type);
		
		switch(type) {
		case 1: 
			spritepath = spritepath + "char_2.png";
			break;
		case 2: 
			spritepath = spritepath + "char_3.png";
			break;
		case 3: 
			spritepath = spritepath + "char_4.png";
			break;
		case 4: 
			spritepath = spritepath + "char_5.png";
			break;
		default:
			spritepath = spritepath + "char_1.png";
			break;
		}
		
		LoadSpritesheet(spritepath, TileWidth, TileHeight, SepX, SepY);
		//WALK
		for(int i = 0; i < 4; i++) {
			Walk_Up.add(spritesheet.getTile(i, 3));
		}
		for(int i = 0; i < 4; i++) {
			Walk_Down.add(spritesheet.getTile(i, 0));
		}
		for(int i = 0; i < 4; i++) {
			Walk_Left.add(spritesheet.getTile(i, 1));
		}
		for(int i = 0; i < 4; i++) {
			Walk_Right.add(spritesheet.getTile(i, 2));
		}
		//RUN
		for(int i = 0; i < 4; i++) {
			Run_Up.add(spritesheet.getTile(i, 7));
		}
		for(int i = 0; i < 4; i++) {
			Run_Down.add(spritesheet.getTile(i, 4));
		}
		for(int i = 0; i < 4; i++) {
			Run_Left.add(spritesheet.getTile(i, 5));
		}
		for(int i = 0; i < 4; i++) {
			Run_Right.add(spritesheet.getTile(i, 6));
		}
		//IDLE
//		for(int i = 0; i < 7; i++) {
			Idle_Up.add(spritesheet.getTile(4, 4));
//		}
//		for(int i = 0; i < 7; i++) {
			Idle_Down.add(spritesheet.getTile(6, 4));
//		}
//		for(int i = 0; i < 7; i++) {
			Idle_Left.add(spritesheet.getTile(5, 4));
//		}
//		for(int i = 0; i < 7; i++) {
			Idle_Right.add(spritesheet.getTile(7, 4));
//		}
		//ATTACK
//		for(int i = 0; i < 7; i++) {
//			Attack_Up.add(spritesheet.getTile(i, 3));
//		}
//		for(int i = 0; i < 7; i++) {
//			Attack_Down.add(spritesheet.getTile(i, 0));
//		}
//		for(int i = 0; i < 7; i++) {
//			Attack_Left.add(spritesheet.getTile(i, 1));
//		}
//		for(int i = 0; i < 7; i++) {
//			Attack_Right.add(spritesheet.getTile(i, 2));
//		}
	}

}
