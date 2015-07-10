/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Teamelite
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.mcteamelite.activity_tracker;

import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;
import com.mcteamelite.activity_tracker.backend.TrackerLog;
import com.mcteamelite.activity_tracker.backend.TrackerUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.persistence.PersistenceException;
import java.sql.Date;
import java.util.*;

/**
 * @name ActivityTracker
 * @author Kieron Wiltshire
 * @contact kieron.wiltshire@outlook.com
 */
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

        // Ensure the backend is in use.
        try {
            this.getDatabase().find(TrackerUser.class).findRowCount();
        } catch (PersistenceException ex) {
            this.installDDL();
        }

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
    public TrackerUser getUserFromPlayer(Player player) {
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
                .eq("unique_id", player.getUniqueId())
                .findUnique();

        // If the User doesn't exist then create it, otherwise update the player's name.
        if (user == null) {
            user = new TrackerUser();
                user.setName(player.getName());
                user.setUniqueId(player.getUniqueId());
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
                    .eq("user_id", user.getId())
                    .eq("date", new Date(System.currentTimeMillis()))
                    .findUnique();

            long time = System.currentTimeMillis() - this.joined.get(user);

            // If the log doesn't exist, then create a new one, otherwise
            // append the time onto the old one.
            if (log == null) {
                log = new TrackerLog();
                    log.setUser(user);
                    log.setDate(new Date(System.currentTimeMillis()));
                    log.setTime(time);
            } else {
                log.setTime(log.getTime() + time);
            }

            // Save the TrackerLog model to the database.
            this.getDatabase().save(log);

            // Remove the TrackerLog model from the HashMap.
            this.joined.remove(user);
        }
    }

    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();

        // Add the classes to the list
        list.add(TrackerUser.class);
        list.add(TrackerLog.class);

        return list;
    }

}
