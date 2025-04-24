package com.example.lutemongame.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lutemongame.BattleField;
import com.example.lutemongame.Home;
import com.example.lutemongame.Lutemon;
import com.example.lutemongame.MainMenuActivity;
import com.example.lutemongame.R;
import com.example.lutemongame.TrainingArea;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.jar.Attributes;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsAttributeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsAttributeFragment extends Fragment {
    private Spinner lutemonSpinner;
    private BarChart stats;
    private Button backToMenu;
    private ArrayList<Lutemon> allTheLutemons = new ArrayList<>();
    private TextView selectLutemonText;



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
        View view = inflater.inflate(R.layout.fragment_stats_attribute, container, false);


        lutemonSpinner = view.findViewById(R.id.Spinner);
        stats = view.findViewById(R.id.barChart);
        backToMenu = view.findViewById(R.id.Back);
        selectLutemonText = view.findViewById(R.id.selectLutemonText);

        spinner();

        backToMenu.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainMenuActivity.class);
            startActivity(intent);
        });

        return view;
    }

    public void spinner() {
        allTheLutemons.addAll(Home.getInstance().getLutemons());
        allTheLutemons.addAll(TrainingArea.getInstance().getLutemons());
        allTheLutemons.addAll(BattleField.getInstance().getLutemons());

        if (allTheLutemons.isEmpty()) {
            selectLutemonText.setText("No Lutemons to select from");
        } else {
            // How the users sees the Lutemons in Spinner
            ArrayList<String> lutemonNames = new ArrayList<>();
            for(Lutemon lutemon : allTheLutemons) {
                lutemonNames.add(lutemon.getName() + " (" + lutemon.getColor() + ")");
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, lutemonNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            lutemonSpinner.setAdapter(adapter);

            lutemonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Lutemon selectedLutemon = allTheLutemons.get(position);
                    showBarChart(selectedLutemon);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }

    }

    public void showBarChart(Lutemon lutemon) {
        // Every BarEntry is a new bar
        ArrayList<BarEntry> bars = new ArrayList<>();
        if (lutemon.getMaxHealth() > 0) {
            bars.add(new BarEntry(0, lutemon.getMaxHealth()));
        }
        if (lutemon.getDefense() > 0) {
            bars.add(new BarEntry(1, lutemon.getDefense()));        }
        if (lutemon.getAttack() > 0) {
            bars.add(new BarEntry(2, lutemon.getAttack()));        }

        if (bars.isEmpty()) {
            selectLutemonText.setText("");

        } else {
            // Collection of bars and label for it
            BarDataSet dataSet = new BarDataSet(bars, "Attributes");
            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            dataSet.setValueTextSize(20f);


            // BarChart will show the data
            BarData data = new BarData(dataSet);
            data.setBarWidth(0.7f);
            stats.setData(data);
            stats.setFitBars(true); // This will adjust the bars to fit well in the view

            String[] labels = {"Health","Defence","Attack"};

            XAxis xAxis = stats.getXAxis(); // This will let me change the xAxis "names"
            xAxis.setValueFormatter(new IndexAxisValueFormatter(labels)); // "Health","Defence","Attack"
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setGranularity(1f);
            xAxis.setGranularityEnabled(true);
            xAxis.setDrawGridLines(false);
            stats.getAxisRight().setEnabled(false);
            stats.getDescription().setEnabled(false);
            stats.invalidate();
            selectLutemonText.setText("Select a Lutemon to view its attributes");

        }
    }
}