package edu.cnm.deepdive.mealplanit;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import layout.AccountFragment;
import layout.FilterFragment;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The Main Activity for the entire application that controls the functions of the fragments for
 * <code>AccountFragment</code> and {@link layout.CalendarFragment}. {@link MainActivity#getDatabase()} has the method
 * to get an instance of the {@link MealDatabase} and {@link MainActivity#getRetrofit()} is a method that gets
 * a builder pattern of {@link Retrofit}.
 *
 * Author Donny Stout - version 1.0
 */
public class MainActivity extends AppCompatActivity {

  private TextView mTextMessage;
  private MealDatabase database;
  private Retrofit retrofit;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar myToolbar = (Toolbar) findViewById(R.id.main_toolbar);
    setSupportActionBar(myToolbar);
    android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
        .beginTransaction();
    AccountFragment account = new AccountFragment();
    account.setArguments(getIntent().getExtras());
    transaction.replace(R.id.content, account).commit();

  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.toolbar_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.home_button:
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        AccountFragment accountFragment = new AccountFragment();
        fragmentTransaction.replace(R.id.content, accountFragment).addToBackStack("account").commit();
        break;
      case R.id.settings_button:
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        FilterFragment filterFragment = new FilterFragment();
        fragmentTransaction.replace(R.id.content, filterFragment).addToBackStack("filter").commit();
        break;
      default:
        return super.onOptionsItemSelected(item);
    }
    invalidateOptionsMenu();
    return true;
  }

  /**
   * Getting an instance of the {@link MealDatabase} and if empty gets the {@link android.content.Context} and
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


  /**
   * Checks to see if retrofit already exists, if not it builds a pattern for {@link Retrofit} that
   * allows for the service call {@link edu.cnm.deepdive.mealplanit.servicemodel.MealList} and
   * {@link edu.cnm.deepdive.mealplanit.servicemodel.MealList.Meal} and converts it with {@link GsonConverterFactory}
   * to a {@link Gson} object. If it does exist it returns that instance of retrofit.
   *
   * @return                                      A built retrofit object.
   */
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
