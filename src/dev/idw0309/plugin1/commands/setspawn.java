package dev.idw0309.plugin1.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import dev.idw0309.plugin1.Core;
import dev.idw0309.plugin1.Message.Message;

public class setspawn implements CommandExecutor {
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if (sender.hasPermission("ddg.setspawn")) {
			if (args.length == 1) {
				String loc = args[0];
				Location loca = player.getLocation();
			
				File f = new File(Core.path, "configs/spawnlocations.yml");
				FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
				
				cfg.set("Spawnlocations.loc"+ loc +".world", loca.getWorld().getName());
				cfg.set("Spawnlocations.loc"+ loc +".x", loca.getX());
				cfg.set("Spawnlocations.loc"+ loc +".y", loca.getY());
				cfg.set("Spawnlocations.loc"+ loc +".z", loca.getZ());
				try {cfg.save(f);} catch (IOException e) {e.printStackTrace();}
			
			}
		} else {
			Message.playerERROR(player, "Je hebt niet de juiste permissies");
		}
		return false;
	}
}
