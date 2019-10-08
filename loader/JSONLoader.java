package loader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
}