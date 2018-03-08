package edu.cnm.deepdive.mealplanit;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import edu.cnm.deepdive.mealplanit.dao.PersonDao;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import edu.cnm.deepdive.mealplanit.models.Person;
import layout.CreateAccountFragment;

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


    @Override
    protected void onStart() {
        super.onStart();
        database = getDatabase();
    }

    public void userLogin(String username) {
        persondao = database.personDao();
        person = persondao.findUsername(username);
    }


    public MealDatabase getDatabase() {
        return MealDatabase.getInstance(this);
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
