package layout;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import edu.cnm.deepdive.mealplanit.MainActivity;
import edu.cnm.deepdive.mealplanit.R;
import edu.cnm.deepdive.mealplanit.dao.PersonDao;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import edu.cnm.deepdive.mealplanit.models.Person;


public class AccountFragment extends Fragment implements View.OnClickListener {


  public static final String FIRST_LAST_NAME_TEXT = "replaceable_name_text";
  public static final String USERNAME_TEXT = "replaceable_username";


  private String replaceableNameText;
  private String replaceableUsernameText;
  private String username;
  private Person person;
  private TextView name;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    final View view = inflater.inflate(R.layout.fragment_account, container, false);
    username = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
                            .getString("username", null);
    ImageButton ub = view.findViewById(R.id.user_image);
    name = view.findViewById(R.id.replaceable_name);
    ub.setOnClickListener(this);
    TextView user = view.findViewById(R.id.replaceable_user);
    user.setText(username);
    new FindUser().execute();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//        Person person = new Person();
//        person.setFirstName("Bob");
//        person.setLastName("Star");
//        person.setUsername("star*labs");
//        ((MainActivity)getActivity()).getDatabase().personDao().insert(person);
//
////                Person personList = ((MainActivity)getActivity()).getDatabase().personDao().findUsername();
////                final ListAdapter adapter = new ArrayAdapter<Person>(getActivity(), android.R.layout.simple_list_item_1, personList);
////                getActivity().runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        ((List<Person>) view).setAdapter(adapter);
////                    }
////                });
//            }
//        }).start();
    return view;
  }


  public void onClick(View v) {
    android.support.v4.app.FragmentTransaction transaction = getFragmentManager()
        .beginTransaction();
    FilterFragment filter = new FilterFragment();
    transaction.replace(R.id.content, filter).commit();
  }


  private class FindUser extends AsyncTask<Object, Object, Person> {

    @Override
    protected Person doInBackground(Object... objects) {
      Person person = MealDatabase.getInstance(getActivity()).personDao().findUsername(username);
      return person;
    }


    @Override
    protected void onPostExecute(Person person) {
      String first = person.getFirstName();
      String last = person.getLastName();
       name.setText(first + " " + last);
    }
  }

}
