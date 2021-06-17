package com.example.android.myfishy.repo;

import android.app.Application;
import android.util.Log;
import android.widget.Toolbar;
import androidx.lifecycle.LiveData;
import com.example.android.myfishy.MainActivity;
import com.example.android.myfishy.db.HealthyDao;
import com.example.android.myfishy.db.HealthyDatabase;
import com.example.android.myfishy.db.entities.*;
import com.example.android.myfishy.db.relations.MealConsistsOfNourishments;
import com.example.android.myfishy.db.relations.UserEatsMeals;
import com.example.android.myfishy.db.relations.UserHasDiets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    public static final String VIEW_MODEL_TAG = "_VIEW_MODEL";
    public static final String FRAGMENT_TAG = "_FRAGMENT";
    public static final String ACTIVITY_TAG = "_ACTIVITY";
    // -- Meal entity related meal type attributes - //
    public static final String MEAL_TYPE_EXTRA = "MEAL_TYPE";
    public static final int MEAL_TYPE_BREAKFAST = 1;
    public static final int MEAL_TYPE_LUNCH = 2;
    public static final int MEAL_TYPE_DINNER = 3;
    public static final int MEAL_TYPE_DRINK = 4;
    public static final int MEAL_TYPE_SNACK = 5;
    // ------ Quantity related constant values ----- //
    public static final float MOLAR_MASS_SODIUM=23;
    // ------- Meal quantity per serving ----------- //
    public static final int NUTRITION_QUANTITY_PER_SERVING_MG = 100;
    public static final int NUTRITION_QUANTITY_PER_SERVING_ML = 100;

    // ----- Class private global attributes ------- //
    private String currentViewModel;

    // -------  private class attributes: ---------- //
    // ------------ DB attributes: ----------------- //
    // --------- DAO reference attribute: ---------- //
    private HealthyDao healthyDao;
    // ------- DB entities object attributes ------- //
    // ------- DB joined entities queries ---------- //
    private LiveData<List<MealConsistsOfNourishments>> mealJoinsNourishment;
    private LiveData<List<UserEatsMeals>> userJoinsMeal;
    private LiveData<List<UserHasDiets>> userJoinsDiet;
    // --------- DB full entity queries ----------- //
    private LiveData<List<DietaryRestrictionTable>> dietaryRestrictionTable;
    private LiveData<List<NutritionFactTable>> nutritionFactTable;
    // --------- DB column entity queries -------- //
    private LiveData<List<String>> nourishmentNamesFromNutritionFactTable;
    // --------- DB specified entity queries ----- //
    private LiveData<User> userTable;
    private List<Meal> mealDataOfPresentDay;
    // --------- DB entity classes --------------- //
    private Diet dietEntity;
    private final String dietClassName = Diet.class.getSimpleName();
    private DietaryRestrictionTable dietaryRestrictionTableEntity;
    private final String dietaryRestrictionTableClassName = DietaryRestrictionTable.class.getSimpleName();
    private Meal mealEntity;
    private final String mealClassName = Meal.class.getSimpleName();
    private Nourishment nourishmentEntity;
    private final String nourishmentClassName = Nourishment.class.getSimpleName();
    private NutritionFactTable nutritionFactTableEntity;
    private final String nutritionFactTableClassName = NutritionFactTable.class.getSimpleName();
    private User userEntity;
    private final String userClassName = User.class.getSimpleName();
    private final String mealConsistsOfNourishmentClassName = MealConsistsOfNourishments.class.getSimpleName();
    private final String userEatMealsClassName = UserEatsMeals.class.getSimpleName();
    private final String userGotDietsClassName = UserHasDiets.class.getSimpleName();


    /**
     * Class is responsible for all related data handling activities
     *
     * @param application  needed to initiate DB instance
     * @param viewModelTag given input decides on which resources should be loaded
     */
    public HealthyRepository(Application application, String viewModelTag) {
        // ------- DB initialisation code Block: ----------- //
        currentViewModel = viewModelTag.replace(VIEW_MODEL_TAG, "");
        try {
            // obtain HealthyDatabase object and initialise with application reference
            HealthyDatabase db = HealthyDatabase.getDatabase(application);
            healthyDao = db.healthyDao(); // get Dao object reference from database object
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            long today = c.getTimeInMillis();

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
                    nourishmentNamesFromNutritionFactTable = healthyDao.getNourishmentNamesFromNutritionFactTable();
                    userJoinsDiet = healthyDao.getUserGotDiets(MainActivity.getCurrUser());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mealDataOfPresentDay = healthyDao.getMealOfPresentDay(MainActivity.getCurrUser(), today);
                        }
                    }).start();
                    break;
            }
        } catch (IOException e) {
            Log.e(HEALTHY_REPOSITORY_TAG, e.getMessage());
        }
    }

    public static String buildTag(String baseTag, String endTag) {
        return baseTag + endTag;
    }

    public LiveData<List<MealConsistsOfNourishments>> getMealJoinsNourishment(int mealId) {
        return healthyDao.getMealConsistsOfNourishments(mealId);
    }

    public LiveData<List<UserEatsMeals>> getUserJoinsMeal() {
        return userJoinsMeal;
    }

    public LiveData<List<UserHasDiets>> getUserJoinsDiet() {
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

    public LiveData<List<String>> getNourishmentNamesFromNutritionFactTable() {
        return nourishmentNamesFromNutritionFactTable;
    }

    public LiveData<NutritionFactTable> getNutritionById(int id) {
        return healthyDao.getNutritionById(id);
    }

    public List<Meal> getMealDataOfPresentDay(){
        return mealDataOfPresentDay;
    }

    public void insertDiet(Diet diet) {
        insertEntityAsyncTask(healthyDao, diet);
    }

    public void insertDietaryRestrictionTable(DietaryRestrictionTable dietaryRestrictionTable) {
        insertEntityAsyncTask(healthyDao, dietaryRestrictionTable);
    }

    public void insertMeal(Meal meal) {
        insertEntityAsyncTask(healthyDao, meal);
    }

    public void insertNourishment(Nourishment nourishment) {
        insertEntityAsyncTask(healthyDao, nourishment);
    }

    public void insertNutritionFactTable(NutritionFactTable nutritionFactTable) {
        insertEntityAsyncTask(healthyDao, nutritionFactTable);
    }

    public void insertUser(User user) {
        insertEntityAsyncTask(healthyDao, user);
    }

    public LiveData<Integer> getLastSavedMealId() {
        return healthyDao.getLastSavedIdFromMealTable();
    }

    private void insertEntityAsyncTask(final HealthyDao healthyDao, final Object data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String dataClassName = data.getClass().getSimpleName();

                if (dataClassName.equals(dietClassName))
                    healthyDao.insertDiet((Diet) data);
                else if (dataClassName.equals(dietaryRestrictionTableClassName))
                    healthyDao.insertDietaryRestrictionTable((DietaryRestrictionTable) data);
                else if (dataClassName.equals(mealClassName))
                    healthyDao.insertMeal((Meal) data);
                else if (dataClassName.equals(nourishmentClassName))
                    healthyDao.insertNourishment((Nourishment) data);
                else if (dataClassName.equals(userClassName))
                    healthyDao.insertUser((User) data);
            }
        }).start();
    }
}
