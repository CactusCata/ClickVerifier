package fr.cactuscata.clickverifier;

import org.bukkit.plugin.java.JavaPlugin;

public class ClickVerifier extends JavaPlugin {
	public static ClickVerifier instance;

	public void onEnable() {

		instance = this;

		new Tps();

		getServer().getPluginManager().registerEvents(new AutoclickListener(), this);
		getCommand("verif").setExecutor(new VerifCommand());
		saveConfig();
		new VerifRunnable().runTaskTimerAsynchronously(this, 0L, 1L);
		new CheckRunnable().runTaskTimerAsynchronously(this, 0L, 20L);
		
		PlayerWrapper.registerAllPlayer();

	}

	public void onDisable() {

		PlayerWrapper.unregisterAllPlayer();

	}

	public double getTps() {

		return Tps.tps + 1.0D;
	}
}