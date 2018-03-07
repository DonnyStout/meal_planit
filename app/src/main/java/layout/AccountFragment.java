package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import edu.cnm.deepdive.mealplanit.MainActivity;
import edu.cnm.deepdive.mealplanit.R;
import edu.cnm.deepdive.mealplanit.models.Person;


public class AccountFragment extends Fragment implements View.OnClickListener {


    public static final String FIRST_LAST_NAME_TEXT = "replaceable_name_text";
    public static final String USERNAME_TEXT = "replaceable_username";


    private String replaceableNameText;
    private String replaceableUsernameText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_account, container, false);
        ImageButton ub = view.findViewById(R.id.user_image);
        TextView name = view.findViewById(R.id.replaceable_name);
        name.setText(replaceableNameText);
        ub.setOnClickListener(this);
        TextView user = view.findViewById(R.id.replaceable_user);
        user.setText(replaceableUsernameText);


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


        public void onClick (View v){
            android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
            FilterFragment filter = new FilterFragment();
            transaction.replace(R.id.content, filter).commit();
        }


        public void text() {
//            Person person = ((MainActivity) getActivity()).getDatabase().personDao()
//                    .findFirstName();

        }

    }
