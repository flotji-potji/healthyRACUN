package com.example.android.myfishy.ui.add_nourishment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.myfishy.R;
import com.example.android.myfishy.ui.meal_logging.MealLoggingViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddNourishmentFragment extends Fragment {

    private AddNourishmentViewModel addNourishmentViewModel;
    private View root;
    private EditText searchBar;
    private List<String> wordList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addNourishmentViewModel =
                new ViewModelProvider(this).get(AddNourishmentViewModel.class);
        root = inflater.inflate(R.layout.fragment_add_nourishment, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.add_nourishment_recyclerview);
        final WordListAdapter adapter = new WordListAdapter(root.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        addNourishmentViewModel.getNourishmentNamesFromNutritionFactTable().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                wordList = strings;
            }
        });
        searchBar = (EditText) root.findViewById(R.id.editText_recyclerView_add_nourishment);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> currWords = new ArrayList<>();
                for (String item : wordList) {
                    if ((item.toLowerCase(Locale.ROOT).contains(s) || item.contains(s)) && s != "") {
                        currWords.add(item);
                    }
                }
                adapter.setWords(currWords);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return root;
    }
}