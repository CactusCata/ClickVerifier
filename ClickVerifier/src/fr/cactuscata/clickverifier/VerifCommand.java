package fr.cactuscata.clickverifier;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class VerifCommand implements CommandExecutor {
	private static Map<Player, PlayerWrapper> verifiers = new HashMap<>();

	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cOnly usable by Players.");
			return true;
		}

		if (args.length != 1) {
			sender.sendMessage("§cInvalide command usage, use '/verif player'");
			return true;
		}

		if (Bukkit.getPlayer(args[0]) != null) {
			Inventory i = Bukkit.createInventory(null, 9, "§cVerif > " + args[0]);
			i.addItem(new ItemStack(Material.GOLD_BLOCK));
			i.addItem(new ItemStack(Material.REDSTONE_BLOCK));
			i.addItem(new ItemStack(Material.IRON_BLOCK));
			i.setItem(3, new ItemStack(Material.EMERALD_BLOCK));
			i.addItem(new ItemStack(Material.DIAMOND_BLOCK));
			verifiers.put((Player) sender, PlayerWrapper.getByPlayer(Bukkit.getPlayer(args[0])));
			((Player) sender).openInventory(i);
		} else {
			((Player) sender).sendMessage("§cJoueur non connecté.");
		}
		return true;
	}

	public static Map<Player, PlayerWrapper> getVerifiers() {
		return verifiers;
	}
	
}
