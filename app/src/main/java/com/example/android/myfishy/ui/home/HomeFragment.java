package com.example.android.myfishy.ui.home;

import android.graphics.Color;
import android.graphics.Paint;
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
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private HorizontalBarChart barChart;
    private PieChart pieChart;
    private PieChart pieChartUser;


    private void setBarChart()
    {

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 1800f));
        BarDataSet set = new BarDataSet(entries, "");
        BarData data = new BarData(set);


        data.setBarWidth(0.5f); // set custom bar width
        set.setDrawValues(false);

        String[] yAxisLables = new String[]{"0","1", "2", "3"};




        barChart.getAxisRight().setAxisMinimum(0f);
        barChart.getAxisLeft().setAxisMinimum(0f);
        barChart.getAxisRight().setAxisMaximum(6000f);
        barChart.getAxisLeft().setAxisMaximum(6000f);
        barChart.getAxisLeft().setEnabled(false);
        barChart.animateY(1100);
        barChart.getAxisRight().setLabelCount(9, true);
        barChart.getAxisLeft().setLabelCount(9,true);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getXAxis().setEnabled(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisRight().setDrawAxisLine(false);
        barChart.getLegend().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setDrawAxisLine(false);
        barChart.getAxisLeft().setDrawTopYLabelEntry(false);
        barChart.getAxisLeft().setDrawTopYLabelEntry(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setDrawZeroLine(false);
        barChart.getAxisRight().setDrawLimitLinesBehindData(false);
        barChart.getAxisRight().setDrawTopYLabelEntry(false);

        //no zoom
        barChart.setPinchZoom(false);
        barChart.setTouchEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);

        barChart.setData(data);
        barChart.invalidate(); // refresh
    }

    private void setupPieChart(){

        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getLegend().setEnabled(false);
        pieChart.getDescription().setEnabled(true);
        pieChart.getDescription().setText("Recommended");
        pieChart.getDescription().setTextSize(16f);
        pieChart.setExtraBottomOffset(10f);
        pieChart.getDescription().setPosition(380f, 505f);



        pieChartUser.setDrawHoleEnabled(true);
        pieChartUser.setUsePercentValues(true);
        pieChartUser.setEntryLabelTextSize(12);
        pieChartUser.setEntryLabelColor(Color.BLACK);
        pieChartUser.getLegend().setEnabled(false);
        pieChartUser.getDescription().setEnabled(true);
        pieChartUser.getDescription().setText("Actual");
        pieChartUser.getDescription().setTextSize(16f);
        pieChartUser.setExtraBottomOffset(10f);
        pieChartUser.getDescription().setPosition(300f, 505f);

//        Legend l = pieChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(false);
//        l.setEnabled(true);
    }


    private void loadPieChart()
    {
        ArrayList<PieEntry> entries = new ArrayList<>();


        //value: daten die mitgegeben werden, um anzuzeigen wv der nährstoffe eingenommen wurdem
        entries.add(new PieEntry(0.55f, "Carbs"));
        entries.add(new PieEntry(0.2f, "Proteins"));
        entries.add(new PieEntry(0.25f, "Fats"));

        //colors liste wurde erstellt um die entries in farben einzukategorisieren.
        ArrayList<Integer> colors = new ArrayList<>();
        for(int color: ColorTemplate.MATERIAL_COLORS)
        {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Nutritions:");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.animateY(600, Easing.EaseInOutQuad);

    }

    private void loadPieChartUser()
    {
        ArrayList<PieEntry> entries = new ArrayList<>();



        //value: daten die mitgegeben werden, um anzuzeigen wv der nährstoffe eingenommen wurdem
        entries.add(new PieEntry(0.55f, "Carbs"));
        entries.add(new PieEntry(0.2f, "Proteins"));
        entries.add(new PieEntry(0.25f, "Fats"));

        //colors liste wurde erstellt um die entries in farben einzukategorisieren.
        ArrayList<Integer> colors = new ArrayList<>();
        for(int color: ColorTemplate.MATERIAL_COLORS)
        {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Nutritions:");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChartUser));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChartUser.setData(data);
        pieChartUser.invalidate();
        pieChartUser.animateY(600, Easing.EaseInOutQuad);

    }

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

        barChart = root.findViewById(R.id.barchart_natrium);
        pieChart = root.findViewById(R.id.piechart_standard);
        pieChartUser = root.findViewById(R.id.piechart_user);

        setBarChart();
        setupPieChart();
        loadPieChartUser();
        loadPieChart();

        return root;
    }
}