package dev.ab.sagetag;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatListener implements Listener {

    HitListener hitListener;

    private SageTag main; // REPLACE "SageTag" WITH THE NAME OF YOUR MAIN CLASS

    public ChatListener(SageTag main){ // SAME AS ABOVE, REPLACE WITH NAME OF MAIN CLASS
        this.main = main;
        this.hitListener = main.getHitListener();
    }


    @EventHandler
    public void onChat(PlayerCommandPreprocessEvent e){
        if (hitListener.isTagged(e.getPlayer())){
            if(e.getMessage().contains("home")){
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "You cannot teleport to your faction home while in combat!");
            }
        }
    }
}
