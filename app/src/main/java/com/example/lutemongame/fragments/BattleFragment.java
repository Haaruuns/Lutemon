package com.example.lutemongame.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lutemongame.BattleField;
import com.example.lutemongame.Home;
import com.example.lutemongame.Lutemon;
import com.example.lutemongame.MainMenuActivity;
import com.example.lutemongame.R;
import com.example.lutemongame.TrainingArea;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BattleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BattleFragment extends Fragment {
    private RadioGroup lutemonListGroup, addressGroup;
    private Button transferBtn, backToMenu;
    private TextView errorMessage, successMessage;

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
        lutemonListGroup = view.findViewById(R.id.ListLutemonsRG3);
        addressGroup = view.findViewById(R.id.AddressRG3);
        transferBtn = view.findViewById(R.id.TransferBtn3);
        backToMenu = view.findViewById(R.id.BackBtn3);
        errorMessage = view.findViewById(R.id.Fail4);
        successMessage = view.findViewById(R.id.Success4);

        clearTheList();

        backToMenu.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainMenuActivity.class);
            startActivity(intent);
        });

        transferBtn.setOnClickListener(v -> {
            int selectedLutemonId = lutemonListGroup.getCheckedRadioButtonId();
            int selectedAddressId = addressGroup.getCheckedRadioButtonId();

            if (selectedLutemonId == -1 && selectedAddressId == -1) {
                errorMessage.setText("Please select a Lutemon and a destination before transferring.");
                return;
            }

            RadioButton selectedLutemon = view.findViewById(selectedLutemonId);
            if (selectedLutemon == null) {
                errorMessage.setText("Please select a Lutemon and a destination before transferring.");
                return;
            }

            int lutemonId = (int) selectedLutemon.getTag();

            Lutemon lutemon = BattleField.getInstance().getLutemonWithId(lutemonId);

            if (lutemon != null ) {
                BattleField.getInstance().removeLutemon(lutemon);

                if(selectedAddressId == R.id.HomeRb2) {
                    Home.getInstance().addLutemon(lutemon);
                    successMessage.setText(lutemon.getName() + " has returned home to rest.");
                } else {
                    TrainingArea.getInstance().addLutemon(lutemon);
                    lutemon.increaseExperience();
                    successMessage.setText(lutemon.getName() + " is now training hard to get stronger!");
                }
            }
            clearTheList();
        });

        return view;
    }
    public void clearTheList() {
        lutemonListGroup.removeAllViews();
        for (Lutemon lutemon : BattleField.getInstance().getLutemons()) {
            RadioButton radioButton = new RadioButton(requireContext());
            radioButton.setText(lutemon.getName() + " (" + lutemon.getColor() + ")");
            radioButton.setId(View.generateViewId());
            radioButton.setTag(lutemon.getId());
            lutemonListGroup.addView(radioButton);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        clearTheList();
    }
}