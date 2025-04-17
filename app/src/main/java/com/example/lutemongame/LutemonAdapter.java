package com.example.lutemongame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LutemonAdapter extends RecyclerView.Adapter<LutemonViewHolder> {
    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    public LutemonAdapter(Context contect,ArrayList<Lutemon> lutemons) {
        this.context = contect;
        this.lutemons = lutemons;
    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);
        holder.lutemonName.setText(lutemon.getName()  + "(" + lutemon.getColor() + ")");
        holder.lutemonAttack.setText("Hyökkäys: "+ lutemon.getAttack());
        holder.lutemonDefence.setText("Puolustus: " + lutemon.getDefense());
        holder.lutemonLife.setText("Elämä: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth());
        holder.lutemonExperience.setText("Kokemus: " + lutemon.getExperience());
        holder.lutemonImage.setImageResource(lutemon.getImage());
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
