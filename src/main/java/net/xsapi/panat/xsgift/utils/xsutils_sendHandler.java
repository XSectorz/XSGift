package net.xsapi.panat.xsgift.utils;

import net.xsapi.panat.xsgift.handlers.xsgift_model;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class xsutils_sendHandler {

    public static void sendGift(xsgift_model xsgift) {
        for(Player target : Bukkit.getOnlinePlayers()) {
            if(!target.getName().equalsIgnoreCase(xsgift.getSender())) {
                if(!xsgift.getExpectedUser().isEmpty()) {
                    if(xsgift.getExpectedUser().contains(target.getName())) {
                        continue;
                    }
                }
                if(!xsgift.getIncludedUser().isEmpty()) {
                    if(!xsgift.getIncludedUser().contains(target.getName())) {
                        continue;
                    }
                }

                if(!xsgift.getWorldName().isEmpty()) {
                    if(!xsgift.getWorldName().contains(target.getWorld().getName())) {
                        continue;
                    }
                }

                if(!xsgift.getPermission().isEmpty()) {
                    boolean isHavePermission = true;
                    for(String perm : xsgift.getPermission()) {
                        if(!target.hasPermission(perm)) {
                            isHavePermission = false;
                            break;
                        }
                    }

                    if(!isHavePermission) {
                        continue;
                    }
                }

                for(ItemStack it : xsgift.getItemsToSend()) {
                    target.getInventory().addItem(it);
                }
                target.sendMessage(xsutils_color.messagesConfig("get_success"));
            }
        }
    }

}
