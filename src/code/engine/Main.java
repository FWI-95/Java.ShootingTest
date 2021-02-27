package code.engine;

import java.awt.event.KeyEvent;

import code.settings;

public class Main {

	static long UpdateSpeed = 25;
	
	public static void main(String[] args) {
		
		StartMenu Stmenu = new StartMenu();
		
		while(Stmenu.IsOpen()){
			if(settings.debug){
				System.out.println("Startmenu still open");
			}
		}

		Frame f = new Frame(Stmenu.getDifficulty());
		Stmenu = null;
		f.makeStrat();
		
		long LastUpdate = System.currentTimeMillis();
		while(true){
			switch(f.getCurrentState()){
				case START:
					break;
				case ALIVE:
					if((System.currentTimeMillis() - LastUpdate) > UpdateSpeed){
						f.calc();
						f.draw();
						LastUpdate = System.currentTimeMillis();
					}
					break;
				case DEAD:
					LastUpdate = Pausemenu(f);
					break;
				case PAUSED:
					LastUpdate = Deadmenu(f);
					break;
			}

		}
	}

	public static long Pausemenu(Frame f){
		f.drawPause();
		return System.currentTimeMillis();
	}

	public static long Deadmenu(Frame f){
		f.drawDead();
		return System.currentTimeMillis();
	}

}
