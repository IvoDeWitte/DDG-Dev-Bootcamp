package dev.idw0309.ddgbootcamp.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {
	
	public static void event(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
		if(player.getInventory().contains(Material.BOW) || player.getInventory().contains(Material.ARROW)) {
			player.getInventory().remove(Material.BOW);
			player.getInventory().remove(Material.ARROW);
		}
		
	}
	
}
