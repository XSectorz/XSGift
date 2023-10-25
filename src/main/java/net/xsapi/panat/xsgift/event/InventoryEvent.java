package net.xsapi.panat.xsgift.event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import net.xsapi.panat.xsgift.config.configuration;
import net.xsapi.panat.xsgift.handlers.XSGiftModelTypeAdapter;
import net.xsapi.panat.xsgift.handlers.xsgift_model;
import net.xsapi.panat.xsgift.main.core;
import net.xsapi.panat.xsgift.ui.xsgiftConfirmUI;
import net.xsapi.panat.xsgift.ui.xsgiftUI;
import net.xsapi.panat.xsgift.user.XSEditType;
import net.xsapi.panat.xsgift.user.xsuser;
import net.xsapi.panat.xsgift.utils.xsutils_color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class InventoryEvent implements Listener {

    public static ArrayList<Integer> InventorySlot = new ArrayList<>(Arrays.asList(10,11,12,13,14,19,20,21,22,23,28,29,30,31,32,37,38,39,40,41));

    @EventHandler
    public void onInteract(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().startsWith(xsutils_color.replaceColor(configuration.customConfig.getString("menu.main.title")))) {
            if (!e.getClickedInventory().equals(e.getWhoClicked().getInventory())) {

                xsuser XSUser = core.xsUser.get(p.getUniqueId());

                if(e.getSlot() == 16) {
                    XSUser.getItemsList().clear();
                    for (int slot : InventorySlot) {
                        Inventory inv = e.getClickedInventory();

                        if(inv.getItem(slot) != null) {
                            if(inv.getItem(slot).getType() != Material.AIR) {
                                XSUser.getItemsList().add(inv.getItem(slot));
                            }
                        }

                    }
                    if(e.getClick().isLeftClick()) {
                        XSUser.setEditType(XSEditType.PERMISSION);
                        p.sendMessage(xsutils_color.messagesConfig("permission_add"));
                        p.closeInventory();
                    } else if(e.getClick().isRightClick()) {
                        if(!XSUser.getPermission().isEmpty()) {
                            XSUser.getPermission().remove(XSUser.getPermission().size()-1);
                            xsgiftUI.openUI(p);
                        }
                    }
                    e.setCancelled(true);
                } else if(e.getSlot() == 25) {
                    XSUser.getItemsList().clear();
                    for (int slot : InventorySlot) {
                        Inventory inv = e.getClickedInventory();

                        if(inv.getItem(slot) != null) {
                            if(inv.getItem(slot).getType() != Material.AIR) {
                                XSUser.getItemsList().add(inv.getItem(slot));
                            }
                        }

                    }
                    if(e.getClick().isLeftClick()) {
                        XSUser.setEditType(XSEditType.EXPECTED_USER);
                        p.sendMessage(xsutils_color.messagesConfig("expected_add"));
                        p.closeInventory();
                    } else if(e.getClick().isRightClick()) {
                        if(!XSUser.getExpecteduser().isEmpty()) {
                            XSUser.getExpecteduser().remove(XSUser.getExpecteduser().size()-1);
                            xsgiftUI.openUI(p);
                        }
                    }
                    e.setCancelled(true);
                } else if(e.getSlot() == 34) {
                    XSUser.getItemsList().clear();
                    for (int slot : InventorySlot) {
                        Inventory inv = e.getClickedInventory();

                        if(inv.getItem(slot) != null) {
                            if(inv.getItem(slot).getType() != Material.AIR) {
                                XSUser.getItemsList().add(inv.getItem(slot));
                            }
                        }

                    }
                    if(e.getClick().isLeftClick()) {
                        XSUser.setEditType(XSEditType.INCLUDED_USER);
                        p.sendMessage(xsutils_color.messagesConfig("included_add"));
                        p.closeInventory();
                    } else if(e.getClick().isRightClick()) {
                        if(!XSUser.getIncludeuser().isEmpty()) {
                            XSUser.getIncludeuser().remove(XSUser.getIncludeuser().size()-1);
                            xsgiftUI.openUI(p);
                        }
                    }
                    e.setCancelled(true);
                } else if(e.getSlot() == 43) {
                    XSUser.getItemsList().clear();
                    for (int slot : InventorySlot) {
                        Inventory inv = e.getClickedInventory();

                        if(inv.getItem(slot) != null) {
                            if(inv.getItem(slot).getType() != Material.AIR) {
                                XSUser.getItemsList().add(inv.getItem(slot));
                            }
                        }

                    }
                    if(e.getClick().isLeftClick()) {
                        XSUser.setEditType(XSEditType.ALLOWED_WORLD);
                        p.sendMessage(xsutils_color.messagesConfig("world_add"));
                        p.closeInventory();
                    } else if(e.getClick().isRightClick()) {
                        if(!XSUser.getWorld().isEmpty()) {
                            XSUser.getWorld().remove(XSUser.getWorld().size()-1);
                            xsgiftUI.openUI(p);
                        }
                    }
                    e.setCancelled(true);
                } else if(e.getSlot() == 48) {
                    XSUser.getItemsList().clear();
                    for (int slot : InventorySlot) {
                        Inventory inv = e.getClickedInventory();

                        if(inv.getItem(slot) != null) {
                            if(inv.getItem(slot).getType() != Material.AIR) {
                                XSUser.getItemsList().add(inv.getItem(slot));
                            }
                        }

                    }
                    xsgiftConfirmUI.openConfirmUI(p);

                    e.setCancelled(true);
                } else {
                    if(xsgiftUI.blocked_slot.contains(e.getSlot())) {
                        e.setCancelled(true);
                    }
                }
            }


        } else if (e.getView().getTitle().startsWith(xsutils_color.replaceColor(configuration.customConfig.getString("menu.confirm.title")))) {
            if (!e.getClickedInventory().equals(e.getWhoClicked().getInventory())) {
                xsuser XSUser = core.xsUser.get(p.getUniqueId());

                if(e.getSlot() == 14) {

                    xsgiftUI.openUI(p);
                } else if(e.getSlot() == 12) {

                    if(XSUser.getItemsList().isEmpty()) {
                        p.sendMessage(xsutils_color.messagesConfig("item_empty"));
                        e.setCancelled(true);
                        return;
                    }

                    p.sendMessage(xsutils_color.messagesConfig("send_success"));

                    if(core.getUsingRedis()) {
                        xsgift_model giftModel = new xsgift_model(p.getName(),XSUser.getItemsList(),XSUser.getPermission(),XSUser.getWorld(),XSUser.getExpecteduser(),XSUser.getIncludeuser());

                        GsonBuilder gsonBuilder = new GsonBuilder();
                        TypeAdapter<xsgift_model> xsgiftModelTypeAdapter = new XSGiftModelTypeAdapter();
                        gsonBuilder.registerTypeAdapter(xsgift_model.class, xsgiftModelTypeAdapter);
                        Gson gson = gsonBuilder.create();

                        String json = gson.toJson(giftModel);
                        core.sendMessageToRedisAsync("XSGift/Channel/" + core.getRedisHost() ,json);
                        Bukkit.getLogger().info("Send!");
                    } else {
                        for(Player target : Bukkit.getOnlinePlayers()) {
                            if(!target.getName().equalsIgnoreCase(p.getName())) {
                                if(!XSUser.getExpecteduser().isEmpty()) {
                                    if(XSUser.getExpecteduser().contains(target.getName())) {
                                        continue;
                                    }
                                }
                                if(!XSUser.getIncludeuser().isEmpty()) {
                                    if(!XSUser.getIncludeuser().contains(target.getName())) {
                                        continue;
                                    }
                                }

                                if(!XSUser.getWorld().isEmpty()) {
                                    if(!XSUser.getWorld().contains(target.getWorld().getName())) {
                                        continue;
                                    }
                                }

                                if(!XSUser.getPermission().isEmpty()) {
                                    boolean isHavePermission = true;
                                    for(String perm : XSUser.getPermission()) {
                                        if(!target.hasPermission(perm)) {
                                            isHavePermission = false;
                                            break;
                                        }
                                    }

                                    if(!isHavePermission) {
                                        continue;
                                    }
                                }

                                for(ItemStack it : XSUser.getItemsList()) {
                                    target.getInventory().addItem(it);
                                }
                                target.sendMessage(xsutils_color.messagesConfig("get_success"));
                            }
                        }
                    }

                    p.closeInventory();
                }

            }
            e.setCancelled(true);
        }

    }

}