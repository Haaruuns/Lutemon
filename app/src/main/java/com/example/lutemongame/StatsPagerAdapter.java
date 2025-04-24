package com.example.lutemongame;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lutemongame.fragments.StatsAttributeFragment;
import com.example.lutemongame.fragments.StatsColorDistributionFragment;

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
            default:
                return new StatsAttributeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
