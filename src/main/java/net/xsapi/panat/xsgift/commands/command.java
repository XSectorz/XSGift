package net.xsapi.panat.xsgift.commands;

import net.xsapi.panat.xsgift.main.core;
import net.xsapi.panat.xsgift.ui.xsgiftUI;
import net.xsapi.panat.xsgift.user.XSEditType;
import net.xsapi.panat.xsgift.user.xsuser;
import net.xsapi.panat.xsgift.utils.xsutils_color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String arg, String[] args) {

        if(commandSender instanceof Player) {
            Player sender = (Player) commandSender;

            if (command.getName().equalsIgnoreCase("xsgift")) {

                if(!sender.hasPermission("xsgift.use")) {
                    sender.sendMessage(xsutils_color.messagesConfig("no_permission"));
                    return false;
                }


                if(!core.xsUser.containsKey(sender.getUniqueId())) {
                    xsuser XSUser = new xsuser(sender);
                    core.xsUser.put(sender.getUniqueId(),XSUser);
                }

                xsuser XSUser = core.xsUser.get(sender.getUniqueId());
                XSUser.getItemsList().clear();
                XSUser.setEditType(XSEditType.NONE);

                xsgiftUI.openUI(sender);
                return true;
            }
        }

        return true;
    }
}