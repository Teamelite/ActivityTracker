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
package com.mcteamelite.activity_tracker.date_checker;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Calendar;

/**
 * @name ActivityTracker
 * @author Kieron Wiltshire
 * @contact kieron.wiltshire@outlook.com
 */
public class DateChangeEvent extends Event {

    // Bukkit's event handler list
    private static final HandlerList handlers = new HandlerList();

    // The old and the current date.
    private Calendar old, current;

    /**
     * Constructs a new instance of a DateChangeEvent.
     *
     * @param old the old date.
     * @param current the current date.
     */
    public DateChangeEvent(Calendar old, Calendar current) {
        this.old = old;
        this.current = current;
    }

    /**
     * @return the old date.
     */
    public Calendar getOldDate() {
        return this.old;
    }

    /**
     * @return the current date.
     */
    public Calendar getCurrentDate() {
        return this.current;
    }

    /**
     * @return Bukkit's event handler list.
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
