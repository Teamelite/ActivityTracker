package com.mcteamelite.activity_tracker;

import com.mcteamelite.activity_tracker.backend.TrackerUser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Date;
import java.util.HashMap;
import java.util.UUID;

public class ActivityTracker extends JavaPlugin implements Listener {

    private HashMap<UUID, Long> joined;

    /**
     * This method is called once the plugin has been
     * enabled by Bukkit.
     */
    public void onEnable() {
        this.joined = new HashMap<UUID, Long>();
    }

    /**
     * This method is called once the plugin has been
     * disabled by Bukkit.
     */
    public void onDisable() {

    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        
    }

    @EventHandler
    private void onQuit(PlayerQuitEvent event) {

    }

}
