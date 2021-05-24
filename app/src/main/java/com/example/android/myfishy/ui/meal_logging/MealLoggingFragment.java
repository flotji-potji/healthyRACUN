package com.example.android.myfishy.ui.meal_logging;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.android.myfishy.R;
import com.example.android.myfishy.db.entities.Meal;
import com.example.android.myfishy.db.relations.UserEatsMeals;
import com.example.android.myfishy.repo.HealthyRepository;

import java.util.ArrayList;
import java.util.List;

public class MealLoggingFragment extends Fragment {

    // ---- Class tag for logging purposes ---- //
    private final String MEAL_LOGGING_FRAGMENT_TAG =
            HealthyRepository.buildTag(HealthyRepository.MEAL_LOGGING_TAG, HealthyRepository.FRAGMENT_TAG);
    private final int NEGATIVE = 0;
    private final int POSITIVE = 1;

    // ---- Repository class for handling data //
    private MealLoggingViewModel mealLoggingViewModel;
    private View root;
    private List<UserEatsMeals> userMealTable;
    private List<Meal> breakfastMeals;
    private List<Meal> lunchMeals;
    private List<Meal> dinnerMeals;

    // ---- View objects to be muted -------- //
    private ImageView imageViewCardViewBreakfast;
    private ImageView imageViewCardViewLunch;
    private ImageView imageViewCardViewDinner;
    private TextView textViewCardViewTitleBreakfast;
    private TextView textViewCardViewTitleLunch;
    private TextView textViewCardViewTitleDinner;
    private List<TextView> mealTextViewTitleBundle;
    private TextView textViewCardViewSubTitleBreakfast;
    private TextView textViewCardViewSubTitleLunch;
    private TextView textViewCardViewSubTitleDinner;
    private List<TextView> mealTextViewSubTitleBundle;
    private List<TextView> breakfastTextViewMealBundle;
    private List<TextView> lunchTextViewMealBundle;
    private List<TextView> dinnerTextViewMealBundle;
    private LinearLayout linearLayoutCardViewBelowSubtitleBreakfast;
    private LinearLayout linearLayoutCardViewBelowSubtitleLunch;
    private LinearLayout linearLayoutCardViewBelowSubtitleDinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // initialisation of fragment
        mealLoggingViewModel =
                new ViewModelProvider(this).get(MealLoggingViewModel.class);
        root = inflater.inflate(R.layout.fragment_meal_logging, container, false);
        mealTextViewTitleBundle = new ArrayList<>();
        mealTextViewSubTitleBundle = new ArrayList<>();
        breakfastTextViewMealBundle = new ArrayList<>();
        breakfastMeals = new ArrayList<>();
        lunchTextViewMealBundle = new ArrayList<>();
        lunchMeals = new ArrayList<>();
        dinnerTextViewMealBundle = new ArrayList<>();
        dinnerMeals = new ArrayList<>();
        //initialiseView();

        // initialisation and allocation of view children
        linearLayoutCardViewBelowSubtitleBreakfast =
                root.findViewById(R.id.linearLayout_cardView_below_subtitle_breakfast);
        linearLayoutCardViewBelowSubtitleLunch =
                root.findViewById(R.id.linearLayout_cardView_below_subtitle_lunch);
        linearLayoutCardViewBelowSubtitleDinner =
                root.findViewById(R.id.linearLayout_cardView_below_subtitle_dinner);

        TypedArray breakfastMealListViewIds = mealLoggingViewModel.getBreakfastMealListViewIds();
        final TypedArray lunchMealListViewIds = mealLoggingViewModel.getLunchMealListViewIds();
        TypedArray dinnerMealListViewIds = mealLoggingViewModel.getDinnerMealListViewIds();
        initialiseTextViews(breakfastTextViewMealBundle, breakfastMealListViewIds);
        initialiseTextViews(lunchTextViewMealBundle, lunchMealListViewIds);
        initialiseTextViews(dinnerTextViewMealBundle, dinnerMealListViewIds);
        mealLoggingViewModel.getUserMeals().observe(getViewLifecycleOwner(), new Observer<List<UserEatsMeals>>() {
            @Override
            public void onChanged(List<UserEatsMeals> userEatsMeals) {
                userMealTable = userEatsMeals;

                breakfastMeals = mealLoggingViewModel.getUserBreakfastMeals(userMealTable);
                lunchMeals = mealLoggingViewModel.getUserLunchMeals(userMealTable);
                dinnerMeals = mealLoggingViewModel.getUserDinnerMeals(userMealTable);

                if (!breakfastMeals.isEmpty())
                    updateMealTypeListedTextViews(breakfastTextViewMealBundle, breakfastMeals);
                if (!lunchMeals.isEmpty())
                    updateMealTypeListedTextViews(lunchTextViewMealBundle, lunchMeals);
                if (!dinnerMeals.isEmpty())
                    updateMealTypeListedTextViews(dinnerTextViewMealBundle, dinnerMeals);
            }
        });

