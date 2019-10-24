/** Provides a method of movement for the AI's in the game. 
 * 
 * @author Chris Wing (cjmw2)
 * */
package commands.ai;

import java.util.Set;

import com.Game;

import commands.IAiCommand;
import entities.NPC;
import entities.Room;
import output.Mode;
import output.OutputHandler;
import tools.Tools;

public class Go implements IAiCommand{
	
	@Override
	public void execute(Game game) {
		
		// Selects a random room from the list of available rooms in the game.
		Room randRoom = game.getRandomRoom();
		
		// Gets the list of exit from the randomly picked room.
		Set<String> roomExits = randRoom.getExits();
		
		// Gets the list of NPCs from the randomly picked room.
		Set<String> roomNPCs = randRoom.getNPCs();
		
		// The new random room picked for the npc to move into.
		Room newRoom = this.returnRandomRoom(roomExits, game, randRoom);
		
		// From the initial random room, get the npc's and return a random one.
		NPC randNPC = this.returnRandomNPC(roomNPCs, randRoom);
		
		// Iff_ there is definitely a new room to move into and there is definitely an NPC to
		// move into it.
		if(newRoom != null && randNPC != null) {
			
			// Now we can move the NPC from randRoom to newRoom.
			randRoom.removeNPC(randNPC.getName());
			newRoom.addNPC(randNPC);
			
			OutputHandler.output("NPC '" + randNPC.getName() + "' has moved from " + randRoom.getName() + 
					" to " + newRoom.getName(), Mode.CONSOLE);
		}
		
	}
		
	/** Based on the random initial room, get the list of NPCs and return a random one.
	 * */
	private NPC returnRandomNPC(Set<String> npcs, Room randRoom) {
		
		if(npcs.size() >= 1) {
			int rand = Tools.randomNumber(npcs);
			
			// System.out.println("When picking rand npc, int used: " + rand);
			String npcName = (String)npcs.toArray()[rand];
			
			NPC npc = randRoom.getNPC(npcName);
			// OutputHandler.output("Random npc name: " + npc.getName(), Mode.CONSOLE);
		
			return npc;
		}
		else return null;
	}
	
	/** Based on the list of possible exits from the initial randomly picked room, pick
	 * a random room based on those exits. 
	 * 
	 * i.e. e pick a room that can only be reached as an exit from the initial random room.
	 * */
	private Room returnRandomRoom(Set<String> rooms, Game game, Room randRoom){
		
		if(rooms.size() > 1) {
			int rand = Tools.randomNumber(rooms);
			
			String roomDir = (String)rooms.toArray()[rand];
			String roomName = randRoom.getExit(roomDir);
			
			// OutputHandler.output("random dir: " + roomDir + " which goes to room: " + roomName, Mode.CONSOLE);
			Room newRoom = game.getRoom(roomName);
			
			// OutputHandler.output("Random room name: " + newRoom.getName(), Mode.CONSOLE);
			return newRoom;
		}
		else return null;
	}
}
