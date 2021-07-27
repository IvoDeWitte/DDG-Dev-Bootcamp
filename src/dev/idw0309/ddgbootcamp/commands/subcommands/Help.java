package dev.idw0309.ddgbootcamp.commands.subcommands;
import org.bukkit.entity.Player;

import dev.idw0309.ddgbootcamp.commands.SubCommand;
import dev.idw0309.ddgbootcamp.message.Message;

public class Help extends SubCommand {
	
	/**
	 * This is a help command that will send all the commands to the player that are in this plugin
	 * @param player is the player that runned the command
	 */
	@Override
    public void onCommand(Player player, String[] args) {
		Message.commandplayer(player, "/bootcamp help\n"
									+ "/bootcamp leave\n"
									+ "/bootcamp list\n"
									+ "/bootcamp join <arena>\n"
									+ "/bootcamp createarena <arena>\n"
									+ "/bootcamp setspawn <arena>\n"
									+ "/bootcamp setlobbyspawn <arena>\n", 1, 1);
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String getPermission() {
		return "bootcamp.ddg.help";
	}
}
