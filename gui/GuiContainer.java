package gui;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.Game;

import commands.CommandHandler;
import commands.ICommand;

public class GuiContainer {

	private Game game;
	private CommandHandler commandHandler;
	private HashMap<String, ICommand> commands;
	
	public GuiContainer(Game game, CommandHandler commandHandler) {
		
		this.game = game;
		this.commandHandler = commandHandler;
		
		this.commands = this.game.getCommands();
		
		setup();
	}
	
	private void setup() {
		
		// Create game window.
		Frame gameWindow = new Frame("~~ Welcome to the World of Zuul ~~");
		gameWindow.setSize(600, 300);
		gameWindow.setVisible(true);

		// Load the buttons into the game.
		loadButtons().forEach(b -> gameWindow.add(b));
		
		// Handles what the Exit button on the window does.
		gameWindow.addWindowListener(new WindowAdapter() {
			
	        public void windowClosing(WindowEvent we) {
	            System.exit(0);
	         }
		});
	}
	
	private List<Button> loadButtons(){
		
		var wrapper = new Object() { 
			
				List<Button> buttons = new ArrayList<Button>(); 
				int count = 0;
		};
		
		this.commands.forEach((k, v) -> {
			
			int count = wrapper.count;
			
			Button b = new Button(k);
			b.setActionCommand(k);
			b.setVisible(true);
			b.setBounds(10, 10 + (count * 20), 60, 20);
			b.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					System.out.println(e.getActionCommand());
				}
			});
			
			wrapper.buttons.add(b);
			
			wrapper.count += 1;
		});
		
		return wrapper.buttons;
	}
}
