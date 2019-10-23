package net.blazemc.wand.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {

    public ItemStack createItem(Material material, String displayname, int amount, List<String> lore, Enchantment enchantment, int level){
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemStack.addEnchantment(enchantment, level);
        itemMeta.setDisplayName(displayname);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack createItem(Material material, String displayname, int amount, List<String> lore){
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayname);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack createItem(Material material, String displayname, int amount, Enchantment enchantment, int level){
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemStack.addEnchantment(enchantment, level);
        itemMeta.setDisplayName(displayname);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack createItem(Material material, String displayname, int amount){
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayname);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
