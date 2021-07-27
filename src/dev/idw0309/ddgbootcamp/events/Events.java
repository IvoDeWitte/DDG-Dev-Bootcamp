package dev.idw0309.ddgbootcamp.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerQuitEvent;



public class Events implements Listener {
	
	/**
	 * This class registers all events and put them thru to the other clases
	 * @param e is the event
	 */
	
	@EventHandler
    private void onBowShot(final ProjectileHitEvent e) {
		onBowShot.HitEvent(e);		
	}
	
	@EventHandler
	private void onLeave(final PlayerQuitEvent e) {
		onLeave.event(e);
	}
	
	
	
}
