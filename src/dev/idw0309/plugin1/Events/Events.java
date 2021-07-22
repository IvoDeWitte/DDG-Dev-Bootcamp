package dev.idw0309.plugin1.Events;


import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import dev.idw0309.plugin1.teleport.teleporter;
import dev.idw0309.plugin1.timer.Timer;



public class Events implements Listener {
	
	 
	@EventHandler
	public void hit(EntityDamageByEntityEvent e){
		 
		if(e.getDamager() instanceof Arrow){
			Arrow arrow = (Arrow) e.getDamager();
		 
			if(arrow.getShooter() instanceof Player){
				
				if(e.getEntity() instanceof Player){
					Player target = (Player) e.getEntity();
					e.setCancelled(true);
					target.sendTitle("§cYou died", "", 10, 25, 10);
					teleporter.respawn(target);
				}
			}
		}
	}
	
	@EventHandler
    private void onShot(final ProjectileHitEvent e) {
			if(e.getEntityType() == EntityType.ARROW) {
				if(e.getHitEntity() == null) { //Persoon mist
					Arrow arrow = (Arrow) e.getEntity();
					Player p = (Player) arrow.getShooter();

					Timer.nonhittimer(p); //Starts timer
					
					e.getEntity().remove();
				} else { //persoon raakt
					Arrow arrow = (Arrow) e.getEntity();
					Player p = (Player) arrow.getShooter();
					
					ItemStack arroww = new ItemStack(Material.ARROW);
					p.getInventory().addItem(arroww);
					p.sendTitle("§a+1 Kill", "", 10, 25, 10);
					
					e.getEntity().remove();
				}
			}
	}
	
	
	
}
