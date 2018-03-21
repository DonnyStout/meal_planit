package layout;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import edu.cnm.deepdive.mealplanit.R;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import edu.cnm.deepdive.mealplanit.model.Person;
import edu.cnm.deepdive.mealplanit.model.PersonRestriction;
import edu.cnm.deepdive.mealplanit.model.Plan;
import edu.cnm.deepdive.mealplanit.model.PlanRestriction;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.LocalDate;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment implements OnDateChangeListener {


  private String username;
  public Bundle date;

  public CalendarFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_calendar, container, false);
    username = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        .getString("username", null);

    CalendarView cv = view.findViewById(R.id.calendar_view);
    cv.setOnDateChangeListener(this);
    return view;
  }


  @Override
  public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
    LocalDate localDate = new LocalDate(year, month, dayOfMonth);
    new PlanInstance().execute(localDate);
  }

  private class PlanInstance extends AsyncTask<LocalDate, Object, Plan> {

    private Plan planInstance;

    @Override
    protected Plan doInBackground(LocalDate... date) {
      planInstance = MealDatabase.getInstance(getActivity()).planDao().findDate(date[0]);
      if (planInstance == null) {
        planInstance = new Plan();
        Person person = MealDatabase.getInstance(getActivity()).personDao().findUsername(username);
        long dietId = person.getDietId();
        long personId = person.getPersonId();
        planInstance.setDate(date[0].toString());
        planInstance.setPersonId(personId);
        planInstance.setDietId(dietId);
//        MealDatabase.getInstance(getActivity()).planDao().insert(personId)
        long planId = MealDatabase.getInstance(getActivity()).planDao().insert(planInstance);
        PersonRestriction personRestriction = MealDatabase.getInstance(getActivity())
                                                          .personRestrictionDao()
                                                          .findByPersonId(personId);
        PlanRestriction planRestriction = new PlanRestriction();
        long restrictionId = personRestriction.getRestrictionId();
        planRestriction.setRestrictionId(restrictionId);
        planRestriction.setPlanId(planId);
        MealDatabase.getInstance(getActivity()).planRestrictionDao().insert(planRestriction);
      }
      return planInstance;
    }

    @Override
    protected void onPostExecute(Plan plan) {
      date = new Bundle();
      date.putString("plan_date", plan.getDate().toString());
      android.support.v4.app.FragmentTransaction transaction = getFragmentManager()
          .beginTransaction();
      PlanFragment planFragment = new PlanFragment();
      planFragment.setArguments(date);
      transaction.replace(R.id.content, planFragment).commit();
    }
  }
}
