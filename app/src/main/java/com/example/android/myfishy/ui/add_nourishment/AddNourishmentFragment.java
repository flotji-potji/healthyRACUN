package com.example.android.myfishy.ui.add_nourishment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.myfishy.R;
import com.example.android.myfishy.db.entities.NutritionFactTable;
import com.example.android.myfishy.repo.HealthyRepository;
import com.example.android.myfishy.utilities.OnCloseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddNourishmentFragment extends Fragment implements NourishmentListAdapter.OnNutritionListener {

    public static final String ADD_NOURISHMENT_FRAGMENT_TAG =
            HealthyRepository.buildTag(HealthyRepository.ADD_NOURISHMENT_TAG, HealthyRepository.FRAGMENT_TAG);

    private AddNourishmentViewModel addNourishmentViewModel;
    private View root;
    private EditText searchBar;
    private List<String> nutritionList;
    private List<String> currWords;
    private List<NutritionFactTable> nutritionFactTableList;
    private NourishmentListAdapter nourishmentListAdapter;
    private OnCloseFragment closeFragment;
    private Bundle extrasBundle;

    public AddNourishmentFragment(OnCloseFragment onCloseFragment) {
        closeFragment = onCloseFragment;
        extrasBundle = new Bundle();
    }

    public static AddNourishmentFragment newInstance(OnCloseFragment onCloseFragment) {
        return new AddNourishmentFragment(onCloseFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addNourishmentViewModel =
                new ViewModelProvider(this).get(AddNourishmentViewModel.class);
        root = inflater.inflate(R.layout.fragment_add_nourishment, container, false);

        currWords = new ArrayList<>();

        RecyclerView recyclerView = root.findViewById(R.id.add_nourishment_recyclerview);
        nourishmentListAdapter = new NourishmentListAdapter(root.getContext(), this);
        recyclerView.setAdapter(nourishmentListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        addNourishmentViewModel.getNourishmentNamesFromNutritionFactTable().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                nutritionList = strings;
            }
        });

        addNourishmentViewModel.getNutritionFactTable().observe(this, new Observer<List<NutritionFactTable>>() {
            @Override
            public void onChanged(List<NutritionFactTable> nutritionFactTables) {
                nutritionFactTableList = nutritionFactTables;
            }
        });

        searchBar = (EditText) root.findViewById(R.id.editText_recyclerView_add_nourishment);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currWords.clear();
                for (String item : nutritionList) {
                    if ((item.toLowerCase(Locale.ROOT).contains(s) || item.contains(s)) && s != "") {
                        currWords.add(item);
                    }
                }
                nourishmentListAdapter.setNutritionNames(currWords);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return root;
    }

    @Override
    public void onNutritionListener(int position) {
        if (nutritionList != null) {
            NutritionFactTable currNourishment =
                    addNourishmentViewModel.getMatchingNutritionFactTable(
                            nutritionFactTableList,
                            currWords.get(position)
                    );
            extrasBundle.putString(ADD_NOURISHMENT_FRAGMENT_TAG,
                    currNourishment.getNourishment_name());
            closeFragment.closeFragment(extrasBundle);
        }
        nourishmentListAdapter.notifyDataSetChanged();
    }
}