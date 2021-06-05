package com.example.android.myfishy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import com.example.android.myfishy.repo.HealthyRepository;
import com.example.android.myfishy.ui.create_meal.CreateMealActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private static String currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setBackground(null);
        navView.getMenu().getItem(2).setEnabled(false);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.activity_main_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUserMeal();
            }
        });
    }

    public static String getCurrUser() {
        return currUser;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE
                );
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void createUserMeal() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                MainActivity.this, R.style.BottomSheetDialogTheme
        );
        final View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(
                        R.layout.fragment_add_quick_meal,
                        (LinearLayout) findViewById(R.id.bottom_sheet_container_quick)
                );
        bottomSheetView.findViewById(R.id.textView_bottomSheet_breakfast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateMealActivity.class);
                intent.putExtra(HealthyRepository.QUICK_ADD_MEAL_TAG, HealthyRepository.MEAL_TYPE_BREAKFAST);
                startActivity(intent);
                bottomSheetDialog.cancel();
            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    public void createCustomMeal(View view) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                view.getContext(), R.style.BottomSheetDialogTheme
        );
        View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(
                        R.layout.fragment_add_custom_meal,
                        (LinearLayout) findViewById(R.id.bottom_sheet_container_custom)
                );
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}