package edu.cnm.deepdive.mealplanit;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import layout.AccountFragment;
import layout.BrowseFragment;
import layout.CalendarFragment;
import layout.PlanFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private MealDatabase database;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_account:
                    android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    AccountFragment account = new AccountFragment();
                    transaction.replace(R.id.content, account).commit();
                    return true;
                case R.id.navigation_plan:
                    transaction = getSupportFragmentManager().beginTransaction();
                    PlanFragment plan = new PlanFragment();
                    transaction.replace(R.id.content, plan).commit();
                    return true;
                case R.id.navigation_browse:
                    transaction = getSupportFragmentManager().beginTransaction();
                    BrowseFragment browse = new BrowseFragment();
                    transaction.replace(R.id.content, browse).commit();
                    return true;
                case R.id.navigation_calendar:
                    transaction = getSupportFragmentManager().beginTransaction();
                    CalendarFragment calendar = new CalendarFragment();
                    transaction.replace(R.id.content, calendar).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        AccountFragment account = new AccountFragment();
        transaction.replace(R.id.content, account).commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    public MealDatabase getDatabase() {
        if (database == null) {
            database = Room.databaseBuilder
                    (getApplicationContext(), MealDatabase.class, "mealdatabase").build();
        }
        return database;
    }


}
