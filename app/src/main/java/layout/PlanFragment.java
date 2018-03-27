package layout;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import edu.cnm.deepdive.mealplanit.MainActivity;
import edu.cnm.deepdive.mealplanit.R;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import edu.cnm.deepdive.mealplanit.model.Diet;
import edu.cnm.deepdive.mealplanit.model.Person;
import edu.cnm.deepdive.mealplanit.model.PersonRestriction;
import edu.cnm.deepdive.mealplanit.model.Plan;
import edu.cnm.deepdive.mealplanit.model.Restriction;
import edu.cnm.deepdive.mealplanit.service.PlanService;
import edu.cnm.deepdive.mealplanit.servicemodel.MealList;
import edu.cnm.deepdive.mealplanit.servicemodel.MealList.Meal;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.LocalDate;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanFragment extends Fragment implements OnClickListener {




  private MealDatabase database;
  private Person person;
  private Diet diet;
  private CardView breakfastCardView;
  private CardView lunchCardView;
  private CardView dinnerCardView;
  private ImageView breakfastImage;
  private ImageView lunchImage;
  private ImageView dinnerImage;
  private TextView breakfastTitle;
  private TextView lunchTitle;
  private TextView dinnerTitle;
  private Button generateButton;
  private LocalDate date;
  private Plan plan;
  private Long userId;
  private Snackbar snack;
  private Bundle bundle;


  public PlanFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_plan, container, false);
    userId = getActivity().getSharedPreferences("user_id", Context.MODE_PRIVATE)
                            .getLong("user_id", -1);
    breakfastCardView = view.findViewById(R.id.breakfast_card_view);
    breakfastCardView.setOnClickListener(this);
    lunchCardView = view.findViewById(R.id.lunch_card_view);
    lunchCardView.setOnClickListener(this);
    dinnerCardView = view.findViewById(R.id.dinner_card_view);
    dinnerCardView.setOnClickListener(this);
    breakfastImage = view.findViewById(R.id.breakfast_photo);
    lunchImage = view.findViewById(R.id.lunch_photo);
    dinnerImage = view.findViewById(R.id.dinner_photo);
    breakfastTitle = view.findViewById(R.id.breakfast_title);
    lunchTitle = view.findViewById(R.id.lunch_title);
    dinnerTitle = view.findViewById(R.id.dinner_title);
    generateButton = view.findViewById(R.id.generate_button);
    generateButton.setOnClickListener(this);
    date = LocalDate.parse(getArguments().getString("plan_date"));
    new DataBaseQuery().execute();
    return view;
  }



  private MealDatabase getDatabase() {
    database = ((MainActivity) getActivity()).getDatabase();
    return database;
  }

  @Override
  public void onClick(View v) {
    if (generateButton.getId() == v.getId()) {
      new APICall().execute();
    } else {
      new CVHandler().onClick(v);
    }
  }


  public void retriveImages() {
    new ImageGetter().execute(plan.getBreakfastId(), plan.getBreakfastUrl(), breakfastImage);
    new ImageGetter().execute(plan.getLunchId(), plan.getLunchUrl(), lunchImage);
    new ImageGetter().execute(plan.getDinnerId(), plan.getDinnerUrl(), dinnerImage);
  }



  public class CVHandler implements CardView.OnClickListener {

    @Override
    public void onClick(View v) {
      bundle = new Bundle();
      FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
      BrowseFragment browseFragment = new BrowseFragment();
      bundle.putLong("food_id", ((long) v.getTag()));
      browseFragment.setArguments(bundle);
      fragmentTransaction.replace(R.id.content, browseFragment).addToBackStack("browse").commit();
    }
  }


  public class APICall extends AsyncTask<MealList, Object, Plan> {


    private Exception exception;



    @Override
    protected Plan doInBackground(MealList... meal) {
      getDatabase();
      Retrofit retrofit = ((MainActivity) getActivity()).getRetrofit();
      PlanService planService = retrofit.create(PlanService.class);
      person = database.personDao().findByPersonId(userId);
      plan = database.planDao().findByDateAndPersonId(date, person.getPersonId());
      PersonRestriction restrictionIdList = database.personRestrictionDao().findByPersonId(person.getPersonId());
      Restriction restriction = database.restrictionDao().findByRestrictionId(restrictionIdList.getRestrictionId());
      Diet diet = database.dietDao().findByDietId(person.getDietId());
      try {
        MealList mealList = planService.list(
            getString(R.string.food_key), diet.getDietType(), restriction.getAllergy(), person.getCaloriesPerDay(), "day").execute().body();
        List<Meal> meals = mealList.getMeals();
        Pattern pattern = Pattern.compile(getString(R.string.REGEX_FOR_EXTENSION));
        plan.setBreakfastId(meals.get(0).getId());
        plan.setLunchId(meals.get(1).getId());
        plan.setDinnerId(meals.get(2).getId());
        plan.setBreakfastTitle(meals.get(0).getTitle());
        plan.setLunchTitle(meals.get(1).getTitle());
        plan.setDinnerTitle(meals.get(2).getTitle());
        Matcher breakfastMatcher = pattern.matcher(meals.get(0).getImage());
        Matcher lunchMatcher = pattern.matcher(meals.get(1).getImage());
        Matcher dinnerMatcher = pattern.matcher(meals.get(2).getImage());
        breakfastMatcher.find();
        lunchMatcher.find();
        dinnerMatcher.find();
        plan.setBreakfastUrl(breakfastMatcher.group(0));
        plan.setLunchUrl(lunchMatcher.group(0));
        plan.setDinnerUrl(dinnerMatcher.group(0));
        database.planDao().update(plan);
        return plan;
      } catch (IOException e) {
        exception = e;
        e.printStackTrace();
        cancel(true);
        return null;
      }

    }

    @Override
    protected void onPostExecute(Plan plan) {
      breakfastTitle.setText(plan.getBreakfastTitle());
      lunchTitle.setText(plan.getLunchTitle());
      dinnerTitle.setText(plan.getDinnerTitle());
      retriveImages();
    }

    @Override
    protected void onCancelled() {
      snack = Snackbar.make(getActivity().findViewById(R.id.plan_layout),
          "Cannot generate plan", Snackbar.LENGTH_LONG);
      snack.show();
    }

  }

  public class ImageGetter extends AsyncTask<Object, Object, Bitmap> {

    Exception exception;

    private ImageView imageView;

    @Override
    protected Bitmap doInBackground(Object... objects) {
      imageView = (ImageView) objects[2];
      Plan planByDate = database.planDao().findByDateAndPersonId(date, person.getPersonId());
      String urlId = String.format("https://spoonacular.com/recipeImages/%1$s-480x360%2$s", objects);
      URL url = null;
      try {
        url = new URL(urlId);
      } catch (MalformedURLException e) {
        exception = e;
        e.printStackTrace();
        cancel(true);
        return null;
      }
      try {
        Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        return bitmap;
      } catch (IOException e) {
        exception = e;
        e.printStackTrace();
        cancel(true);
        return null;
      }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
      imageView.setImageBitmap(bitmap);

    }

    @Override
    protected void onCancelled() {
      snack = Snackbar.make(getActivity().findViewById(R.id.plan_layout),
          "Images can't be displayed", Snackbar.LENGTH_LONG);
      snack.show();
    }
  }

  public class DataBaseQuery extends AsyncTask<Object, Object, Plan>{

    @Override
    protected Plan doInBackground(Object... objects) {
      getDatabase();
      person = database.personDao().findByPersonId(userId);
      plan = database.planDao().findByDateAndPersonId(date, person.getPersonId());
      return plan;
    }

    @Override
    protected void onPostExecute(Plan plan) {
      if (plan.getBreakfastUrl() != null) {
        breakfastTitle.setText(plan.getBreakfastTitle());
        lunchTitle.setText(plan.getLunchTitle());
        dinnerTitle.setText(plan.getDinnerTitle());
        breakfastCardView.setTag(plan.getBreakfastId());
        lunchCardView.setTag(plan.getLunchId());
        dinnerCardView.setTag(plan.getDinnerId());
        retriveImages();
      }
    }
  }

}