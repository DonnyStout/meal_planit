package layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import edu.cnm.deepdive.mealplanit.MainActivity;
import edu.cnm.deepdive.mealplanit.R;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import edu.cnm.deepdive.mealplanit.model.Person;
import java.io.IOException;

/**
 * A {@link Fragment} class that the user is greeted with after logging in. It is the main interface
 * of the entire application. The contents displayed within this fragment are the user's first name last name and
 * username along with a picture that the user can upload. It also includes the {@link CalendarFragment} within this
 * fragment.
 */
public class AccountFragment extends Fragment implements View.OnClickListener {


  private MealDatabase database;
  private Long userId;
  private Person person;
  private TextView name;
  private TextView username;
  private ImageButton imageButton;



  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    final View view = inflater.inflate(R.layout.fragment_account, container, false);
    userId = getActivity().getSharedPreferences("user_id", Context.MODE_PRIVATE)
        .getLong("user_id", -1);
    imageButton = view.findViewById(R.id.user_image);
    name = view.findViewById(R.id.replaceable_name);
    username = view.findViewById(R.id.replaceable_user);
    imageButton.setOnClickListener(this);
    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    CalendarFragment calendarFragment = new CalendarFragment();
    fragmentTransaction.add(R.id.calendar_frame, calendarFragment).commit();
    new FindUser().execute();
    return view;
  }


  public void onClick(View v) {
    android.support.v4.app.FragmentTransaction transaction = getFragmentManager()
        .beginTransaction();
    FilterFragment filter = new FilterFragment();
    transaction.replace(R.id.content, filter).commit();
  }


  private MealDatabase getDatabase() {
    database = ((MainActivity) getActivity()).getDatabase();
    return database;
  }




  private class FindUser extends AsyncTask<Object, Object, Person> {

    private Exception exception;

    @Override
    protected Person doInBackground(Object... objects) {
      getDatabase();
      person = database.personDao().findByPersonId(userId);
      return person;
    }


    @Override
    protected void onPostExecute(Person person) {
      String first = person.getFirstName();
      String last = person.getLastName();
      String user = person.getUsername();
      name.setText(first + " " + last);
      username.setText(user);
      if (person.getImageUri() == null) {
        imageButton.setImageResource(R.drawable.user_picture);
      } else {
        Uri databaseImage = Uri.parse(person.getImageUri());
        try {
          Bitmap bitmap = Media.getBitmap(getActivity().getContentResolver(), databaseImage);
          imageButton.setImageBitmap(bitmap);
        } catch (IOException e) {
          exception = e;
          e.printStackTrace();
          cancel(true);
        }
      }
    }
  }
}

