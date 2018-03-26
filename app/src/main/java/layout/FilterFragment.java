package layout;


import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import edu.cnm.deepdive.mealplanit.MainActivity;
import edu.cnm.deepdive.mealplanit.R;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import edu.cnm.deepdive.mealplanit.model.Diet;
import edu.cnm.deepdive.mealplanit.model.Person;
import edu.cnm.deepdive.mealplanit.model.PersonRestriction;
import edu.cnm.deepdive.mealplanit.model.Restriction;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment implements OnClickListener {

  private static final int PICK_IMAGE = 100;

  private MealDatabase database;
  private Person person;
  private List<Restriction> restrictionList;
  private List<Diet> dietList;
  private Long userId;
  private Button changePhoto;
  private Button update;
  private EditText userField;
  private Spinner caloriesField;
  private Spinner dietChange;
  private Spinner allergyChange;
  private Uri imageUri;
  private Snackbar snack;


  public FilterFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_filter, container, false);
    userId = getActivity().getSharedPreferences("user_id", Context.MODE_PRIVATE)
        .getLong("user_id", -1);
    changePhoto = view.findViewById(R.id.image_button);
    changePhoto.setOnClickListener(this);
    update = view.findViewById(R.id.update_button);
    update.setOnClickListener(this);
    userField = view.findViewById(R.id.username_change_field);
    caloriesField = view.findViewById(R.id.calories_spinner_settings);
    dietChange = view.findViewById(R.id.diet_box_settings);
    allergyChange = view.findViewById(R.id.allergy_box_settings);
    new AllergyAdapter().execute();
    new DietAdapter().execute();
    adapter();
    return view;
  }

  private MealDatabase getDatabase() {
    database = ((MainActivity) getActivity()).getDatabase();
    return database;
  }

  @Override
  public void onClick(View v) {
    if (changePhoto.getId() == v.getId()) {
      openGallery();
    } else if (update.getId() == v.getId() && !userField.getText().toString().isEmpty()) {
      new UpdateSettings().execute(userId);
    }
    if (update.getId() == v.getId() && userField.getText().toString().isEmpty()) {
      snack = Snackbar.make(getActivity().findViewById(R.id.content),
          "Please confirm userId or enter new userId", Snackbar.LENGTH_LONG);
      snack.show();
    }
  }

  private void openGallery() {
    Intent gallery = new Intent(Intent.ACTION_OPEN_DOCUMENT, Media.INTERNAL_CONTENT_URI);
    startActivityForResult(gallery, PICK_IMAGE);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
      imageUri = data.getData();
      new ChangeUserPhoto().execute();
    } else {
      snack = Snackbar.make(getView().findViewById(R.id.filter_list), "Something went wrong",
          Snackbar.LENGTH_LONG);
      snack.show();
    }
  }

  private void adapter() {
    SpinnerAdapter caloriesAdapter = new ArrayAdapter<>(getActivity(),
        android.R.layout.simple_spinner_item,
        Arrays.asList(1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000, 2100, 2200,
            2300, 2400, 2500, 2600, 2700, 2800, 2900, 3000, 3100, 3200, 3300, 3400, 3500));
    caloriesField.setAdapter(caloriesAdapter);
  }

  public class ChangeUserPhoto extends AsyncTask<Object, Object, Person> {

      @Override
      protected Person doInBackground(Object... objects) {
        getDatabase();
        person = database.personDao().findByPersonId(userId);
        person.setImageUri(imageUri.toString());
        database.personDao().update(person);
        return person;
      }

      @Override
      protected void onPostExecute(Person person) {

      }
    }

  public class AllergyAdapter extends AsyncTask<Object, Object, List<Restriction>> {

    @Override
    protected List<Restriction> doInBackground(Object... objects) {
      getDatabase();
      restrictionList = database.restrictionDao().getAll();
      return restrictionList;
    }

    @Override
    protected void onPostExecute(List<Restriction> restriction) {
      allergyChange.setAdapter(new ArrayAdapter<Restriction>(getActivity(),
          android.R.layout.simple_spinner_item, restriction));
    }
  }


  public class DietAdapter extends AsyncTask<Object, Object, List<Diet>> {

    @Override
    protected List<Diet> doInBackground(Object... objects) {
      getDatabase();
      dietList = database.dietDao().getAll();
      return dietList;
    }

    @Override
    protected void onPostExecute(List<Diet> diet) {
      dietChange.setAdapter(new ArrayAdapter<Diet>(getActivity(),
          android.R.layout.simple_spinner_item, diet));
    }
  }

  public class UpdateSettings extends AsyncTask<Long, Object, Object> {

    @Override
    protected Object doInBackground(Long... userId) {
      getDatabase();
      person = database.personDao().findByPersonId(userId[0]);
      PersonRestriction personRestriction = database.personRestrictionDao().findByPersonId(person.getPersonId());
      person.setUsername(userField.getText().toString());
      person.setCaloriesPerDay((Integer) caloriesField.getSelectedItem());
      person.setDietId(((Diet) dietChange.getSelectedItem()).getDietId());
      personRestriction.setRestrictionId(
          ((Restriction)allergyChange.getSelectedItem()).getRestrictionId());
      personRestriction.setPersonId(person.getPersonId());
      database.personDao().update(person);
      database.personRestrictionDao().update(personRestriction);
      return null;
    }

    @Override
    protected void onPostExecute(Object o) {
      snack = Snackbar.make(getActivity().findViewById(R.id.content),
          "Settings Updated", Snackbar.LENGTH_LONG);
      snack.show();
    }
  }

}
