package dev.idw0309.ddgbootcamp.events;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import dev.idw0309.ddgbootcamp.commands.subcommands.joinArena;
import dev.idw0309.ddgbootcamp.message.Message;
import dev.idw0309.ddgbootcamp.teleport.Teleporter;
import dev.idw0309.ddgbootcamp.timer.Timer;

public class onBowShot implements Listener {
	
	/**
	 * This will check if a player hitted a person or block, and after that it will do the rest like add a kill and death.
	 * @param e is from the event
	 * @return
	 */
    public static void HitEvent(final ProjectileHitEvent e) {
    	//Checks is entitytype is a arrow, otherwise it will return
		if(!(e.getEntityType() == EntityType.ARROW)) return;
		Arrow arrow = (Arrow) e.getEntity();
		
		e.getEntity().remove();
		
		//checks if the shooter is a player, otherwise return
		if(!(arrow.getShooter() instanceof Player)) return;
		Player p = (Player) arrow.getShooter();
		
		//Checks if the player hitted a player, otherwise starts a 10sec timer and returns
		if(!(e.getHitEntity() instanceof Player)) {
			Timer.nonhittimer(p); //Starts 10 sec timer
			return;
		}
		
		Player target = (Player) e.getHitEntity();
		World world = target.getLocation().getWorld();
		
		//if (!(target instanceof Player)) return;  ( MOGELIJK ZELFDE ALS LIJN 36 )
		
		//Checks if the shooter of the arrow if the hitted person, otherwise it will skip this
		if (target == p) {
			Message.player(p, "Je kan je zelf niet beschieten!");
			Timer.nonhittimer(p); //Starts timer
			return;
		}
		
		//CHecks if player is in a created area, otherwise it will start a timer and return
		if(!(joinArena.arena.containsKey(target))) {
			//TODO Checken ofdat de persoon in de zelfde arena zit
			Timer.nonhittimer(p); //Starts timer
			return;
		}
		
		ItemStack arroww = new ItemStack(Material.ARROW);
		p.getInventory().addItem(arroww);
		p.sendTitle("§a+1 Kill", "", 10, 25, 10);
			
		target.sendTitle("§cYou died", "", 10, 25, 10);
		Teleporter.respawn(target, world);
		
	}
	
}
