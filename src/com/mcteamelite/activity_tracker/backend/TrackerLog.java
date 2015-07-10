package com.mcteamelite.activity_tracker.backend;

import com.avaje.ebean.validation.NotEmpty;

import javax.persistence.*;
import java.sql.Date;

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
    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL)
    private TrackerUser user;

    /**
     * The date of the track.
     */
    @NotEmpty
    private Date date;

    /**
     * The amount of time the user has played.
     */
    private long time;

    /**
     * Construct a TrackerLog object model to be used
     * for relational mapping.
     *
     * @param user the user to track.
     * @param date the date to log.
     * @param time the time played in milliseconds.
     */
    public TrackerLog(TrackerUser user, Date date, long time) {
        this.user = user;
        this.date = date;
        this.time = time;
    }

    /**
     * @return the TrackerUser instance.
     */
    public TrackerUser getUser() { return this.user; }

    /**
     * @return the track date.
     */
    public Date getDate() { return this.date; }

    /**
     * @return the time played in milliseconds.
     */
    public long getTime() { return this.time; }

}
