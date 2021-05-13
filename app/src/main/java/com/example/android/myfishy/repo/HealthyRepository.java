package com.example.android.myfishy.repo;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import com.example.android.myfishy.MainActivity;
import com.example.android.myfishy.db.HealthyDao;
import com.example.android.myfishy.db.HealthyDatabase;
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
    private static final String HEALTHY_REPOSITORY_TAG = "CLASS_HEALTHY_REPOSITORY";
    public static final String MAIN_ACTIVITY_TAG = "CLASS_MAIN_ACTIVITY";
    public static final String SPLASH_SCREEN_TAG = "CLASS_SPLASH_SCREEN";
    public static final String PROFILE_FORM_TAG = "CLASS_PROFILE_FORM";
    public static final String HOME_TAG = "CLASS_HOME";
    public static final String MEAL_LOGGING_TAG = "CLASS_MEAL_LOGGING";
    public static final String QUICK_ADD_MEAL_TAG = "CLASS_QUICK_ADD_MEAL";
    public static final String NUTRITION_ALARM_TAG = "CLASS_NUTRITION_ALARM";
    public static final String PROFILE_TAG = "CLASS_PROFILE";
    public static final String CREATE_MEAL_TAG = "CLASS_CREATE_MEAL";
    public static final String ADD_NOURISHMENT_TAG = "CLASS_ADD_NOURISHMENT";

    // ----- Class private global attributes ------- //
    private String currentViewModel;

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
     * Class is responsible for all related data handling activities
     *
     * @param application  needed to initiate DB instance
     * @param viewModelTag given input decides on which resources should be loaded
     */
    public HealthyRepository(Application application, String viewModelTag) {
        // ------- DB initialisation code Block: ----------- //
        currentViewModel = viewModelTag.replace("_VIEW_MODEL", "");
        try {
            // obtain HealthyDatabase object and initialise with application reference
            HealthyDatabase db = HealthyDatabase.getDatabase(application);
            healthyDao = db.healthyDao(); // get Dao object reference from database object

            // ------- DB query tables and store result in table object attributes: ----------- //
            // initialise class dependent attributes
            switch (currentViewModel) {
                case MAIN_ACTIVITY_TAG:
                    break;
                case SPLASH_SCREEN_TAG:
                    userTable = healthyDao.getUser(MainActivity.getCurrUser());
                    break;
                case PROFILE_FORM_TAG:
                    dietaryRestrictionTable = healthyDao.getDietaryRestrictionTable();
                    break;
                case HOME_TAG:
                    userJoinsDiet = healthyDao.getUserGotDiets(MainActivity.getCurrUser());
                    userJoinsMeal = healthyDao.getUserEatMeals(MainActivity.getCurrUser());
                    break;
                case MEAL_LOGGING_TAG:
                    userJoinsMeal = healthyDao.getUserEatMeals(MainActivity.getCurrUser());
                    break;
                case NUTRITION_ALARM_TAG:
                    userJoinsDiet = healthyDao.getUserGotDiets(MainActivity.getCurrUser());
                    break;
                case PROFILE_TAG:
                    userTable = healthyDao.getUser(MainActivity.getCurrUser());
                    userJoinsDiet = healthyDao.getUserGotDiets(MainActivity.getCurrUser());
                    break;
                case ADD_NOURISHMENT_TAG:
                    nutritionFactTable = healthyDao.getNutritionFactTable();
                    userJoinsDiet = healthyDao.getUserGotDiets(MainActivity.getCurrUser());
                    break;
            }
        } catch (IOException e) {
            Log.e(HEALTHY_REPOSITORY_TAG, e.getMessage());
        }
    }

    public LiveData<List<MealConsistsOfNourishments>> getMealJoinsNourishment() {

        return mealJoinsNourishment;
    }

    public LiveData<List<UserEatMeals>> getUserJoinsMeal() {
        return userJoinsMeal;
    }

    public LiveData<List<UserGotDiets>> getUserJoinsDiet() {
        return userJoinsDiet;
    }

    public LiveData<List<DietaryRestrictionTable>> getDietaryRestrictionTable() {
        return dietaryRestrictionTable;
    }

    public LiveData<List<NutritionFactTable>> getNutritionFactTable() {
        return nutritionFactTable;
    }

    public LiveData<User> getUserTable() {
        return userTable;
    }


}