        TypedArray mealTitleListStringIds = mealLoggingViewModel.getMealTitleListStringIds();
        TypedArray mealTitleListViewIds = mealLoggingViewModel.getMealTitleListViewIds();
        initialiseTextViews(mealTextViewTitleBundle, mealTitleListViewIds);
        for (int i = 0; i < mealTitleListStringIds.length(); i++) {
            mealTextViewTitleBundle.get(i).setText(
                    root.getResources().getString((mealTitleListStringIds.getResourceId(i, 0))));
        }

        TypedArray mealSubTitleListStringIds = mealLoggingViewModel.getMealSubTitleListStringIds();
        TypedArray mealSubTitleListViewIds = mealLoggingViewModel.getMealSubTitleListViewIds();
        initialiseTextViews(mealTextViewSubTitleBundle, mealSubTitleListViewIds);
        for (int i = 0; i < mealSubTitleListStringIds.length(); i++) {
            Log.e(MEAL_LOGGING_FRAGMENT_TAG, mealTextViewSubTitleBundle.size() + "");
            if (checkIfMealIsEmpty(breakfastMeals, mealSubTitleListStringIds, i)) {
                setBreakfastToGone();
            } else {
                if (breakfastMeals.size() >= 3) {
                    setBreakfastToVisible();
                }
            }
            if (checkIfMealIsEmpty(lunchMeals, mealSubTitleListStringIds, i)) {
                setLunchToGone();
            } else {
                if (lunchMeals.size() >= 3) {
                    setLunchToVisible();
                }
            }
            if (checkIfMealIsEmpty(dinnerMeals, mealSubTitleListStringIds, i)) {
                setDinnerToGone();
            } else {
                if (dinnerMeals.size() >= 3) {
                    setDinnerToVisible();
                }
            }
        }

        breakfastMealListViewIds.recycle();
        lunchMealListViewIds.recycle();
        dinnerMealListViewIds.recycle();
        mealTitleListStringIds.recycle();
        mealTitleListViewIds.recycle();
        mealSubTitleListStringIds.recycle();
        mealSubTitleListViewIds.recycle();
        return root;
    }


    // ---- private class helper methods ---- //
    private void initialiseTextViews(List<TextView> viewList, TypedArray viewListIds) {
        TextView currTextView;
        for (int i = 0; i < viewListIds.length(); i++) {
            currTextView = (TextView) root.findViewById(viewListIds.getResourceId(i, 0));
            viewList.add(currTextView);
        }
    }

    private boolean checkIfMealIsEmpty(List<Meal> mealList, TypedArray titleStringIds, int pos) {
        if (mealList.isEmpty()) {
            mealTextViewSubTitleBundle.get(pos).setText(
                    root.getResources().getText(titleStringIds.getResourceId(NEGATIVE, 0))
            );
            return true;
        } else {
            mealTextViewSubTitleBundle.get(pos).setText(
                    root.getResources().getText(titleStringIds.getResourceId(POSITIVE, 0))
            );
            return false;
        }
    }

    private void updateMealTypeListedTextViews(List<TextView> textViews, List<Meal> mealList) {
        for (int i = 0; i < textViews.size(); i++) {
            if (i == mealList.size()) {
                break;
            }
            textViews.get(i).setText(mealList.get(mealList.size() - 1 - i).getMeal_name());
        }
    }

    private void initialiseView() {
        imageViewCardViewBreakfast = root.findViewById(R.id.button_cardView_breakfast);
        imageViewCardViewLunch = root.findViewById(R.id.button_cardView_lunch);
        imageViewCardViewDinner = root.findViewById(R.id.button_cardView_dinner);
        textViewCardViewTitleBreakfast = root.findViewById(R.id.textView_cardView_breakfast_title);
        textViewCardViewTitleLunch = root.findViewById(R.id.textView_cardView_lunch_title);
        textViewCardViewTitleDinner = root.findViewById(R.id.textView_cardView_dinner_title);
    }

    private void setBreakfastToGone() {
        linearLayoutCardViewBelowSubtitleBreakfast.setVisibility(View.GONE);
    }

    private void setBreakfastToVisible() {
        linearLayoutCardViewBelowSubtitleBreakfast.setVisibility(View.VISIBLE);
    }

    private void setLunchToGone() {
        linearLayoutCardViewBelowSubtitleLunch.setVisibility(View.GONE);
    }

    private void setLunchToVisible() {
        linearLayoutCardViewBelowSubtitleLunch.setVisibility(View.VISIBLE);
    }

    private void setDinnerToGone() {
        linearLayoutCardViewBelowSubtitleDinner.setVisibility(View.GONE);
    }

    private void setDinnerToVisible() {
        linearLayoutCardViewBelowSubtitleDinner.setVisibility(View.VISIBLE);
    }
}