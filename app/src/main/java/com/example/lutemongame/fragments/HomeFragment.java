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
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        lutemonListGroup = view.findViewById(R.id.ListLutemonsRG);
        addressGroup = view.findViewById(R.id.AddressRG);
        transferBtn = view.findViewById(R.id.TransferBtn);
        backToMenu = view.findViewById(R.id.BackBtn);
        errorMessage = view.findViewById(R.id.Fail2);
        successMessage = view.findViewById(R.id.Success2);
        clearTheList();

        backToMenu.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainMenuActivity.class);
            startActivity(intent);
        });

        transferBtn.setOnClickListener(v -> {
            successMessage.setText("");
            errorMessage.setText("");
            int selectedLutemonId = lutemonListGroup.getCheckedRadioButtonId();
            int selectedAddressId = addressGroup.getCheckedRadioButtonId();

            if (selectedLutemonId == -1 || selectedAddressId == -1) {
                errorMessage.setText("Please select a Lutemon and a destination before transferring.");
                return;
            }

            RadioButton selectedLutemon = view.findViewById(selectedLutemonId);// No need for R.id because we generated the ID manually
            if (selectedLutemon == null) {
                errorMessage.setText("Please select a Lutemon and a destination before transferring.");
                return;
            }

            int lutemonId = (int) selectedLutemon.getTag();

            Lutemon lutemon = Home.getInstance().getLutemonWithId(lutemonId);

            if (lutemon != null) {
                Home.getInstance().removeLutemon(lutemon);

                if (selectedAddressId == R.id.TrainingRb) {
                    TrainingArea.getInstance().addLutemon(lutemon);
                    successMessage.setText(lutemon.getName() + " is now training hard to get stronger!");
                } else {
                    BattleField.getInstance().addLutemon(lutemon);
                    successMessage.setText(lutemon.getName() + " was sent to the battlefield. Let the battle begin!");
                }
            }
            clearTheList();
        });

        return view;
    }

    private void clearTheList(){
        lutemonListGroup.removeAllViews();
        for (Lutemon lutemon : Home.getInstance().getLutemons()){
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
    } // Updates the Fragment when the user switches between them

}
