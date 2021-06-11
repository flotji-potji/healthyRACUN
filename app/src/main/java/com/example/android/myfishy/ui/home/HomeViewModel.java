package com.example.android.myfishy.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<String>> mList;

    public HomeViewModel() {
        mList = new MutableLiveData<>();
        List<String> ml = new ArrayList<String>();
        ml.add(Integer.toString(7));
        ml.add(Integer.toString(25));
        ml.add(Integer.toString(15));
        ml.add(Integer.toString(3));
        ml.add(Integer.toString(50));
        mList.setValue(ml);
    }

    public LiveData<List<String>> getText() {
        return mList;
    }
}

/*      Commented out Placeholder-Code
        private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

 */