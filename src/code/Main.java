package code;

public class Main {

	static long UpdateSpeed = 25;
	
	public static void main(String[] args) {
		
		Frame f = new Frame();
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
		
		System.exit(0);
	}

}
