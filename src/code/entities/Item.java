package code.entities;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.imageio.ImageIO;

public class Item extends Rectangle implements ImageObserver{
	public final static int MTMovement = 0;
	public final static int MTShootSpeed = 1;
	public final static int MTBulletCount = 2;
	public final static int MTBulletLife = 3;
	public final static int MTBulletPassthrough = 4;

	public int MType;
	public double MValue;

	public long MAliveSince;
	public long MTTL;

	Image ItemImage;

	public Item(int pType, long pPosX, long pPosY) {
		this.setBounds((int) pPosX, (int) pPosY, 50, 50);

		MType = pType;
		String imagename = null;

		switch(MType) {
		case MTMovement:
			MTTL = (30 * 1000);
			MValue = 1.05;
			imagename = "potion_blue_edgy.png";
			break;
		case MTShootSpeed:
			MTTL = (20 * 1000);
			MValue = 1.10;
			imagename = "potion_blue_flask.png";
			break;
		case MTBulletCount:
			MTTL = (20 * 1000);
			MValue = 1.5;
			imagename = "potion_yellow.png";
			break;
		case MTBulletLife:
			MTTL = (15 * 1000);
			MValue = 2;
			imagename = "potion_purple.png";
			break;
		case MTBulletPassthrough:
			MTTL = (10 * 1000);
			imagename = "potion_red.png";
			break;
		}
        try {
			URL iconUrl = this.getClass().getResource("/gfx/items/" + imagename);
			ItemImage = ImageIO.read(iconUrl);
        } catch (IOException e) {
            System.out.println("The image " + imagename + " was not loaded.");
        }
	}

	public void Retrieved() {
		MAliveSince = System.currentTimeMillis();
	}

	public ImageObserver getObserver() {
		return this;
	}

	public Image getImage() {

		return ItemImage;
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {

		return false;
	}
}