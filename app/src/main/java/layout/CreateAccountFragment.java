package layout;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import edu.cnm.deepdive.mealplanit.Dao.DietDao;
import edu.cnm.deepdive.mealplanit.Dao.PersonDao;
import edu.cnm.deepdive.mealplanit.Dao.RestrictionDao;
import edu.cnm.deepdive.mealplanit.R;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import edu.cnm.deepdive.mealplanit.models.Diet;
import edu.cnm.deepdive.mealplanit.models.Person;
import edu.cnm.deepdive.mealplanit.models.Restriction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFragment extends Fragment implements View.OnClickListener{


    private MealDatabase database;
    private Person person;
    private PersonDao persondao;
    private Diet diet;
    private DietDao dietDao;
    private Restriction restriction;
    private RestrictionDao restrictionDao;
    private EditText firstNameField;
    private EditText lastNameField;
    private EditText usernameField;
    private Spinner caloriesbox;
    private Spinner allergiesBox;
    private Spinner dietBox;


    public CreateAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        caloriesbox = view.findViewById(R.id.calories_spinner);
        return view;
    }


    public void setAccount() {

    }

    @Override
    public void onClick(View v) {
        person.setFirstName(firstNameField.getText().toString());
        person.setLastName(lastNameField.getText().toString());
        person.setUsername(usernameField.getText().toString());
        person.setCaloriesPerDay((Integer)caloriesbox.getSelectedItem());
        diet.setDietId(dietBox.getId());
        restriction.setAllergies(allergiesBox.toString());
    }


    public void adapter() {
        SpinnerAdapter caloriesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,
                Arrays.asList(1000, 1500, 2000, 2500, 3000, 3500));
        caloriesbox.setAdapter(caloriesAdapter);
        SpinnerAdapter allergiesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,
                Arrays.asList("Dairy", "Egg", "Gluten", "Grains", "Peanut", "Seafood",
                        "Sesame", "Shellfish", "Soy", "Tree", "Nut", "Wheat"));
        SpinnerAdapter dietAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,
                Arrays.asList("No Diet", "Lacto Vegetarian", "Ovo Vegetarian",
                        "Paleo", "Primal", "Pescetarian", "Vegan", "Vegetarian",
                        "Ketogenic", "Whole 30"));


    }




}
