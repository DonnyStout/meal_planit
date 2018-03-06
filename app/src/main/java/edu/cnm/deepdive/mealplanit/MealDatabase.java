package edu.cnm.deepdive.mealplanit;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import edu.cnm.deepdive.mealplanit.Dao.*;
import edu.cnm.deepdive.mealplanit.converters.DateTypeConverter;
import edu.cnm.deepdive.mealplanit.models.*;

@TypeConverters({DateTypeConverter.class})
@Database(entities = {Diet.class, Person.class, Plan.class, Restriction.class,
PersonRestriction.class, PlanRestriction.class}, version = 1)
public abstract class MealDatabase extends RoomDatabase {

    public abstract DietDao dietDao();
    public abstract PersonDao personDao();
    public abstract PlanDao planDao();
    public abstract RestrictionDao restrictionDao();
    public abstract PlanRestrictionDao planRestrictionDao();
    public abstract PersonRestrictionDao personRestrictionDao();
}
