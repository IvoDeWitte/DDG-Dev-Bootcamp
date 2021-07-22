package dev.idw0309.ddgbootcamp.Message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message {
	
	private static String prefix2;
	
	static {
		if(prefix2 == null) {
			prefix2 = ChatColor.RED + ChatColor.BOLD.toString() + "DDG-BOOTCAMP " + ChatColor.WHITE;
		}
	}
	
	public static void console(String message) {
		Bukkit.getConsoleSender().sendMessage(prefix2 + message);
	}
	
	public static void player(CommandSender sender, String message) {
		sender.sendMessage(prefix2 + message);
	}

	public static void playerERROR(Player pl, String message) {
		pl.sendMessage(prefix2 + message);
	}
}
