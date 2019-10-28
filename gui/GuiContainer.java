package gui;

import java.awt.Button;
import java.awt.Frame;
import java.awt.TextArea;
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
import output.OutputHandler;

public class GuiContainer {

	private Game game;
	private CommandHandler commandHandler;
	private HashMap<String, ICommand> commands;
	private static TextArea console;
	
	private static int numPlayers = 0;
	
	public GuiContainer(Game game, CommandHandler commandHandler) {
		
		this.game = game;
		this.commandHandler = commandHandler;
		console = new TextArea();
		
		this.commands = this.game.getCommands();
		
		setup();
	}
	
	private void setup() {
		
		// Create game window.
		Frame gameWindow = new Frame("~~ Welcome to the World of Zuul ~~");
		gameWindow.setSize(700, 400);
		gameWindow.setVisible(true);

		// Load the buttons into the game.
		loadButtons().forEach(b -> gameWindow.add(b));
		
		// Handles what the Exit button on the window does.
		gameWindow.addWindowListener(new WindowAdapter() {
			
	        public void windowClosing(WindowEvent we) {
	            System.exit(0);
	         }
		});
		
		console.setBounds(10, 300, 690, 90);
		
		gameWindow.add(console);
	}
	
	public int setPlayers() {
		
		
		while(numPlayers == 0) {
			
			OutputHandler.output("waiting...\n");
		}
		
		return numPlayers;
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
					
					// System.out.println(e.getActionCommand());
					
					commandHandler.handleCommand(e.getActionCommand().split(" "));
				}
			});
			
			Button one = new Button("1 Player");
			one.setActionCommand("1");
			one.setVisible(true);
			one.setBounds(10, 10 + (11 * 20), 60, 20);
			one.setActionCommand("1");
			
			one.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					GuiContainer.setNumPlayers(1);
				}
			});
			
			Button two = new Button("2 Player");
			two.setActionCommand("2");
			two.setVisible(true);
			two.setBounds(10, 10 + (12 * 20), 60, 20);
			two.setActionCommand("2");
			
			two.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					GuiContainer.setNumPlayers(2);
				}
			});
			
			wrapper.buttons.add(b);
			wrapper.buttons.add(one);
			wrapper.buttons.add(two);
			wrapper.count += 1;
		});
		
		return wrapper.buttons;
	}
	
	/** Enables the gui game to output the games contents to the gui console.
	 * */
	public static TextArea getConsole() {
		
		return console;
	}
	
	public static void setNumPlayers(int n) {
		
		numPlayers = n;
	}
}
