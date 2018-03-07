package edu.cnm.deepdive.mealplanit;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.Snackbar;
import android.support.transition.FragmentTransitionSupport;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import edu.cnm.deepdive.mealplanit.Dao.PersonDao;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import edu.cnm.deepdive.mealplanit.models.Person;
import layout.CreateAccountFragment;

import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private Person person = new Person();
    private EditText login;
    private PersonDao persondao;
    private MealDatabase database;
    private Button enter;
    private Button create;
    private Snackbar snack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        enter = findViewById(R.id.login_button);
        enter.setOnClickListener(this);
        create = findViewById(R.id.create_button);
        create.setOnClickListener(this);
        login = findViewById(R.id.login_field);
    }


    public void userLogin(String username) {
        persondao = database.personDao();
        person = persondao.findUsername(username);
    }


    public MealDatabase getDatabase() {
        if (database == null) {
            database = Room.databaseBuilder
                    (getApplicationContext(), MealDatabase.class, "mealdatabase").addCallback(new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull final SupportSQLiteDatabase data) {
                    super.onCreate(data);
                    Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                        @Override
                        public void run() {
                            data.execSQL("INSERT INTO restriction (allergies) VALUES ('Dairy'), ('Egg'), ('Gluten'), ('Grains'), ('Peanut')" +
                                            "('Seafood'), ('Sesame'), ('Shellfish'), ('Soy'), ('Tree Nut'), ('Wheat')");
                                    data.execSQL("INSERT INTO diet (diet_type) VALUES ('No Diet'), ('Lacto Vegetarian'), " +
                                            "('Ovo Vegetarian'), ('Paleo'), ('Primal')" +
                                            "('Pescetarian'), ('Vegan'), ('Vegetarian'), ('Ketogenic'), ('Whole 30'");
                        }
                    });
                }
            }).build();
        }


        return database;
    }



    @Override
    public void onClick(View v) {
        if (enter.getId() == v.getId() && person.equals(login.getText().toString())) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (create.getId() == v.getId()) {
            FragmentTransaction transitionSupport = getSupportFragmentManager().beginTransaction();
            CreateAccountFragment createAccountFragment = new CreateAccountFragment();
            transitionSupport.replace(R.id.box, createAccountFragment).commit();
        } else {
            snack = Snackbar.make(findViewById(R.id.coordinator_layout), "Username not found", Snackbar.LENGTH_LONG);
            snack.show();
        }
    }

}
