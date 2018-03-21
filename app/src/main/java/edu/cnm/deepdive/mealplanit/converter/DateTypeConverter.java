package edu.cnm.deepdive.mealplanit.converter;

import android.arch.persistence.room.TypeConverter;
import java.util.Date;
import org.joda.time.LocalDate;

/**
 * Converts a [@link Date] to a [@link Long] in the database, this is necessary because Room has issues with
 * Date objects.
 */
public class DateTypeConverter {

// If a date does not exist in the database it returns nothing, if there is however it returns a new Date value.
    @TypeConverter
    public static LocalDate toLocalDate(String value) {
        return value == null ? null : LocalDate.parse(value);
    }

// Takes a Date as a parameter and returns nothing if there is no Long in the database that has been converted
//  from a Date.
    @TypeConverter
    public static String toString(LocalDate value) {
        return value == null ? null : value.toString();
    }
}
