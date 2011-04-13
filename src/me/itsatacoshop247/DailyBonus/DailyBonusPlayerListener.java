package me.itsatacoshop247.DailyBonus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.Plugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import com.nijiko.coelho.iConomy.iConomy;
import com.nijiko.coelho.iConomy.system.Account;


public class DailyBonusPlayerListener extends PlayerListener{
	
	public static DailyBonus plugin;
	public static PermissionHandler Permissions;
	
	public DailyBonusPlayerListener(DailyBonus instance) {
		plugin = instance;
	}
	
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		addnewplayerfile(player);
		String amount = Integer.toString(DailyBonusLoadSettings.amntonlogin);
		if(CheckLastLogin(player)){
        	if (iConomy.getBank().hasAccount(player.getName()) && hasPermission(player, "DailyBonus.get")) {	
				Account account = iConomy.getBank().getAccount(player.getName());
				double balance = account.getBalance();
				balance +=DailyBonusLoadSettings.amntonlogin;
				account.setBalance(balance);
				player.sendMessage(ChatColor.GOLD +DailyBonusLoadSettings.message.replace("$amount$", amount).replace("$currecny_name$", iConomy.getBank().getCurrency()));
        	}
		}
}

	private void addnewplayerfile(Player p) {
		File file = new File ("plugins/DailyBonus/"+p.getName()+".txt");
	    if (!file.exists()){
	    	try {
				file.createNewFile();
			    Calendar cal2 = Calendar.getInstance();
			    int day = cal2.get(Calendar.DATE);
			    int month = cal2.get(Calendar.MONTH) + 1;
			    int day2 = day;
			    int month2 = month;
			    BufferedWriter out = new BufferedWriter(new FileWriter("plugins/DailyBonus/"+p.getName()+".txt", true));
			    out.write(""+day2);
			    out.newLine();
			    out.write(""+month2);
			    out.newLine();
			    out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    }
	}

	private boolean CheckLastLogin(Player p) {
		  int count = 0;
		  int count2 = 0;
		  int count3 = 0;
		  int count4 = 0;
	      try{
	    FileInputStream fstream = new FileInputStream("plugins/DailyBonus/"+p.getName()+".txt");
	    DataInputStream in = new DataInputStream(fstream);
	    Calendar cal = Calendar.getInstance();
	    int day = cal.get(Calendar.DATE);
	    int month = cal.get(Calendar.MONTH) + 1;
	    int[] w = {1000, 1000};
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    String strLine;
	    while ((strLine = br.readLine()) != null)   {
	    	w[count4] = Integer.parseInt(strLine.trim());
	    	if (count3 == 0){
	    		if(w[0] < day){
	    			count = 1;
	    		}
	    	}
	    	if (count3 == 1){
	    		if(w[1] <= month){
	    			count2 = 1;
	    		}
	    	}
	    	count3++;
	    	count4++;
	    }
	    in.close();
	    if(count > 0 && count2 > 0){
	    	File inputFile = new File("plugins/DailyBonus/"+p.getName()+".txt");
	    	inputFile.delete();
		    BufferedWriter out = new BufferedWriter(new FileWriter("plugins/DailyBonus/"+p.getName()+".txt", true));
		    out.write(""+day);
		    out.newLine();
		    out.write(""+month);
		    out.newLine();
		    out.close();
	    }
	    }catch (Exception e){
	    }
	    if(count > 0 && count2 > 0){
	    	return true;
	    }
	return false;
}
	public Boolean hasPermission(CommandSender sender, String node) {
        if (!(sender instanceof Player)) return true;
        
        Player player = (Player) sender;
        if (Permissions != null) return Permissions.has(player, node);
        else {
                Plugin test = plugin.getServer().getPluginManager().getPlugin("Permissions");
                if (test != null) {
                        Permissions = ((Permissions) test).getHandler();
                        return Permissions.has(player, node);
                }
        }
        return player.isOp();
}
}