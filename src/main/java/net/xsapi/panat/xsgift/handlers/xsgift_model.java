package net.xsapi.panat.xsgift.handlers;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class xsgift_model {

    public String sender;
    public ArrayList<ItemStack> itemsToSend;

    public ArrayList<String> permission;
    public ArrayList<String> worldName;
    public ArrayList<String> expectedUser;
    public ArrayList<String> includedUser;

    public xsgift_model(String sender,ArrayList<ItemStack> itemsToSend,ArrayList<String> permission,ArrayList<String> worldName,ArrayList<String> expectedUser,ArrayList<String> includedUser) {
        this.sender = sender;
        this.itemsToSend = itemsToSend;
        this.permission = permission;
        this.worldName = worldName;
        this.expectedUser = expectedUser;
        this.includedUser = includedUser;
    }

    public String getSender() {
        return sender;
    }

    public ArrayList<String> getExpectedUser() {
        return expectedUser;
    }

    public ArrayList<String> getIncludedUser() {
        return includedUser;
    }

    public ArrayList<String> getPermission() {
        return permission;
    }

    public ArrayList<String> getWorldName() {
        return worldName;
    }

    public ArrayList<ItemStack> getItemsToSend() {
        return itemsToSend;
    }
}
