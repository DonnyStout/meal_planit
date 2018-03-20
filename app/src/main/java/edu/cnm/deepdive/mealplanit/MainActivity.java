package edu.cnm.deepdive.mealplanit;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import layout.AccountFragment;
import layout.BrowseFragment;
import layout.CalendarFragment;
import layout.PlanFragment;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The Main Activity for the entire application that controls the functions of the fragments for
 * <code>AccountFragment</code>, <code>BrowseFragment</code>, <code>CalendarFragment</code>,
 * <code>PlanFragment</code>, and <code>FilterFragment</code>.
 */
public class MainActivity extends AppCompatActivity {

  private TextView mTextMessage;
  private MealDatabase database;
  private Retrofit retrofit;

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    /**
     * Switch for the logic of which fragment is displayed depending on which tab is selected
     * in the bottom of the navigation.
     *
     * @param item            The navigation tab.
     * @return Whether an item has been selected.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_account:
          android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
              .beginTransaction();
          AccountFragment account = new AccountFragment();
          transaction.replace(R.id.content, account).commit();
          return true;
        case R.id.navigation_calendar:
          transaction = getSupportFragmentManager().beginTransaction();
          CalendarFragment calendar = new CalendarFragment();
          transaction.replace(R.id.content, calendar).commit();
          return true;
        case R.id.navigation_browse:
          transaction = getSupportFragmentManager().beginTransaction();
          BrowseFragment browse = new BrowseFragment();
          transaction.replace(R.id.content, browse).commit();
          return true;
      }
      return false;
    }

  };


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
        .beginTransaction();
    AccountFragment account = new AccountFragment();
    account.setArguments(getIntent().getExtras());
    transaction.replace(R.id.content, account).commit();
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

  }


  /**
   * Getting an instance of the <code>MealDatabase</code> and if empty gets the [@link Context] and
   * builds the database before returning the database.
   *
   * @return Mealdatabase.
   */
  public MealDatabase getDatabase() {
    if (database == null) {
      database = Room.databaseBuilder
          (getApplicationContext(), MealDatabase.class, "mealdatabase").build();
    }
    return database;
  }


  public Retrofit getRetrofit() {
    if (retrofit == null) {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      retrofit = new Retrofit.Builder()
          .baseUrl(getString(R.string.base_url))
          .addConverterFactory(GsonConverterFactory.create(gson))
          .build();
      return retrofit;
    } else {
      return retrofit;
    }
  }

}
