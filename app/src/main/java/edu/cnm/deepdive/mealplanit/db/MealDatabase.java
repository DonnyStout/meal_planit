package edu.cnm.deepdive.mealplanit.db;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import edu.cnm.deepdive.mealplanit.converters.DateTypeConverter;
import edu.cnm.deepdive.mealplanit.dao.DietDao;
import edu.cnm.deepdive.mealplanit.dao.PersonDao;
import edu.cnm.deepdive.mealplanit.dao.PersonRestrictionDao;
import edu.cnm.deepdive.mealplanit.dao.PlanDao;
import edu.cnm.deepdive.mealplanit.dao.PlanRestrictionDao;
import edu.cnm.deepdive.mealplanit.dao.RestrictionDao;
import edu.cnm.deepdive.mealplanit.models.Diet;
import edu.cnm.deepdive.mealplanit.models.Person;
import edu.cnm.deepdive.mealplanit.models.PersonRestriction;
import edu.cnm.deepdive.mealplanit.models.Plan;
import edu.cnm.deepdive.mealplanit.models.PlanRestriction;
import edu.cnm.deepdive.mealplanit.models.Restriction;
import java.util.concurrent.Executors;


@Database(entities = {Diet.class, Person.class, Plan.class, Restriction.class,
PersonRestriction.class, PlanRestriction.class}, version = 1, exportSchema = true)
@TypeConverters({DateTypeConverter.class})
public abstract class MealDatabase extends RoomDatabase {

    private static MealDatabase instance = null;

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


    private static class Callback extends RoomDatabase.Callback {

//        private Context context;
//
//        private Callback(Context context) {
//            this.context = context;
//        }

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
