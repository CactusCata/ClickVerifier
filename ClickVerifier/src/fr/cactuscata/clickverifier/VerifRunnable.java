package fr.cactuscata.clickverifier;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class VerifRunnable extends BukkitRunnable {
	public void run() {
		for (Player players : VerifCommand.getVerifiers().keySet()) {
			if ((players.getOpenInventory().getTopInventory() != null)
					&& (players.getOpenInventory().getTopInventory().getTitle().startsWith("§cVerif >"))) {
				if (Bukkit.getPlayer(players.getOpenInventory().getTopInventory().getName().split("> ")[1]) != null) {
					PlayerWrapper wp = (PlayerWrapper) VerifCommand.getVerifiers().get(players);

					final int[] click = wp.getClick();
					ItemStack item1 = new ItemStack(Material.GOLD_BLOCK, click[0] > 64 ? 64 : click[0]);
					ItemMeta item1M = item1.getItemMeta();
					item1M.setDisplayName("§cClicks des 5 dernieres secondes :");
					item1M.setLore(Arrays.asList("- " + click[0], "- " + click[1], "- " + click[2], "- " + click[3],
							"- " + click[4]));
					item1.setItemMeta(item1M);

					// -----------------

					int nombrealert = 1;
					if (wp.nombreAlertesAutoClick > 0)
						nombrealert = wp.nombreAlertesAutoClick;
					ItemStack item2 = new ItemStack(Material.REDSTONE_BLOCK, nombrealert > 64 ? 64 : nombrealert);
					ItemMeta item2M = item2.getItemMeta();
					item2M.setDisplayName("§cNombre d'alertes: " + wp.nombreAlertesAutoClick);
					item2.setItemMeta(item2M);

					// -----------------

					final int ping = wp.getPing();
					ItemStack item3 = new ItemStack(Material.IRON_BLOCK, ping > 64 ? 64 : ping);
					ItemMeta item3M = item3.getItemMeta();
					item3M.setDisplayName("§6Ping: §f" + ping);

					item3M.setLore(Arrays.asList(
							"§6Connecté depuis " + (System.currentTimeMillis() - wp.Connexion) / 60000 + " minutes."));

					item3.setItemMeta(item3M);

					// -----------------

					ItemStack item4 = new ItemStack(Material.EMERALD_BLOCK, wp.clicks > 64 ? 64 : wp.clicks);
					ItemMeta item4M = item4.getItemMeta();
					item4M.setDisplayName("§9Clics actuellement: " + wp.clicks);
					item4.setItemMeta(item4M);

					// -----------------

					ItemStack item5 = new ItemStack(Material.DIAMOND_BLOCK);
					ItemMeta item5M = item5.getItemMeta();
					item4M.setDisplayName(" ");
					item5M.setLore(Arrays.asList("§eMaximum de", "§eclicks: §f" + wp.maxClicks));
					item5.setItemMeta(item5M);

					players.getOpenInventory().getTopInventory().setItem(0, item1);
					players.getOpenInventory().getTopInventory().setItem(1, item2);
					players.getOpenInventory().getTopInventory().setItem(2, item3);
					players.getOpenInventory().getTopInventory().setItem(3, item4);
					players.getOpenInventory().getTopInventory().setItem(4, item5);

				}
			} else {
				VerifCommand.getVerifiers().remove(players);
			}
		}
	}
}