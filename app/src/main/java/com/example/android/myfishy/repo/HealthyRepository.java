package com.example.android.myfishy.repo;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import com.example.android.myfishy.db.HealthyDao;
import com.example.android.myfishy.db.HealthyDatabase;
import com.example.android.myfishy.db.entities.Diet;
import com.example.android.myfishy.db.relations.MealConsistsOfNourishments;
import com.example.android.myfishy.db.relations.UserEatMeals;
import com.example.android.myfishy.db.relations.UserGotDiets;

import java.io.IOException;
import java.util.List;

public class HealthyRepository {

    // ----------- final attributes: --------------- //
    private static final String HEALTHY_REPOSITORY_TAG = "HEALTHY_REPOSITORY_CLASS";

    // -------  private class attributes: ---------- //
    // ------------ DB attributes: ----------------- //
    // --------- DAO reference attribute: ---------- //
    private HealthyDao healthyDao;
    // ------- DB entities object attributes ------- //
    // ------- DB joined entities queries ---------- //
    private LiveData<List<MealConsistsOfNourishments>> mealJoinsNourishment;
    private LiveData<List<UserEatMeals>> userJoinsMeal;
    private LiveData<List<UserGotDiets>> userJoinsDiet;


    public HealthyRepository(Application application) {
        // ------- DB initialisation code Block: ----------- //
        try {
            // obtain HealthyDatabase object and initialise with application reference
            HealthyDatabase db = HealthyDatabase.getDatabase(application);
            healthyDao = db.healthyDao(); // get Dao object reference from database object

            // ------- DB query tables and store result in table object attributes: ----------- //

        } catch (IOException e){
            Log.e(HEALTHY_REPOSITORY_TAG, e.getMessage());
        }
    }

}
