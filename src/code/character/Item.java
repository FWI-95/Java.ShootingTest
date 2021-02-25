package code.character;

import java.awt.Rectangle;

public class Item extends Rectangle{
	public final static int MTMovement = 1;
	public final static int MTShootSpeed = 2;
	public final static int MTBulletCount = 3;
	public final static int MTBulletLife = 4;
	public final static int MTBulletPassthrough = 5;
		
	public int MType;
	public double MValue;
	
	public long MAliveSince;
	public long MTTL;
	
	public Item(int pType, long pPosX, long pPosY) {
		this.setBounds((int) pPosX, (int) pPosY, 25, 25);
		
		MType = pType;
		
		switch(MType) {
		case MTMovement:
			MTTL = (30 * 1000);
			MValue = 1.05;
			break;
		case MTShootSpeed:
			MTTL = (20 * 1000);
			MValue = 1.10;
			break;
		case MTBulletCount:
			MTTL = (20 * 1000);
			MValue = 1.5;
			break;
		case MTBulletLife:
			MTTL = (15 * 1000);
			MValue = 2;
			break;
		case MTBulletPassthrough:
			MTTL = (10 * 1000);
			break;
		}
	}
	
	public void Retrieved() {
		MAliveSince = System.currentTimeMillis();
	}
}
