package com.example.lutemongame.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lutemongame.BattleField;
import com.example.lutemongame.FragmentAdapter;
import com.example.lutemongame.Home;
import com.example.lutemongame.Lutemon;
import com.example.lutemongame.MainMenuActivity;
import com.example.lutemongame.R;
import com.example.lutemongame.TrainingArea;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BattleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BattleFragment extends Fragment {
    private RadioGroup addressGroup;
    private FragmentAdapter adapter;
    private Button transferBtn, backToMenu;
    private TextView message;
    private RecyclerView rv;

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
        View view = inflater.inflate(R.layout.fragment_battle, container, false);
        addressGroup = view.findViewById(R.id.AddressRG3);
        rv = view.findViewById(R.id.ListRV3);
        transferBtn = view.findViewById(R.id.TransferBtn3);
        backToMenu = view.findViewById(R.id.BackBtn3);
        message = view.findViewById(R.id.Message3);


        adapter = new FragmentAdapter(getContext(), BattleField.getInstance().getLutemons());
        ArrayList<Lutemon> selectedLutemons = adapter.getSelected();
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        backToMenu.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainMenuActivity.class);
            startActivity(intent);
        });

        transferBtn.setOnClickListener(v -> {
            int selectedAddressId = addressGroup.getCheckedRadioButtonId();

            if (selectedLutemons.isEmpty() || selectedAddressId == -1) {
                message.setBackgroundColor(Color.parseColor("#6FDE2D2D"));
                message.setText("Please select a Lutemon and a destination before transferring.");
                return;
            }

            for (Lutemon lutemon : selectedLutemons) {
                BattleField.getInstance().removeLutemon(lutemon);
                message.setBackgroundColor(Color.parseColor("#6F14A205"));
                if (selectedAddressId == R.id.HomeRb3) {
                    Home.getInstance().addLutemon(lutemon);
                    message.setText("Lutemon(s) sent home to rest.");
                } else {
                    TrainingArea.getInstance().addLutemon(lutemon);
                    message.setText("Lutemon(s) sent to training ground and are training hard to get stronger!");
                }
            }
            adapter.setLutemons(BattleField.getInstance().getLutemons());
            adapter.clearList();
            adapter.notifyDataSetChanged();

        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        message.setText("");
        if (adapter != null) {
            adapter.setLutemons(BattleField.getInstance().getLutemons());
            adapter.notifyDataSetChanged();
        }
    }
}