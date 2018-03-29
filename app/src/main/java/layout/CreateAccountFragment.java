package layout;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import edu.cnm.deepdive.mealplanit.model.Diet;
import edu.cnm.deepdive.mealplanit.model.Person;
import edu.cnm.deepdive.mealplanit.model.PersonRestriction;
import edu.cnm.deepdive.mealplanit.model.PlanRestriction;
import edu.cnm.deepdive.mealplanit.model.Restriction;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFragment extends Fragment implements OnClickListener {


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
    submitButton.setOnClickListener(this);
    return view;
  }


  private void clear() {
    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    fragmentTransaction.remove(this).commit();
  }


  private void adapter() {
    SpinnerAdapter caloriesAdapter = new ArrayAdapter<>(getActivity(),
        android.R.layout.simple_spinner_item,
        Arrays.asList(getResources().getStringArray(R.array.kilo_calories_intake_value)));
    caloriesbox.setAdapter(caloriesAdapter);
  }

  @Override
  public void onClick(View v) {
    if (submitButton.getId() == v.getId()) {
      if (!firstNameField.getText().toString().isEmpty()
          && !lastNameField.getText().toString().isEmpty() &&
          !usernameField.getText().toString().isEmpty()) {
        new HandleAccountCreation().execute();
      } else {
        snack = Snackbar.make(getActivity().findViewById(R.id.box),
            R.string.fields_snackbar, Snackbar.LENGTH_LONG);
        snack.show();
      }
    }
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

  private class HandleAccountCreation extends AsyncTask<Object, Object, Person> {

    private Person personUsernameVariable;

    private Person person;
    private PersonRestriction personRestriction;

    @Override
    protected Person doInBackground(Object... objects) {
      PersonDao usernameFind = MealDatabase.getInstance(getActivity()).personDao();
      personUsernameVariable = usernameFind.findUsername(usernameField.getText().toString());
      if (!firstNameField.getText().toString().isEmpty() && !lastNameField.getText().toString()
          .isEmpty() &&
          !usernameField.getText().toString().isEmpty()) {
        if (personUsernameVariable != null) {
          cancel(true);
          return null;
        } else {
          person = new Person();
          personRestriction = new PersonRestriction();
          person.setFirstName(firstNameField.getText().toString());
          person.setLastName(lastNameField.getText().toString());
          person.setUsername(usernameField.getText().toString());
          person.setCaloriesPerDay(Integer.parseInt(((String) caloriesbox.getSelectedItem())));
          person.setDietId(((Diet) dietBox.getSelectedItem()).getDietId());
          personRestriction.setRestrictionId(
              ((Restriction) allergyBox.getSelectedItem()).getRestrictionId());
          long personId = MealDatabase.getInstance(getActivity()).personDao().insert(person);
          personRestriction.setPersonId(personId);
          MealDatabase.getInstance(getActivity()).personRestrictionDao()
              .insert(personRestriction);
        }
      }
      return person;
    }


    @Override
    protected void onCancelled() {
      snack = Snackbar.make(getActivity().findViewById(R.id.box),
          R.string.user_exists_snackbar, Snackbar.LENGTH_LONG);
      snack.show();
    }

    @Override
    protected void onPostExecute(Person person) {
      snack = Snackbar.make(getActivity().findViewById(R.id.box),
          R.string.account_created_snackbar, Snackbar.LENGTH_LONG);
      snack.show();
      ((LoginActivity) getActivity()).update();
      clear();
    }
  }
}
