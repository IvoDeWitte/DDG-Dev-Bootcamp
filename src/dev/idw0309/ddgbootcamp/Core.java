package dev.idw0309.ddgbootcamp;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import dev.idw0309.ddgbootcamp.commands.CommandManager;
import dev.idw0309.ddgbootcamp.events.Events;

public class Core extends JavaPlugin implements Listener {
	
	public static Core plugin;
	public static String path;
	public static Set<Player> map = new HashSet<>();
	public CommandManager commandManager;
	
	@Override
	public void onEnable() {
		plugin = this;
		path = getDataFolder().getAbsolutePath();
		getServer().getPluginManager().registerEvents(new Events(), this);
		
		if(!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		createconfig();
		
		commandManager = new CommandManager();

        commandManager.setup();

	}
	
	/**
	 * Creates configs if they do not exist
	 * @param File The expected file that it wants to create
	 */
	private void createconfig() {
		if(!new File(Core.path, "configs/arenas.yml").exists()) {
			//Checks if file exist, otherwise it will make one
			File f = new File(Core.path, "configs/arenas.yml");
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			
			cfg.options().header("DDG-WEEK-1 V1.0 Spawn locations Config || Copyright (C) 2021 IdW0309 || PRIXI0US");
			try {
				//Try's to save the config, otherwise it will put out a error
				cfg.save(f);
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
	}
	
	public static Core getInstance() {
		return plugin;
	}
}
