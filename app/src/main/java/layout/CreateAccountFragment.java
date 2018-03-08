package layout;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;
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
import edu.cnm.deepdive.mealplanit.MainActivity;
import edu.cnm.deepdive.mealplanit.R;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import edu.cnm.deepdive.mealplanit.models.Diet;
import edu.cnm.deepdive.mealplanit.models.Person;
import edu.cnm.deepdive.mealplanit.models.Restriction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFragment extends Fragment implements View.OnClickListener {


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
    private Button submitButton;


    public CreateAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        caloriesbox = view.findViewById(R.id.calories_spinner);
        firstNameField = view.findViewById(R.id.first_name_field);
        lastNameField = view.findViewById(R.id.last_name_field);
        usernameField = view.findViewById(R.id.username_set_field);
        allergiesBox = view.findViewById(R.id.allergies_box);
        dietBox = view.findViewById(R.id.diet_box);
        submitButton = view.findViewById(R.id.submit_button);
        return view;
    }


    public void setAccount() {

    }

    @Override
    public void onClick(View v) {
        adapter();
        if (submitButton.getId() == v.getId() && firstNameField != null && lastNameField != null &&
                usernameField != null)
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
    }


    private class DietSpinner extends AsyncTask<Object, Object, List<Diet>> {

    @Override
    protected List<Diet> doInBackground(Object... objects) {
        return ((MainActivity) getActivity()).getDatabase().dietDao().getAll();
    }

    @Override
    protected void onPostExecute(List<Diet> dietType) {
        dietBox.setAdapter(new ArrayAdapter<Diet>(getActivity(), android.R.layout.simple_spinner_item, dietType));
    }
}

    private class RestrictionSpinner extends AsyncTask<Object, Object, List<Restriction>> {

        @Override
        protected List<Restriction> doInBackground(Object... objects) {
            return ((MainActivity) getActivity()).getDatabase().restrictionDao().getAll();
        }

        @Override
        protected void onPostExecute(List<Restriction> allergies) {
            allergiesBox.setAdapter(new ArrayAdapter<Restriction>(getActivity(),
                    android.R.layout.simple_spinner_item, allergies));
        }
    }



}
