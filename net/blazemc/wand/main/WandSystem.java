package net.blazemc.wand.main;

import net.blazemc.wand.commands.WandCMD;
import net.blazemc.wand.listener.WandListener;
import net.blazemc.wand.utils.WandGUI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class WandSystem extends JavaPlugin {

    public String prefix = "§8•●• §3Wand §8» §7";

    public String getPrefix() {
        return prefix;
    }

    public String getNoperm() {
        return noperm;
    }

    public String noperm = "§cAcces Denied!";

    public static WandSystem getInstance() {
        return instance;
    }

    public File file = new File(getDataFolder() + "//wand.yml");
    public YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

    public static WandSystem instance;

    public void onEnable(){
        instance = this;
        init(Bukkit.getPluginManager());
        WandGUI.init();
    }

    public void init(PluginManager pluginManager){
        saveResource("wand.yml", false);
        pluginManager.registerEvents(new WandListener(), this);
        getCommand("wand").setExecutor(new WandCMD());
    }

}
