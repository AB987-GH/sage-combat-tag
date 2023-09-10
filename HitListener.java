package dev.ab.sagetag;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class HitListener implements Listener {

    private final SageTag main; // REPLACE "SageTag" WITH THE NAME OF YOUR MAIN CLASS
    public HitListener(SageTag main) {this.main = main; } // SAME AS ABOVE

    private HashMap<UUID, Integer> combatTagged = new HashMap<>();



    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if ((!(e.getEntity() instanceof Player)) || (!(e.getDamager() instanceof Player))){
            return;
        }

        Player attacker = (Player) e.getDamager();
        Player victim = (Player) e.getEntity();


        if (combatTagged.containsKey(attacker.getUniqueId())){
            combatTagged.remove(attacker.getUniqueId());
            combatTagged.put(attacker.getUniqueId(), 30);
        } else {
            combatTagged.put(attacker.getUniqueId(), 30);
        }

        if (combatTagged.containsKey(victim.getUniqueId())){
            combatTagged.remove(victim.getUniqueId());
            combatTagged.put(victim.getUniqueId(), 30);
        } else {
            combatTagged.put(victim.getUniqueId(), 30);
        }

        System.out.println(attacker);
        System.out.println(victim);

        runTimer(attacker.getUniqueId());
        runTimer(victim.getUniqueId());



    }


    public void runTimer(UUID uuid){
        BukkitRunnable bukkitRunnable = new BukkitRunnable() {
            @Override
            public void run(){
                if (!combatTagged.containsKey(uuid)){
                    return;
                }
                int time = combatTagged.get(uuid);
                combatTagged.remove(uuid);
                if (time - 1 > 0){
                    combatTagged.put(uuid, time - 1);
                }
            }
        };
        bukkitRunnable.runTaskTimerAsynchronously(plugin,0,20);
    }


    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("SageTag"); // REPLACE THAT STRING WITH THE NAME OF YOUR PLUGIN (from plugin.yml)








    public boolean isTagged(Player player){ return combatTagged.containsKey(player.getUniqueId()); }
}
