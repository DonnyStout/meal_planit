package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cnm.deepdive.mealplanit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanFragment extends Fragment {


  public static final String BASE_URL = "https://spoonacular.com/recipeImages/";

  public static final String RESOLUTION_URL = "";


  private String idUrl;
  private String extensionUrl;


  public PlanFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_plan, container, false);
  }

//  HttpResponse<JsonNode> response = Unirest.get(
//      "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/mealplans/generate?diet=no+diet&exclude=paleo&targetCalories=2500&timeFrame=day")
//      .header("X-Mashape-Key", "")
//      .header("Accept", "application/json")
//      .asJson();

}