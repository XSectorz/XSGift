package net.xsapi.panat.xsgift.event;

import net.xsapi.panat.xsgift.main.core;
import net.xsapi.panat.xsgift.ui.xsgiftUI;
import net.xsapi.panat.xsgift.user.XSEditType;
import net.xsapi.panat.xsgift.user.xsuser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class chatEvent implements Listener {


    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();

        if(p.hasPermission("xsgift.use")) {
            if(core.xsUser.containsKey(p.getUniqueId())) {
                xsuser XSUser = core.xsUser.get(p.getUniqueId());

                if(e.getMessage().equalsIgnoreCase("ยกเลิก")) {
                    XSUser.setEditType(XSEditType.NONE);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(core.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            xsgiftUI.openUI(p);
                        }
                    }, 5L);
                    e.setCancelled(true);
                }

                if(XSUser.getEditType().equals(XSEditType.PERMISSION)) {
                    XSUser.getPermission().add(e.getMessage());
                    XSUser.setEditType(XSEditType.NONE);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(core.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            xsgiftUI.openUI(p);
                        }
                    }, 5L);
                    e.setCancelled(true);
                } else if(XSUser.getEditType().equals(XSEditType.EXPECTED_USER)) {
                    XSUser.getExpecteduser().add(e.getMessage());
                    XSUser.setEditType(XSEditType.NONE);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(core.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            xsgiftUI.openUI(p);
                        }
                    }, 5L);
                    e.setCancelled(true);
                } else if(XSUser.getEditType().equals(XSEditType.INCLUDED_USER)) {
                    XSUser.getIncludeuser().add(e.getMessage());
                    XSUser.setEditType(XSEditType.NONE);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(core.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            xsgiftUI.openUI(p);
                        }
                    }, 5L);
                    e.setCancelled(true);
                } else if(XSUser.getEditType().equals(XSEditType.ALLOWED_WORLD)) {
                    XSUser.getWorld().add(e.getMessage());
                    XSUser.setEditType(XSEditType.NONE);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(core.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            xsgiftUI.openUI(p);
                        }
                    }, 5L);
                    e.setCancelled(true);
                }

            }
        }

    }

}