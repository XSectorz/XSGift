package net.xsapi.panat.xsgift.utils;

import net.xsapi.panat.xsgift.config.messages;

public class xsutils_color {

    public static String replaceColor(String str) {
        return str.replace("&", "§");
    }

    public static String messagesConfig(String path) {
        return replaceColor(messages.customConfig.getString(path)).replace("<prefix>",replaceColor(messages.customConfig.getString("prefix")));
    }

}