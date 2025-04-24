package com.example.lutemongame.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lutemongame.BattleField;
import com.example.lutemongame.Home;
import com.example.lutemongame.Lutemon;
import com.example.lutemongame.MainMenuActivity;
import com.example.lutemongame.R;
import com.example.lutemongame.TrainingArea;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsColorDistributionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsColorDistributionFragment extends Fragment {
    private ArrayList<Lutemon> allTheLutemons = new ArrayList<>();
    private PieChart stats;
    private Button backToMenu;
    private TextView headline;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats_color_distribution, container, false);
        stats = view.findViewById(R.id.pieChart);
        backToMenu = view.findViewById(R.id.back2Menu);
        headline = view.findViewById(R.id.txtHeadline);
        pieChart();

        backToMenu.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainMenuActivity.class);
            startActivity(intent);
        });
        return view;
    }

    public void pieChart() {
        int totalWhite = 0;
        int totalBlack = 0;
        int totalGreen = 0;
        int totalPink = 0;
        int totalOrange = 0;
        allTheLutemons.addAll(Home.getInstance().getLutemons());
        allTheLutemons.addAll(TrainingArea.getInstance().getLutemons());
        allTheLutemons.addAll(BattleField.getInstance().getLutemons());

        for (Lutemon lutemon : allTheLutemons) {
            if (lutemon.getColor().equals("White")) {
                totalWhite += 1;
            } else if (lutemon.getColor().equals("Black")) {
                totalBlack += 1;
            } else if (lutemon.getColor().equals("Green")) {
                totalGreen += 1;
            } else if (lutemon.getColor().equals("Pink")) {
                totalPink += 1;
            } else {
                totalOrange += 1;
            }
        }
        ArrayList<Integer> colors = new ArrayList<>();


        ArrayList<PieEntry> pieChart = new ArrayList<>();
        if (totalWhite >0) {
            pieChart.add(new PieEntry(totalWhite, "White"));
            colors.add(Color.parseColor("#FFFFFF")); // Colour red

        }
        if (totalBlack >0) {
            pieChart.add(new PieEntry(totalBlack, "Black"));
            // Adjust the colors to the right entries. I used ChatGPT to help me with this.
            colors.add(Color.parseColor("#000000")); // Colour black
        }
        if (totalGreen >0) {
            pieChart.add(new PieEntry(totalGreen, "Green"));
            colors.add(Color.parseColor("#008000")); // Colour green
        }
        if (totalPink >0) {
            pieChart.add(new PieEntry(totalPink, "Pink"));
            colors.add(Color.parseColor("#FFC0CB")); // Colour Pink
        }
        if (totalOrange >0) {
            pieChart.add(new PieEntry(totalOrange, "Orange"));
            colors.add(Color.parseColor("#FFA500")); // Colour Orange
        }

        if(pieChart.isEmpty()) {
            stats.setNoDataText("No chart data available.");
            stats.invalidate();
            headline.setText("No Lutemons to display");

        } else {
            PieDataSet dataSet = new PieDataSet(pieChart, "Lutemon Colours");


            dataSet.setColors(colors);
            dataSet.setValueTextSize(20f);

            PieData data = new PieData(dataSet);

            stats.setData(data);
            stats.getDescription().setEnabled(false);
            stats.setUsePercentValues(true);
            stats.invalidate();
            headline.setText("This chart displays the percentage \n distribution of Lutemon colors. ");
        }

    }
}