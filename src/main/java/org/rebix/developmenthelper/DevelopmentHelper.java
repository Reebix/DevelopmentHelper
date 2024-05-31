package org.rebix.developmenthelper;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.rebix.developmenthelper.server.CommandServer;

import java.util.ArrayList;
import java.util.List;

public class DevelopmentHelper extends JavaPlugin {
    public static final List<String> commands = new ArrayList<>();
    static CommandServer commandServer;

    @Override
    public void onEnable() {
        commandServer = new CommandServer();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            if (!commands.isEmpty()) {
                String command = commands.get(0);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                commands.remove(0);
            }
        }, 0, 10);
    }

    @Override
    public void onDisable() {
        commandServer.stop();
    }
}
