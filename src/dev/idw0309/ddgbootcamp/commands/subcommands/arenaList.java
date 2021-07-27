package dev.idw0309.ddgbootcamp.commands.subcommands;

import java.io.File;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import dev.idw0309.ddgbootcamp.Core;
import dev.idw0309.ddgbootcamp.commands.SubCommand;
import dev.idw0309.ddgbootcamp.message.Message;

public class arenaList extends SubCommand {
	
	/**
	 * This puts out a message with all the arena's
	 * @param player is the player that runned the command
	 */
	@Override
    public void onCommand(Player player, String[] args) {
		
		File f = new File(Core.path, "configs/arenas.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		List<String> spawns = cfg.getStringList("Arenas.allarenas");
		
		//Checks if the List has 0 strings in it, otherwise it will pass this.
		if (spawns.size() == 0) {
			Message.player(player, "Er zijn nog geen arena's aangemaakt!");
			return;
		}
		//Sends a message with all the Strings from the list
		Message.player(player, "" + spawns);
    }

    @Override
    public String name() {
        return "list";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String getPermission() {
		return "bootcamp.ddg.arenalist";
	}
}
