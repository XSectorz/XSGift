package net.xsapi.panat.xsgift.main;

import net.xsapi.panat.xsgift.commands.commandLoader;
import net.xsapi.panat.xsgift.config.configLoader;
import net.xsapi.panat.xsgift.event.InventoryEvent;
import net.xsapi.panat.xsgift.event.chatEvent;
import net.xsapi.panat.xsgift.event.joinEvent;
import net.xsapi.panat.xsgift.event.leaveEvent;
import net.xsapi.panat.xsgift.user.xsuser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class core extends JavaPlugin {



    public static core plugin;

    public static core getPlugin() { return plugin; }

    public static HashMap<UUID,xsuser> xsUser = new HashMap<UUID, xsuser>();

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("§x§f§f§a§c§2§f******************************");
        Bukkit.getLogger().info("§x§f§f§a§c§2§f   XSAPI Gift v1.0     ");
        Bukkit.getLogger().info("§r");
        Bukkit.getLogger().info("§x§f§f§a§c§2§f  Make Everyone Happy!");
        Bukkit.getLogger().info("§r");
        Bukkit.getLogger().info("§x§f§f§a§c§2§f******************************");

        plugin = this;

        new commandLoader();
        new configLoader();

        for(Player p : Bukkit.getOnlinePlayers()) {

            if(p.hasPermission("xsgift.use")) {
                xsuser XSUser = new xsuser(p);
                xsUser.put(p.getUniqueId(),XSUser);
            }

        }

        Bukkit.getPluginManager().registerEvents(new joinEvent(),core.getPlugin());
        Bukkit.getPluginManager().registerEvents(new leaveEvent(),core.getPlugin());
        Bukkit.getPluginManager().registerEvents(new InventoryEvent(),core.getPlugin());
        Bukkit.getPluginManager().registerEvents(new chatEvent(),core.getPlugin());



    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("§c[XSGIFT] Plugin Disabled 1.19.3!");
    }
}
