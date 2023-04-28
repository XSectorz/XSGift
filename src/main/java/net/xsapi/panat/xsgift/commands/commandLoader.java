package net.xsapi.panat.xsgift.commands;

import net.xsapi.panat.xsgift.main.core;

public class commandLoader {

    public commandLoader() {
        core.getPlugin().getCommand("xsgift").setExecutor(new command());
    }

}