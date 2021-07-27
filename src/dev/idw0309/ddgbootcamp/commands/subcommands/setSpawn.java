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

public class setSpawn extends SubCommand {	
	
	/**
	 * This command will set a spawnlocation before the game starts in the arena
	 * @param player is the player that runned the command
	 * @param args is the filled in arena
	 */
	@Override
    public void onCommand(Player player, String[] args) {		
		//Checks if the person filled in the full command
		if(args.length == 1) {
			Message.player(player, "/bootcamp setspawn <Arena>");
			return;
		}
		
		Location loca = player.getLocation();
		File f = new File(Core.path, "configs/arenas.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		String arena = args[1];
		
		//Checks if the arena exist, otherwise it will send a message and return
		if(cfg.getString("Arenas." + arena + ".world") == null) {
			Message.player(player, "De ingevulde arena bestaat niet!");
			return;
		}
		
		List<String> spawns = cfg.getStringList("Arenas." + arena  + ".spawns"); 
		
		//Adds the spawnlocation to a String list in the config.
		String newspawnString = loca.getBlockX() + "--" + loca.getBlockY() + "--" + loca.getBlockZ();
		spawns.add(newspawnString);
		cfg.set("Arenas." + arena + ".spawns", spawns);
		
		//Try's to save the config, otherwise puts out a error
		try {cfg.save(f);} catch (IOException e) {e.printStackTrace();}
		
		Message.player(player, "Je hebt spawnlocatie geplaatst.");
    }

    @Override
    public String name() {
        return "setspawn";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String getPermission() {
		return "bootcamp.ddg.setspawn";
	}



}
