package edu.cnm.deepdive.mealplanit.converter;

import android.arch.persistence.room.TypeConverter;
import org.joda.time.LocalDate;

/**
 * Converts a {@link LocalDate} to a {@link String} in the database, this is necessary because there
 * are issues with storing dates in the database.
 */
public class DateTypeConverter {

  // If a LocalDate does not exist in the database it returns nothing, if there is however it returns a new LocalDate.
  @TypeConverter
  public static LocalDate toLocalDate(String value) {
    return value == null ? null : LocalDate.parse(value);
  }

//   Takes a LocalDate as a parameter and returns null if there is no String in the database that has been converted
//  from a LocalDate.
  @TypeConverter
  public static String toString(LocalDate value) {
    return value == null ? null : value.toString();
  }
}
