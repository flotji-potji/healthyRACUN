package com.example.android.myfishy.ui.nutrition_alarm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NutritionAlarmViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NutritionAlarmViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}