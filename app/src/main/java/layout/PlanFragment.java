package layout;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanFragment extends Fragment implements OnClickListener {


  public static final String BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/mealplans";


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
  private TextView breakfastReady;
  private TextView lunchReady;
  private TextView dinnerReady;
  private Button generateButton;
  private Date date;
  private Plan plan;
  private String username;


  public PlanFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_plan, container, false);
    username = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
                            .getString("username", null);
    breakfastCardView = view.findViewById(R.id.breakfast_card_view);
    lunchCardView = view.findViewById(R.id.lunch_card_view);
    dinnerCardView = view.findViewById(R.id.dinner_card_view);
    breakfastImage = view.findViewById(R.id.breakfast_photo);
    lunchImage = view.findViewById(R.id.lunch_photo);
    dinnerImage = view.findViewById(R.id.dinner_photo);
    breakfastTitle = view.findViewById(R.id.breakfast_title);
    lunchTitle = view.findViewById(R.id.lunch_title);
    dinnerTitle = view.findViewById(R.id.dinner_title);
    breakfastReady = view.findViewById(R.id.breakfast_ready_text);
    lunchReady = view.findViewById(R.id.lunch_ready_text);
    dinnerReady = view.findViewById(R.id.dinner_ready_text);
    generateButton = view.findViewById(R.id.generate_button);
    generateButton.setOnClickListener(this);
    date = new Date(getArguments().getLong("plan_date"));
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
    }
  }


  public class APICall extends AsyncTask<MealList, Object, Plan> {


    public static final String ExtensionCatch = "\\.([^\\.]+)$";

    private Exception exception;



    @Override
    protected Plan doInBackground(MealList... meal) {
      getDatabase();
      Retrofit retrofit = ((MainActivity) getActivity()).getRetrofit();
      PlanService planService = retrofit.create(PlanService.class);
      Person person = database.personDao().findUsername(username);
      PersonRestriction restrictionId = database.personRestrictionDao().findByPersonId(person.getPersonId());
      Restriction restriction = database.restrictionDao().findByRestrictionId(restrictionId.getRestrictionId());
      Diet diet = database.dietDao().findByDietId(person.getDietId());
      try {
        MealList mealList = planService.list(
            diet.getDietType(), restriction.getAllergy(), person.getCaloriesPerDay()).execute().body();
        List<Meal> meals = mealList.getMeals();
        Pattern pattern = Pattern.compile(ExtensionCatch);
        plan.setBreakfastId(meals.get(0).getId());
        plan.setLunchId(meals.get(1).getId());
        plan.setDinnerId(meals.get(2).getId());
        plan.setBreakfastTitle(meals.get(0).getTitle());
        plan.setLunchTitle(meals.get(1).getTitle());
        plan.setDinnerTitle(meals.get(2).getTitle());
        Matcher breakfastMatcher = pattern.matcher(meals.get(0).getImage());
        Matcher lunchMatcher = pattern.matcher(meals.get(1).getImage());
        Matcher dinnerMatcher = pattern.matcher(meals.get(2).getImage());
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
//      breakfastImage.setImageBitmap();
      lunchTitle.setText(plan.getLunchTitle());
      dinnerTitle.setText(plan.getDinnerTitle());
    }

    @Override
    protected void onCancelled() {
      super.onCancelled();
    }

  }

  public class ImageGetter extends AsyncTask<Object, Object, Bitmap> {

    Exception exception;

    @Override
    protected Bitmap doInBackground(Object... objects) {
      Plan planByDate = database.planDao().findDate(date);
      String urlId = String.format("https://spoonacular.com/recipeImages/%s1$-90x90.%s2$", objects);
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
      super.onPostExecute(bitmap);
    }

    @Override
    protected void onCancelled() {
      super.onCancelled();
    }
  }

}