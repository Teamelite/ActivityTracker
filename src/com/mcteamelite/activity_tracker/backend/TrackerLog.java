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

import com.avaje.ebean.validation.NotNull;

import javax.persistence.*;
import java.sql.Date;

/**
 * @name ActivityTracker
 * @author Kieron Wiltshire
 * @contact kieron.wiltshire@outlook.com
 */
@Entity()
@Table(name = "activitytracker_logs")
public class TrackerLog {

    /**
     * An id to be used as a primary key.
     */
    @Id
    private long id;

    /**
     * The id of the user.
     */
    @NotNull
    @ManyToOne(targetEntity = TrackerUser.class)
    private TrackerUser user;

    /**
     * The date of the track.
     */
    @NotNull
    private Date date;

    /**
     * The amount of time the user has played.
     */
    private long time;

    /**
     * Construct a TrackerLog object model to be used
     * for relational mapping.
     */
    public TrackerLog() {}

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
     * Set the User of the record.
     *
     * @param user the TrackerUser object being tracked.
     */
    public void setUser(TrackerUser user) { this.user = user; }

    /**
     * @return the TrackerUser instance.
     */
    public TrackerUser getUser() { return this.user; }

    /**
     * Set the date of the record.
     *
     * @param date the track date.
     */
    public void setDate(Date date) { this.date = date; }

    /**
     * @return the track date.
     */
    public Date getDate() { return this.date; }

    /**
     * Set the time played in milliseconds.
     *
     * @param time
     */
    public void setTime(long time) { this.time = time; }

    /**
     * @return the time played in milliseconds.
     */
    public long getTime() { return this.time; }

}
