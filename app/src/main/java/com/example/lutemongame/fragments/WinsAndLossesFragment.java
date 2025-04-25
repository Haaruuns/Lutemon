package com.example.lutemongame.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WinsAndLossesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WinsAndLossesFragment extends Fragment {
    private Spinner lutemonSpinner2;
    private BarChart stats2;
    private Button backToMenu2;
    private ArrayList<Lutemon> allTheLutemons2 = new ArrayList<>();
    private ArrayList<Lutemon> battleLutemons = new ArrayList<>();
    private TextView selectLutemonText2;

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
        View view = inflater.inflate(R.layout.fragment_wins_and_losses, container, false);
        lutemonSpinner2 = view.findViewById(R.id.Spinner2);
        stats2 = view.findViewById(R.id.barChart2);
        backToMenu2 = view.findViewById(R.id.Back2);
        selectLutemonText2 = view.findViewById(R.id.selectLutemonText2);
        spinner();



        backToMenu2.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainMenuActivity.class);
            startActivity(intent);
        });
        return view;
    }

    public void spinner () {
        allTheLutemons2.addAll(Home.getInstance().getLutemons());
        allTheLutemons2.addAll(TrainingArea.getInstance().getLutemons());
        allTheLutemons2.addAll(BattleField.getInstance().getLutemons());

        for (Lutemon lutemon : allTheLutemons2) {
            int battles = lutemon.getWins() + lutemon.getLosses();
            if (battles > 0) {
                battleLutemons.add(lutemon);
            }
        }

        if (battleLutemons.isEmpty()) {
            selectLutemonText2.setText("No Lutemons to select from");
        }

        ArrayList<String> names = new ArrayList<>();
        for(Lutemon lutemon : battleLutemons) {
            names.add(lutemon.getName() + " (" + lutemon.getColor() + ")");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item,names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lutemonSpinner2.setAdapter(adapter);

        lutemonSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Lutemon selectedLutemon = battleLutemons.get(position);
                barChart(selectedLutemon);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void barChart(Lutemon lutemon) {
        int battles = lutemon.getWins() + lutemon.getLosses();

        ArrayList<BarEntry> bars = new ArrayList<>();
        if(battles > 0) {
            bars.add(new BarEntry(0,battles));
        }
        if (lutemon.getWins() > 0) {
            bars.add(new BarEntry(1,lutemon.getWins()));
        }
        if (lutemon.getLosses() > 0) {
            bars.add(new BarEntry(2,lutemon.getLosses()));
        }

        if (bars.isEmpty()) {
            selectLutemonText2.setText("");
        } else {
            BarDataSet dataSet = new BarDataSet(bars,"Stats");
            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            dataSet.setValueTextSize(20f);


            BarData data = new BarData(dataSet);
            data.setBarWidth(0.7f);
            stats2.setData(data);
            stats2.setFitBars(true);

            String[] labels = {"Battles", "Wins", "Losses"};

            XAxis xAxis = stats2.getXAxis();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setGranularity(1f);
            xAxis.setGranularityEnabled(true);
            xAxis.setDrawGridLines(false);
            stats2.getAxisRight().setEnabled(false);
            stats2.getDescription().setEnabled(false);
            stats2.invalidate();
            selectLutemonText2.setText("Select a Lutemon to view its stats");
            Log.d("BarChart", "Drawing stats for: " + lutemon.getName());
            Log.d("BarChart", "Battles: " + battles + " Wins: " + lutemon.getWins() + " Losses: " + lutemon.getLosses());

        }
    }
}