package net.xsapi.panat.xsgift.event;

import net.xsapi.panat.xsgift.main.core;
import net.xsapi.panat.xsgift.user.xsuser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if(p.hasPermission("xsgift.use")) {
            xsuser XSUser = new xsuser(p);
            core.xsUser.put(p.getUniqueId(),XSUser);
        }

    }

}