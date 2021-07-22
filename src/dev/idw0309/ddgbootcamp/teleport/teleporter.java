package dev.idw0309.ddgbootcamp.teleport;

import java.io.File;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import dev.idw0309.ddgbootcamp.Core;
import dev.idw0309.ddgbootcamp.Message.Message;

public class teleporter {

	public static void respawn(Player target) {
		Random randomNo = new Random();
		int min = 1;
		int max = 4;
		int output = randomNo.nextInt((max-min) + 1) + min;
		
		File f = new File(Core.path, "configs/spawnlocations.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		String world = (String) cfg.get("Spawnlocations.loc"+ output +".world");
		int x = cfg.getInt("Spawnlocations.loc"+ output +".x");
		int y = cfg.getInt("Spawnlocations.loc"+ output +".y");
		int z = cfg.getInt("Spawnlocations.loc"+ output +".z");
		
		if (world == null) {
			Message.console("De spawn locatie " + output + " is niet bekend in de config!");
		} else {
			Location loc = new Location(Bukkit.getWorld(world), x, y, z, 0, 0);
			target.teleport(loc);
		}
	}

}
