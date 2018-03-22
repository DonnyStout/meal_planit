package edu.cnm.deepdive.mealplanit;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import edu.cnm.deepdive.mealplanit.dao.PersonDao;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import edu.cnm.deepdive.mealplanit.model.Person;
import java.util.List;
import layout.CreateAccountFragment;

/**
 * A login activity that queries the database to see if a username that is provided exists and if not allows
 * for a new user to create an account.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {



    private Person person = new Person();
    private Spinner login;
    private PersonDao persondao;
    private MealDatabase database;
    private Button enter;
    private Button create;
    private Snackbar snack;
    private String loginString;
    private Bundle bundle;
    private CreateAccountFragment createAccountFragment;
    private List<Person> people;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        enter = findViewById(R.id.login_button);
        enter.setOnClickListener(this);
        create = findViewById(R.id.create_button);
        create.setOnClickListener(this);
        login = findViewById(R.id.login_field);
        new AutoComplete().execute();
    }


    @Override
    protected void onStart() {
        super.onStart();
        database = getDatabase();
    }


  private void userLogin(String username) {
        persondao = database.personDao();
        person = persondao.findUsername(username);
    }

    private String getText() {
      loginString = login.getSelectedItem().toString();
      return loginString;
    }


    private MealDatabase getDatabase() {
        return MealDatabase.getInstance(this);
    }


  /**
   * Logic for logging in comparing the username provided against the database and seeing if it exists.
   * If so it switches to the <code>AccountFragment</code>. Also allows the user to create a new account by switching to
   * the <code>CreateAccountFragment</code>.
   *
   * @param v             The enter and create [@link Button]
   */
    @Override
    public void onClick(final View v) {

      new Thread(new Runnable() {
        @Override
        public void run() {
          getText();
          userLogin(loginString);
          if (enter.getId() == v.getId() && person != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            bundle = new Bundle();
            Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
            editor.putString("username", person.getUsername());
            editor.apply();
            intent.putExtras(bundle);
            startActivity(intent);
          } else if (create.getId() == v.getId()) {
            FragmentTransaction transitionSupport = getSupportFragmentManager().beginTransaction();
            createAccountFragment = new CreateAccountFragment();
            transitionSupport.replace(R.id.box, createAccountFragment, "create_tag").commit();
            } else if (login.getId() == v.getId() && createAccountFragment == null) {

          } else {
              snack = Snackbar.make(findViewById(R.id.coordinator_layout), "Username not found",
                  Snackbar.LENGTH_LONG);
              snack.show();
            }
        }
      }).start();
    }

  public class AutoComplete extends AsyncTask<Object, Object, List<Person>> {

    @Override
    protected List<Person> doInBackground(Object... objects) {
      return MealDatabase.getInstance(LoginActivity.this).personDao().getAll();
    }

    @Override
    protected void onPostExecute(List<Person> people) {
      login.setAdapter(new ArrayAdapter<Person>(LoginActivity.this,
          android.R.layout.simple_spinner_item, people));
    }
  }
}
