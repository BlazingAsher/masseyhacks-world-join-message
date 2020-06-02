package ca.masseyhacks.worldjoinmessage;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldChangeEventListener implements Listener {
    private WorldJoinMessage plugin;
    public WorldChangeEventListener(WorldJoinMessage plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onConnect(PlayerChangedWorldEvent event){
        Player player = event.getPlayer();

        if(plugin.worldJoinMessages.containsKey(player.getWorld().getName())){
            String[] messages = plugin.worldJoinMessages.get(player.getWorld().getName()).split("\\\\n");

            for(String message : messages){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }

        }
    }

}