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
package com.mcteamelite.activity_tracker.backend;

import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;
import org.bukkit.entity.Player;

import javax.persistence.*;
import java.util.UUID;

/**
 * @name ActivityTracker
 * @author Kieron Wiltshire
 * @contact kieron.wiltshire@outlook.com
 */
@Entity()
@Table(name = "activitytracker_users")
public class TrackerUser {

    /**
     * An id to be used as a primary key.
     */
    @Id
    private long id;

    /**
     * The name of the player.
     */
    @NotNull
    private String name;

    /**
     * The uuid of the player.
     */
    @NotNull
    @Column(unique = true)
    private UUID uniqueId;

    /**
     * Construct a TrackerUser object model to be used
     * for relational mapping.
     */
    public TrackerUser() {}

    /**
     * Set the id of the record.
     *
     * @param id
     */
    public void setId(long id) { this.id = id; }

    /**
     * @return the id of the record.
     */
    public long getId() { return this.id; }

    /**
     * Set the name of the record.
     *
     * @param name the name of the player.
     */
    public void setName(String name) { this.name = name; }

    /**
     * @return the name of the player.
     */
    public String getName() { return this.name; }

    /**
     * Set the uuid of the record.
     *
     * @param uuid the uuid of the player.
     */
    public void setUniqueId(UUID uuid) { this.uniqueId = uuid; }

    /**
     * @return the uuid of the player.
     */
    public UUID getUniqueId() { return this.uniqueId; }

}
