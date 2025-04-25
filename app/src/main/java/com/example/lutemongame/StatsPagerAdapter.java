package com.example.lutemongame;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lutemongame.fragments.StatsAttributeFragment;
import com.example.lutemongame.fragments.StatsColorDistributionFragment;
import com.example.lutemongame.fragments.WinsAndLossesFragment;

public class StatsPagerAdapter extends FragmentStateAdapter {

    public StatsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new StatsAttributeFragment();
            case 1:
                return new StatsColorDistributionFragment();
            case 2:
                return new WinsAndLossesFragment();
            default:
                return new StatsAttributeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
