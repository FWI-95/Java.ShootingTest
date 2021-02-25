package code.entities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Background implements ImageObserver{
	Image BackgroundImage;
	SpriteSheet spritesheet;
	BufferedImage textures;
	int TileWidth = 24;
	int TileHeight = 24;
	int TileSeparationX = 2;
	int TileSeparationY = 2;
	int TileNoX;
	int TileNoY;
	
	public Background(int Type) {
		try {
			URL iconUrl = this.getClass().getResource("/gfx/background/Textures1_24_2.png");
			textures = ImageIO.read(iconUrl);
		} catch(IOException e){
			System.out.println("Background could not be loaded");
		}
		spritesheet = new SpriteSheet(textures, TileWidth, TileHeight, TileSeparationX, TileSeparationY);
		
		switch(Type) {
		case 1:
			TileNoX = 4;
			TileNoY = 0;
			break;
		case 2:
			TileNoX = 6;
			TileNoY = 1;
			break;
		case 3:
			TileNoX = 2;
			TileNoY = 3;
			break;
		case 4:
			TileNoX = 4;
			TileNoY = 3;
			break;
		case 5:
			TileNoX = 5;
			TileNoY = 3;
			break;
		case 6:
			TileNoX = 6;
			TileNoY = 3;
			break;
		case 7:
			TileNoX = 7;
			TileNoY = 3;
			break;
		case 8:
			TileNoX = 8;
			TileNoY = 3;
			break;
		case 9:
			TileNoX = 4;
			TileNoY = 4;
			break;
		case 10:
			TileNoX = 5;
			TileNoY = 4;
			break;
		case 11:
			TileNoX = 6;
			TileNoY = 4;
			break;
		case 12:
			TileNoX = 7;
			TileNoY = 4;
			break;
		case 13:
			TileNoX = 8;
			TileNoY = 4;
			break;
		case 14:
			TileNoX = 4;
			TileNoY = 5;
			break;
		case 15:
			TileNoX = 5;
			TileNoY = 5;
			break;
		case 16:
			TileNoX = 6;
			TileNoY = 5;
			break;
		case 17:
			TileNoX = 7;
			TileNoY = 5;
			break;
		default:
			TileNoX = 8;
			TileNoY = 5;
			break;
		}
		
		BackgroundImage = spritesheet.getTile(TileNoX, TileNoY);
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
