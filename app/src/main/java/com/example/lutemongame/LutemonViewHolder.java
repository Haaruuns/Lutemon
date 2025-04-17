package com.example.lutemongame;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonViewHolder extends RecyclerView.ViewHolder {
    ImageView lutemonImage;
    TextView lutemonName, lutemonDefence, lutemonAttack, lutemonExperience, lutemonLife;

    public LutemonViewHolder(@NonNull View itemView) {
        super(itemView);
        lutemonName = itemView.findViewById(R.id.LutemonName);
        lutemonAttack = itemView.findViewById(R.id.LutemonAttack);
        lutemonDefence = itemView.findViewById(R.id.LutemonDefence);
        lutemonLife = itemView.findViewById(R.id.LutemonLife);
        lutemonExperience = itemView.findViewById(R.id.LutemonExperience);
        lutemonImage = itemView.findViewById(R.id.LutemonImage);
    }
}
