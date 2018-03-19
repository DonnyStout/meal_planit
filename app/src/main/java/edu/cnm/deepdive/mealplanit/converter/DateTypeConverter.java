package edu.cnm.deepdive.mealplanit.converter;

import android.arch.persistence.room.TypeConverter;
import java.util.Date;

/**
 * Converts a [@link Date] to a [@link Long] in the database, this is necessary because Room has issues with
 * Date objects.
 */
public class DateTypeConverter {

// If a date does not exist in the database it returns nothing, if there is however it returns a new Date value.
    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

// Takes a Date as a parameter and returns nothing if there is no Long in the database that has been converted
//  from a Date.
    @TypeConverter
    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }
}
