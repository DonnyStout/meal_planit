package layout;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cnm.deepdive.mealplanit.MainActivity;
import edu.cnm.deepdive.mealplanit.R;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import edu.cnm.deepdive.mealplanit.model.Diet;
import edu.cnm.deepdive.mealplanit.model.Person;
import edu.cnm.deepdive.mealplanit.model.PersonRestriction;
import edu.cnm.deepdive.mealplanit.model.Restriction;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {

  private MealDatabase database;
  private Person person;
  private Restriction restriction;
  private Diet diet;
  private String username;


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
    return view;
  }

  private MealDatabase getDatabase() {
    database = ((MainActivity) getActivity()).getDatabase();
    return database;
  }

    public class ChangeUser extends AsyncTask<Object, Object, Person> {

      @Override
      protected Person doInBackground(Object... objects) {
        person = database.personDao().findUsername(username);
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
