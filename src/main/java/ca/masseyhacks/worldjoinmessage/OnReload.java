package ca.masseyhacks.worldjoinmessage;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class OnReload implements CommandExecutor {
    private WorldJoinMessage plugin;
    public OnReload(WorldJoinMessage plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("masseyhacks.worldjoinmessage.reload")){
            plugin.reloadConfig();
            plugin.config = plugin.getConfig();
            plugin.worldJoinMessages = plugin.deserialStorage(plugin.config.getStringList("worldMessages"));

            sender.sendMessage("Reloaded World Join Messages.");
        }
        return true;
    }
}
