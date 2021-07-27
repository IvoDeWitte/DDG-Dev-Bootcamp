package dev.idw0309.ddgbootcamp.timer;

import java.util.concurrent.TimeUnit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import dev.idw0309.ddgbootcamp.Core;

public class Timer {
	
	
	/**
	 * This will start the timer for the player after he missed his shot
	 * @param p is the player who missed his bow shot
	 */
	public static void nonhittimer(Player p) {
		
		long duration = TimeUnit.SECONDS.toNanos(10); //timer 10 seconds
		long start = System.nanoTime();
		new BukkitRunnable() {
		    public void run() {
		        long diff = System.nanoTime() - start;
		      //Checks if the timer has runned for 10 seconds
		        if (diff > duration) {
		        	p.setExp(0F);
		        	ItemStack arroww = new ItemStack(Material.ARROW);
					p.getInventory().addItem(arroww);
		            this.cancel(); //we're done!
		        }
		        float xpbar = 0F + (float) diff / (float) duration; 
		        
		        //Checks if the xp bar goes higher then 0.99 to prevent errors (max is 1.0), otherwise it will skip this bit
		        if (!(xpbar >= 0.99F)) {
		        	p.setExp(xpbar); 
		        } 
		        
		    }
		}.runTaskTimer(Core.plugin, 0, 1);
		
	}

}
