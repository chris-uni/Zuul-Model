package ai;

import java.util.Set;

import com.Game;

import entities.Room;
import output.Mode;
import output.OutputHandler;

public class AICommand implements Runnable{

	private Game game;
	
	public AICommand(Game game) {
		
		this.game = game;
	}
	
	public void run() {
		
		System.out.println("Multithread test running on thread: " + Thread.currentThread().getId());
		
		// Gets the randomly picked room.
		Room randRoom = this.game.getRandomRoom();
		
		Set<String> exits = randRoom.getExits();
		String dir = (String)exits.toArray()[0];
		
		
		OutputHandler.output("Random room: " + randRoom.getName(), Mode.CONSOLE);
	}
}
