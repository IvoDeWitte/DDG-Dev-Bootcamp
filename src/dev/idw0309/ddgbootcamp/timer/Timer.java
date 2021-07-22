package dev.idw0309.ddgbootcamp.timer;

import java.util.concurrent.TimeUnit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import dev.idw0309.ddgbootcamp.Core;

public class Timer {
	
	public static void nonhittimer(Player p) {
		
		long duration = TimeUnit.SECONDS.toNanos(10); //10 seconds
		long start = System.nanoTime();
		new BukkitRunnable() {
		    public void run() {
		        long diff = System.nanoTime() - start;
		        if (diff > duration) {
		        	p.setExp(0F);
		        	ItemStack arroww = new ItemStack(Material.ARROW);
					p.getInventory().addItem(arroww);
		            this.cancel(); //we're done!
		        }
		        
		        if (!((0F + ((float) diff / (float) duration)) >= 0.99F)) {
		        	p.setExp(0F + ((float) diff / (float) duration));
		        } 
		        
		    }
		}.runTaskTimer(Core.plugin, 0, 1);
		
	}

}
