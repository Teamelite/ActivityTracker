package com.mcteamelite.activity_tracker;

import com.mcteamelite.activity_tracker.backend.TrackerLog;
import com.mcteamelite.activity_tracker.backend.TrackerUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ActivityTracker extends JavaPlugin implements Listener {

    private HashMap<TrackerUser, Long> joined;

    /**
     * This method is called once the plugin has been
     * enabled by Bukkit.
     */
    public void onEnable() {
        this.joined = new HashMap<TrackerUser, Long>();

        // Register the Listener
        Bukkit.getPluginManager().registerEvents(this, this);

        // Iterate through every online player, and register them
        // to the tracker.
        for (Player player : Bukkit.getOnlinePlayers()) {
            this.register(player);
        }
    }

    /**
     * This method is called once the plugin has been
     * disabled by Bukkit.
     */
    public void onDisable() {
        // Unregister every online player from the tracker.
        for (Player player : Bukkit.getOnlinePlayers()) {
            this.unregister(player);
        }
    }


    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        this.register(event.getPlayer());
    }

    @EventHandler
    private void onQuit(PlayerQuitEvent event) {
        this.unregister(event.getPlayer());
    }

    /**
     * @param player the player to use as a reference.
     * @return the TrackerUser object associated with the Player.
     */
    private TrackerUser getUserFromPlayer(Player player) {
        for (TrackerUser user : this.joined.keySet()) {
            if (user.getUniqueId().equals(player.getUniqueId())) return user;
        }
        return null;
    }

    /**
     * Register a player into the tracker.
     *
     * @param player
     */
    private void register(Player player) {
        // Attempt to find the User.
        TrackerUser user = this.getDatabase().find(TrackerUser.class).where()
                .eq("uuid", player.getUniqueId())
                .findUnique();

        // If the User doesn't exist then create it, otherwise update the player's name.
        if (user == null) {
            user = new TrackerUser(player.getName(), player.getUniqueId());
        } else {
            user.setName(player.getName());
        }

        // Save the TrackerUser model to the database.
        this.getDatabase().save(user);

        // Store the User model within the HashMap to use within the TrackerLog.
        this.joined.put(user, System.currentTimeMillis());
    }

    /**
     * Unregister a player from the tracker.
     *
     * @param player
     */
    private void unregister(Player player) {
        // Attempt to get the User.
        TrackerUser user = this.getUserFromPlayer(player);

        // If the User exists then get their track log.
        if (user != null) {
            TrackerLog log = this.getDatabase().find(TrackerLog.class).where()
                    .eq("id", user.getId())
                    .eq("date", new Date(System.currentTimeMillis()))
                    .findUnique();

            long time = System.currentTimeMillis() - this.joined.get(user);

            // If the log doesn't exist, then create a new one, otherwise
            // append the time onto the old one.
            if (log == null) {
                log = new TrackerLog(user, new Date(System.currentTimeMillis()), time);
            } else {
                log.setTime(log.getTime() + time);
            }

            // Save the TrackerLog model to the database.
            this.getDatabase().save(log);

            // Remove the TrackerLog model from the HashMap.
            this.joined.remove(user);
        }
    }

}
