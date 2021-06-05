package com.example.android.myfishy.ui.create_meal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.android.myfishy.MainActivity;
import com.example.android.myfishy.R;
import com.example.android.myfishy.ui.add_nourishment.AddNourishmentFragment;
import com.example.android.myfishy.utilities.OnCloseFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreateMealActivity extends AppCompatActivity implements OnCloseFragment {

    private FragmentManager fragmentManager;
    private Fragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);

        FloatingActionButton fab = findViewById(R.id.activity_create_meal_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
        fragmentManager = getSupportFragmentManager();
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

    @Override
    public void closeFragment(Bundle bundle) {
        MainActivity.hideKeyboard(this);
        fragmentManager.beginTransaction().remove(fragment).commit();
        Toast.makeText(
                this,
                bundle.getString(AddNourishmentFragment.ADD_NOURISHMENT_FRAGMENT_TAG),
                Toast.LENGTH_SHORT).show();
    }
}