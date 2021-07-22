package dev.idw0309.ddgbootcamp.Events;


import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import dev.idw0309.ddgbootcamp.teleport.teleporter;
import dev.idw0309.ddgbootcamp.timer.Timer;



public class Events implements Listener {

	
	@EventHandler
    private void onShot(final ProjectileHitEvent e) {
		if(!(e.getEntityType() == EntityType.ARROW)) return;
		Arrow arrow = (Arrow) e.getEntity();
		
		e.getEntity().remove();
		
		if(!(arrow.getShooter() instanceof Player)) return;
		Player p = (Player) arrow.getShooter();
		if(!(e.getHitEntity() instanceof Player)) {
			Timer.nonhittimer(p); //Starts timer
			return;
		}
		Player target = (Player) e.getHitEntity();
		
		if (!(target instanceof Player)) return;
		
		ItemStack arroww = new ItemStack(Material.ARROW);
		p.getInventory().addItem(arroww);
		p.sendTitle("§a+1 Kill", "", 10, 25, 10);
			
		target.sendTitle("§cYou died", "", 10, 25, 10);
		teleporter.respawn(target);
		
	}
	
	
	
}
