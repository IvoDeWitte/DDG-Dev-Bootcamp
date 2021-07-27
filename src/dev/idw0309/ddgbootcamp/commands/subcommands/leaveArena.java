package dev.idw0309.ddgbootcamp.commands.subcommands;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.idw0309.ddgbootcamp.commands.SubCommand;
import dev.idw0309.ddgbootcamp.message.Message;
import dev.idw0309.ddgbootcamp.teleport.Teleporter;

public class leaveArena extends SubCommand {
	
	/**
	 * This is a command to leave a arena
	 * @param player is the player that runned the command
	 */
	@Override
    public void onCommand(Player player, String[] args) {
		//Checks if player is in a arena, otherwise it will send a message that he isn't in a arena
		if (joinArena.arena.containsKey(player)) {
			String arena = joinArena.arena.get(player);
			Teleporter.gamelobby(player, arena);
			joinArena.arena.remove(player);
			
			ItemStack arrow = new ItemStack(Material.ARROW);
			ItemStack bow = new ItemStack(Material.BOW);
			ItemMeta itemMeta = bow.getItemMeta();
			itemMeta.setUnbreakable(true);
			bow.setItemMeta(itemMeta);
			
			player.getInventory().removeItem(arrow);
			player.getInventory().removeItem(bow);
			
			Message.player(player, "Je bent de arena geleaved!");
		} else {
			Message.player(player, "Je zit niet in een arena");
		}
    }

    @Override
    public String name() {
        return "leave";
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
