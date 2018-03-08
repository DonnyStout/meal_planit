package layout;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.*;
import edu.cnm.deepdive.mealplanit.LoginActivity;
import edu.cnm.deepdive.mealplanit.dao.DietDao;
import edu.cnm.deepdive.mealplanit.dao.PersonDao;
import edu.cnm.deepdive.mealplanit.dao.PlanRestrictionDao;
import edu.cnm.deepdive.mealplanit.dao.RestrictionDao;
import edu.cnm.deepdive.mealplanit.R;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import edu.cnm.deepdive.mealplanit.models.Diet;
import edu.cnm.deepdive.mealplanit.models.Person;
import edu.cnm.deepdive.mealplanit.models.PersonRestriction;
import edu.cnm.deepdive.mealplanit.models.PlanRestriction;
import edu.cnm.deepdive.mealplanit.models.Restriction;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFragment extends Fragment {


  private MealDatabase database;
  private Person person;
  private PersonDao persondao;
  private Diet diet;
  private DietDao dietDao;
  private Restriction restriction;
  private RestrictionDao restrictionDao;
  private PlanRestriction planRestriction;
  private PlanRestrictionDao planRestrictionDao;
  private PersonRestriction personRestriction;
  private EditText firstNameField;
  private EditText lastNameField;
  private EditText usernameField;
  private Spinner caloriesbox;
  private Spinner allergyBox;
  private Spinner dietBox;
  private Button submitButton;
  private Snackbar snack;


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
    allergyBox = view.findViewById(R.id.allergy_box);
    dietBox = view.findViewById(R.id.diet_box);
    submitButton = view.findViewById(R.id.submit_button);
    new DietSpinner().execute();
    new RestrictionSpinner().execute();
    adapter();
    submitButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (submitButton.getId() == v.getId() && !firstNameField.getText().toString().isEmpty()
            && !lastNameField.getText().toString().isEmpty() &&
            !usernameField.getText().toString().isEmpty()) {
          new Thread(new Runnable() {
            @Override
            public void run() {
              person = new Person();
              personRestriction = new PersonRestriction();
              person.setFirstName(firstNameField.getText().toString());
              person.setLastName(lastNameField.getText().toString());
              person.setUsername(usernameField.getText().toString());
              person.setCaloriesPerDay((Integer) caloriesbox.getSelectedItem());
              person.setDietId(((Diet) dietBox.getSelectedItem()).getDietId());
              personRestriction.setRestrictionId(((Restriction) allergyBox.getSelectedItem()).getRestrictionId());
              long personId = MealDatabase.getInstance(getActivity()).personDao().insert(person);
              personRestriction.setPersonId(personId);
              MealDatabase.getInstance(getActivity()).personRestrictionDao().insert(personRestriction);
              snack = Snackbar.make(getActivity().findViewById(R.id.create_linear_layout),
                  "Account created, please login", Snackbar.LENGTH_LONG);
              snack.show();
            }
          }).start();
        } else {
          snack = Snackbar.make(getActivity().findViewById(R.id.create_linear_layout),
              "Please check that all fields are completed", Snackbar.LENGTH_LONG);
          snack.show();
        }


      }
    });
    return view;
  }




  public void adapter() {
    SpinnerAdapter caloriesAdapter = new ArrayAdapter<>(getActivity(),
        android.R.layout.simple_spinner_item,
        Arrays.asList(1200, 1500, 2000, 2500, 3000, 3500));
    caloriesbox.setAdapter(caloriesAdapter);
  }


  private class DietSpinner extends AsyncTask<Object, Object, List<Diet>> {

    @Override
    protected List<Diet> doInBackground(Object... objects) {
      return MealDatabase.getInstance(getActivity()).dietDao().getAll();
    }

    @Override
    protected void onPostExecute(List<Diet> dietType) {
      dietBox.setAdapter(
          new ArrayAdapter<Diet>(getActivity(), android.R.layout.simple_spinner_item, dietType));
    }
  }

  private class RestrictionSpinner extends AsyncTask<Object, Object, List<Restriction>> {

    @Override
    protected List<Restriction> doInBackground(Object... objects) {
      return MealDatabase.getInstance(getActivity()).restrictionDao().getAll();
    }

    @Override
    protected void onPostExecute(List<Restriction> allergies) {
      allergyBox.setAdapter(new ArrayAdapter<Restriction>(getActivity(),
          android.R.layout.simple_spinner_item, allergies));
    }
  }

  private class PersonRestrictionIdInsert extends AsyncTask<Object, Object, List<Restriction>> {

    @Override
    protected List<Restriction> doInBackground(Object... objects) {
      long personId = persondao.insert(person);
      personRestriction.setPersonId(personId);
      return null;
    }
  }


}
