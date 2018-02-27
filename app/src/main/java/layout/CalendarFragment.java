package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CalendarView;
import edu.cnm.deepdive.mealplanit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment implements View.OnClickListener {


    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        CalendarView cv = view.findViewById(R.id.calendar_layout);
        cv.setOnDateChangeListener(null);
        return view;
    }


    @Override
    public void onClick(View v) {
                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        PlanFragment plan = new PlanFragment();
        transaction.replace(R.id.content, plan).commit();
    }


}
