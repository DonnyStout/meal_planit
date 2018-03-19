package layout;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import edu.cnm.deepdive.mealplanit.R;
import edu.cnm.deepdive.mealplanit.db.MealDatabase;
import edu.cnm.deepdive.mealplanit.model.Diet;
import edu.cnm.deepdive.mealplanit.model.Person;
import edu.cnm.deepdive.mealplanit.model.Plan;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanFragment extends Fragment {


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
  private Date date;
  private Plan plan;


  public PlanFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_plan, container, false);
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
    date = new Date(getArguments().getLong("plan_date"));
//    breakfastTitle.setText(date.toString());
//    breakfastReady.setText("yes");
//    lunchTitle.setText("Blue berry muffins");
//    lunchReady.setText("90");
//    dinnerTitle.setText("Blue berry muffins");
//    dinnerReady.setText("90");
//    dinnerImage.setImageResource(R.drawable.ic_face_black_24dp);
//    breakfastImage.setImageResource(R.drawable.ic_face_black_24dp);
//    lunchImage.setImageResource(R.drawable.ic_face_black_24dp);
    return view;
  }



  private MealDatabase getDatabase() {
    if (database == null) {
      database = Room.databaseBuilder
          (getActivity().getApplicationContext(), MealDatabase.class, "mealdatabase").build();
    }
    return database;
  }


}