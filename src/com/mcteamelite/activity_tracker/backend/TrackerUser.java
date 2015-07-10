package com.mcteamelite.activity_tracker.backend;

import com.avaje.ebean.validation.NotEmpty;
import org.bukkit.entity.Player;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Table(name = "activitytracker_users")
public class TrackerUser {

    /**
     * An id to be used as a primary key.
     */
    @Id private long id;

    /**
     * The name of the player.
     */
    @NotEmpty private String name;

    /**
     * The uuid of the player.
     */
    @NotEmpty private UUID uuid;

    /**
     * Construct a TrackerUser object model to be used
     * for relational mapping.
     *
     * @param name the name of the player.
     * @param uuid the uuid of the player.
     */
    public TrackerUser(String name, UUID uuid) {
        this.name = this.name;
        this.uuid = uuid;
    }

    /**
     * @return the id of the record.
     */
    public long getId() { return this.id; }

    /**
     * Set the name of the player.
     *
     * @param name the name of the player.
     */
    public void setName(String name) { this.name = name; }

    /**
     * @return the name of the player.
     */
    public String getName() { return this.name; }

    /**
     * @return the uuid of the player.
     */
    public UUID getUniqueId() { return this.uuid; }

}
