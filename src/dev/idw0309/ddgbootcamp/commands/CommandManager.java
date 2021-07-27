package dev.idw0309.ddgbootcamp.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.idw0309.ddgbootcamp.Core;
import dev.idw0309.ddgbootcamp.commands.subcommands.arenaList;
import dev.idw0309.ddgbootcamp.commands.subcommands.createArena;
import dev.idw0309.ddgbootcamp.commands.subcommands.Help;
import dev.idw0309.ddgbootcamp.commands.subcommands.joinArena;
import dev.idw0309.ddgbootcamp.commands.subcommands.leaveArena;
import dev.idw0309.ddgbootcamp.commands.subcommands.setLobbypoint;
import dev.idw0309.ddgbootcamp.commands.subcommands.setSpawn;
import dev.idw0309.ddgbootcamp.message.Message;

public class CommandManager implements CommandExecutor {
	
	private ArrayList<SubCommand> commands = new ArrayList<SubCommand>();
    private Core plugin = Core.getInstance();
    
    /**
     * Registers the main / sub commands on startup of the plugin
     */
    public void setup() {
    	plugin.getCommand("bootcamp").setExecutor(this);

        this.commands.add(new setSpawn());
        this.commands.add(new joinArena());
        this.commands.add(new leaveArena());
        this.commands.add(new setLobbypoint());
        this.commands.add(new createArena());
        this.commands.add(new arenaList());
        this.commands.add(new Help());
    }
    
    /**
     * Main command of the plugin
     * @return true if the plugin detects something what isnt right, it will return true and stop the process.
     */
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		//Checks if the sender is player, otherwise it will return a message
    	if (!(sender instanceof Player)) {
	     	 Message.console("Your not a player....");
	     	 return true;
    	 }

	     Player player = (Player) sender;
	     
	     if (command.getName().equalsIgnoreCase("bootcamp")) { 	 
	    	 //Checks if there is put in a subcommand, otherwise will return a message
	    	 if (args.length == 0) {
	         	Message.player(player, "Vul een subcommand in!");
	         	return true;
	     	 }

	            SubCommand target = this.get(args[0]);
	            //Checks if subcommand exist, otherwise will return a message
	            if (target == null) {
	                Message.player(player, "Dit subcommand bestaat niet!");
	                return true;
	            }
	            
	            //Checks if the command has permissions, if it doesn't have permissions, it will pass this phase
	            if (!(target.getPermission() == null)) {
	            	//Checks if the player has the right permission, otherwise will return a message
                	if(player.hasPermission(target.getPermission())) {
                	} else {
                		Message.player(player, "Je hebt niet de juiste permissies");
                		return true;
                	}
                }
	            
	            
	            
	            ArrayList<String> arrayList = new ArrayList<String>();

	            arrayList.addAll(Arrays.asList(args));
	            arrayList.remove(0);
	            
	            //Try's to run the sub command / args on the player, otherwise if there is a error it will return a error in the console and a message in chat
	            try{
	                target.onCommand(player,args);
	            }catch (Exception e){
	                Message.player(player, "Er was een error");

	                e.printStackTrace();
	            }
	        }

	        return true;
    }

	/**
	 * This checks if the subcommand exists
	 * @param name is the putted in sub command
	 * @return sc is returning because if it is null, it will stop the command and thinks that there isn't a existing subcommand like name
	 * @return null this will return if the subcommand doensn't exists
	 */
	private SubCommand get(String name) {
        Iterator<SubCommand> subcommands = this.commands.iterator();
        
        //Checks if subcommands has the writed subcommand in it, otherwise it will return null
        while (subcommands.hasNext()) {
        	SubCommand sc = (SubCommand) subcommands.next();
            
        	//Checks if the putted in subcommand is the same as the one putted in the class, otherwise it will look at the aliases
            if (sc.name().equalsIgnoreCase(name)) {
            	return sc;
            }
            
            String[] aliases;
            int length = (aliases = sc.aliases()).length;
            
            //Checks the aliases for the command, otherwise if it still doesn't exists, it will return null;
            for (int var5 = 0; var5 < length; ++var5) {
                String alias = aliases[var5];
                if (name.equalsIgnoreCase(alias)) {
                   return sc;
                }

            }
        }

        return null;
    }

}
