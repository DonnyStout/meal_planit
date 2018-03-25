package edu.cnm.deepdive.mealplanit.db;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import edu.cnm.deepdive.mealplanit.converter.DateTypeConverter;
import edu.cnm.deepdive.mealplanit.converter.ImageConverter;
import edu.cnm.deepdive.mealplanit.dao.DietDao;
import edu.cnm.deepdive.mealplanit.dao.PersonDao;
import edu.cnm.deepdive.mealplanit.dao.PersonRestrictionDao;
import edu.cnm.deepdive.mealplanit.dao.PlanDao;
import edu.cnm.deepdive.mealplanit.dao.PlanRestrictionDao;
import edu.cnm.deepdive.mealplanit.dao.RestrictionDao;
import edu.cnm.deepdive.mealplanit.model.Diet;
import edu.cnm.deepdive.mealplanit.model.Person;
import edu.cnm.deepdive.mealplanit.model.PersonRestriction;
import edu.cnm.deepdive.mealplanit.model.Plan;
import edu.cnm.deepdive.mealplanit.model.PlanRestriction;
import edu.cnm.deepdive.mealplanit.model.Restriction;
import java.util.concurrent.Executors;


/**
 * Database class using Room as an Object Relational Mapper with the tables being the
 * <code>Diet</code>, <code>Person</code>, <code>Plan</code>, and <code>Restriction</code> models as the
 * base tables with <code>PlanRestriction</code> and <code>PersonRestriction</code> as intermediate
 * tables and the Dao interfaces associated with them. This class also populates the Diet and Restriction
 * tables the first time the app is accessed by a user.
 */
@Database(entities = {Diet.class, Person.class, Plan.class, Restriction.class,
PersonRestriction.class, PlanRestriction.class}, version = 1, exportSchema = true)
@TypeConverters({DateTypeConverter.class, ImageConverter.class})
public abstract class MealDatabase extends RoomDatabase {

  // A field assigning MealDatabase as a variable
    private static MealDatabase instance = null;

  /**
   * A method to check to see if the <code>MealDatabase</code> is empty and if so to build an instance of
   * a builder method using Room to create a callback.
   * @param context         Takes in a [@link Context]
   * @return                The Mealdatabase created with the database builder.
   */
    public static MealDatabase getInstance(Context context) {
        if (instance == null) {
            instance = (MealDatabase) Room.databaseBuilder(context.getApplicationContext(),
                                                          MealDatabase.class, "mealdatabase")
                                                           .addCallback(new Callback())
                                                           .build();
        }
        return instance;
    }


  public abstract DietDao dietDao();
    public abstract PersonDao personDao();
    public abstract PlanDao planDao();
    public abstract RestrictionDao restrictionDao();
    public abstract PlanRestrictionDao planRestrictionDao();
    public abstract PersonRestrictionDao personRestrictionDao();

  /**
   * A nested class that holds the logic for populating the Diet and Restriction database tables if it
   * is empty.
   */
    private static class Callback extends RoomDatabase.Callback {


    /**
     * Method that utilizes SQL to place specific items in the database during the [@link onCreate]
     * portion of the lifecycle if the database table are empty.
     *
     * @param data          Local variable for the MealDatabase.
     */
        @Override
        public void onCreate(@NonNull final SupportSQLiteDatabase data) {
            super.onCreate(data);
            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                @Override
                public void run() {
                  data.beginTransaction();
                  try {
                    data.execSQL(
                        "INSERT INTO restriction (allergy) VALUES ('None'), ('Dairy'), ('Egg'), ('Gluten'),"
                            +
                            " ('Grains'), ('Peanut'), ('Seafood'), ('Sesame'), ('Shellfish'), ('Soy'), "
                            +
                            "('Tree Nut'), ('Wheat')");
                    data.execSQL(
                        "INSERT INTO diet (diet_type) VALUES ('No Diet'), ('Lacto Vegetarian'), " +
                            "('Ovo Vegetarian'), ('Paleo'), ('Primal')," +
                            "('Pescetarian'), ('Vegan'), ('Vegetarian'), ('Ketogenic'), ('Whole 30')");
                    data.setTransactionSuccessful();
                  } catch  (Exception e) {
                    Log.d("exception occured", e.toString());
                  } finally {
                    data.endTransaction();
                  }

                }
            });

        }


    @Override
      public void onOpen(@NonNull SupportSQLiteDatabase db) {
        super.onOpen(db);
      }
    }

}
