package dev.idw0309.plugin1;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import dev.idw0309.plugin1.Events.Events;
import dev.idw0309.plugin1.commands.setspawn;

public class Core extends JavaPlugin {
	
	public static Core plugin;
	public static String path;
	public static Set<Player> map = new HashSet<>();
	
	@Override
	public void onEnable() {
		plugin = this;
		path = getDataFolder().getAbsolutePath();
		getServer().getPluginManager().registerEvents(new Events(), this);
		
		if(!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		createconfig();
		commands();

	}
	
	private void commands() {
		getCommand("setspawn").setExecutor(new setspawn());
	}

	private void createconfig() {
		if(!new File(Core.path, "configs/spawnlocations.yml").exists()) {
			File f = new File(Core.path, "configs/spawnlocations.yml");
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			
			cfg.options().header("DDG-WEEK-1 V1.0 Spawn locations Config || Copyright (C) 2021 IdW0309 || PRIXI0US");
			cfg.set("Spawnlocations.loc1.x", "world");
			cfg.set("Spawnlocations.loc1.x", Integer.parseInt("0"));
			cfg.set("Spawnlocations.loc1.y", Integer.parseInt("0"));
			cfg.set("Spawnlocations.loc1.z", Integer.parseInt("0"));
			try {
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
