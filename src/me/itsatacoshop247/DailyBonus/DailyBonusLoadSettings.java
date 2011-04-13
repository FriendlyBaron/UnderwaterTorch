package me.itsatacoshop247.DailyBonus;

import me.itsatacoshop247.DailyBonus.DailyBonusPluginProperties;

public class DailyBonusLoadSettings {
		static int amntonlogin;
		static String message;
		
		public static void loadMain(){
			String propertiesFile = DailyBonus.maindirectory + "MainConfig.properties";
			DailyBonusPluginProperties properties = new DailyBonusPluginProperties(propertiesFile);
			properties.load();
			
			amntonlogin= properties.getInteger("AMNT_TO_GIVE_ON_LOGIN", 15);
			message = properties.getString("message", "You just got $amount$ $currecny_name$ for logging in today!");
			properties.save("^===DailyBonus Main Configuration===^");
	}
}
