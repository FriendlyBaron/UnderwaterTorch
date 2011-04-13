package me.itsatacoshop247.DailyBonus;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.coelho.iConomy.iConomy;
import com.nijiko.permissions.PermissionHandler;

public class DailyBonus extends JavaPlugin {
	private static final Logger log = Logger.getLogger("Minecraft");
	static String maindirectory = "plugins/DailyBonus/";
	static File locations = new File(maindirectory + "DailyBonusTimes.txt");
	private static iConomy iConomy = null;
	private static Server Server = null;
	public static PermissionHandler Permissions;
	public String pName;
	 private final DailyBonusPlayerListener playerListener = new DailyBonusPlayerListener(this);
	 
	@Override
	public void onDisable() {
		log.info("DailyBonus Disabled");
	}

	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
		log.info("DailyBonus is running!");
		Server = getServer();
		PluginDescriptionFile pdf = this.getDescription();
		pName = pdf.getName();
		new File(maindirectory).mkdir();
		DailyBonusLoadSettings.loadMain();
	}
    public static Server getBukkitServer() {
        return Server;
    }

    public static iConomy getiConomy() {
        return iConomy;
    }
    
    public static boolean setiConomy(iConomy plugin) {
        if (iConomy == null) {
            iConomy = plugin;
        } else {
            return false;
        }
        return true;
    }
}