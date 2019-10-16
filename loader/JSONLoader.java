package loader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONLoader {

	private JSONParser parser = new JSONParser();
	private JSONObject jsonData;
	
	/** Creates the object JSONLoader and passes in the JSON file.
	 * */
	public JSONLoader(String filename) {
		
		loadJSON(filename);
	}
	
	/** Used to load in the JSON data file. Saves the data for later when we can use it to load rooms/ items etc.*/
	private void loadJSON(String filename) {
		
		try(Reader reader = new FileReader(filename)){
			
			JSONObject obj  = (JSONObject) parser.parse(reader);
			
			JSONObject json = (JSONObject) obj.get("rooms");
			
			jsonData = json;
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	// Room section.
	
	/** Returns the top level JSON Objects in the data file. In this case the top level objects are the room names. Courtyard, Cinema etc.
	 * */
	public List<String> getAllRooms() {
		
		List<String> keys = new ArrayList<String>(jsonData.keySet());
		
		return keys;
	}
	
	/** Finds the corresponding room in the JSON file, gets its description and returns back to the player.
	 * */
	public String getRoomDescription(String roomName) {
		
		JSONObject room = (JSONObject) jsonData.get(roomName);
		
		return (String) room.get("description");
	}
	
	// Exit section.
	
	/** Finds the corresponding exits for the provided room.
	 * */
	public HashMap<String, String> getRoomExits(String roomName){
		
		JSONObject room = (JSONObject) jsonData.get(roomName);
		
		HashMap<String, String> exits = (HashMap<String, String>) room.get("exits");
		
		return exits;
	}
	
	// Item section.
	
	/** Returns the name of each item for any given room.
	 * */
	public List<String> getItemKeys(String roomName){
		
		// Gets the room.
		JSONObject room = (JSONObject) jsonData.get(roomName);
		JSONObject items = (JSONObject) room.get("items");
		
		List<String> keys = new ArrayList<String>(items.keySet());
		
		return keys;
	}
	
	/** Based on an items key, will load its information (description, weight etc).
	 * */
	public String[] getRoomItems(String roomName, String itemKey){
		
		// Gets the room.
		JSONObject room = (JSONObject) jsonData.get(roomName);
		JSONObject items = (JSONObject) room.get("items");
		
		JSONObject item = (JSONObject) items.get(itemKey);
		
		String description = item.get("description").toString();
		String weight = item.get("weight").toString();
		
		return new String[]{description, weight};
	}
	
	// NPC section.
	
	/** Finds the corresponding npcs for any given room. Returns a list of the entry keys.
	 * */
	public List<String> getNPCKeys(String roomName){
		
		JSONObject room = (JSONObject) jsonData.get(roomName);
		JSONObject npcs = (JSONObject) room.get("npcs");
		
		List<String> keys = new ArrayList<String>(npcs.keySet());
		
		return keys;
	}
	
	/** Based on an npcs key, will load its information (description, weight etc).
	 * */
	public String[] getRoomNPCs(String roomName, String npcKey){
		
		// Gets the room.
		JSONObject room = (JSONObject) jsonData.get(roomName);
		JSONObject npcs = (JSONObject) room.get("npcs");
		
		JSONObject npc = (JSONObject) npcs.get(npcKey);
		
		String dialog = npc.get("dialog").toString();
		
		return new String[]{dialog};
	}
}
