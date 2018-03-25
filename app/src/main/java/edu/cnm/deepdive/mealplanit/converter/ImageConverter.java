package edu.cnm.deepdive.mealplanit.converter;

import android.arch.persistence.room.TypeConverter;
import android.net.Uri;

public class ImageConverter {

  // If a date does not exist in the database it returns nothing, if there is however it returns a new Date value.
  @TypeConverter
  public static Uri toString(String value) {
    return value == null ? null : Uri.parse(value);
  }

  // Takes a Date as a parameter and returns nothing if there is no Long in the database that has been converted
//  from a Date.
  @TypeConverter
  public static String toString(Uri value) {
    return value == null ? null : value.toString();
  }
}

