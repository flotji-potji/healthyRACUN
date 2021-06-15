package com.example.android.myfishy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.android.myfishy.ui.nutrition.NutritionFragment;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MaxActivity extends AppCompatActivity {

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max);

        pieChart = findViewById(R.id.piechart);
        setupPieChart();
        loadPieChart();

        CardView nutCard = findViewById(R.id.nutritioncard);

        nutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getNut = new Intent(getApplicationContext(), NutritionFragment.class);
                startActivity(getNut);
            }
        });
    }


    private void setupPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
//        pieChart.setCenterText("Data");
//        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }
    private void loadPieChart(){

        ArrayList<PieEntry> entries = new ArrayList<>();


        //value: daten die mitgegeben werden, um anzuzeigen wv der nährstoffe eingenommen wurdem
        entries.add(new PieEntry(0.4f, "Potassium"));
        entries.add(new PieEntry(0.3f, "Iron"));
        entries.add(new PieEntry(0.1f, "Kalium"));
        entries.add(new PieEntry(0.2f, "Calcium"));

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



}