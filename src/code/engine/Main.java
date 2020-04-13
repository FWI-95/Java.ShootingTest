package code.engine;

public class Main {

	static long UpdateSpeed = 25;
	
	public static void main(String[] args) {
		
		MainMenu mm = new MainMenu(400, 200);
		
		Frame f = new Frame(mm.Show());
		f.makeStrat();
		
		long LastUpdate = System.currentTimeMillis();
		while(f.gameIsRunning()){
			if((System.currentTimeMillis() - LastUpdate) > UpdateSpeed){
				f.calc();
				f.draw();
				LastUpdate = System.currentTimeMillis();
			}
		}
		try {
			Thread.sleep(750);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		f.ExitGame();
	}

}
