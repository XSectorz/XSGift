package net.xsapi.panat.xsgift.event;

import net.xsapi.panat.xsgift.main.core;
import net.xsapi.panat.xsgift.user.xsuser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class leaveEvent implements Listener {


    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if(p.hasPermission("xsgift.use")) {

            if(core.xsUser.containsKey(p.getUniqueId())) {
                core.xsUser.remove(p.getUniqueId());
            }
        }
    }
}