package layout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.mealplanit.MainActivity;
import edu.cnm.deepdive.mealplanit.R;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import edu.cnm.deepdive.mealplanit.service.BrowseService;
import edu.cnm.deepdive.mealplanit.servicemodel.Recipe;
import java.io.IOException;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BrowseFragment extends Fragment {


  private MealDatabase database;
  private CardView recipeCard;
  private WebView recipeText;
  private TextView recipeTitle;
  private Long foodId;


  public BrowseFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_browse, container, false);
    foodId = getArguments().getLong("food_id");
    recipeCard = view.findViewById(R.id.browse_card_view);
    recipeText = view.findViewById(R.id.recipe_text);
    recipeTitle = view.findViewById(R.id.recipe_title);
    new GrabRecipe().execute();
    return view;
  }

  private MealDatabase getDatabase() {
    database = ((MainActivity) getActivity()).getDatabase();
    return database;
  }

  public class GrabRecipe extends AsyncTask<Recipe, Object, Recipe> {

    private Exception exception;
    private Snackbar snack;

    @Override
    protected Recipe doInBackground(Recipe... recipes) {
      getDatabase();
      Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
      Retrofit retrofit = new Retrofit.Builder()
                                      .baseUrl(getString(R.string.recipe_url))
                                      .addConverterFactory(GsonConverterFactory.create(gson))
                                      .build();
      BrowseService browseService = retrofit.create(BrowseService.class);
      Recipe recipe = null;
      try {
        recipe = browseService.list(
            getString(R.string.food_key), foodId).execute().body();
      } catch (IOException e) {
        exception = e;
        e.printStackTrace();
        cancel(true);
      }
      return recipe;
    }

    @Override
    protected void onPostExecute(Recipe recipe) {
      recipeTitle.setText(recipe.getTitle());
      recipeText.loadData(recipe.getSummary(), "text/html; charset=utf-8", "UTF-8");
    }

    @Override
    protected void onCancelled() {
      snack = Snackbar.make(getActivity().findViewById(R.id.content), "Recipe could not be displayed", Snackbar.LENGTH_LONG);
      snack.show();
    }
  }

}

