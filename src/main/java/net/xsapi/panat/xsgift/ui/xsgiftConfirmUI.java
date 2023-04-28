package net.xsapi.panat.xsgift.ui;

import net.xsapi.panat.xsgift.config.configuration;
import net.xsapi.panat.xsgift.utils.xsutils_color;
import net.xsapi.panat.xsgift.utils.xsutils_items;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;

public class xsgiftConfirmUI {

    public static ArrayList<Integer> blocked_slot = new ArrayList<>(Arrays.asList(0,1,2,3,5,6,7,8,9,10,11,13,15,16,17,18,19,20,21,22,23,24,25,26));


    public static void openConfirmUI(Player p) {

        Inventory inv = Bukkit.createInventory(null,27, xsutils_color.replaceColor(configuration.customConfig.getString("menu.confirm.title")));

        for(int slot : blocked_slot) {
            inv.setItem(slot, xsutils_items.createDecoration(p,"confirm","blocked_items"));
        }

        inv.setItem(12, xsutils_items.createDecoration(p,"confirm","confirm"));
        inv.setItem(4, xsutils_items.createDecoration(p,"confirm","itemlist"));
        inv.setItem(14, xsutils_items.createDecoration(p,"confirm","cancel"));

        p.openInventory(inv);
    }

}