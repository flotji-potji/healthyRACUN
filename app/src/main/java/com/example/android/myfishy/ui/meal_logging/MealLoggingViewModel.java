package com.example.android.myfishy.ui.meal_logging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MealLoggingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MealLoggingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}