package dev.idw0309.ddgbootcamp.teleport;

import java.io.File;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import dev.idw0309.ddgbootcamp.Core;
import dev.idw0309.ddgbootcamp.commands.subcommands.joinArena;
import dev.idw0309.ddgbootcamp.message.Message;

public class Teleporter {
	
	/**
	 * This will respawn the players after a hit from a arrow form a player
	 * @param target is the player who got shot by a arrow
	 * @param world is the world where the arena is in
	 */
	public static void respawn(Player target, World world) {
		
		File f = new File(Core.path, "configs/arenas.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		String arena = joinArena.arena.get(target);
				
		List<String> spawns = cfg.getStringList("Arenas." + arena  + ".spawns");
		
		//Checks if there is a spawnpoint, otherwise it will send a message and return
		if (spawns.size() == 0) {
			Message.player(target, "Er zijn nog geen game spawnpoints gezet!");
		}
		
		String stringSpawn = spawns.get(ThreadLocalRandom.current().nextInt(0, spawns.size()));
		String[] spawnData = stringSpawn.split("--");
		Location spawn = new Location(world, Integer.parseInt(spawnData[0]), Integer.parseInt(spawnData[1]), Integer.parseInt(spawnData[2]));
	    //Teleports the player to a random spawn location
		target.teleport(spawn);
	
	}
	
	/**
	 * This will teleport the player to the lobbypoint after joining the arena
	 * @param player the player who runned the command
	 * @param arena is the arena that the player filled in while joining
	 */
	public static void gamelobby(Player player, String arena) {
		File f = new File(Core.path, "configs/arenas.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		//Checks what the world is of the filled in arena
		String worldd = cfg.getString("Arenas." + arena + ".world");
		
		//Checks the lobby point of the world
		String stringSpawn = cfg.getString("Arenas." + arena + ".lobbypoint");
		String[] spawnData = stringSpawn.split("--");
		Location spawn = new Location(Bukkit.getWorld(worldd), Integer.parseInt(spawnData[0]), Integer.parseInt(spawnData[1]), Integer.parseInt(spawnData[2]));
		//Teleports the player to the lobby point and adds the player to the Map with the arena name
		player.teleport(spawn);
		joinArena.arena.put(player, arena);
	}

}
