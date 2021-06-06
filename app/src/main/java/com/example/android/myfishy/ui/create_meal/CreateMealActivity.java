package com.example.android.myfishy.ui.create_meal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.myfishy.MainActivity;
import com.example.android.myfishy.R;
import com.example.android.myfishy.db.entities.Meal;
import com.example.android.myfishy.db.entities.NutritionFactTable;
import com.example.android.myfishy.repo.HealthyRepository;
import com.example.android.myfishy.ui.add_nourishment.AddNourishmentFragment;
import com.example.android.myfishy.utilities.OnCloseFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateMealActivity extends AppCompatActivity implements OnCloseFragment, IngredientListAdapter.OnNutritionListener {

    public static final String CREATE_MEAL_ACTIVITY_TAG =
            HealthyRepository.buildTag(HealthyRepository.CREATE_MEAL_TAG, HealthyRepository.ACTIVITY_TAG);

    private FragmentManager fragmentManager;
    private Fragment fragment;
    private CreateMealViewModel createMealViewModel;
    private IngredientListAdapter ingredientListAdapter;
    private String mealTitle;
    private String mealInstruction;
    private List<NutritionFactTable> nutritionFactTableList;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);
        createMealViewModel =
                new ViewModelProvider(this).get(CreateMealViewModel.class);
        mealTitle = "";
        mealInstruction = "";
        nutritionFactTableList = new ArrayList<>();

        final LifecycleOwner cool = this;

        FloatingActionButton fab = findViewById(R.id.activity_create_meal_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Meal meal = new Meal(
                        MainActivity.getCurrUser(),
                        mealTitle,
                        HealthyRepository.MEAL_TYPE_BREAKFAST, // TODO: diese Zuweisung muss noch dynamisch erfolgen
                        System.currentTimeMillis()
                );
                createMealViewModel.insertMeal(meal);

                final int[] res = {0};
                createMealViewModel.getLastSavedMealId().observe(cool, new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        res[0] = integer;
                    }
                });

                for (NutritionFactTable item : nutritionFactTableList) {
                    createMealViewModel.insertNourishment(res[0], item);
                }

                Intent replyIntent = new Intent();
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
        fragmentManager = getSupportFragmentManager();

        RecyclerView recyclerView = findViewById(R.id.list_ingredients_recyclerView);
        ingredientListAdapter = new IngredientListAdapter(this, this);
        recyclerView.setAdapter(ingredientListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void searchForNourishment(View view) {
        fragment = AddNourishmentFragment.newInstance(this);
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction
                .add(R.id.create_meal_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeFragment(List<?> bundle) {
        MainActivity.hideKeyboard(this);
        fragmentManager.beginTransaction().remove(fragment).commit();

        ArrayList<String> currList = new ArrayList<>();
        for (Object item : bundle) {
            NutritionFactTable nut = (NutritionFactTable) item;
            createMealViewModel.getNutritionById(nut.getNutrition_id())
                    .observe(this, new Observer<NutritionFactTable>() {
                        @Override
                        public void onChanged(NutritionFactTable nutritionFactTable) {
                            nutritionFactTableList.add(nutritionFactTable);
                        }
                    });
        }
        if (nutritionFactTableList != null) {
            for (NutritionFactTable item : nutritionFactTableList) {
                currList.add(item.getNourishment_name());
            }
        }
        ingredientListAdapter.setNutritionNames(currList);
    }

    @Override
    public void onNutritionListener(int position) {

    }
}