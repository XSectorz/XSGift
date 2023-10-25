package net.xsapi.panat.xsgift.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import net.xsapi.panat.xsgift.commands.commandLoader;
import net.xsapi.panat.xsgift.config.configLoader;
import net.xsapi.panat.xsgift.config.configuration;
import net.xsapi.panat.xsgift.event.InventoryEvent;
import net.xsapi.panat.xsgift.event.chatEvent;
import net.xsapi.panat.xsgift.event.joinEvent;
import net.xsapi.panat.xsgift.event.leaveEvent;
import net.xsapi.panat.xsgift.handlers.XSGiftModelTypeAdapter;
import net.xsapi.panat.xsgift.handlers.xsgift_model;
import net.xsapi.panat.xsgift.user.xsuser;
import net.xsapi.panat.xsgift.utils.xsutils_sendHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class core extends JavaPlugin {



    private static boolean isUsingRedis = false;

    private static String redisHost;
    private static int redisPort;
    private static String password;
    private static String redisHostServer;

    public static core plugin;

    public static core getPlugin() { return plugin; }

    public static HashMap<UUID,xsuser> xsUser = new HashMap<UUID, xsuser>();
    public static ArrayList<Thread> threads = new ArrayList<>();

    public static String getRedisHost() {
        return redisHost;
    }

    public static String getRedisHostServer() { return redisHostServer; }

    public static boolean getUsingRedis() {
        return isUsingRedis;
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§x§f§f§a§c§2§f******************************");
        Bukkit.getConsoleSender().sendMessage("§x§f§f§a§c§2§f   XSAPI Gift v1.0     ");
        Bukkit.getConsoleSender().sendMessage("§r");
        Bukkit.getConsoleSender().sendMessage("§x§f§f§a§c§2§f  Make Everyone Happy!");
        Bukkit.getConsoleSender().sendMessage("§r");
        Bukkit.getConsoleSender().sendMessage("§x§f§f§a§c§2§f******************************");

        plugin = this;

        new commandLoader();
        new configLoader();

        isUsingRedis = configuration.customConfig.getBoolean("redis.enable");

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

        if(getUsingRedis()) {
            redisConnection();
            subscribeToChannelAsync("XSGift/Channel/" + redisHostServer);
        }



    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§c[XSGIFT] Plugin Disabled 1.20.1");
        destroyAllThread();
    }

    private boolean redisConnection() {
        redisHost = configuration.customConfig.getString("redis.host");
        redisPort = configuration.customConfig.getInt("redis.port");
        password = configuration.customConfig.getString("redis.password");
        redisHostServer = configuration.customConfig.getString("redis.host-server");


        try {
            Jedis jedis = new Jedis(redisHost, redisPort);
            if(!password.isEmpty()) {
                jedis.auth(password);
            }
            jedis.close();
            Bukkit.getConsoleSender().sendMessage("§x§E§7§F§F§0§0[XSGIFT] Redis Server : §x§6§0§F§F§0§0Connected");
            return true;
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("§x§E§7§F§F§0§0[XSGIFT] Redis Server : §x§C§3§0§C§2§ANot Connected");
            e.printStackTrace();
        }
        return false;
    }

    public static void sendMessageToRedisAsync(String CHName, String message) {
        Thread thread = new Thread(() -> {
            try (Jedis jedis = new Jedis(redisHost, redisPort)) {
                if(!password.isEmpty()) {
                    jedis.auth(password);
                }
                jedis.publish(CHName, message);
            } catch (Exception e) {
                // จัดการข้อผิดพลาดที่เกิดขึ้น
                e.printStackTrace();
            }
        });
        thread.start();
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
               thread.stop();
            }
        }, 40L);
    }

    private void subscribeToChannelAsync(String channelName) {

        Thread thread = new Thread(() -> {
            try (Jedis jedis = new Jedis(redisHost, redisPort)) {
                if(!password.isEmpty()) {
                    jedis.auth(password);
                }
                JedisPubSub jedisPubSub = new JedisPubSub() {
                    @Override
                    public void onMessage(String channel, String message) {
                        //Bukkit.getConsoleSender().sendMessage("Received data from ---> " + channel);

                        //Channel ---> XSGift/Channel/SyncSurvival/01
                        if(channel.equalsIgnoreCase("XSGift/Channel/" + redisHostServer)) {
                            GsonBuilder gsonBuilder = new GsonBuilder();
                            TypeAdapter<xsgift_model> xsgiftModelTypeAdapter = new XSGiftModelTypeAdapter();
                            gsonBuilder.registerTypeAdapter(xsgift_model.class, xsgiftModelTypeAdapter);
                            Gson gson = gsonBuilder.create();

                            xsgift_model gift = gson.fromJson(message, xsgift_model.class);

                            xsutils_sendHandler.sendGift(gift);
                        }
                    }
                };
                jedis.subscribe(jedisPubSub, channelName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();

        threads.add(thread);
    }

    public static void destroyAllThread() {
        for(Thread thread : threads) {
            thread.interrupt();
            thread.stop();
        }
    }
}
