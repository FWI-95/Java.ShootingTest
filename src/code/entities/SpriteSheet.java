package code.entities;

import java.awt.image.BufferedImage;

public class SpriteSheet
{
    private BufferedImage spritesheet;
    private int TileWidth;
    private int TileHeight;
    private int xSeparation;
    private int ySeparation;

    public SpriteSheet(BufferedImage sheet, int width, int height, int sepx, int sepy) 
    {
    	this.spritesheet = sheet;
    	this.TileWidth = width;
    	this.TileHeight = height;
    	this.xSeparation = sepx;
    	this.ySeparation = sepy;
    }
    
    public SpriteSheet(BufferedImage sheet, int width, int sep) 
    {
    	this.spritesheet = sheet;
    	this.TileWidth = width;
    	this.TileHeight = width;
    	this.xSeparation = sep;
    	this.ySeparation = sep;
    }

    public BufferedImage getTile(int xTile, int yTile, int width, int height)
    {
    	BufferedImage sprite = spritesheet.getSubimage(xTile, yTile, width, height);
    	return sprite;
    }
    
    public BufferedImage getTile(int xTile, int yTile)
    {
    	int xPos = (xTile * TileWidth);
    	int yPos = (yTile * TileHeight);
    	if (xTile > 0) {
    		xPos += (xTile * xSeparation);
    	}
    	if (yTile > 0) {
    		yPos += (yTile * ySeparation);
    	}
//    	System.out.println("xTile " + xTile + " yTile " + yTile + " x " + xPos + " y " + yPos + " w " + TileWidth + " h " + TileHeight);
    	BufferedImage sprite = spritesheet.getSubimage(xPos, yPos, TileWidth, TileHeight);
    	return sprite;
    }
}
