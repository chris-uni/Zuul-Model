package commands.valid;

import com.Game;

import commands.ICommand;
import entities.Room;
import player.Player;

public class Ally implements ICommand{
	
	public void execute(Game game, String[] userInput) {
		
		/* Used to set npc's as friends/ally's. Used to make npc's follow the player when the player moves rooms. Can be used to defeat the monster in the game...
		 * 
		 * 1. Check that the user entered an npc name
		 * 2. Check to see if the entered npc is in-fact in the same room as the user.
		 * 3. If yes, add that npc to the HashMap<String, NPC> ally hashmap.
		 * 4. If not, error message.
		 * */
		
		Player player = game.getPlayer();
		Room currentRoom = game.getCurrentRoom();
		
		// To finish off later...
	}
}
