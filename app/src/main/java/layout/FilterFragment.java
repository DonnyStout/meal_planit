package layout;


import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.Button;
import edu.cnm.deepdive.mealplanit.MainActivity;
import edu.cnm.deepdive.mealplanit.R;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import edu.cnm.deepdive.mealplanit.model.Diet;
import edu.cnm.deepdive.mealplanit.model.Person;
import edu.cnm.deepdive.mealplanit.model.PersonRestriction;
import edu.cnm.deepdive.mealplanit.model.Restriction;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment implements OnClickListener {

  private static final int PICK_IMAGE = 100;

  private MealDatabase database;
  private Person person;
  private Restriction restriction;
  private Diet diet;
  private String username;
  private Button changePhoto;
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
    username = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
                            .getString("username", null);
    changePhoto = view.findViewById(R.id.image_button);
    changePhoto.setOnClickListener(this);

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
      new ChangeUser().execute();
    } else {
      snack = Snackbar.make(getView().findViewById(R.id.filter_list), "Something went wrong",
          Snackbar.LENGTH_LONG);
      snack.show();
    }
  }

  public class ChangeUser extends AsyncTask<Object, Object, Person> {

    private Exception exception;

      @Override
      protected Person doInBackground(Object... objects) {
        getDatabase();
        person = database.personDao().findUsername(username);
        person.setImageUri(imageUri.toString());
        database.personDao().update(person);
        return person;
      }

      @Override
      protected void onPostExecute(Person person) {

      }
    }

  public class ChangeAllergy extends AsyncTask<Object, Object, Restriction> {

    @Override
    protected Restriction doInBackground(Object... objects) {
      PersonRestriction restrictionFind = database.personRestrictionDao().findByPersonId(person.getPersonId());
      restriction = database.restrictionDao().findByRestrictionId(restrictionFind.getRestrictionId());
      return restriction;
    }

    @Override
    protected void onPostExecute(Restriction restriction) {

    }
  }


  public class ChangeDiet extends AsyncTask<Object, Object, Diet> {

    @Override
    protected Diet doInBackground(Object... objects) {
      diet = database.dietDao().findByDietId(person.getDietId());
      return diet;
    }

    @Override
    protected void onPostExecute(Diet diet) {

    }
  }

}
