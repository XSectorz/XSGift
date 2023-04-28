package net.xsapi.panat.xsgift.utils;

import net.xsapi.panat.xsgift.config.configuration;
import net.xsapi.panat.xsgift.main.core;
import net.xsapi.panat.xsgift.user.xsuser;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class xsutils_items {
    private static ItemFlag hideEnchants;

    public static ItemStack createItem(Material mat, int amount, int customModel, String name, ArrayList<String> lore, boolean isGlow,
                                       Map<Enchantment,Integer> enchants) {
        ItemStack itm;

        itm = new ItemStack(mat,amount);

        ItemMeta itmmeta = itm.getItemMeta();
        itmmeta.setCustomModelData(customModel);

        if (!name.isEmpty())
            itmmeta.setDisplayName(name.replace('&', '\u00A7'));
        if (!lore.isEmpty()) {
            ArrayList<String> lore_temp = new ArrayList<String>();

            for (String lores : lore) {
                lores = lores.replace('&', '\u00A7');
                lore_temp.add(lores);
            }

            itmmeta.setLore(lore_temp);
        }
        if (isGlow) {
            itmmeta.addEnchant(org.bukkit.enchantments.Enchantment.DURABILITY, 1, true);
            itmmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        if(!enchants.isEmpty()) {
            for(Map.Entry<Enchantment,Integer> enchant : enchants.entrySet()) {
                itmmeta.addEnchant(enchant.getKey(),enchant.getValue(),true);
            }
        }

        itmmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itm.setItemMeta(itmmeta);
        return itm;
    }

    public static ItemStack createDecoration(Player p ,String invType, String type) {

        ArrayList<String> lores = new ArrayList<>();

        xsuser user = core.xsUser.get(p.getUniqueId());

        for(String lore : configuration.customConfig.getStringList("menu."+ invType +"."+ type +".lore")) {

            if(lore.equalsIgnoreCase("{xsgift:permission}")) {
                if(user.getPermission().isEmpty()) {
                    lores.add(xsutils_color.messagesConfig("empty"));
                } else {
                    for(String perm : user.getPermission()) {
                        lores.add("&x&D&D&D&F&7&9- &x&D&D&D&F&7&9&o" + perm);
                    }
                }
            } else if(lore.equalsIgnoreCase("{xsgift:expected_player}")) {

                if(user.getExpecteduser().isEmpty()) {
                    lores.add(xsutils_color.messagesConfig("empty"));
                } else {
                    for(String expected : user.getExpecteduser()) {
                        lores.add("&x&D&D&D&F&7&9- &x&D&D&D&F&7&9&o" + expected);
                    }
                }

            } else if(lore.equalsIgnoreCase("{xsgift:include_player}")) {

                if(user.getIncludeuser().isEmpty()) {
                    lores.add(xsutils_color.messagesConfig("empty"));
                } else {
                    for(String included : user.getIncludeuser()) {
                        lores.add("&x&D&D&D&F&7&9- &x&D&D&D&F&7&9&o" + included);
                    }
                }
            } else if(lore.equalsIgnoreCase("{xsgift:world}")) {
                if(user.getWorld().isEmpty()) {
                    lores.add(xsutils_color.messagesConfig("empty"));
                } else {
                    for(String world : user.getWorld()) {
                        lores.add("&x&D&D&D&F&7&9- &x&D&D&D&F&7&9&o" + world);
                    }
                }

            } else if(lore.equalsIgnoreCase("{xsgift:item}")) {
                if(user.getItemsList().isEmpty()) {
                    lores.add(xsutils_color.messagesConfig("empty"));
                } else {
                    for(ItemStack it : user.getItemsList()) {

                        String typeStr = it.getType().toString();


                        if(it.hasItemMeta()) {
                            if(it.getItemMeta().hasDisplayName()) {
                                typeStr = it.getItemMeta().getDisplayName();
                            }
                        }

                        lores.add("&x&D&D&D&F&7&9- &x&5&0&D&E&6&7x" + it.getAmount() + " &f" + typeStr);
                    }
                }

            } else if(lore.contains("{amount}")) {
                lores.add(lore.replace("{amount}",user.getItemsList().size()+""));
            } else {
                lores.add(lore);
            }

        }
        return createItem(
                Material.valueOf(configuration.customConfig.getString("menu." + invType + "." + type +".material")),
                1,
                configuration.customConfig.getInt("menu." + invType + "."+ type +".customModeldata"),
                configuration.customConfig.getString("menu." + invType +"."+ type +".name"),
                lores,
                false,
                new HashMap<>());
    }
}