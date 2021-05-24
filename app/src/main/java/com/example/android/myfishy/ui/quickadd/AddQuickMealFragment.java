package com.example.android.myfishy.ui.quickadd;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.myfishy.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import org.jetbrains.annotations.NotNull;

public class AddQuickMealFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_quick_meal, container, false);

        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(root.findViewById(R.id.bottom_sheet_container));
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull @NotNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull @NotNull View bottomSheet, float slideOffset) {

            }
        });
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        return root;
    }
}