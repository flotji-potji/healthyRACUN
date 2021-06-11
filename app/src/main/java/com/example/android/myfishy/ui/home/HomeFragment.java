package com.example.android.myfishy.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.android.myfishy.R;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView tvPotassium = root.findViewById(R.id.tvPotassium);
        final TextView tvSodium = root.findViewById(R.id.tvSodium);
        final TextView tvPhosphate = root.findViewById(R.id.tvPhosphate);
        final TextView tvCalcium = root.findViewById(R.id.tvCalcium);
        final TextView tvWater = root.findViewById(R.id.tvWater);
        final PieChart pieChart = root.findViewById(R.id.piechart);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> ml) {
                assert ml != null;
                tvPotassium.setText(ml.get(0));
                tvSodium.setText(ml.get(1));
                tvPhosphate.setText(ml.get(2));
                tvCalcium.setText(ml.get(3));
                tvWater.setText(ml.get(4));

                pieChart.addPieSlice(
                        new PieModel(
                                "",
                                Integer.parseInt(tvPotassium.getText().toString()),
                                Color.parseColor("#" + Integer.toHexString(getContext().getColor(R.color.blue_gray_50)))));
                pieChart.addPieSlice(
                        new PieModel(
                                "",
                                Integer.parseInt(tvSodium.getText().toString()),
                                Color.parseColor("#" + Integer.toHexString(getContext().getColor(R.color.blue_gray_100)))));
                pieChart.addPieSlice(
                        new PieModel(
                                "",
                                Integer.parseInt(tvPhosphate.getText().toString()),
                                Color.parseColor("#" + Integer.toHexString(getContext().getColor(R.color.blue_gray_200)))));
                pieChart.addPieSlice(
                        new PieModel(
                                "",
                                Integer.parseInt(tvCalcium.getText().toString()),
                                Color.parseColor("#" + Integer.toHexString(getContext().getColor(R.color.blue_gray_300)))));
                pieChart.addPieSlice(
                        new PieModel(
                                "",
                                Integer.parseInt(tvWater.getText().toString()),
                                Color.parseColor("#" + Integer.toHexString(getContext().getColor(R.color.blue_gray_400)))
                        )
                );

                // To animate the pie chart
                pieChart.startAnimation();
            }
        });

        return root;
    }
}