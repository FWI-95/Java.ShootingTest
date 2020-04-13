package code.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

import code.character.Bullet;
import code.character.Item;
import code.character.Player;
import code.enemy.Enemy;
import code.level.Difficulty;

public class Frame extends JFrame{
	Keyboard keys;
	Mouse mouse;
	BufferStrategy strat;
	
	Player player;
	ArrayList<Bullet> Bullets;
	ArrayList<Enemy> Enemies;
	ArrayList<Item> Items;
	
	int MaxEnemies = 15;
	int MinTBEnemySpawn = 100;
	double EnemySpawnChance = 90;
	long TSLEnemySpawn = 0;
	
	public Frame(Difficulty d) {
		keys = new Keyboard();
		mouse = new Mouse();
		
		player = new Player(keys, mouse, this, d);
		
		Bullets = new ArrayList<Bullet>();
		Enemies = new ArrayList<Enemy>();
		Items = new ArrayList<Item>();
		
		setTitle("Shooting Test");
//		setSize(600, 400);
		calcSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		addKeyListener(keys);
		addMouseListener(mouse);
		setVisible(true);
	}
	
	public void makeStrat() {
		createBufferStrategy(3);
		strat = getBufferStrategy();
	}
	
	public void calcSize() {
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		setState(JFrame.MAXIMIZED_BOTH);
	}
	
	public boolean gameIsRunning() {
		if(!player.IsAlive())return false;
		if(keys.GameEscaped)return false;
		return true;
	}
	
	public void calc() {
		for(int e = Enemies.size() - 1; e >= 0; e--) {
			if(!Enemies.get(e).Alive)Enemies.remove(e);
		}
		
		for(int b = Bullets.size() - 1; b >= 0; b--) {
			if(!Bullets.get(b).Alive)Bullets.remove(b);
		}
		
		player.update(Bullets, Items);
		
		for(int b = 0; b < Bullets.size(); b++) {
			Bullets.get(b).update(getSize().width, getSize().height);
		}
		
		for(int e = 0; e < Enemies.size(); e++) {
			Enemies.get(e).update(player, Bullets, getSize().width, getSize().height);
		}
		
		if((System.currentTimeMillis() - TSLEnemySpawn) > MinTBEnemySpawn) {
			if((Math.random() * 100) > EnemySpawnChance)Enemies.add(new Enemy(player, getSize().width, getSize().height));
			
			if(MinTBEnemySpawn > 1)MinTBEnemySpawn--;
			
			TSLEnemySpawn = System.currentTimeMillis();
		}
	}
	
	public void draw() {
		Graphics g = strat.getDrawGraphics();
		do {
			try {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, getBounds().width, getBounds().height);
				
				g.setColor(Color.GRAY);
				for(int b = 0; b < Bullets.size(); b++) {
					g.fillRect(Bullets.get(b).getBounds().x, Bullets.get(b).getBounds().y, Bullets.get(b).getBounds().width, Bullets.get(b).getBounds().height);
				}
				
				g.setColor(Color.YELLOW);
				for(int i = 0; i < Items.size(); i++) {
					g.fillOval((int) Items.get(i).getBounds().x, (int) Items.get(i).getBounds().y, Items.get(i).getBounds().width, Items.get(i).getBounds().height);
					g.setColor(Color.BLACK);
					g.drawString("Type: " + Items.get(i).MType, Items.get(i).getBounds().x, Items.get(i).getBounds().y);
					g.setColor(Color.YELLOW);
				}
				
				g.setColor(Color.RED);
				for(int e = 0; e < Enemies.size(); e++) {
					g.fillRect(Enemies.get(e).getBounds().x, Enemies.get(e).getBounds().y, Enemies.get(e).getBounds().width, Enemies.get(e).getBounds().height);
					g.setColor(Color.BLACK);
					g.drawString("x: " + Enemies.get(e).getBounds().x + " y: " + Enemies.get(e).getBounds().y, Enemies.get(e).getBounds().x, Enemies.get(e).getBounds().y);
					g.drawString("dest x: " + Enemies.get(e).lastDestinationX + " y: " + Enemies.get(e).lastDestinationY, Enemies.get(e).getBounds().x, Enemies.get(e).getBounds().y + 10);
					g.drawString("perc x: " + Enemies.get(e).lastPercX + " y: " + Enemies.get(e).lastPercY, Enemies.get(e).getBounds().x, Enemies.get(e).getBounds().y + 20);
					g.drawString("after x: " + Enemies.get(e).lastAfterX + " y: " + Enemies.get(e).lastAfterY, Enemies.get(e).getBounds().x, Enemies.get(e).getBounds().y + 30);
					g.setColor(Color.RED);
				}
				
				g.setColor(Color.BLUE);
				g.fillRect(player.getBounds().x, player.getBounds().y, player.getBounds().width, player.getBounds().height);
				
				g.setColor(Color.BLACK);
				g.drawString("EnemiesKilled: " + player.EnemiesKilled, 15, 15);
				
				for(int i = 0; i < player.getItems().size(); i++) {
					g.drawString("Item " + player.getItems().get(i).MType + ": " + (System.currentTimeMillis() - player.getItems().get(i).MAliveSince - player.getItems().get(i).MTTL) / 1000, 15, (i * 20) + 30);
				}
			} finally {
				g.dispose();
			}
			
			strat.show();
		} while (strat.contentsLost());
	}
	
	public void SpawnItem() {
		int IType = (int) (Math.random() * 5);
		int IPosX = (int) (Math.random() * getWidth());
		int IPosY = (int) (Math.random() * getHeight());
		System.out.println("Item " + IType + " spawned at: " + IPosX + " " + IPosY);
		Items.add(new Item(IType, IPosX, IPosY));
	}

	public void RetrieveItem(int which) {
		Items.remove(which);
	}

	public void ExitGame() {
		System.exit(0);
	}

}
