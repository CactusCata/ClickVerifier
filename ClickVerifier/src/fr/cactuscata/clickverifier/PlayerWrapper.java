package fr.cactuscata.clickverifier;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerWrapper {

	public static HashMap<UUID, PlayerWrapper> players = new HashMap<>();
	public int clicks = 0;
	private int[] click = new int[5];
	public int nombreAlertesAutoClick = 0;
	public int maxClicks = 0;
	public long lastBlockInteraction = 0L;
	public long lastAlert = 0L;
	public long Connexion = 0L;
	public String pseudo;

	public PlayerWrapper(Player p) {
		players.put(p.getUniqueId(), this);
		this.pseudo = p.getName();
		this.Connexion = System.currentTimeMillis();
	}

	public String getName() {
		return this.pseudo;
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(this.pseudo);
	}

	public static PlayerWrapper getByPlayer(Player p) {
		return (PlayerWrapper) players.get(p.getUniqueId());
	}

	public static PlayerWrapper getByString(String name) {
		if (Bukkit.getPlayerExact(name) == null) {
			return null;
		}
		return (PlayerWrapper) players.get(Bukkit.getPlayerExact(name).getUniqueId());
	}

	public static void removePlayer(Player p) {
		players.remove(p.getUniqueId());
	}

	public int getPing() {
		return ((CraftPlayer) Bukkit.getPlayer(getName())).getHandle().ping;
	}

	public int[] getClick() {
		return this.click;
	}

	public void updateClicks() {
		click[4] = click[3];
		click[3] = click[2];
		click[2] = click[1];
		click[1] = click[0];
		click[0] = clicks;
		clicks = 0;
	}

	public static void registerAllPlayer() {
		for (Player players : Bukkit.getOnlinePlayers())
			new PlayerWrapper(players);
	}

	public static void unregisterAllPlayer() {
		for (Player players : Bukkit.getOnlinePlayers())
			PlayerWrapper.removePlayer(players);

	}
}
