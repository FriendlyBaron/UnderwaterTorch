package me.itsatacoshop247.UnderWaterTorch;

import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class UnderWaterTorch extends JavaPlugin{
	private static final Logger log = Logger.getLogger("Minecraft");
	 private final UnderWaterTorchBlockListener blockListener = new UnderWaterTorchBlockListener(this);
	 
	 @Override
	 public void onDisable() {
		log.info("UnderwaterTorch Disabled");
	 }

	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_FROMTO, blockListener, Event.Priority.Normal, this);
		log.info("UnderwaterTorch Enabled");
	}

}