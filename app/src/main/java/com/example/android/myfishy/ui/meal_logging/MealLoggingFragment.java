package com.example.android.myfishy.ui.meal_logging;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.myfishy.R;
import com.example.android.myfishy.repo.HealthyRepository;

import java.util.List;
import java.util.Objects;

public class MealLoggingFragment extends Fragment {

    private final String MEAL_LOGGING_FRAGMENT_TAG =
            HealthyRepository.buildTag(HealthyRepository.MEAL_LOGGING_TAG, HealthyRepository.FRAGMENT_TAG);
    private MealLoggingViewModel mealLoggingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mealLoggingViewModel =
                new ViewModelProvider(this).get(MealLoggingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_meal_logging, container, false);

        return root;
    }
}