package Discord;

import Discord.commands.discordCMD;
import org.bukkit.plugin.java.JavaPlugin;

public final class discord extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("discord").setExecutor(new discordCMD(this));
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
