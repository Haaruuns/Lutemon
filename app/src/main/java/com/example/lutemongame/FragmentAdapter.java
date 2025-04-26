package com.example.lutemongame;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentAdapter extends RecyclerView.Adapter<LutemonViewHolder>{
    private Context context;
    private ArrayList<Lutemon> lutemons;
    private ArrayList<Lutemon> selectedLutemons = new ArrayList<>();

    public FragmentAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.context = context;
        this.lutemons = lutemons;
    }

    public ArrayList<Lutemon> getSelected() {
        return selectedLutemons;
    }
    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view,parent,false));
    }



    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);
        holder.lutemonName.setText(lutemon.getName()  + " (" + lutemon.getColor() + ")");
        holder.lutemonAttack.setText("Attack: "+ lutemon.getAttack());
        holder.lutemonDefence.setText("Defense: " + lutemon.getDefense());
        holder.lutemonLife.setText("Life: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth());
        holder.lutemonExperience.setText("Experience: " + lutemon.getExperience());
        holder.lutemonImage.setImageResource(lutemon.getImage());

        if (selectedLutemons.contains(lutemon)) {
            holder.itemView.setBackgroundColor(Color.parseColor("#3F4247"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#2B2D30"));
        }
        holder.itemView.setOnClickListener(v -> {
            if (selectedLutemons.contains(lutemon)) {
                selectedLutemons.remove(lutemon);
            } else {
                selectedLutemons.add(lutemon);
            }
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
    public void setLutemons(ArrayList<Lutemon> lutemons) {
        this.lutemons = lutemons;
    }
    public void clearList() {
        selectedLutemons.clear();
        notifyDataSetChanged();
    }
}

