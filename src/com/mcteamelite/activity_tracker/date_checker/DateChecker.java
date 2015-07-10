/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015 Teamelite
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.mcteamelite.activity_tracker.date_checker;

import com.mcteamelite.activity_tracker.ActivityTracker;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Date;
import java.util.Calendar;

/**
 * @name ActivityTracker
 * @author Kieron Wiltshire
 * @contact kieron.wiltshire@outlook.com
 */
public class DateChecker extends BukkitRunnable {

    // Singleton
    private static DateChecker instance;

    /**
     * Get the DateChecker instance
     *
     * @return Singleton
     */
    public static DateChecker getInstance() {
        return instance != null ? instance : (instance = new DateChecker());
    }

    private long sleep;

    /**
     * Constructs a DateChecker instance
     */
    private DateChecker() {
        this.runTaskTimer(ActivityTracker.getInstance(), 0L, 25L);
    }

    @Override
    public void run() {
        if (sleep == 0) {

            // The old date
            Calendar oldDate = Calendar.getInstance();
                oldDate.setTimeInMillis(System.currentTimeMillis());

            // The current date
            Calendar newDate = Calendar.getInstance();
                newDate.setTimeInMillis(System.currentTimeMillis() + 5000);

            /**
             * If the date a second ago, doesn't match the current date
             * then, the date has changed. (It must be 12pm)
             */
            if (oldDate.get(Calendar.DAY_OF_MONTH) != newDate.get(Calendar.DAY_OF_MONTH)) {
                Bukkit.getPluginManager().callEvent(new DateChangeEvent(oldDate, newDate));
                sleep += 10;
            }
        } else {
            sleep--;
        }
    }
}
