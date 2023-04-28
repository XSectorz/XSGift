package net.xsapi.panat.xsgift.ui;

import net.xsapi.panat.xsgift.config.configuration;
import net.xsapi.panat.xsgift.event.InventoryEvent;
import net.xsapi.panat.xsgift.main.core;
import net.xsapi.panat.xsgift.user.xsuser;
import net.xsapi.panat.xsgift.utils.xsutils_color;
import net.xsapi.panat.xsgift.utils.xsutils_items;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class xsgiftUI {

    public static ArrayList<Integer> blocked_slot = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,15,17,18,24,26,27,33,35,36,42,44,45,46,47,49,50,51,52,53));

    public static void openUI(Player p) {

        Inventory inv = Bukkit.createInventory(null,54, xsutils_color.replaceColor(configuration.customConfig.getString("menu.main.title")));


        for(int slot : blocked_slot) {
            inv.setItem(slot, xsutils_items.createDecoration(p,"main","blocked_items"));
        }

        xsuser XSUser = core.xsUser.get(p.getUniqueId());

        int index = 0;
        for(ItemStack it : XSUser.getItemsList()) {
            inv.setItem(InventoryEvent.InventorySlot.get(index),it);
            index++;
        }

        inv.setItem(16, xsutils_items.createDecoration(p,"main","permission"));
        inv.setItem(25, xsutils_items.createDecoration(p,"main","expected_user"));
        inv.setItem(34, xsutils_items.createDecoration(p,"main","included_user"));
        inv.setItem(43, xsutils_items.createDecoration(p,"main","world"));
        inv.setItem(48, xsutils_items.createDecoration(p,"main","confirm"));

        p.openInventory(inv);
    }


}