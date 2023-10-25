package net.xsapi.panat.xsgift.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class xsutils_itemstack {

    public static String itemStackToBase64(ItemStack itemStack) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeObject(itemStack);

            byte[] byteItemStack = outputStream.toByteArray();
            String base64 = Base64.getEncoder().encodeToString(byteItemStack);

            return base64;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ItemStack itemStackFromBase64(String base64) {
        try {
            byte[] byteItemStack = Base64.getDecoder().decode(base64);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(byteItemStack);
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            // อ่าน NBT และสร้าง ItemStack จาก inputStream
            ItemStack itemStack = (ItemStack) dataInput.readObject();

            return itemStack;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
