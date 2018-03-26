package layout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import edu.cnm.deepdive.mealplanit.R;



public class BrowseFragment extends Fragment {


  private String tag;
  private String tag2;
  private String tag3;
  private EditText tagField;
  private EditText tag2Field;
  private EditText tag3Field;
  private Button browseSearchButton;
  private Bundle bundle;


  public BrowseFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_browse, container, false);
//    tagField = getActivity().findViewById(R.id.limit_license_field);
//    tag2Field = getActivity().findViewById(R.id.number_field);
//    tag3Field = getActivity().findViewById(R.id.tags_field);
//    browseSearchButton = getActivity().findViewById(R.id.browse_search_button);
    bundle = getArguments().getBundle("title");
    return view;
  }
}

//  @Override
//  public void onClick(View v) {
//    if (browseSearchButton.getId() == v.getId()) {
//      recipeCall();
//    }
//  }


//  public void recipeCall() {
//    String url = createUrl();
//    Log.d("tag", url);
//    if (url != null) {
//
//    } else {
//      // TODO snackbar
//    }
//  }



//  private String createUrl() {
//    tag = tagField.getText().toString();
//    tag2 = tag2Field.getText().toString();
//    tag3 = tag3Field.getText().toString();
//    if (!tagField.getText().toString().isEmpty() && tag2Field.getText().toString().isEmpty() &&
//        tag3Field.getText().toString().isEmpty()) {
//      return tag;
//    }
//    else if (!tagField.getText().toString().isEmpty() && !tag2Field.getText().toString().isEmpty() &&
//        tag3Field.getText().toString().isEmpty()) {
//      return tag + "," + tag2;
//    }
//    else if (!tagField.getText().toString().isEmpty() && !tag2Field.getText().toString().isEmpty() &&
//        !tag3Field.getText().toString().isEmpty()) {
//      return tag + "," + tag2 + "," + tag3;
//    } else {
//      // TODO snackbar
//    }
//    return null;
//  }


//  public class GrabRecipe extends AsyncTask<Object, Object, >
//
//}
