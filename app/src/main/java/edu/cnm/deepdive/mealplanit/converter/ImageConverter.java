package edu.cnm.deepdive.mealplanit.converter;

import android.arch.persistence.room.TypeConverter;
import android.net.Uri;

/**
 * Converter that allows for storing a {@link Uri} in the databse by converting it to a {@link String}
 */
public class ImageConverter {

  // If a String does not exist in the database it returns nothing, if there is however it returns a new parsed Uri value.
  @TypeConverter
  public static Uri toString(String value) {
    return value == null ? null : Uri.parse(value);
  }

  // Takes a Uri as a parameter and returns nothing if there is no String in the database that has been converted
//  from a Uri.
  @TypeConverter
  public static String toString(Uri value) {
    return value == null ? null : value.toString();
  }
}

