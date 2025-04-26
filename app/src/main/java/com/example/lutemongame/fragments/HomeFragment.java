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

import com.example.lutemongame.BattleArenaAdapter;
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
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private RadioGroup addressGroup;
    private FragmentAdapter adapter;
    private RecyclerView rv;
    private Button transferBtn, backToMenu;
    private TextView message;

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rv = view.findViewById(R.id.ListRV);
        addressGroup = view.findViewById(R.id.AddressRG);
        transferBtn = view.findViewById(R.id.TransferBtn);
        backToMenu = view.findViewById(R.id.BackBtn);
        message = view.findViewById(R.id.Message);
        adapter = new FragmentAdapter(getContext(), Home.getInstance().getLutemons());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        backToMenu.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainMenuActivity.class);
            startActivity(intent);
        });

        transferBtn.setOnClickListener(v -> {
            message.setText("");

            int selectedAddressId = addressGroup.getCheckedRadioButtonId();

            ArrayList<Lutemon> selectedLutemons = adapter.getSelected();

            if (selectedLutemons.isEmpty() || (selectedAddressId == -1)) {
                message.setBackgroundColor(Color.parseColor("#6FDE2D2D"));
                message.setText("Please select atleast one Lutemon and a destination before transferring.");
                return;
            }

            for (Lutemon lutemon : selectedLutemons) {
                Home.getInstance().removeLutemon(lutemon);
                message.setBackgroundColor(Color.parseColor("#6F14A205"));
                if (selectedAddressId == R.id.TrainingRb) {
                    TrainingArea.getInstance().addLutemon(lutemon);
                    lutemon.increaseExperience();
                    message.setText("Lutemon(s) sent to training ground and are training hard to get stronger!");
                } else {
                    BattleField.getInstance().addLutemon(lutemon);
                    message.setText("Lutemon(s) sent to the battlefield. Let the battle begin!");
                }

            }
            adapter.setLutemons(Home.getInstance().getLutemons());
            adapter.notifyDataSetChanged();
            adapter.clearList();

        });

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        message.setText("");
        if (adapter != null) {
            adapter.setLutemons(Home.getInstance().getLutemons());
            adapter.notifyDataSetChanged();

        }
    } // Updates the Fragment when the user switches between them

}
