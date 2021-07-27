package dev.idw0309.ddgbootcamp.commands.subcommands;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.idw0309.ddgbootcamp.Core;
import dev.idw0309.ddgbootcamp.commands.SubCommand;
import dev.idw0309.ddgbootcamp.message.Message;
import dev.idw0309.ddgbootcamp.teleport.Teleporter;

public class joinArena extends SubCommand {
	
	public static HashMap<Player, String> arena = new HashMap<>();
	
	/**
	 * This command is to join a arena that exist
	 * @param player is the player that runned the command
	 * @param args is the filled in arena
	 */
	@Override
    public void onCommand(Player player, String[] args) {
		//Checks if the person filled in the full command
		if(args.length == 1) {
			Message.player(player, "/bootcamp join <Arena>");
			return;
		}
		
		File f = new File(Core.path, "configs/arenas.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		String arena = args[1];
		
		//Checks if the Arena has a lobby point, otherwise it will return and send a message to the player
		if(cfg.getString("Arenas." + arena + ".lobbypoint") == null) {
			Message.player(player, "Deze arena bestaat nog niet of heeft nog geen lobby point!");
			return;
		}
		
		//Checks if a player is in a arena, otherwise it will pass this phase
		if(joinArena.arena.containsKey(player)) {
			Message.player(player, "Je zit al in deze arena");
			return;
		}
		
		Teleporter.gamelobby(player, arena);
		ItemStack arrow = new ItemStack(Material.ARROW);
		ItemStack bow = new ItemStack(Material.BOW);
		ItemMeta itemMeta = bow.getItemMeta();
		itemMeta.setUnbreakable(true);
		bow.setItemMeta(itemMeta);
		
		player.getInventory().addItem(arrow);
		player.getInventory().addItem(bow);
		Message.player(player, "Je bent de arena "+ arena + " gejoined!");
		
    }

    @Override
    public String name() {
        return "join";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String getPermission() {
		return null;
	}
}
