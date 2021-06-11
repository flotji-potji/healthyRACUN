package com.example.android.myfishy.ui.add_nourishment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
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
import java.util.Set;

public class AddNourishmentFragment extends Fragment implements NourishmentListAdapter.OnNutritionListener {

    public static final String ADD_NOURISHMENT_FRAGMENT_TAG =
            HealthyRepository.buildTag(HealthyRepository.ADD_NOURISHMENT_TAG, HealthyRepository.FRAGMENT_TAG);

    private AddNourishmentViewModel addNourishmentViewModel;

    private View root;
    private EditText searchBar;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private List<String> nutritionList;
    private List<String> currNutritionList;
    private List<String> alreadySelectedNutritionList;
    private static List<NutritionFactTable> nutritionFactTableList;
    private NourishmentListAdapter nourishmentListAdapter;
    private OnCloseFragment closeFragment;
    private List<NutritionFactTable> extrasBundle;
    private Button submitButton;

    public AddNourishmentFragment(
            OnCloseFragment onCloseFragment, List<String> selectedNutritionList) {
        closeFragment = onCloseFragment;
        nutritionFactTableList = new ArrayList<>();
        extrasBundle = new ArrayList<>();
        alreadySelectedNutritionList = selectedNutritionList;
    }

    public static AddNourishmentFragment newInstance(
            OnCloseFragment onCloseFragment, List<String> selectedNutritionList) {
        return new AddNourishmentFragment(onCloseFragment, selectedNutritionList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addNourishmentViewModel =
                new ViewModelProvider(this).get(AddNourishmentViewModel.class);
        root = inflater.inflate(R.layout.fragment_add_nourishment, container, false);

        currNutritionList = new ArrayList<>();
        searchBar = root.findViewById(R.id.editText_recyclerView_add_nourishment);
        progressBar = root.findViewById(R.id.progressBar_addNourishment);
        showProgressBar();

        recyclerView = root.findViewById(R.id.add_nourishment_recyclerview);
        nourishmentListAdapter = new NourishmentListAdapter(root.getContext(), this);
        recyclerView.setAdapter(nourishmentListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        addNourishmentViewModel.getNutritionFactTable().observe(this, new Observer<List<NutritionFactTable>>() {
            @Override
            public void onChanged(List<NutritionFactTable> nutritionFactTables) {
                nutritionFactTableList = nutritionFactTables;
                deleteProgressBarForList();
            }
        });

        addNourishmentViewModel.getNourishmentNamesFromNutritionFactTable().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                nutritionList = strings;
                deleteProgressBar();
                showProgressBarForList();
            }
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currNutritionList.clear();
                for (String item : nutritionList) {
                    if (s != null && item != null) {
                        if (s != "") {
                            if (!alreadySelectedNutritionList.contains(item)) {
                                if (item.toLowerCase().contains(s) || item.contains(s)) {
                                    currNutritionList.add(item);
                                }
                            }
                        }
                    }
                }
                nourishmentListAdapter.setNutritionNames(currNutritionList);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        submitButton = root.findViewById(R.id.button_add_nourishment);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment.closeFragment(extrasBundle);
            }
        });

        return root;
    }

    private void showProgressBar() {
        searchBar.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void deleteProgressBar() {
        searchBar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private void showProgressBarForList() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void deleteProgressBarForList() {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private boolean isButtonVisible() {
        return submitButton.getVisibility() == View.VISIBLE;
    }

    private void setButtonVisible() {
        submitButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNutritionListener(final int position) {
        if (nutritionList != null) {
            final NutritionFactTable currNourishment =
                    getMatchingNutritionFactTable(
                            currNutritionList.get(position)
                    );
            AlertDialog.Builder quantityBuilder = new AlertDialog.Builder(root.getContext());
            if (currNourishment.getNourishment_category().contains("Getränk") ||
                    currNourishment.getNourishment_category().contains("Öl")) {
                quantityBuilder.setTitle(R.string.alert_title_quantity);
            } else {
                quantityBuilder.setTitle(R.string.alert_title_mass);
            }
            quantityBuilder
                    .setNegativeButton(
                            getString(R.string.alert_label_cancel),
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                    .setPositiveButton(
                            getString(R.string.alert_label_ok),
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (addNourishmentViewModel.checkDietaryRestriction(currNourishment)) {
                                        extrasBundle.add(currNourishment);
                                        alreadySelectedNutritionList.add(
                                                currNutritionList.get(position)
                                        );
                                        if (!isButtonVisible()) {
                                            setButtonVisible();
                                        }
                                    } else {
                                        AlertDialog.Builder attentionBuilder = new AlertDialog.Builder(root.getContext());
                                        attentionBuilder
                                                .setTitle("NICHT GUT!")
                                                .setPositiveButton(R.string.alert_label_ok, new DialogInterface.OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.cancel();
                                                    }
                                                })
                                        ;
                                        attentionBuilder.show();
                                    }
                                }
                            }
                    );
            quantityBuilder.show();
        }
        nourishmentListAdapter.notifyDataSetChanged();
    }

    public NutritionFactTable getMatchingNutritionFactTable(
            @NonNull String match) {

        for (NutritionFactTable item : nutritionFactTableList) {
            if (item.getNourishment_name().equals(match)) {
                return item;
            }
        }
        return null;
    }
}