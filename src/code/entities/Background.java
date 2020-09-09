package code.entities;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Background implements ImageObserver{
	Image BackgroundImage;
	int TileWidth = 24;
	int TileHeight = 24;
	int TileSeparationX = 2;
	int TileSeparationY = 2;
	
	public Background(int Type) {
		String imagename = "";
		
		switch(Type) {
		case 1:
			imagename = "Brick_1.png";
			break;
		case 2:
			imagename = "Brick_2.png";
			break;
		case 3:
			imagename = "Brick_3.png";
			break;
		case 4:
			imagename = "Dirt_1.png";
			break;
		case 5:
			imagename = "Dirt_2.png";
			break;
		case 6:
			imagename = "Dirt_3.png";
			break;
		case 7:
			imagename = "Dirt_4.png";
			break;
		case 8:
			imagename = "Dirt_5.png";
			break;
		case 9:
			imagename = "Dirt_6.png";
			break;
		case 10:
			imagename = "Dirt_7.png";
			break;
		case 11:
			imagename = "Stone_1.png";
			break;
		case 12:
			imagename = "Stone_2.png";
			break;
		case 13:
			imagename = "Stone_3.png";
			break;
		case 14:
			imagename = "Stone_4.png";
			break;
		case 15:
			imagename = "Stone_5.png";
			break;
		case 16:
			imagename = "Stone_6.png";
			break;
		case 17:
			imagename = "Stone_7.png";
			break;
		default:
			imagename = "Standard.png";
			break;
		}
        try {
        	System.out.println("Using background " + Type + " " + imagename);
			URL iconUrl = this.getClass().getResource("/gfx/background/" + imagename);
			BackgroundImage = ImageIO.read(iconUrl);
        } catch (IOException e) {
            System.out.println("The background " + Type + " " + imagename + " was not loaded.");
        }
	}
	
	public Image getImage()
	{
		return BackgroundImage;
	}

	public int getTileWidth() {
		return TileWidth;
	}

	public int getTileHeight() {
		return TileHeight;
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}

}
