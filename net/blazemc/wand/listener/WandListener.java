package net.blazemc.wand.listener;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.config.Configuration;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotBlock;
import net.blazemc.wand.main.WandSystem;
import net.blazemc.wand.utils.WandGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class WandListener implements Listener {


    @EventHandler
    public void onClick(final InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();


        if(!(event.getWhoClicked() instanceof Player)) return;
        if(event.getClickedInventory() == null || event.getCurrentItem() == null || event.getClickedInventory().getTitle() == null) return;
        if (!(event.getClickedInventory().getTitle().equals("§3Wand §8»")) && event.getCurrentItem().getType() == Material.AIR) return;

            final Material material = event.getCurrentItem().getType();
            String perm = WandGUI.perm.get(material);
            event.setCancelled(true);
            if(player.hasPermission(perm)) {
                    PlotAPI plotAPI = new PlotAPI();
                    Plot plot = plotAPI.getPlot(player);

                    PlotBlock[] plotBlocks = Configuration.BLOCKLIST.parseString(material.name());
                    if(plotAPI.isInPlot(player)) {
                        if (plot.getConnectedPlots().size() > 1) {
                            for (Plot plots : plot.getConnectedPlots()) {
                                if (plots.getOwners().contains(player.getUniqueId()) || player.hasPermission("wand.admin")) {
                                    plotAPI.getPlotManager(player.getWorld()).setComponent(plots.getArea(), plots.getId(), "wall", plotBlocks);		                        Bukkit.getScheduler().scheduleAsyncDelayedTask(WandSystem.getInstance(), new Runnable() {
                                        @Override
                                        public void run() {
                                            plot.setSign();
                                        }
                                    }, 20);
                                }else {
                                    player.sendMessage(WandSystem.getInstance().getPrefix() + "§cDu bist nicht der Inhaber des Grundstückes!");
                                }
                            }
                            player.sendMessage(WandSystem.getInstance().getPrefix() + "Du hast deine §eWand §7zu §e" +  material.toString() + "§7 geändert!");
                        } else {
                            if (plot.getOwners().contains(player.getUniqueId()) || player.hasPermission("wand.admin")) {
                                plotAPI.getPlotManager(player.getWorld()).setComponent(plot.getArea(), plot.getId(), "wall", plotBlocks);
                                player.sendMessage(WandSystem.getInstance().getPrefix() + "Du hast deine §eWand §7zu §e" +  material.toString() + "§7 geändert!");
                                Bukkit.getScheduler().scheduleAsyncDelayedTask(WandSystem.getInstance(), () -> plot.setSign(), 20);
                            }else {
                                player.sendMessage(WandSystem.getInstance().getPrefix() + "§cDu bist nicht der Inhaber des Grundstückes!");
                            }
                        }
                    }else {
                        player.sendMessage(WandSystem.getInstance().getPrefix() + "§cDu bist auf keinem Plot!");
                    }
            }else{
                player.sendMessage(WandSystem.getInstance().getNoperm());
            }



    }

}
