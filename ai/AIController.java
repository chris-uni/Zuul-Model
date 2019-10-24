package ai;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.Game;

public class AIController {
	
	Thread aiThread;
	Timer timer;
	TimerTask helper;
	
	public AIController(Game game) {

		this.aiThread = new Thread(new AICommand(game));
		this.timer = new Timer();
	}
	
	/** Main runner for the AI functionality of the game.
	 * */
	public void run(int interval) {
			
		Random random = new Random();
		
		try {
			
			this.helper = new AITimer(aiThread);
			
			int rand = random.nextInt(interval);
			
			this.timer.schedule(this.helper, rand, rand * 2);
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}
}

class AITimer extends TimerTask{

	Thread thread;
	
	AITimer(Thread thread){
		
		this.thread = thread;
	}
	
	@Override
	public void run() {
		
		thread.run();
	}
}
