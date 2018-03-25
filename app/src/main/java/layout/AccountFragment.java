package layout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
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
import java.io.InputStream;

/**
 * A [@link Fragment] class that the user is greeted with after logging in. It is the main interface
 * of the entire application. The contents displayed within this fragment are the user's name and
 * username along with a picture that the user can upload.
 */
public class AccountFragment extends Fragment implements View.OnClickListener {


  private static final String FIRST_LAST_NAME_TEXT = "replaceable_name_text";
  private static final String USERNAME_TEXT = "replaceable_username";


  private MealDatabase database;
  private String replaceableNameText;
  private String replaceableUsernameText;
  private String username;
  private Person person;
  private TextView name;
  private FrameLayout calendarFrame;
  private ImageButton imageButton;


  /**
   * Uses a saved instance of the username the user entered in the login activity using shared
   * preferences. Creates all the instances of [@link Button], [@link EditText], and [@link
   * TextView].
   *
   * @param inflater Inflates the view from the xml file.
   */
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    final View view = inflater.inflate(R.layout.fragment_account, container, false);
    username = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        .getString("username", null);
    imageButton = view.findViewById(R.id.user_image);
    name = view.findViewById(R.id.replaceable_name);
    imageButton.setOnClickListener(this);
    TextView user = view.findViewById(R.id.replaceable_user);
    user.setText(username);
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
      person = MealDatabase.getInstance(getActivity()).personDao().findUsername(username);
      return person;
    }


    @Override
    protected void onPostExecute(Person person) {
      String first = person.getFirstName();
      String last = person.getLastName();
      name.setText(first + " " + last);
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

