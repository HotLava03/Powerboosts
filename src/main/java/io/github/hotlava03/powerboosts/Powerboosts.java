package io.github.hotlava03.powerboosts;

import io.github.hotlava03.powerboosts.commands.BuyPowerCmd;
import io.github.hotlava03.powerboosts.commands.PowerboostsCmd;
import io.github.hotlava03.powerboosts.strings.Strings;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Powerboosts extends JavaPlugin {

    public static Economy econ;
    public static FileConfiguration powerboosts;
    public static FileConfiguration messages;
    public static File file;
    public static File file2;

    @Override
    public void onEnable() {

        if (!Bukkit.getPluginManager().isPluginEnabled("Factions")) {
            getLogger().warning("Factions is not enabled or installed." +
                    " Please install this plugin in order for Powerboosts " +
                    "to enable.");
            getLogger().warning("*** This plugin will be disabled. ***");
            this.setEnabled(false);
            return;
        }

        if (!setupEconomy()) {
            getLogger().warning("Economy not found." +
                    " Please add Vault and/or an economy plugin " +
                    "in order for Powerboosts to load.");
            getLogger().warning("*** This plugin will be disabled. ***");
            this.setEnabled(false);
            return;
        }

        saveDefaultConfig();
        reloadConfig();

        file = new File(getDataFolder(),"powerboosts.yml");
        file2 = new File(getDataFolder(), "messages.yml");
        if(!(file.exists()))
            saveResource("powerboosts.yml",true);
        if(!(file2.exists()))
            saveResource("messages.yml", true);

        powerboosts = YamlConfiguration.loadConfiguration(file);
        messages = YamlConfiguration.loadConfiguration(file2);

        this.getCommand("buypower").setExecutor(new BuyPowerCmd(this));
        this.getCommand("powerboosts").setExecutor(new PowerboostsCmd(this));
    }

    public void reload() {
        reloadConfig();
        if(!(file.exists()))
            saveResource("powerboosts.yml",true);
        if(!(file2.exists()))
            saveResource("messages.yml", true);
        messages = YamlConfiguration.loadConfiguration(file2);
        powerboosts = YamlConfiguration.loadConfiguration(file);
        Strings.reloadMessages();
        getLogger().info("Successfully reloaded HoloSharp.");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null)
            return false;

        RegisteredServiceProvider<Economy> rsp = getServer()
                .getServicesManager()
                .getRegistration(Economy.class);

        if (rsp == null)
            return false;

        econ = rsp.getProvider();
        return econ != null;
    }
}
