package com.example.android.myfishy.repo;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import com.example.android.myfishy.MainActivity;
import com.example.android.myfishy.db.HealthyDao;
import com.example.android.myfishy.db.HealthyDatabase;
import com.example.android.myfishy.db.entities.Diet;
import com.example.android.myfishy.db.entities.DietaryRestrictionTable;
import com.example.android.myfishy.db.entities.NutritionFactTable;
import com.example.android.myfishy.db.entities.User;
import com.example.android.myfishy.db.relations.MealConsistsOfNourishments;
import com.example.android.myfishy.db.relations.UserEatMeals;
import com.example.android.myfishy.db.relations.UserGotDiets;

import java.io.IOException;
import java.util.List;

public class HealthyRepository {

    // ----------- final attributes: --------------- //
    private static final String HEALTHY_REPOSITORY_TAG = "HEALTHY_REPOSITORY_CLASS";
    public static final String MAIN_ACTIVITY_TAG = "MAIN_ACTIVITY_CLASS";
    public static final String SPLASH_SCREEN_FRAGMENT_TAG = "SPLASH_SCREEN_FRAGMENT_CLASS";
    public static final String PROFILE_FORM_FRAGMENT_TAG = "PROFILE_FORM_FRAGMENT_CLASS";
    public static final String HOME_FRAGMENT_TAG = "HOME_FRAGMENT_CLASS";
    public static final String MEAL_LOGGING_FRAGMENT_TAG = "MEAL_LOGGING_FRAGMENT_CLASS";
    public static final String QUICK_ADD_MEAL_FRAGMENT_TAG = "QUICK_ADD_MEAL_FRAGMENT_CLASS";
    public static final String NUTRITION_ALARM_FRAGMENT_TAG = "NUTRITION_ALARM_FRAGMENT_CLASS";
    public static final String PROFILE_FRAGMENT_TAG = "PROFILE_FRAGMENT_CLASS";

    // -------  private class attributes: ---------- //
    // ------------ DB attributes: ----------------- //
    // --------- DAO reference attribute: ---------- //
    private HealthyDao healthyDao;
    // ------- DB entities object attributes ------- //
    // ------- DB joined entities queries ---------- //
    private LiveData<List<MealConsistsOfNourishments>> mealJoinsNourishment;
    private LiveData<List<UserEatMeals>> userJoinsMeal;
    private LiveData<List<UserGotDiets>> userJoinsDiet;
    // --------- DB full entity queries ----------- //
    private LiveData<List<DietaryRestrictionTable>> dietaryRestrictionTable;
    private LiveData<List<NutritionFactTable>> nutritionFactTable;
    private LiveData<User> userTable;

    /**
     *
     * Class is responsible for all related data handling activities
     *
     * @param application needed to initiate DB instance
     * @param viewModelTag given input decides on which resources should be loaded
     */
    public HealthyRepository(Application application, String viewModelTag) {
        // ------- DB initialisation code Block: ----------- //
        try {
            // obtain HealthyDatabase object and initialise with application reference
            HealthyDatabase db = HealthyDatabase.getDatabase(application);
            healthyDao = db.healthyDao(); // get Dao object reference from database object

            // ------- DB query tables and store result in table object attributes: ----------- //
            // initialise class dependent attributes
            switch (viewModelTag) {
                case MAIN_ACTIVITY_TAG:
                    break;
                case SPLASH_SCREEN_FRAGMENT_TAG:
                    userTable = healthyDao.getUser(MainActivity.getCurrUser());
                    break;
                case HOME_FRAGMENT_TAG:
                    userJoinsDiet = healthyDao.getUserGotDiets(MainActivity.getCurrUser());
                    userJoinsMeal = healthyDao.getUserEatMeals(MainActivity.getCurrUser());
                    break;
                case MEAL_LOGGING_FRAGMENT_TAG:
                    userJoinsMeal = healthyDao.getUserEatMeals(MainActivity.getCurrUser());
                    break;
                case NUTRITION_ALARM_FRAGMENT_TAG:
                    userJoinsDiet = healthyDao.getUserGotDiets(MainActivity.getCurrUser());
                    break;
                case PROFILE_FRAGMENT_TAG:
                    userTable = healthyDao.getUser(MainActivity.getCurrUser());
                    userJoinsDiet = healthyDao.getUserGotDiets(MainActivity.getCurrUser());
                    break;
            }
        } catch (IOException e){
            Log.e(HEALTHY_REPOSITORY_TAG, e.getMessage());
        }
    }

}
