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
 * Use the {@link TrainingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrainingFragment extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        rv = view.findViewById(R.id.ListRV2);
        addressGroup = view.findViewById(R.id.AddressRG2);
        transferBtn = view.findViewById(R.id.TransferBtn2);
        backToMenu = view.findViewById(R.id.BackBtn2);
        message = view.findViewById(R.id.Message2);

        adapter = new FragmentAdapter(getContext(), TrainingArea.getInstance().getLutemons());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        backToMenu.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainMenuActivity.class);
            startActivity(intent);
        });

        transferBtn.setOnClickListener(v -> {
            message.setText("");
            ArrayList<Lutemon> selectedLutemons = adapter.getSelected();
            int selectedAddressId = addressGroup.getCheckedRadioButtonId();

            if (selectedLutemons.isEmpty() || selectedAddressId == -1) {
                message.setBackgroundColor(Color.parseColor("#6FDE2D2D"));
                message.setText("Please select a Lutemon and a destination before transferring.");
                return;
            }
            message.setBackgroundColor(Color.parseColor("#6F14A205"));
            for (Lutemon lutemon : selectedLutemons) {
                TrainingArea.getInstance().removeLutemon(lutemon);
                if (selectedAddressId == R.id.HomeRb2) {
                    Home.getInstance().addLutemon(lutemon);
                    message.setText("Lutemon(s) sent home to rest");
                } else {
                    BattleField.getInstance().addLutemon(lutemon);
                    message.setText("Lutemon(s) sent to the battlefield. Let the battle begin!");
                }
            }
            adapter.setLutemons(TrainingArea.getInstance().getLutemons());
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
            adapter.setLutemons(TrainingArea.getInstance().getLutemons());
            adapter.notifyDataSetChanged();
        }

    }
}