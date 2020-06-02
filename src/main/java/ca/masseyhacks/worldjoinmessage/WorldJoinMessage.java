package ca.masseyhacks.worldjoinmessage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class WorldJoinMessage extends JavaPlugin {

    public FileConfiguration config;
    public HashMap<String, String> worldJoinMessages;

    public WorldJoinMessage() {
        worldJoinMessages = new HashMap<>();
    }

    public HashMap<String, String> deserialStorage(List<String> lines) {
        HashMap<String, String> ret = new HashMap<>();
        for (String line : lines){
            int splitLocation = line.indexOf(":");
            String worldName = line.substring(0, splitLocation);
            String message = line.substring(splitLocation+1);
            ret.put(worldName, message);
        }
        return ret;
    }


    @Override
    public void onEnable() {
        // Plugin startup logic
        config = this.getConfig();
        config.addDefault("worldMessages", new ArrayList<String>());

        config.options().copyDefaults(true);
        saveConfig();

        // Convert saved info to usable format
        getLogger().info("Loading plugin state.");
        worldJoinMessages = deserialStorage(config.getStringList("worldMessages"));

        // Register commands
        getLogger().info("Registering commands.");
        getCommand("wjmreload").setExecutor(new OnReload(this));

        getLogger().info("Registering event handlers.");
        getServer().getPluginManager().registerEvents(new WorldChangeEventListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
