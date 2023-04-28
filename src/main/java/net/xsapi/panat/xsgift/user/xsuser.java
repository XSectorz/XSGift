package net.xsapi.panat.xsgift.user;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class xsuser {

    public Player p;
    public ArrayList<String> permission = new ArrayList<>();
    public ArrayList<String> expecteduser = new ArrayList<>();
    public ArrayList<String> includeuser = new ArrayList<>();
    public ArrayList<String> world = new ArrayList<>();
    public ArrayList<ItemStack> itemslist = new ArrayList<>();
    public XSEditType xsEdit;


    public xsuser(Player p) {
        this.p = p;
    }

    public void setEditType(XSEditType xsEdit) {
        this.xsEdit = xsEdit;
    }

    public XSEditType getEditType() {
        return xsEdit;
    }

    public ArrayList<String> getPermission() {
        return this.permission;
    }

    public ArrayList<String> getExpecteduser() {
        return this.expecteduser;
    }

    public ArrayList<String> getIncludeuser() {
        return this.includeuser;
    }

    public ArrayList<String> getWorld() {
        return this.world;
    }

    public ArrayList<ItemStack> getItemsList() { return this.itemslist; }

}