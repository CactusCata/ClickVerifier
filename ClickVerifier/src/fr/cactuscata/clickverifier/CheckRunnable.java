package fr.cactuscata.clickverifier;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import fr.cactuscata.clickverifier.customevents.AutoclickAlertEvent;

public class CheckRunnable extends BukkitRunnable {
	private final int maxCps = 18;

	public void run() {
		for (PlayerWrapper wp : PlayerWrapper.players.values()) {
			int ping = wp.getPing();
			double tps = Math.round(ClickVerifier.instance.getTps() * 100.0D) / 100.0D;
			int AntiLag = (int) ((20.0D - tps) * 2.0D);
			AntiLag += ping / 50;
			if ((wp.clicks >= this.maxCps + AntiLag)
					&& (wp.lastAlert + 1000L < System.currentTimeMillis())) {
				AutoclickAlertEvent event = new AutoclickAlertEvent(wp.pseudo, wp.clicks, ping, tps);
				Bukkit.getServer().getPluginManager().callEvent(event);
				wp.lastAlert = System.currentTimeMillis();
				if (!event.isCancelled()) {
					Bukkit.broadcastMessage("§f[§9§lAntiCheat§f]§r §3 " + wp.pseudo + "§b a fait §4" + wp.clicks
							+ " §bclics (MS: " + ping + " [TPS: " + tps + "])");
					wp.nombreAlertesAutoClick++;
				}
			}
			wp.updateClicks();
		}
	}
}
