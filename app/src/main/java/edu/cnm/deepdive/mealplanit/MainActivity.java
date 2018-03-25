package edu.cnm.deepdive.mealplanit;

import android.app.FragmentTransaction;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import layout.AccountFragment;
import layout.BrowseFragment;
import layout.CalendarFragment;
import layout.FilterFragment;
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
        fragmentTransaction.replace(R.id.content, accountFragment).commit();
        break;
      case R.id.settings_button:
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        FilterFragment filterFragment = new FilterFragment();
        fragmentTransaction.replace(R.id.content, filterFragment).commit();
        break;
      default:
        return super.onOptionsItemSelected(item);
    }
    invalidateOptionsMenu();
    return true;
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
