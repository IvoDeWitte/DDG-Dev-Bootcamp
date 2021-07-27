package dev.idw0309.ddgbootcamp.commands.subcommands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import dev.idw0309.ddgbootcamp.Core;
import dev.idw0309.ddgbootcamp.commands.SubCommand;
import dev.idw0309.ddgbootcamp.message.Message;

public class createArena extends SubCommand {	
	
	/**
	 * This is a command that creates a arena
	 * @param player is the player that runned the command
	 * @param args is the filled in arena
	 */
	@Override
    public void onCommand(Player player, String[] args) {
		//Checks if the person filled in the full command
		if(args.length == 1) {
			Message.player(player, "/bootcamp createarena <Arena>");
			return;
		}
		
		Location loca = player.getLocation();		
		File f = new File(Core.path, "configs/arenas.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		String arena = args[1];
		
		//Puts the normal arena data in config file like ( World )
		cfg.set("Arenas." + arena + ".world", loca.getWorld().getName());
		//cfg.set("Arenas." + args[1] + ".maxplayers", Integer.parseInt(args[2]));  NOG NIET NODIG ATM
		
		List<String> arenas = cfg.getStringList("Arenas.allarenas"); 
		String newarenasString = arena;
		arenas.add(newarenasString);
		
		//Puts the arena to the total arena list
		cfg.set("Arenas.allarenas", arenas);
		
		//Try's to save the config, otherwise it wil put out a error
		try {cfg.save(f);} catch (IOException e) {e.printStackTrace();}
		
		Message.player(player, "Je hebt een arena genaamd " + arena +" aangemaakt!");
		Message.player(player, "Plaats nu de lobby spawnpoint en game spawnpoints!");
    }

    @Override
    public String name() {
        return "createarena";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String getPermission() {
		return "bootcamp.ddg.createarena";
	}
}
