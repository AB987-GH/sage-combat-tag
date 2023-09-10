package dev.ab.sagetag;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class SageTag extends JavaPlugin {

    private HitListener hitListener;

    @Override
    public void onEnable() {

        hitListener = new HitListener(this);

        System.out.println("Enabling SageCombatTag - Developed for SagePvP by AB987");

        getServer().getPluginManager().registerEvents(hitListener, this);

        getServer().getPluginManager().registerEvents(new ChatListener(this), this);


    }

    @Override
    public void onDisable() {
      System.out.println("Disabling SageCombatTag!");
    }



    public HitListener getHitListener(){
        return this.hitListener;
    }
}
