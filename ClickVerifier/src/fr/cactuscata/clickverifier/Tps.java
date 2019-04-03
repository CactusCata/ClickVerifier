package fr.cactuscata.clickverifier;

import org.bukkit.Bukkit;

public class Tps implements Runnable {
	private long sec;
	private long currentSec;
	public int ticks;
	public static double tps = 20.0D;

	public Tps() {
		Bukkit.getScheduler().runTaskTimer(ClickVerifier.instance, this, 0L, 1L);
	}

	public void run() {
		this.sec = (System.currentTimeMillis() / 1000L);
		if (this.currentSec == this.sec) {
			this.ticks++;
		} else {
			this.currentSec = this.sec;
			tps = tps == 0.0D ? this.ticks : (tps + this.ticks) / 2.0D;
			this.ticks = 0;
		}
	}
}
