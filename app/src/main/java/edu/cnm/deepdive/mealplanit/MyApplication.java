package edu.cnm.deepdive.mealplanit;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;

public class MyApplication extends Application {
        public void onCreate() {
            super.onCreate();
          MealDatabase.getInstance(this);
            Stetho.initializeWithDefaults(this);
        }
    }
