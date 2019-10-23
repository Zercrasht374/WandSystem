package net.blazemc.wand.utils;

import net.blazemc.wand.main.WandSystem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WandGUI {


    public static List<ItemStack> randitems = new ArrayList<>();
    public static HashMap<Material, String> perm = new HashMap<>();

    public static void init() {
        final ConfigurationSection configSection = WandSystem.getInstance().yamlConfiguration.getConfigurationSection("Inventory.Items");
        configSection.getKeys(false).forEach(keys -> {
            final ItemStack itemStack = new ItemStack(Material.getMaterial(configSection.getString(keys + ".Material")),
                    1);
            final ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(configSection.getString(keys + ".DisplayName").replaceAll("&", "§"));
            itemStack.setItemMeta(itemMeta);
            randitems.add(itemStack);
            perm.put(Material.getMaterial(configSection.getString(keys + ".Material")), configSection.getString(keys + ".Perm"));
        });
    }

    public static void openGui(Player player){
        Inventory inventory = Bukkit.createInventory(null, 27, "§3Wand §8»");
        for (ItemStack randitem : randitems) {
            inventory.addItem(randitem);
        }
        player.openInventory(inventory);
    }

}
